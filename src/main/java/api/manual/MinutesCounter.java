package api.manual;

public class MinutesCounter {
	private long miliSeconds;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public MinutesCounter(){
		set();
	}
	
	
	public long getMili() {
		long timeMili =System.currentTimeMillis()-miliSeconds;
		return timeMili;
	}
	public long getSec() {
		return getMili()/1000;
	}
	public long getMin() {
		return getSec()/60;
	}
	
	
	public void set() {
		this.miliSeconds = System.currentTimeMillis();
	}

}
