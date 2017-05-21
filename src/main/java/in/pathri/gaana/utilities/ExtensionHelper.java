package in.pathri.gaana.utilities;

import in.pathri.gaana.dao.ExtensionDAO;

public class ExtensionHelper {
	private static ExtensionDAO extensionDAO;
	public static void awaitExtensionMsg() {
		extensionDAO = ExtensionDAO.getNewInstance();
		try{
			synchronized (extensionDAO) {
				//TODO: Logger
				extensionDAO.wait();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static String recieveMessage(String msg) {
		//System.out.println("Recieved msg::" + msg + Thread.currentThread().getId());
		NativeMessagingHelper.logToFile("Recieved msg::" + msg + Thread.currentThread().getId());
		String hasError = "true";
		try {
			synchronized (extensionDAO) {
				extensionDAO.setData(msg);				
				NativeMessagingHelper.logToFile("JSON val msg::" + extensionDAO.toString() + Thread.currentThread().getId());
				if(!extensionDAO.hasError()){
					hasError = "false";
					extensionDAO.notify();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.waitForExit();
		return hasError;
	}	

}
