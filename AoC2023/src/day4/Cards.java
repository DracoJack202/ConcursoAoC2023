package day4;

public class Cards {
	private int id;
	private int sigCards;
	private int repeats;
	
	public Cards(int id, int izq, int der) {
		this.id = id;
		sigCards = izq;
		repeats = der;
	}
	
	public Cards(int id) {
		this.id = id;
		sigCards = 0;
		repeats = 1;
	}
	
	public void setSigCards(int i) {
		sigCards = i;
	}
	
	public void setRepeats(int i) {
		repeats = i;
	}
	
	public void addRepeats(int i) {
		repeats += i;
	}
	
	public int id() {
		return id;
	}
	public int sigCards() {
		return sigCards;
	}
	public int repeats() {
		return repeats;
	}
	public String toString() {
		return ("["+id+", "+sigCards+", "+repeats+"]");
	}
}
