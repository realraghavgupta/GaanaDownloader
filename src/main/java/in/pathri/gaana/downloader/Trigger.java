package in.pathri.gaana.downloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.utilities.ExtensionHelper;
import in.pathri.gaana.utilities.NativeMessagingHelper;
import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;
import it.sauronsoftware.junique.MessageHandler;


public class Trigger {
	static final Logger logger = LogManager.getLogger();
	public static void main(String[] args) {
		logger.traceEntry("Parameters::{}", String.join(";", args));
		boolean fromExtension = args.length > 0? Boolean.valueOf(args[0]):false;
		appInit(fromExtension);	
	}
	
	private static void appInit(boolean fromExtension){
		boolean isRunning = checkIfRunning();
		if (!isRunning) {
			if(fromExtension){
				NativeMessagingHelper.sendMessage(Global.DOWNLOADER_NOT_RUNNING, true);
			} else {
				GaanaDownloader.init();
			}
		}else{
			String msg = NativeMessagingHelper.readMessage(System.in);
			String retVal = "true";
			if(!msg.isEmpty()){
				retVal = JUnique.sendMessage(Global.APP_ID,msg);	
			}			
//			System.out.println("Send message returned" + retVal + Thread.currentThread().getId());	
			if(retVal.equalsIgnoreCase("true")){
				NativeMessagingHelper.sendMessage(Global.INVALID_REQUEST, true);	
			}else{
				NativeMessagingHelper.sendMessage(Global.DOWNLOAD_TRIGGERED, false);
			}
			
		}
	}
	
	private static boolean checkIfRunning(){
		try {
			JUnique.acquireLock(Global.APP_ID, new MessageHandler(){
				@Override
				public String handle(String msg) {
					String retVal = ExtensionHelper.recieveMessage(msg);
					return retVal;
				}				
			});
			return false;
		}catch (AlreadyLockedException e) {
//			System.out.println("catching exception" + Thread.currentThread().getId());
			return true;
		}		
	}

}
