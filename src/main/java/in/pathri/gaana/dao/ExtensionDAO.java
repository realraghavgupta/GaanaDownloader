package in.pathri.gaana.dao;
 
 import java.util.List;
import java.util.stream.Collectors;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;
 
 public class ExtensionDAO {
	 private static ExtensionDAO extensionDAO;
 private List<String> songIds;
 private List<String> albumIds;
 private boolean hasError = false;
 private JSONObject jsonData;
 
 public static ExtensionDAO getNewInstance() {
	if(extensionDAO == null){
		extensionDAO = new ExtensionDAO();
		return extensionDAO;
	}
	return extensionDAO.reset();
}
 
 public static ExtensionDAO getInstance() {
	return extensionDAO;
}
 
 private ExtensionDAO reset() {
	this.songIds = null;
	this.albumIds = null;
	this.jsonData = null;
	this.hasError = false;
	return this;
}

public List<String> getAlbumIds(){
 	return this.albumIds;
 }

public String getAlbumIdString() {
	return String.join(",", this.albumIds);
}

public String getSongIdString(){
 	return String.join(",", this.songIds);
 }


 public List<String> getSongIds(){
 	return this.songIds;
 }
 
 public void setData(String extensionData) throws ParseException {
 	this.jsonData = (JSONObject) JSONValue.parseWithException(extensionData);
 	if(jsonData.containsKey("song_ids") && jsonData.containsKey("album_ids")){
 		JSONArray album_ids = (JSONArray)jsonData.get("album_ids");
 		JSONArray song_ids = (JSONArray)jsonData.get("song_ids");
 		this.albumIds = album_ids.stream().map(id -> id.toString()).collect(Collectors.toList());
 		this.songIds = song_ids.stream().map(id -> id.toString()).collect(Collectors.toList());
 		hasError = false;
 	}else{
 		hasError = true;
 	}
 }
 
 public boolean hasError() {
	return this.hasError;
}
 
@Override
public String toString() {
	return jsonData.toJSONString();
} 
 }