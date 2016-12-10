package in.pathri.gaana.dao;

import java.util.ArrayList;
import java.util.List;

public class DownloadLinksDAO {
private static ArrayList<String> downloadLinks;

public static void resetData(){
	downloadLinks = new ArrayList<String>();
}

public static void updateSize(int capacity){
	downloadLinks.ensureCapacity(capacity);
}


public static List<String> getDownloadLinks() {
	if(null == downloadLinks){
		resetData();
	}
	return downloadLinks;
}

public static void addLink(String downloadURL) {
	if(null == downloadLinks){
		resetData();
	}
	downloadLinks.add(downloadURL);
}
}
