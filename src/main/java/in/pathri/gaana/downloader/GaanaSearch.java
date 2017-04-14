package in.pathri.gaana.downloader;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.enums.SearchType;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.MiscUtilities;
import in.pathri.gaana.utilities.ProgressLogger;
import in.pathri.gaana.utilities.UserPrompts;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class GaanaSearch {
	static final Logger logger = LogManager.getLogger();

	public static void doSearch(SearchType searchType) {
		logger.entry(searchType);
		JSONObject userData;
		JSONArray tempAlbumResults;
		JSONArray tempTrackResults;
		int totalResult = 0;
		int searchCount = 0;
		boolean isFirstRun = true;
		boolean hasAlbumResults, hasTrackResults;
		boolean firstSearch = true;
		ProgressLogger progressLogger = new ProgressLogger("Loading Results...");
		while (true) {
			hasAlbumResults = hasTrackResults = true;
			Map<String, String> params = searchType.getNextParams(searchCount);
			if (isFirstRun || searchType.hasQuerySwitched()) {
				isFirstRun = true;
			}

			if (isFirstRun)
				progressLogger.resetProgress();

			if (params.isEmpty()) {
				logger.debug("getSearchParams resulted empty params");
				break;
			}

			if (isFirstRun)
				logger.info("Getting total result count...");
			userData = sendSearchReq(params);
			if (null == userData) {
				logger.debug("sendSearchReq resulted empty userData");
				break;
			}

			tempAlbumResults = (JSONArray) userData.get("album");
			if (null == tempAlbumResults) {
				logger.debug("sendSearchReq resulted empty userData album");
				hasAlbumResults = false;
			}

			tempTrackResults = (JSONArray) userData.get("tracks");
			if (null == tempTrackResults) {
				logger.debug("sendSearchReq resulted empty userData tracks");
				hasTrackResults = false;
			}

			if (!hasAlbumResults && !hasTrackResults) {
				if (firstSearch)
					UserPrompts.noResultsFound();
				break;
			}

			totalResult = MiscUtilities.parseInt(userData.getAsString("count"));
			if (isFirstRun) {
				logger.debug("totalResult of Initial Request::{}", totalResult);
				logger.info("Total Result Count::{}", totalResult);
			}
			searchType.setTotalCount(totalResult);

			if (hasAlbumResults) {
				SearchExporter.appendAlbumResults(tempAlbumResults);
				searchCount = tempAlbumResults.size();
			}

			if (hasTrackResults) {
				SearchExporter.appendTrackResults(tempTrackResults);
				searchCount = tempTrackResults.size();
			}
			logger.debug("tempSearchResultSize::{}", searchCount);
			if (isFirstRun) {
				logger.info("Getting Search Results...");
				progressLogger.setTotalCount(totalResult);
			}
			progressLogger.updateProgress(searchCount).displayProgress();
			isFirstRun = false;
			firstSearch = false;
		}
		logger.traceExit();
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
