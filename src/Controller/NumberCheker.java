package Controller;

public class NumberCheker {

	public static boolean isNumber(String s)
	{
		for (int i = 0; i < s.length(); i++) {
			if (s.toCharArray()[i]<'0' || s.toCharArray()[i]>'9'){
				System.out.println("Incoretct char:"+s.toCharArray()[i]);
				return false;
			}
		}
		return true;
	}
	
}
