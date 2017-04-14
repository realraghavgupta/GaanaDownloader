package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.enums.ExportType;
import in.pathri.gaana.enums.SearchType;
import in.pathri.gaana.enums.UsageOptions;
import in.pathri.gaana.extractor.MainExtractor;
import in.pathri.gaana.utilities.MiscUtilities;
import in.pathri.gaana.utilities.UserPrompts;

public class GaanaDownloader {
	static final Logger logger = LogManager.getLogger();

	public native String[] stringFromMethod();

	public static void main(String[] args) {
		logger.traceEntry("Parameters::{}", String.join(";", args));
		try {
			bulkDownload();
			logger.traceExit();
			UserPrompts.doExit();
		} catch (Exception e) {
			logger.catching(e);
		}
		logger.traceExit();
	}

	public static void bulkDownload() {
		logger.traceEntry();
		UserPrompts.greetAndInfo();
		if (UserLogin.doLogin()) {
			logger.info("Login Successfull");
			UsageOptions usageOption = UserPrompts.doNewSearch();

			switch (usageOption) {
			case NEW_SEARCH:
				logger.info("Doing a new search");
				SearchType searchType = UserPrompts.getSearchType();
				SearchExporter.init(ExportType.CSV, Global.SEARCH_RESULTS_FILE_NAME);
				GaanaSearch.doSearch(searchType);
				SearchExporter.end();
				boolean waitForUserSelection = UserPrompts.waitForUserSelection();
				if (!waitForUserSelection) {
					logger.traceExit();
					UserPrompts.doExit();
				}
				MiscUtilities.openSearchResult();

			case GENERATE_DOWNLOAD_LINKS:
				boolean hasUpdatedResultsSheet = UserPrompts.hasUpdatedResultsSheet();
				if (!hasUpdatedResultsSheet) {
					logger.traceExit();
					UserPrompts.pleaseUpdateResultsSheet();
				}
				logger.info("Generating Download links for the Selected Items");
				UserPrompts.getDownloadQuality();
				DownloadLinkGenerator.doGenerate();
				DownloadLinkGenerator.exportDownloadLinks();
				logger.traceExit();
				boolean externalDownload = UserPrompts.linksGenerated();
				if (externalDownload) {
					logger.traceExit();
					UserPrompts.doExit();
				}

			case DOWNLOAD_FROM_GENERATED_LINKS:
				logger.info("Starting Download for the Generated Links");
				DownloadHelper.doDownload();
				logger.info("Downloads Complete");

			case COVERT_DOWNLOADED_SONGS:
				logger.info("Starting Conversion");
				String srcDir = Global.DOWNLOAD_FOLDER_NAME;
				MainExtractor.extract(srcDir, true);
				break;

			default:
				logger.traceExit("Unhandled UsageOption::{}" + usageOption.toString());
			}
		}
		logger.traceExit();
	}
}
