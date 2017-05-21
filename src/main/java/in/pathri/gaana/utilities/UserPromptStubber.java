package in.pathri.gaana.utilities;

import in.pathri.gaana.dao.ExtensionDAO;
import in.pathri.gaana.enums.SearchType;
import in.pathri.gaana.enums.StubTypes;

public class UserPromptStubber {
	private static boolean isStubbed = false;
	private static boolean stubSearchSelection;
	private static String albumIDs, trackIDs;
	
	public static void setStubType(StubTypes stubType){
		isStubbed = true;
		switch (stubType) {
		case EXTENSION:
			stubSearchSelection = true;
			setAlbumIDs(ExtensionDAO.getInstance().getAlbumIdString());
			setTrackIDs(ExtensionDAO.getInstance().getSongIdString());
			break;

		default:
			break;
		}
	}
	
	public static boolean isStubbed(){
		return isStubbed;
	}
	
	public static void setAlbumIDs(String albumIDs){
		UserPromptStubber.albumIDs = albumIDs;
	}

	public static void setTrackIDs(String trackIDs){
		UserPromptStubber.trackIDs = trackIDs;
	}

	public static String getAlbumIDs() {
		return albumIDs;
	}

	public static SearchType getSearchType() {
		return SearchType.ALBUM_TRACK_IDS; 
	}

	public static String getTrackIDs() {
		return trackIDs;
	}

	public static boolean stubSearchSelection() {		
		return stubSearchSelection;
	}
}
