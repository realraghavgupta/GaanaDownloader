package in.pathri.gaana.utilities;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadTask implements Runnable {
	private final String filePath;
	private final String downloadURL;
	private static final int BUFFER_SIZE = 4096;
	
	static final Logger logger = LogManager.getLogger();
	
	public DownloadTask(String downloadURL, String filePath) {
		this.filePath = filePath;
		this.downloadURL = downloadURL;
	}

	@Override
	public void run() {
		try {
			downloadFile(downloadURL, filePath);
		} catch (Exception e) {
		
		}
	}

	private void downloadFile(String downloadURL, String filePath) throws Exception{
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
 
            logger.info("File Downloaded::{}" + filePath);
        } else {
        	logger.info("No file to download. Server replied HTTP code::{}",responseCode);
        }
        httpConn.disconnect();
	}

}
