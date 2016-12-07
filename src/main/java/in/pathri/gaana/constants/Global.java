package in.pathri.gaana.constants;

public class Global {
	public static final String HASH_KEY = "1594fdf1a2d607590747d5cad924fcac";
	public static final String URL_DECODE_KEY = "c1c6b8b2c1b1a1c7";
	
	public static final String OPTION_PROMT = "Please select a option number from above:";
	
	public static final String GAANA_BASE_URL = "http://api.gaana.com";
	public static final String LOGIN_ENDPOINT = "/user.php";
			
	public static final String USERNAME_PROMPT = "Please Enter your User Name:";
	public static final String PASSWORD_PROMPT = "Please Enter your Password:";
	
	public static final String GENERIC_LOGIN_ERROR = "Unknown Login Error!";
	
	public static final String DO_NEW_SEARCH_PROMPT = "Do you want to do a new Search or downlaod using updated Excel?";
	public static final String DO_NEW_SEARCH_PROMPT_NEW_SEARCH = "Do a new search";
	public static final String DO_NEW_SEARCH_PROMPT_DOWNLOAD = "Download using updated Excel";
	
	public static final String LANGUAGE_PROMPT = "Please select the language:";
	public static final String SEARCHTYPE_PROMPT = "Please select a type of Search";

	public static final String[] EXPORT_COLUMNS = "Album_ID,Track_IDs,Name,Download_YesNo".split(",");
	public static final String SEARCH_RESULTS_FILE_NAME = "SearchResult.csv";
	
	
	
	public static final String EXIT_PROMPT = "Please press Enter key to exit";
	public static final String DO_WAIT_FOR_SELECTION = "Do you want the program to wait till you make your selection in Excel? (Yes or No). If No, re-running the program lets to pick up from here.";
	public static final String LINKS_GENERATED_PROMPT = "Download links have been generated in the Excel. Please use your desired download manager to download";
	public static final String HAS_UPDATED_RESULTS = "Please update Search Result sheet, if not done already. Do you wish to proceed? (Yes or No). If No, program will quit";
	public static final String PLEASE_UPDATE_RESULTS = "Please update the Results sheet with your Selection. Re-run program after your selections";
	public static final String DOWNLOAD_LINK_FILE_NAME = "DownloadLinks.csv";
	public static final String[] DOWNLOAD_LINK_EXPORT_HEADERS = "Album_ID,Name,Track_ID,Download_Link".split(",");
	public static final String GET_DOWNLOAD_URL_ENDPOINT = "/getURLV1.php";

		
}
