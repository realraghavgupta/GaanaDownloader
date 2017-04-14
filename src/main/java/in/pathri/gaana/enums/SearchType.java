package in.pathri.gaana.enums;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum SearchType {
	ALL_ALBUMS("All Albums", 1, QueryType.ALL_ALBUMS), 
	ALBUM_TRACK_IDS("Album and Track IDs", 2, QueryType.ALBUM_LIST, QueryType.TRACK_LIST);
	
	private String value;
	private int option;
	private QueryType[] queryTypes;
	private int currentQueryIndex;
	private boolean isTotalCountSet = false;
	private boolean hasSwitched = false;
	static final Logger logger = LogManager.getLogger();

	SearchType(final String value, final int option, final QueryType... queryTypes) {
		this.value = value;
		this.option = option;
		this.queryTypes = queryTypes;
		this.currentQueryIndex = 0;
	}

	public String getValue() {
		return value;
	}

	public int getOption() {
		return option;
	}

	public static SearchType getEnum(int option) {
		for (SearchType v : values())
			if (v.getOption() == option)
				return v;
		return null;
	}

	@Override
	public String toString() {
		return this.getValue();
	}

	public Map<String, String> getNextParams(int prevResultCount) {
		logger.entry(prevResultCount);
		Map<String, String> params = queryTypes[currentQueryIndex].getNextParams(prevResultCount);
		if (params.isEmpty()) {
			if (currentQueryIndex + 2 <= queryTypes.length) {
				currentQueryIndex = currentQueryIndex + 1;
				isTotalCountSet = false;
				hasSwitched = true;
				logger.debug("Switching to Query::{}", queryTypes[currentQueryIndex].name());
				return queryTypes[currentQueryIndex].getNextParams(0);
			}
		}
		hasSwitched = false;
		return params;
	}

	public boolean hasQuerySwitched() {
		return hasSwitched;
	}

	public void setTotalCount(int totalResult) {
		if (!isTotalCountSet) {
			queryTypes[currentQueryIndex].setTotalCount(totalResult);
			isTotalCountSet = true;
		}
	}
}
