package in.pathri.gaana.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.DownloadParam;
import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.DownloadLinksDAO;
import in.pathri.gaana.utilities.CSVExporterImport;
import in.pathri.gaana.utilities.DownloadParamHelper;
import in.pathri.gaana.utilities.GaanaUtilities;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.ProgressLogger;
import in.pathri.gaana.utilities.UserPrompts;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class DownloadLinkGenerator {
	static final Logger logger = LogManager.getLogger();
	private static List<String[]> importedResults;
	private static List<String[]> downloadLinks = new ArrayList<String[]>();
	private static final String getDownloadLinkURL = Global.GAANA_BASE_URL + Global.GET_DOWNLOAD_URL_ENDPOINT;

	public static void doGenerate() {
		if (null == importedResults) {
			importResults();
		}
		DownloadLinksDAO.resetData();
		int recordCount = importedResults.size();
		int currentRecord = 0;
		ProgressLogger progressLogger = new ProgressLogger("Fetching Track Download links...");
		for (String[] record : importedResults) {
			currentRecord++;
			String album_id = record[0].replace("'", "");
			String[] trackIds = record[1].replace("'", "").split(",");
			String name = record[2];
			String[] downloadLinkRecord = null;
			logger.info("Fetching Download links for Album {} of {}", currentRecord, recordCount);
			int trackCount = trackIds.length;
			progressLogger.setTotalCount(trackCount);
			for (String track_id : trackIds) {
				downloadLinkRecord = new String[4];
				progressLogger.updateProgress(1).displayProgress();
				String downloadURL = getTrackDownloadLinks(track_id);
				if (null != downloadURL) {
					downloadLinkRecord[0] = album_id;
					downloadLinkRecord[1] = name;
					downloadLinkRecord[2] = track_id;
					downloadLinkRecord[3] = downloadURL;
				}
				if (null != downloadLinkRecord) {
					downloadLinks.add(downloadLinkRecord);
					DownloadLinksDAO.addLink(track_id, downloadURL);
				}
			}
		}
	}

	private static String getTrackDownloadLinks(String track_id) {
		DownloadParamHelper downloadParamHelper = new DownloadParamHelper();
		downloadParamHelper.initParams(DownloadParam.ConnectionType.WIFI, DownloadParam.getQuality(), DownloadParam.DeliveryType.DOWNLOAD);
		downloadParamHelper.setTrackId(track_id);
		Map<String, String> params = downloadParamHelper.getParams();
		try {
			String reponse = HTTPHelper.sendGet(getDownloadLinkURL, params);
			JSONObject downloadData = (JSONObject) JSONValue.parse(reponse);
			int status = downloadData.getAsNumber("status").intValue();
			if (1 == status) {
				String data = downloadData.getAsString("data");
				if (!data.isEmpty()) {
					String downloadURL = GaanaUtilities.decodeDownloadURL(data);
					return downloadURL;
				}
			} else {
				UserPrompts.notPlusUser();
			}
			return "";
		} catch (Exception e) {
			logger.catching(e);
		}
		return null;
	}

	public static void exportDownloadLinks() {
		if (null == importedResults) {
			importResults();
		}
		if (null == downloadLinks) {
			doGenerate();
		}
		if (downloadLinks.isEmpty()) {
			return;
		}
		CSVExporterImport exporter = new CSVExporterImport();
		exporter.initExporter(Global.DOWNLOAD_LINK_FILE_NAME);
		exporter.addColumnHeader(Global.DOWNLOAD_LINK_EXPORT_HEADERS);
		exporter.addAllValues(downloadLinks);
		exporter.doExport();
	}

	public static boolean checkIfResultsUpdated() {
		if (null == importedResults) {
			importResults();
		}
		if (importedResults.isEmpty()) {
			return false;
		}
		return true;
	}

	private static void importResults() {
		importedResults = new ArrayList<String[]>();
		String[] tempRecord = new String[4];
		CSVExporterImport importer = new CSVExporterImport();
		importer.initImporter(Global.SEARCH_RESULTS_FILE_NAME);
		importer.getColumnHeader();
		do {
			tempRecord = importer.getNextRecord();
			if (null != tempRecord && !tempRecord[3].isEmpty()) {
				importedResults.add(tempRecord);
			}
		} while (null != tempRecord);
	}

	public static void importDownloadLinks() {
		logger.traceEntry();
		String[] tempRecord = new String[4];
		CSVExporterImport importer = new CSVExporterImport();
		importer.initImporter(Global.DOWNLOAD_LINK_FILE_NAME);
		importer.getColumnHeader();
		DownloadLinksDAO.resetData();
		do {
			tempRecord = importer.getNextRecord();
			if (null != tempRecord) {
				String track_id = tempRecord[2];
				String downloadURL = tempRecord[3];
				if (!track_id.isEmpty() && !downloadURL.isEmpty()) {
					DownloadLinksDAO.addLink(track_id, downloadURL);
				}
			}
		} while (null != tempRecord);
	}
}
