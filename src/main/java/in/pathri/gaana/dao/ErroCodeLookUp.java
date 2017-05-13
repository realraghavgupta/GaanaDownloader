package in.pathri.gaana.dao;

import java.util.HashMap;
import java.util.Map;

public class ErroCodeLookUp {
	private static Map<String, String> errorCodeMap = new HashMap<String, String>();

	public static void addErrorCode(String errorCode, String errorMessage) {
		errorCodeMap.put(errorCode, errorMessage);
	}

	public static String getErrorMessage(String errorCode) {
		return errorCodeMap.getOrDefault(errorCode, "");
	}
}
