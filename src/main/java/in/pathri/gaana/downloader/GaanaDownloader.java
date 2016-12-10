package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.ExportType;
import in.pathri.gaana.constants.Global;
import in.pathri.gaana.constants.Language;
import in.pathri.gaana.constants.SearchType;
import in.pathri.gaana.constants.UsageOptions;
import in.pathri.gaana.utilities.MiscUtilities;
import in.pathri.gaana.utilities.UserPrompts;

public class GaanaDownloader {
	static final Logger logger = LogManager.getLogger();
	public native String[] stringFromMethod();

	public static void main(String[] args) {
		logger.traceEntry("Parameters::{}", args);
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
		// TODO: Implement following
		/*
		 * getGaanaConst //Load .so File getUserData //prompt if user.dat
		 * empty.serialised cred storage. User data DAO. Includes 'should split
		 * into Album Folders' gaanaLogin if(doNewSearch) //New search or
		 * download based on previous selection gaanaSearch //Store in Search
		 * DAO generateSearchResults //Excel in Enum if(shouldProceed) // prompt
		 * whether selection will be made now (wait for input) or later (exit
		 * program now) waitForDownloadSelection //prompt for key press on excel
		 * update triggerDownload //exit if no selection convertDownloaded //
		 * generateLog //Generate log exit //close excel handles if any
		 */
		UserPrompts.greetAndInfo();
		if (UserLogin.doLogin()) {
			logger.info("Login Successfull");
			UsageOptions usageOption = UserPrompts.doNewSearch();
			
			switch (usageOption) {
			case NEW_SEARCH:
				logger.info("Doing a new search");
				Language language = UserPrompts.getSearchLanguage();
				SearchType searchType = UserPrompts.getSearchType();
				GaanaSearch.doSearch(searchType, language);
				SearchExporter.exportSearchResults(ExportType.CSV, Global.SEARCH_RESULTS_FILE_NAME);
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
				DownloadLinkHelper.generateDownloadLinks();
				DownloadLinkHelper.exportDownloadLinks();
				logger.traceExit();
				boolean externalDownload = UserPrompts.linksGenerated();
				if(externalDownload){
					logger.traceExit();
					UserPrompts.doExit();					
				}
				
			case DOWNLOAD_FROM_GENERATED_LINKS:
				logger.info("Starting Download for the Generated Links");				
				break;
				
			case COVERT_DOWNLOADED_SONGS:
				
				break;
				
			default:
				logger.traceExit("Unhandled UsageOption::{}" + usageOption.toString());
			}
		}
		logger.traceExit();
	}
}
