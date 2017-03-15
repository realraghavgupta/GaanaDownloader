package in.pathri.gaana.dao;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

public class ExtensionDAO {
private String[] songIds;
private String[] albumIds;

public String[] getAlbumIds(){
	return this.albumIds;
}

public String[] getSongIds(){
	return this.songIds;
}

public void setData(String extensionData) throws ParseException {
	JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(extensionData);
	if(jsonObj.getAsString("errorFlag").equalsIgnoreCase("true")){
		//TODO: throws exceptions
	}else{
		this.albumIds = (String[])jsonObj.get("albumIds");
		this.songIds = (String[])jsonObj.get("songIds");
	}
}

}
