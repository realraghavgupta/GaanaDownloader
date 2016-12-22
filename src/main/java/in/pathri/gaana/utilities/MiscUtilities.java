package in.pathri.gaana.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;

public class MiscUtilities {
	static final Logger logger = LogManager.getLogger();

	public static String getGaanURL(String endPoint) {
		return Global.GAANA_BASE_URL + (endPoint.startsWith("/") ? endPoint : "/" + endPoint);
	}

	public static void openSearchResult() {
		File searchResult = new File(Global.SEARCH_RESULTS_FILE_NAME);
		if (searchResult.exists()) {
			try {
				Desktop.getDesktop().open(searchResult);
			} catch (IOException e) {
				logger.catching(e);
			}
		}
	}

	public static int parseInt(String value) {
		if (null == value || value.isEmpty()) {
			return 0;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
	}

	public static void createParentFolders(String filePath) {
		File parentPath = new File(filePath).getParentFile();
		if(!parentPath.exists()){
			parentPath.mkdirs();
		}		
	}
}
