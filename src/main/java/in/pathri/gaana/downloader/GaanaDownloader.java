package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GaanaDownloader {
	static final Logger logger = LogManager.getLogger();
    public native String[] stringFromMethod();

    static {
        try {
            System.loadLibrary("ViewAnim");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		logger.traceEntry("Parameters::{}",args);
		GaanaDownloader thisObj = new GaanaDownloader();
		thisObj.testNative();
		logger.traceExit();
	}
	
	public void testNative(){
		logger.traceEntry();
		String[] stringFromMethod = stringFromMethod();
		logger.traceExit(stringFromMethod);
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
		
		
		
		logger.traceExit();
	}

}
