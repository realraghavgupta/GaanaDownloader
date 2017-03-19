package in.pathri.gaana.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import in.pathri.gaana.constants.Global;
import net.minidev.json.JSONObject;

public class NativeMessagingHelper {
	public static void sendMessage(String msg, boolean errorFlag) {
		try {
			JSONObject retMsg = new JSONObject();
			retMsg.put(Global.ERROR_FLAG, errorFlag);
			retMsg.put(Global.NATIVE_MESSAGE, msg);
			sendMessage(retMsg.toJSONString());
		} catch (Exception e) {
			logToFile(Arrays.toString(e.getStackTrace()));
		}
	}

	public static String readMessage(InputStream in){
		try {
		byte[] b = new byte[4];
		in.read(b);

		int size = getInt(b);
		logToFile(String.format("The size is %d", size));

		if (size == 0) {
			throw new InterruptedIOException("Blocked communication");
		}

		b = new byte[size];
		in.read(b);

		return new String(b, "UTF-8");
		} catch (Exception e) {
			logToFile(Arrays.toString(e.getStackTrace()));
			return "";
		}
	}

	private static void sendMessage(String message) throws IOException {
		System.out.write(getBytes(message.length()));
		System.out.write(message.getBytes("UTF-8"));
		System.out.flush();
		logToFile("mandou: " + message);
	}

	private static int getInt(byte[] bytes) {
		return (bytes[3] << 24) & 0xff000000 | (bytes[2] << 16) & 0x00ff0000 | (bytes[1] << 8) & 0x0000ff00
				| (bytes[0] << 0) & 0x000000ff;
	}

	private static byte[] getBytes(int length) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (length & 0xFF);
		bytes[1] = (byte) ((length >> 8) & 0xFF);
		bytes[2] = (byte) ((length >> 16) & 0xFF);
		bytes[3] = (byte) ((length >> 24) & 0xFF);
		return bytes;
	}

	public static void logToFile(String msg) {
		File file = new File("NativeMessageLogger.log");

		try {
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			bufferedWriter.write(dateFormat.format(date) + ": " + msg + "\r\n");
			bufferedWriter.close();
		} catch (Exception e) {
			logToFile("ERROR ==> Method (log)" + e.getMessage());
			e.printStackTrace();
		}
	}

}
