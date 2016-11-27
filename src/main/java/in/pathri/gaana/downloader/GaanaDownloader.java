package in.pathri.gaana.downloader;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.utilities.UserLogin;

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
		}
		
		logger.traceExit();
	}

}
