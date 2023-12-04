package day3;

import es.upm.aedlib.Pair;

public class Num {
	boolean signo;
	Pair<Integer, Integer> coord1;
	Pair<Integer, Integer> coord2;
	Pair<Integer, Integer> coord3;
	Integer valor=null;
	boolean utilizado;
	
	Num(){
		signo = false;
		this.coord1 = new Pair<Integer,Integer>(-10,-10);
		this.coord2 = new Pair<Integer,Integer>(-10,-10);
		this.coord3 = new Pair<Integer,Integer>(-10,-10);
		utilizado = false;
	}
	Num(Pair<Integer, Integer> coord, boolean signo){
		this.signo = signo;
		this.coord1 = coord;
		this.coord2 = new Pair<Integer,Integer>(-10,-10);
		this.coord3 = new Pair<Integer,Integer>(-10,-10);
		utilizado = false;
	}
	
	Num (boolean signo, Pair<Integer, Integer> coord){
		this.signo = signo;
		this.coord1 = coord;
		this.coord2 = new Pair<Integer,Integer>(-10,-10);
		this.coord3 = new Pair<Integer,Integer>(-10,-10);
		utilizado = false;
	}
	
	Num(Pair<Integer, Integer> coord){
		if(this.coord2.getLeft()!=-10) this.coord3 = coord;
		if(this.coord1.getLeft()!=-10) this.coord2 = coord; 
		this.coord1 = coord;
	}
	
	public void addCoord(Pair<Integer, Integer> coord) {
		if(this.coord2.getLeft()!=-10) this.coord3 = coord;
		else if(this.coord1.getLeft()!=-10) this.coord2 = coord; 
		else this.coord1 = coord;
	}
	
	public boolean utilizar(Pair<Integer,Integer> coord) {
		boolean aux = utilizado;
		if(!aux) {
			//System.out.println(getValor()+"\n"+"coord1");
			boolean adj1 = (adjacent(coord, coord1));
			//System.out.println("coord2");
			boolean adj2 = (adjacent(coord, coord2));
			//System.out.println("coord3");
			boolean adj3 = (adjacent(coord, coord3));
			aux = (adj1||adj2||adj3);
			utilizado = aux;
		}
		return aux;
	}
	
	public int utilizar2(Pair<Integer,Integer> coord) {
		boolean aux = false;
			//System.out.println(getValor()+"\n"+"coord1");
			boolean adj1 = (adjacent(coord, coord1));
			//System.out.println("coord2");
			boolean adj2 = (adjacent(coord, coord2));
			//System.out.println("coord3");
			boolean adj3 = (adjacent(coord, coord3));
			aux = (adj1||adj2||adj3);
			
		if(aux) return 1; else return 0;
	}
	
	public boolean adjacent(Pair<Integer,Integer> coord, Pair<Integer,Integer> comparar) {
		int x = coord.getLeft(); int y = coord.getRight();
		boolean adj = utilizado;
		if(comparar.getLeft()>-5)
		for (int i=0; i<3&&!adj; i++) {
			for(int j=0; j<3&&!adj; j++) {
				Pair<Integer,Integer> aux = new Pair<Integer,Integer>(x-1+i,y-1+j);
				//System.out.println(comparar.toString()+" "+aux.toString());
				adj = comparar.equals(aux);
			}
		}
		return adj;
	}
	
	public boolean utilizado() {
		return utilizado;
	}
	
	public boolean signo() {
		return signo;
	}
	
	public Pair coord1() {
		return coord1;
	}
	
	public void darValor(Integer a) {
		valor = a;
	}
	
	public Integer getValor() {
		return valor;
	}
	
}
