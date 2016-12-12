package in.pathri.gaana.downloader;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.dao.DownloadLinksDAO;
import in.pathri.gaana.utilities.DownloadTask;
import in.pathri.gaana.utilities.UserPrompts;

public class DownloadHelper {
	static final Logger logger = LogManager.getLogger();

	public static void doDownload() {
		Map<String, String> downloadLinks = DownloadLinksDAO.getDownloadLinks();
		if (downloadLinks.isEmpty()) {
			DownloadLinkGenerator.importDownloadLinks();
		}
		if (downloadLinks.isEmpty()) {
			UserPrompts.noDownloadLinks();
			UserPrompts.doExit();
		}
		triggerBulkDownload(downloadLinks);
	}

	private static void triggerBulkDownload(Map<String, String> downloadLinks) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for (Entry<String, String> downloadElement : downloadLinks.entrySet()) {
			String track_id = downloadElement.getKey();
			String downloadURL = downloadElement.getValue();
			pool.submit(new DownloadTask(downloadURL, getDownloadPath(track_id)));
		}
		pool.shutdown();
		try {
			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			logger.info("All Songs Downloaded");
		} catch (InterruptedException e) {
			logger.catching(e);
		}
	}

	private static String getDownloadPath(String track_id) {
		return "Downloaded_Songs/" + track_id;
	}
}
