package in.pathri.gaana.utilities;

import java.util.HashMap;
import java.util.Map;

import in.pathri.gaana.constants.Language;

public class SearchParamHelper {
	private String type = "";
	private String subType = "";
	private String language = "";

	private Map<String, String> params = new HashMap<String, String>();

	public void initParams(String type, String subType) {
		this.type = type;
		this.subType = subType;
	}

	public void setLanguage(Language language) {
		this.language = language.getValue();
	}

	public void updateLimit(int start, int count) {
		params.put("limit", String.valueOf(start) + "," + String.valueOf(count));
	}

	public Map<String, String> getParams() {
		if (type == "" || subType == "") {
			return params;
		}
		if (params.isEmpty()) {
			generateParams();
		}
		return params;
	}

	private void generateParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("subtype", subType);
		params.put("language", language);
	}

}
