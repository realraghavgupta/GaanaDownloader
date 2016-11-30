package in.pathri.gaana.dao;

import net.minidev.json.JSONArray;

public class SearchResults {
	private static JSONArray searchResultsArray;
		
	public static void resetResults(){
		searchResultsArray = new JSONArray();
	}
	
	public static void appendResult(JSONArray tempResults){
		searchResultsArray.
		addAll(tempResults);
	}
	
	public static void updateSize(int size){
		searchResultsArray.ensureCapacity(size + 10);
	}
	
	public static int getSize(){
		return searchResultsArray.size();
	}
	
	public static String getAsString(){
		return searchResultsArray.toJSONString();
	}
	
	public static JSONArray getResultArray(){
		return searchResultsArray;
	}
}
