package day7;

import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class Mano implements Comparable<Mano>{
	
	int c1;
	int c2;
	int c3;
	int c4;
	int c5;
	PositionList<Integer> mano = new NodePositionList<Integer>();
	int joker;
	int tipo;
	
	public Mano(String mano) {
		char[] cartas = mano.toCharArray();
		c1 = carta(cartas[0]);
		c2 = carta(cartas[1]);
		c3 = carta(cartas[2]);
		c4 = carta(cartas[3]);
		c5 = carta(cartas[4]); 
		this.mano.addLast(c1); this.mano.addLast(c2); this.mano.addLast(c3);
		this.mano.addLast(c4); this.mano.addLast(c5);
		// tipo = tipo();
		tipo = tipoTrasJoker(tipo());
	}
	
	private int tipo() {
		Position<Integer> cursor = mano.first();
		int aux = 0; boolean hayMas = true;
		while(cursor!=null&&hayMas) {
			int carta = cursor.element();
			int reps = 1;
			Position<Integer> nexts = mano.next(cursor);
			while(nexts!=null) {
				if(carta==nexts.element()) {
					reps++;
					Position<Integer> auxNext = mano.next(nexts);
					mano.remove(nexts);
					nexts=auxNext;
				}
				else nexts = mano.next(nexts);
			}
			if(reps==5) {
				hayMas=false;
				aux = 6;
			}
			if(reps==4) {
				hayMas = false;
				aux = 5;
			}
			if(reps==3) {
				aux+=3;
			}
			if(reps==2) {
				aux++;
			}
			cursor=mano.next(cursor);
		}
		return aux;
	}
	
	private int tipoTrasJoker(int tipo) {
		if(tipo==5&&joker==4) {
			return 6;
		}
		if(tipo==5&&joker==1) {
			return 6;
		}
		if(tipo==4&&joker==3) {
			return 6;
		}
		if(tipo==4&&joker==2) {
			return 6;
		}
		if(tipo==3&&joker==3) {
			return 5;
		}
		if(tipo==3&&joker==1) {
			return 5;
		}
		if(tipo==2&&joker==2) {
			return 5;
		}
		if(tipo==2&&joker==1) {
			return 4;
		}
		if(tipo==1&&joker==2) {
			return 3;
		}
		if(tipo==1&&joker==1) {
			return 3;
		}
		if(tipo==0&&joker==1) {
			return 1;
		}
		return tipo;
	}
	
	private int carta(char c) {
		switch(c) {
		case 'A':
			return 14;
		case 'K':
			return 13;
		case 'Q':
			return 12;
		case 'J':
			joker++;
			// return 11
			return 1;
		case 'T':
			return 10;
		default:
			return Integer.parseInt(""+c);
		}
	}
	
	public int compareTo(Mano m) {
		if(tipo!=m.getTipo()) {
			return tipo-m.getTipo();
		}
		else if(c1!=m.getC1()) {
			return c1-m.getC1();
		}
		else if(c2!=m.getC2()) {
			return c2-m.getC2();
		}
		else if(c3!=m.getC3()) {
			return c3-m.getC3();
		}
		else if(c4!=m.getC4()) {
			return c4-m.getC4();
		}
		else if(c5!=m.getC5()) {
			return c5-m.getC5();
		}
		return 0;
	}
	
	public int getTipo() {
		return tipo;
	}
	public int getC1() {
		return c1;
	}
	public int getC2() {
		return c2;
	}
	public int getC3() {
		return c3;
	}
	public int getC4() {
		return c4;
	}
	public int getC5() {
		return c5;
	}
	
	public String toString() {
		return tipo+": ["+(c1==1?"J":(c1+""))+"/"+(c2==1?"J":(c2+""))+"/"+(c3==1?"J":(c3+""))+"/"+(c4==1?"J":(c4+""))+"/"+(c5==1?"J":(c5+""))+"]";
		// return tipo+": ["+c1+"/"+c2+"/"+c3+"/"+c4+"/"+c5+"]";
	}
}
