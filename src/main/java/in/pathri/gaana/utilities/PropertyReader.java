package in.pathri.gaana.utilities;

import in.pathri.gaana.dao.User;

public class PropertyReader {
public static User getUserCred(){
	String userName = "";
	String password = "";
	//TODO: read file and get cred	
	return new User(userName, password); 	
}
}
