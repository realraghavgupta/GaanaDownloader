package in.pathri.gaana.utilities;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.DownloadParam;
import in.pathri.gaana.constants.DownloadParam.Quality;
import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.User;
import in.pathri.gaana.downloader.DownloadLinkGenerator;
import in.pathri.gaana.enums.Language;
import in.pathri.gaana.enums.SearchType;
import in.pathri.gaana.enums.UsageOptions;

public class UserPrompts {
	static final Logger logger = LogManager.getLogger();
	private static Scanner input;

	static {
		input = new Scanner(System.in);
	}

	public static void greetAndInfo() {
		printBreak();
		System.out.println(Global.GREET);
		printBreak();
		System.out.println(Global.PROCESS_FLOW);
		printBreak();
		System.out.println("");
	}

	public static User promptForCred() {
		String userName = "";
		String password = "";

		System.out.println(Global.USERNAME_PROMPT);
		userName = input.nextLine();

		System.out.println(Global.PASSWORD_PROMPT);
		password = input.nextLine();

		return new User(userName, password);
	}

	public static UsageOptions doNewSearch() {
		String userResponse = "";
		UsageOptions response = null;
		System.out.println(Global.USAGE_OPTION_PROMPT);

		for (UsageOptions v : UsageOptions.values()) {
			printOptionNumber(v.getOption());
			System.out.println(v.getValue());
		}

		while (null == response) {
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}", userResponse);
			response = UsageOptions.getEnum(MiscUtilities.parseInt(userResponse));
		}
		return response;
	}

	public static void promptWrongCred(String message) {
		System.out.println(message);
	}

	public static Language getSearchLanguage() {
		String userResponse = "";
		Language response = null;
		System.out.println(Global.LANGUAGE_PROMPT);

		for (Language v : Language.values()) {
			printOptionNumber(v.getOption());
			System.out.println(v.getValue());
		}

		while (null == response) {
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}", userResponse);
			response = Language.getEnum(MiscUtilities.parseInt(userResponse));
		}
		return response;
	}

	public static SearchType getSearchType() {
		String userResponse = "";
		SearchType response = null;
		System.out.println(Global.SEARCHTYPE_PROMPT);

		for (SearchType v : SearchType.values()) {
			printOptionNumber(v.getOption());
			System.out.println(v.getValue());
		}

		while (null == response) {
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}", userResponse);
			response = SearchType.getEnum(MiscUtilities.parseInt(userResponse));
		}
		return response;
	}

	public static void getDownloadQuality() {
		String userResponse = "";
		Quality response = null;
		System.out.println(Global.DOWNLOAD_QUALITY_PROMPT);

		for (Quality v : Quality.values()) {
			printOptionNumber(v.getOption());
			System.out.println(v.getValue());
		}

		while (null == response) {
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}", userResponse);
			response = Quality.getEnum(MiscUtilities.parseInt(userResponse));
		}

		DownloadParam.setQuality(response);
	}

	public static String getAlbumIDs() {
		String userResponse = "";
		System.out.println("");
		System.out.println(Global.ALBUM_ID_LIST_PROMPT);
		userResponse = input.nextLine();
		return userResponse;
	}

	public static String getTrackIDs() {
		String userResponse = "";
		System.out.println("");
		System.out.println(Global.TRACK_ID_LIST_PROMPT);
		userResponse = input.nextLine();
		return userResponse;
	}

	public static void noResultsFound() {
		System.out.println(Global.NO_RESULTS_FOUND);
		doExit();
	}

	public static void notPlusUser() {
		System.out.println("");
		System.out.println(Global.NOT_PLUS_USER);
		doExit();
	}

	public static void doExit() {
		System.out.println(Global.EXIT_PROMPT);
		input.nextLine();
		System.exit(0);
	}

	public static boolean waitForUserSelection() {
		return yesNoPrompt(Global.DO_WAIT_FOR_SELECTION);
	}

	public static boolean hasUpdatedResultsSheet() {
		if (yesNoPrompt(Global.HAS_UPDATED_RESULTS)) {
			return DownloadLinkGenerator.checkIfResultsUpdated();
		}
		return false;
	}

	public static void pleaseUpdateResultsSheet() {
		System.out.println(Global.PLEASE_UPDATE_RESULTS);
		doExit();
	}

	public static boolean linksGenerated() {
		return yesNoPrompt(Global.LINKS_GENERATED_PROMPT);
	}

	private static boolean yesNoPrompt(String prompt) {
		String userResponse = "";
		System.out.println("");
		while (true) {
			System.out.println(prompt);
			userResponse = input.nextLine();
			userResponse = userResponse.trim().toLowerCase();
			if ("yes".equals(userResponse) || "y".equals(userResponse)) {
				return true;
			} else if ("no".equals(userResponse) || "n".equals(userResponse)) {
				return false;
			}
		}
	}

	private static void printOptionNumber(int option) {
		String toPrint = "[" + option + "]";
		System.out.print(toPrint);
	}

	public static void unknownError() {
		System.out.println(Global.UNKNOWN_ERROR);
		doExit();
	}

	private static void printBreak() {
		System.out.println(Global.BREAK);
	}

	public static void noDownloadLinks() {
		System.out.println(Global.NO_DOWNLOAD_LINKS);
	}

	public static void promtDownloadFailure(List<String> failureList) {
		System.out.println(Global.DOWNLOAD_FAILURE + String.join(";", failureList));
	}
}
