package in.pathri.gaana.constants;

public class Global {
	public static final String HASH_KEY = "1594fdf1a2d607590747d5cad924fcac";
	public static final String URL_DECODE_KEY = "c1c6b8b2c1b1a1c7";

	public static final String GAANA_BASE_URL = "http://api.gaana.com";
	public static final String LOGIN_ENDPOINT = "/user.php";
	public static final String GET_DOWNLOAD_URL_ENDPOINT = "/getURLV1.php";

	public static final String[] EXPORT_COLUMNS = "Album_ID,Track_IDs,Name,Download_YesNo".split(",");
	public static final String SEARCH_RESULTS_FILE_NAME = "SearchResult.csv";

	public static final String DOWNLOAD_LINK_FILE_NAME = "DownloadLinks.csv";
	public static final String[] DOWNLOAD_LINK_EXPORT_HEADERS = "Album_ID,Name,Track_ID,Download_Link".split(",");

	public static final String BREAK = "************************************************";
	public static final String GREET = "Hello! Welcome to Gaana Downloader!";
	public static final String USAGE_OPTION_NEW_SEARCH = "Do a New Search";
	public static final String USAGE_OPTION_NEW_SEARCH_HINT = "(Produces CSV file with list of Tracks/Albums)";
	public static final String USAGE_OPTION_SELECT_TO_DOWNLOAD = "Select the Albums/Tracks from the CSV";
	public static final String USAGE_OPTION_GENERATE_DOWNLOAD_LINKS = "Generate Download links for the selected Albums/Artist";
	public static final String USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS = "Download using the Generated Links";
	public static final String USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS_HINT = "(If gonna use external Download Tool like JDownloader, this step should be skipped)";
	public static final String USAGE_OPTION_COVERT_DOWNLOADED_SONGS = "Convert the downloaded songs to meaningful audio files";
	
	public static final String PROCESS_FLOW = "The process flow to download songs is:" + "\n" +
												"1. " + USAGE_OPTION_NEW_SEARCH + USAGE_OPTION_NEW_SEARCH_HINT + "\n" +
												"1B. " + USAGE_OPTION_SELECT_TO_DOWNLOAD + "\n" +
												"2. " + USAGE_OPTION_GENERATE_DOWNLOAD_LINKS + "\n" +
												"3. " + USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS + USAGE_OPTION_DOWNLOAD_FROM_GENERATED_LINKS_HINT + "\n" +
												"4. " + USAGE_OPTION_COVERT_DOWNLOADED_SONGS;
	
	public static final String USERNAME_PROMPT = "Please Enter your User Name:";
	public static final String PASSWORD_PROMPT = "Please Enter your Password:";
		
	public static final String OPTION_PROMT = "Please select a option number from above:";
	
	public static final String USAGE_OPTION_PROMPT = "How do you want to proceed?";
	public static final String LANGUAGE_PROMPT = "Please select the language:";
	public static final String SEARCHTYPE_PROMPT = "Please select a type of Search";

	public static final String QUIT_RESUME = "program will Quit and can be Resumed through a Re-Run";
	
	public static final String DO_WAIT_FOR_SELECTION = "Do you want the program to wait till you make your selection in Excel? (Yes or No). If No, " + QUIT_RESUME;
	public static final String LINKS_GENERATED_PROMPT = "Download links have been generated in the Excel. Do you wish to download songs using external program?. If yes, " + QUIT_RESUME;
	public static final String HAS_UPDATED_RESULTS = "Please update Search Result sheet, if not done already. Do you wish to proceed? (Yes or No). If No, " + QUIT_RESUME;
	public static final String PLEASE_UPDATE_RESULTS = "Please update the Results sheet with your Selection. Re-run program after your selections";

	public static final String EXIT_PROMPT = "Please press Enter key to exit";

	public static final String GENERIC_LOGIN_ERROR = "Unknown Login Error!";
	public static final String NO_DOWNLOAD_LINKS = "No download links found in " + DOWNLOAD_LINK_FILE_NAME;
	public static final String UNKNOWN_ERROR = "Some Unknown Error Occured! Please re-run the program.";
	public static final String DOWNLOAD_FAILURE = "Following Tracks were not downloaded due to error::";
	public static final String DOWNLOAD_FOLDER_NAME = "Downloaded_Songs";


}
