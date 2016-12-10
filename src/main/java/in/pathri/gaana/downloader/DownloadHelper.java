package in.pathri.gaana.downloader;

import java.util.List;

import in.pathri.gaana.dao.DownloadLinksDAO;
import in.pathri.gaana.utilities.UserPrompts;

public class DownloadHelper {
public static void doDownload() {
	List<String> downloadLinks = DownloadLinksDAO.getDownloadLinks();
if(downloadLinks.isEmpty()){
	DownloadLinkHelper.importDownloadLinks();
}
if(downloadLinks.isEmpty()){
	UserPrompts.fatalError();
}
triggerBulkDownload(downloadLinks);
}

private static void triggerBulkDownload(List<String> downloadLinks) {
	// TODO Auto-generated method stub
	
}
}
