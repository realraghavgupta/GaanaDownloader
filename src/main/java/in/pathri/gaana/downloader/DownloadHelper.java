package in.pathri.gaana.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.DownloadLinksDAO;
import in.pathri.gaana.utilities.DownloadTask;
import in.pathri.gaana.utilities.UserPrompts;

public class DownloadHelper {
	static final Logger logger = LogManager.getLogger();
	static List<String> failureList = new ArrayList<String>();

	public static void doDownload() {
		Map<String, String> downloadLinks = DownloadLinksDAO.getDownloadLinks();
		if (downloadLinks.isEmpty()) {
			logger.info("Importing Download Links");
			DownloadLinkGenerator.importDownloadLinks();
			downloadLinks = DownloadLinksDAO.getDownloadLinks();
		}
		if (downloadLinks.isEmpty()) {
			UserPrompts.noDownloadLinks();
			UserPrompts.doExit();
		}
		logger.debug("Download Links Size::{}",downloadLinks.size());
		triggerBulkDownload(downloadLinks);
	}

	private static void triggerBulkDownload(Map<String, String> downloadLinks) {
		logger.traceEntry("DownloadLinks Size:{}",downloadLinks.size());
		resetDownloadFailureList();
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (Entry<String, String> downloadElement : downloadLinks.entrySet()) {
			String track_id = downloadElement.getKey();
			String downloadURL = downloadElement.getValue();
			pool.submit(new DownloadTask(downloadURL, getDownloadPath(track_id),track_id));
		}
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			if(failureList.isEmpty()){
				logger.info("All Songs Downloaded");	
			}else{
				UserPrompts.promtDownloadFailure(failureList);
			}
			
		} catch (InterruptedException e) {
			logger.catching(e);
		}
	}

	private static void resetDownloadFailureList() {
		failureList.clear();		
	}
	
	

	private static String getDownloadPath(String track_id) {
		return Global.DOWNLOAD_FOLDER_NAME + "\\" + track_id;
	}

	public static void addFailure(String track_id) {
		failureList.add(track_id);
	}
}
