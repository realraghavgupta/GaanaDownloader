package in.pathri.gaana.dao;

import java.util.HashMap;
import java.util.Map;

public class DownloadLinksDAO {
	private static Map<String, String> downloadLinks;

	public static void resetData() {
		downloadLinks = new HashMap<String, String>();
	}

	public static Map<String, String> getDownloadLinks() {
		if (null == downloadLinks) {
			resetData();
		}
		return downloadLinks;
	}

	public static void addLink(String file_id, String downloadURL) {
		if (null == downloadLinks) {
			resetData();
		}
		downloadLinks.put(file_id, downloadURL);
	}
}
