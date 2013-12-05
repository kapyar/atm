package dataBase;

public class Utils {
	public static String implodeArray(String[] inputArray, String glueString) {
		String output = "";
		if (inputArray.length > 0) {
		    StringBuilder sb = new StringBuilder();
		    sb.append(inputArray[0]);
		    for (int i=1; i<inputArray.length; i++) {
		        sb.append(glueString);
		        sb.append(inputArray[i]);
		    }
		    output = sb.toString();  
		}
		return output;
	}
	
	public enum Buttons {
		ADD,
		REMOVE, 
		SAVE;
	}
	
	public static String generateRandomString() {
		return "just_random_session";
	}
}
