package in.pathri.gaana.utilities;

import java.util.Base64;

import org.apache.commons.codec.digest.HmacUtils;

public class GaanaUtilities {
	public static String getHashCode(String track_id, String token) {
		String codedValue = getBase64(track_id);
		String hashCode = HmacUtils.hmacMd5Hex(token, codedValue);
		return hashCode;
	}

	public static String decodeDownloadURL(String data) {
		byte[] url = Base64.getDecoder().decode(data);
		return new String(url);
	}

	public static String getBase64(String str) {
		return Base64.getEncoder().encodeToString(str.getBytes()).trim();
	}
}
