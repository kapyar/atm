package dataBase;
public class Test {
	public static void main (String args[]) {
		
		Database db = new Database();
		System.out.println(db.getColumComments("Adresses", "id"));
		
		
	}
}