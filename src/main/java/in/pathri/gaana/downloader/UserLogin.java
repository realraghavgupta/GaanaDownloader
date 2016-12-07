package in.pathri.gaana.downloader;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.User;
import in.pathri.gaana.utilities.DownloadParamHelper;
import in.pathri.gaana.utilities.HTTPHelper;
import in.pathri.gaana.utilities.MiscUtilities;
import in.pathri.gaana.utilities.PropertyHelper;
import in.pathri.gaana.utilities.UserPromts;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class UserLogin {
	static final Logger logger = LogManager.getLogger();
	private static User user;
	
	static {
		user = PropertyHelper.getUserObj();
	}
	
	public static String doLogin(User user){
		String token = "";
		String errorMessage = "";
		Map<String, String> params = new HashMap<String,String>();
		params.put("type", "nxtgen_authenticate");
		params.put("username", user.getUserName());
		params.put("password", user.getPassword());
		
		String login_url = MiscUtilities.getGaanURL(Global.LOGIN_ENDPOINT);
		
		try {
			String loginResponse = HTTPHelper.sendPost(login_url, params);
			JSONObject userData = (JSONObject) JSONValue.parse(loginResponse);
			if(userData.containsKey("token")){
				token = userData.getAsString("token");	
			}else{
				if(userData.containsKey("Error")){
					errorMessage = userData.getAsString("Error");					
				}else if(userData.containsKey("error")){
					errorMessage = userData.getAsString("error");
				}else{
					errorMessage = Global.GENERIC_LOGIN_ERROR;
				}
				UserPromts.promptWrongCred(errorMessage);
			}
			
		} catch (Exception e) {
			logger.catching(e);
			token = "";
		}
		
		return token;
	}
	
	public static User getUser(){
		if(null == user || user.isEmpty()){
			return UserPromts.promptForCred();
		}
		return user;
	}
	
	public static boolean doLogin(){
		if(null == user){
			user = UserPromts.promptForCred();
		}
		if(null != user && !user.hasToken()){
			String token = doLogin(user);
			if(!token.isEmpty()){
				user.setToken(token);
				PropertyHelper.setUserObj(user);
			}			
		}
		if(user.hasToken()){
			logger.info("User Token::{}",user.getToken());
			DownloadParamHelper.setUserToken(user.getToken());
			return true;			
		}
		return false;
	}
}
