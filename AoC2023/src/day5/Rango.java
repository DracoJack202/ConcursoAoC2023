package day5;

public class Rango {
	
	private long min;
	private long max;
	
	public Rango(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	public Rango() {
		this.min = -10;
		this.max = -10;
	}
	
	public long comparator(Rango r) {
		return min-r.getMin();
	}
	
	public boolean enRango(long num) {
		return min<=num&&max>num;
	}
	
	public void addMin(long min) {
		this.min = min;
	}
	public void addMax(long max) {
		this.max = max;
	}
	
	public long getMin() {
		return min;
	}
	public long getMax() {
		return max;
	}
	
	public String toString() {
		return "("+min+", "+max+")";
	}
}
