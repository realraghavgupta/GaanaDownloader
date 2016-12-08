package in.pathri.gaana.downloader;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.constants.Language;
import in.pathri.gaana.constants.SearchType;
import in.pathri.gaana.dao.SearchResults;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.SearchParamHelper;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class GaanaSearch {
	static final Logger logger = LogManager.getLogger();
	private static SearchParamHelper paramObj = new SearchParamHelper();

	public static void doSearch(SearchType searchType, Language language) {
		logger.entry(searchType, language);
		int totalResult = 0;
		int start = 5990;
//		int start = 0;
		int count = 99;
		int searchCount = 0;
		JSONObject userData;
		JSONArray tempSearchResults;

		SearchResults.resetResults();

		Map<String, String> params = getSearchParams(searchType, language);
		if (params.isEmpty()) {
			logger.debug("getSearchParams resulted empty params");
			return;
		}

		params = updateLimit(start, count);
		logger.debug("updateLimit resulted::{}", params);
		userData = sendSearchReq(params);
		if (null == userData) {
			logger.debug("sendSearchReq resulted empty userData");
			return;
		}

		totalResult = userData.getAsNumber("count").intValue();
		logger.debug("totalResult of Initial Request::{}", totalResult);
		SearchResults.updateSize(totalResult);

		tempSearchResults = (JSONArray) userData.get("album");
		logger.debug("tempSearchResult::{}", tempSearchResults);
		logger.debug("tempSearchResultSize::{}", tempSearchResults.size());
		SearchResults.appendResult(tempSearchResults);
		logger.debug("searchResultsArray Size:{}", SearchResults.getSize());
		searchCount = tempSearchResults.size();
		start = start + searchCount;
		logger.debug("Start count:{}", start);
		while (start < totalResult) {
			params = updateLimit(start, count);
			userData = sendSearchReq(params);
			if (null == userData) {
				return;
			}
			tempSearchResults = (JSONArray) userData.get("album");
			SearchResults.appendResult(tempSearchResults);
			searchCount = tempSearchResults.size();
			start = start + searchCount;
		}
		logger.debug("Search Result::{}", SearchResults.getAsString());
		logger.traceExit();
	}

	private static Map<String, String> getSearchParams(SearchType searchType, Language language) {
		logger.entry(searchType, language);
		switch (searchType) {
		case ALL_ALBUMS:
			logger.trace("Into ALL_ALBUMS");
			paramObj.initParams("album", "all_albums");
			paramObj.setLanguage(language);
			break;

		default:
			break;
		}
		logger.traceExit("Params:{}", paramObj.getParams());
		return paramObj.getParams();
	}

	private static Map<String, String> updateLimit(int start, int count) {
		paramObj.updateLimit(start, count);
		return paramObj.getParams();
	}

	private static JSONObject sendSearchReq(Map<String, String> params) {
		try {
			String reponse = HTTPHelper.sendGet(Global.GAANA_BASE_URL, params);
			Object jsonVal = JSONValue.parse(reponse);
			JSONObject userData = (JSONObject) jsonVal;
			return userData;
		} catch (Exception e) {
			logger.catching(e);
		}
		return null;
	}
}
