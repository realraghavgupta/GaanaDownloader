package in.pathri.gaana.utilities;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import in.pathri.gaana.constants.Global;

public class GaanaUtilities {
	private static final char[] n = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
	private static final char[] o = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	public static byte[] a(byte[] bArr) {
		return b(bArr).getBytes(Charset.forName("UTF-8"));
	}

	public static String b(byte[] bArr) {
		return new String(c(bArr));
	}

	public static char[] c(byte[] bArr) {
		return a(bArr, true);
	}

	public static char[] a(byte[] bArr, boolean z) {
		return a(bArr, z ? n : o);
	}

	protected static char[] a(byte[] bArr, char[] cArr) {
		int i = 0;
		int length = bArr.length;
		char[] cArr2 = new char[(length << 1)];
		for (int i2 = 0; i2 < length; i2++) {
			int i3 = i + 1;
			cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
			i = i3 + 1;
			cArr2[i3] = cArr[bArr[i2] & 15];
		}
		return cArr2;
	}
	
	   public static String getBase64(String str) {
		   return Base64.getEncoder().encodeToString(str.getBytes()).trim();
	    }

	public static String getHashCode(String track_id, String token) {		
		String msg = getBase64(track_id);
		String hashCode = doHMAC(msg, token);
		return hashCode;
	}
	
//	private static String doHMAC(String msg, String key){
//		try {
//			Key secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacMD5");
//			Mac instance = Mac.getInstance("HmacMD5");
//			instance.init(secretKeySpec);
//			return new String(a(instance.doFinal(msg.getBytes())), "UTF-8");
//		} catch (NoSuchAlgorithmException e2) {
//			e2.printStackTrace();
//			return null;
//		} catch (InvalidKeyException e3) {
//			e3.printStackTrace();
//			return null;
//		} catch (UnsupportedEncodingException e4) {
//			e4.printStackTrace();
//			return null;
//		} catch (Throwable e) {
//			throw new RuntimeException(e);
//		}		
//	}
	
	private static String doHMAC(String msg, String keyString){
		 String digest = null;
		 String algo = "HmacMD5";
		    try {
		      SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
		      Mac mac = Mac.getInstance(algo);
		      mac.init(key);

		      byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));

		      StringBuffer hash = new StringBuffer();
		      for (int i = 0; i < bytes.length; i++) {
		        String hex = Integer.toHexString(0xFF & bytes[i]);
		        if (hex.length() == 1) {
		          hash.append('0');
		        }
		        hash.append(hex);
		      }
		      digest = hash.toString();
		    } catch (UnsupportedEncodingException e) {
		    } catch (InvalidKeyException e) {
		    } catch (NoSuchAlgorithmException e) {
		    }
		    return digest;
		  }		

	public static String decodeDownloadURL(String data) {
	        try {
//	            byte[] c = new l(Constants.F).c(str);
	        	byte[] c = decodeDownloadURL(Global.URL_DECODE_KEY, data);
	            if (c != null) {
	                String str2 = new String(c);
	                if (str2.startsWith("http")) {
	                    return str2.trim();
	                }
	                System.out.println("StreamingFailure" + "Server - URL decoding failure");
	            }
	        } catch (Exception e) {
	            System.out.println("StreamingFailure" + "Server - URL decoding failure");
	            e.printStackTrace();
	        }
	        return null;
	}
	
	
	
	 private static byte[] decodeDownloadURL(String urlDecodeKey, String data) throws Exception {
		 SecretKeySpec b = new SecretKeySpec(urlDecodeKey.getBytes(), "AES");
	        try {
	            Cipher c = Cipher.getInstance("AES/ECB/NoPadding");
	            if (data == null || data.length() == 0) {
		            throw new Exception("Empty string");
		        }
		        try {
		            c.init(2, b);
		            return c.doFinal(a(data));
		        } catch (Exception e) {
		            throw new Exception("[decrypt] " + e.getMessage());
		        }	            
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (NoSuchPaddingException e2) {
	            e2.printStackTrace();
	        }
	        return null;
	}
	 
	    public static byte[] a(String str) {
	        byte[] bArr = null;
	        if (str != null && str.length() >= 2) {
	            int length = str.length() / 2;
	            bArr = new byte[length];
	            for (int i = 0; i < length; i++) {
	                bArr[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16);
	            }
	        }
	        return bArr;
	    }

		
}
