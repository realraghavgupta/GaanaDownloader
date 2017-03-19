package in.pathri.gaana.downloader;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
//import in.pathri.gaana.dao.SearchResultsDAO;
import in.pathri.gaana.enums.Language;
import in.pathri.gaana.enums.SearchType;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.MiscUtilities;
import in.pathri.gaana.utilities.ProgressLogger;
import in.pathri.gaana.utilities.SearchParamHelper;
import in.pathri.gaana.utilities.UserPrompts;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class GaanaSearch {
	static final Logger logger = LogManager.getLogger();
	private static SearchParamHelper paramObj = new SearchParamHelper();

	public static void doSearch(SearchType searchType, Language language) {
		logger.entry(searchType, language);
		int totalResult = 0;
		int start = 4429; //tamil
//		int start = 0;
		int count = 99;
		int searchCount = 0;
		JSONObject userData;
		JSONArray tempSearchResults;

//		SearchResultsDAO.resetResults();

		Map<String, String> params = getSearchParams(searchType, language);
		if (params.isEmpty()) {
			logger.debug("getSearchParams resulted empty params");
			return;
		}

		params = updateLimit(start, count);
		logger.debug("updateLimit resulted::{}", params);
		logger.info("Getting total result count...");
		userData = sendSearchReq(params);
		if (null == userData) {
			logger.debug("sendSearchReq resulted empty userData");
			return;
		}

		totalResult = MiscUtilities.parseInt(userData.getAsString("count"));		
		logger.debug("totalResult of Initial Request::{}", totalResult);
		logger.info("Total Result Count::{}",totalResult);
//		SearchResultsDAO.updateSize(totalResult);

		tempSearchResults = (JSONArray) userData.get("album");
		if(null == tempSearchResults){
			logger.debug("sendSearchReq resulted empty userData album");
			UserPrompts.noResultsFound();
			return;
		}
//		logger.debug("tempSearchResult::{}", tempSearchResults);
		logger.debug("tempSearchResultSize::{}", tempSearchResults.size());
//		SearchResultsDAO.appendResult(tempSearchResults);
		SearchExporter.appendResults(tempSearchResults);
//		logger.debug("searchResultsArray Size:{}", SearchResultsDAO.getSize());
		searchCount = tempSearchResults.size();
		start = start + searchCount;
		logger.debug("Start count:{}", start);
		logger.info("Getting Search Results...");
		ProgressLogger progressLogger = new ProgressLogger("Loading Results...");
		progressLogger.setTotalCount(totalResult);		
		progressLogger.updateProgress(searchCount).displayProgress();
		while (start < totalResult) {
			params = updateLimit(start, count);
			userData = sendSearchReq(params);
			if (null == userData) {
				return;
			}
			tempSearchResults = (JSONArray) userData.get("album");
//			SearchResultsDAO.appendResult(tempSearchResults);
			SearchExporter.appendResults(tempSearchResults);
			searchCount = tempSearchResults.size();
			start = start + searchCount;
			progressLogger.updateProgress(searchCount).displayProgress();
		}
//		logger.debug("Search Result::{}", SearchResultsDAO.getAsString());
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
