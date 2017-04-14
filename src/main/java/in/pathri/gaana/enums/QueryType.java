package in.pathri.gaana.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.utilities.UserPrompts;

public enum QueryType {
	ALL_ALBUMS("album", "all_albums", true, "language"), 
	TRACK_LIST("song", "song_detail", false, "track_id"), 
	ALBUM_LIST("album", "album_detail_info", false, "album_id");

	private String type;
	private String subType;
	private String[] otherParams;
	private boolean hasLimitParam;
	private Map<String, String> paramValues;
	private final int count = 99;
	private int totalCount = 0;
	private int completedCount = 0; //tamil - 6590
	private boolean totalCountSet = false;
	static final Logger logger = LogManager.getLogger();

	QueryType(String type, String subType, boolean hasLimitParam, String... otherParams) {
		this.type = type;
		this.subType = subType;
		this.otherParams = otherParams;
		this.hasLimitParam = hasLimitParam;
		this.paramValues = new HashMap<String, String>();
	}

	private String getParamsFromUser(String otherParam) {
		logger.entry(otherParam);
		switch (otherParam) {
		case "language":
			Language language = UserPrompts.getSearchLanguage();
			return language.getValue();
		case "track_id":
			return UserPrompts.getTrackIDs();
		case "album_id":
			return UserPrompts.getAlbumIDs();
		default:
			return "";
		}
	}

	private void generateParams() {
		logger.traceEntry();
		paramValues.put("type", type);
		paramValues.put("subtype", subType);
		for (String otherParam : otherParams) {
			String paramValue = getParamsFromUser(otherParam);
			if (!paramValue.isEmpty()) {
				paramValues.put(otherParam, paramValue);
			}
		}
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		this.totalCountSet = true;
	}

	public Map<String, String> getNextParams(int prevResultCount) {
		logger.entry(prevResultCount);
		if (paramValues.isEmpty()) {
			generateParams();
		}
		updateParams(prevResultCount);
		return paramValues;
	}

	private void updateParams(int prevResultCount) {
		logger.entry(prevResultCount);
		completedCount = completedCount + prevResultCount;
		if (hasLimitParam) {
			if (!totalCountSet || completedCount < totalCount) {
				paramValues.put("limit", String.valueOf(completedCount) + "," + String.valueOf(count));
			} else {
				logger.debug("clearing params::totalCountSet-{}|completedCount-{}|totalCount-{}", totalCountSet, completedCount, totalCount);
				paramValues.clear();
			}
		} else {
			if (completedCount == 0) {
				completedCount = prevResultCount;
			} else {
				logger.debug("clearing params as no limit param and completedcount is not 0::{}", completedCount);
				paramValues.clear();
			}
		}
	}
}
