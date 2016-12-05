package in.pathri.gaana.downloader;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.ExportType;
import in.pathri.gaana.constants.Language;
import in.pathri.gaana.constants.SearchType;
import in.pathri.gaana.utilities.UserPromts;

public class GaanaDownloader {
	static final Logger logger = LogManager.getLogger();
    public native String[] stringFromMethod();

	public static void main(String[] args) {
		logger.traceEntry("Parameters::{}",args);		
		try {
			bulkDownload();
			System.out.println("Please press Enter key to exit");
			System.in.read();
		} catch (IOException e) {
			logger.catching(e);
		}
		logger.traceExit();
	}
	
	public static void bulkDownload(){
		logger.traceEntry();
		//TODO: Implement following
/*		getGaanaConst //Load .so File
		getUserData //prompt if user.dat empty.serialised cred storage. User data DAO. Includes 'should split into Album Folders'
		gaanaLogin
		if(doNewSearch)	//New search or download based on previous selection		
			gaanaSearch //Store in Search DAO
			generateSearchResults //Excel in Enum
			if(shouldProceed) // prompt whether selection will be made now (wait for input) or later (exit program now)
				waitForDownloadSelection //prompt for key press on excel update
		triggerDownload //exit if no selection
		convertDownloaded //
		generateLog	//Generate log
		exit //close excel handles if any				
*/		

		
		if(UserLogin.doLogin()){
			logger.info("Login Successfull");
			boolean doNewSearch = UserPromts.doNewSearch();
			if(doNewSearch){
				logger.info("Doing a new search");
				Language language = UserPromts.getSearchLanguage();
				SearchType searchType = UserPromts.getSearchType();
				GaanaSearch.doSearch(searchType,language);
				SearchExporter.exportSearchResults(ExportType.CSV, "SearchResult.csv");
			}else{
				logger.info("Downloading");
			}			
			
		}
		
		logger.traceExit();
	}

}
