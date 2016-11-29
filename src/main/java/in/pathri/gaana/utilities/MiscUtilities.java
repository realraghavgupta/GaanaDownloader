package in.pathri.gaana.utilities;

import in.pathri.gaana.constants.Global;

public class MiscUtilities {
	
	public static String getGaanURL(String endPoint) {
		return Global.GAANA_BASE_URL + (endPoint.startsWith("/")?endPoint:"/"+endPoint);
	}
	
	public static int parseInt(String value){
		if(null == value || value.isEmpty()){
			return 0;
		}else{
			return Integer.parseInt(value);
		}
	}

}
