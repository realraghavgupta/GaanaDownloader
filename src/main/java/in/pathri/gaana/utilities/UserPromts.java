package in.pathri.gaana.utilities;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.constants.NewSearchPromt;
import in.pathri.gaana.dao.User;

public class UserPromts {
	static final Logger logger = LogManager.getLogger();
	private static Scanner input;
	static {
		input = new Scanner(System.in);
	}
	public static User promptForCred(){
		String userName = "";
		String password = "";
		
		System.out.println(Global.USERNAME_PROMPT);
		userName = input.nextLine();
		
		System.out.println(Global.PASSWORD_PROMPT);
		password = input.nextLine();
		
//		input.close();
		
		return new User(userName, password); 
	}
	
	public static boolean doNewSearch(){
		String option = "";
		System.out.println(Global.DO_NEW_SEARCH_PROMPT);
		System.out.println(NewSearchPromt.NEW_SEARCH.getName());
		System.out.println(NewSearchPromt.DOWNLOAD.getName());	
//		logger.info(NewSearchPromt.values().toString());
		while((NewSearchPromt.NEW_SEARCH.getValue() != MiscUtilities.parseInt(option)) && (NewSearchPromt.DOWNLOAD.getValue() != MiscUtilities.parseInt(option))){
			System.out.println(Global.OPTION_PROMT);
			option = input.nextLine();
			logger.info("selected option:{}",option);
		}
		NewSearchPromt response = NewSearchPromt.getEnum(option);
		if(response == NewSearchPromt.NEW_SEARCH){
			return true;
		}
		return false;
	}
}
