package in.pathri.gaana.utilities;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.downloader.DownloadHelper;

public class DownloadTask implements Runnable {
	private final String filePath;
	private final String downloadURL;
	private final String track_id;
	private static final int BUFFER_SIZE = 4096;

	static final Logger logger = LogManager.getLogger();

	public DownloadTask(String downloadURL, String filePath, String track_id) {
		logger.entry(downloadURL, filePath);
		this.filePath = filePath;
		this.downloadURL = downloadURL;
		this.track_id = track_id;
	}

	@Override
	public void run() {
		logger.traceEntry("Download Task Triggered");
		try {
			if (!downloadFile(downloadURL, filePath)) {
				DownloadHelper.addFailure(this.track_id);
			}
		} catch (Exception e) {
			logger.throwing(e);
		}
	}

	private boolean downloadFile(String downloadURL, String filePath) throws Exception {
		boolean success = false;
		MiscUtilities.createParentFolders(filePath);
		logger.entry(downloadURL, filePath);
		URL url = new URL(downloadURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		int responseCode = httpConn.getResponseCode();

		// always check HTTP response code first
		if (responseCode == HttpURLConnection.HTTP_OK) {
			// opens input stream from the HTTP connection
			InputStream inputStream = httpConn.getInputStream();

			// opens an output stream to save into file
			FileOutputStream outputStream = new FileOutputStream(filePath);

			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			outputStream.close();
			inputStream.close();

			logger.info("File Downloaded::" + filePath);
			success = true;
			DownloadHelper.progressLogger.updateProgress(1).displayProgress();
		} else {
			logger.info("No file to download. Server replied HTTP code::{}", responseCode);
		}
		httpConn.disconnect();
		return success;
	}
}
