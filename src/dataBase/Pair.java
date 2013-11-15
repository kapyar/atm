package dataBase;

public class Pair<FIRST, SECOND> {
	private FIRST first;
	private SECOND second;
	
	Pair (FIRST fs, SECOND sd) {
		this.first = fs;
		this.second = sd;
	}
	
	public FIRST first() {
		return first;
	}
	
	public SECOND second() {
		return second;
	}
}
