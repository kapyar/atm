package dataBase;

public class Pair<FIRST, SECOND> {
	private FIRST first;
	private SECOND second;
	

	
	public Pair (FIRST fs, SECOND sd) {
		this.first = fs;
		this.second = sd;
	}
	
	public FIRST first() {
		return first;
	}
	
	public SECOND second() {
		return second;
	}
	
	public void set_first(FIRST val) {
		first = val;
	}
	
	public void set_second(SECOND val) {
		second = val;
	}
}
