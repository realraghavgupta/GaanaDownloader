package in.pathri.gaana.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import in.pathri.gaana.dao.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyHelper {
	static final Logger logger = LogManager.getLogger();

	public static void setUserObj(User user) {
		serialize(user, "user.dat");
	}

	public static User getUserObj() {
		Object obj = deserialize("user.dat");
		if (obj != null) {
			return (User) obj;
		}
		return null;
	}

	private static void serialize(Object obj, String filePath) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			try {
				out.writeObject(obj);
			} catch (IOException e) {
				logger.catching(e);
			} finally {
				out.close();
				fileOut.close();
			}
		} catch (IOException e) {
			logger.catching(e);
		}
	}

	private static Object deserialize(String filePath) {
		Object obj = null;
		File f = new File(filePath);
		if (f.isFile() && f.canRead()) {
			try {
				// Open the stream.
				FileInputStream fileIn = new FileInputStream(f);
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				try {
					obj = objIn.readObject();
				} catch (Exception e) {
					logger.catching(e);
				} finally {
					objIn.close();
					fileIn.close();
				}
			} catch (IOException e) {
				logger.catching(e);
			}
		}
		return obj;
	}
}