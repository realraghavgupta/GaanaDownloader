package in.pathri.gaana.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Language;

public class SearchParamHelper {
	static final Logger logger = LogManager.getLogger();
	private String type = "";
	private String subType = "";
	private String language = "";

	private Map<String, String> params = new HashMap<String, String>();

	public void initParams(String type, String subType) {
		logger.entry(type,subType);
		this.type = type;
		this.subType = subType;
		logger.traceExit();
	}

	public void setLanguage(Language language) {
		logger.entry(language);
		this.language = language.getValue();
		logger.traceExit();
	}

	public void updateLimit(int start, int count) {
		logger.entry(start,count);
		params.put("limit", String.valueOf(start) + "," + String.valueOf(count));
		logger.traceExit();
	}

	public Map<String, String> getParams() {		
		if (type == "" || subType == "") {
			logger.traceExit("Returning Empty params");
			return params;
		}
		if (params.isEmpty()) {
			logger.debug("Generating params");
			generateParams();
		}
		logger.traceExit(params);
		return params;
	}

	private void generateParams() {
		logger.traceEntry();
		params.put("type", type);
		params.put("subtype", subType);
		params.put("language", language);
		logger.traceExit(params);
	}

}
