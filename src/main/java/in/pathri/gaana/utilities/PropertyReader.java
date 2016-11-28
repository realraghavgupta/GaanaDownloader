package in.pathri.gaana.utilities;

import in.pathri.gaana.dao.User;

public class PropertyReader {
public static User getUserObj(){
	String userName = "";
	String password = "";
	//TODO: read file and get cred
	
	
	
	File f = new File("user.dat");
	if (f.isFile() && f.canRead()) {
	  try {
	    // Open the stream.
	    FileInputStream in = new FileInputStream(f);
	    // To read chars from it, use new InputStreamReader
	    // and specify the encoding.
	    try {
	      // Do something with in.
	    } finally {
	      in.close();
	    }
	  } catch (IOException ex) {
	    // Appropriate error handling here.
	  }
	}
	
	
	
	return new User(userName, password); 	
}


}

import java.io.*;
public class DeserializeDemo {

   public static void main(String [] args) {
      Employee e = null;
      try {
         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         e = (Employee) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized Employee...");
      System.out.println("Name: " + e.name);
      System.out.println("Address: " + e.address);
      System.out.println("SSN: " + e.SSN);
      System.out.println("Number: " + e.number);
   }
}

import java.io.*;
public class SerializeDemo {

   public static void main(String [] args) {
      Employee e = new Employee();
      e.name = "Reyan Ali";
      e.address = "Phokka Kuan, Ambehta Peer";
      e.SSN = 11122333;
      e.number = 101;
      
      try {
         FileOutputStream fileOut =
         new FileOutputStream("/tmp/employee.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(e);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i) {
         i.printStackTrace();
      }
   }
}
