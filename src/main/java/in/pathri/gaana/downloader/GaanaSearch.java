package in.pathri.gaana.downloader;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.constants.Language;
import in.pathri.gaana.constants.SearchType;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.SearchParamHelper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class GaanaSearch {
	static final Logger logger = LogManager.getLogger();
	private static SearchParamHelper paramObj = new SearchParamHelper();	
	private static JSONArray searchResultsArray = new JSONArray();
	
	public static void doSearch(SearchType searchType, Language language) {
		int totalResult = 0;
		int pendingItems = 0;
		int start = 0;
		int count = 99;
		Map<String, String> params = getSearchParams(searchType, language);
		if (params.isEmpty()) {
			return;
		}
		do{
			params = updateLimit(start, count);
			try {
				String reponse = HTTPHelper.sendGet(Global.GAANA_BASE_URL, params);
				Object jsonVal = JSONValue.parse(reponse);			
				JSONObject userData = (JSONObject)jsonVal;
				//TODO: Update local data store with data; searchResultsArray
				//TODO: Get total count and update totalResult
				//TODO: loop for total count by using limit update
				//TODO: Update pendingItems & start
			} catch (Exception e) {
				logger.catching(e);
			}			
		}while(pendingItems > 0);

	}

	private static Map<String, String> getSearchParams(SearchType searchType, Language language) {		
		switch (searchType) {
		case ALL_ALBUMS:
			paramObj.initParams("album", "all_albums");
			paramObj.setLanguage(language);
			break;
			
		default:
			break;
		}		
		return paramObj.getParams();
	}
	
	private static Map<String, String> updateLimit(int start, int count){
		paramObj.updateLimit(start, count);
		return paramObj.getParams();
	}

}
