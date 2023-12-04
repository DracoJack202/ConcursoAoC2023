package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import day3.Num;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;

public class Day4 {
	
	public static PositionList<Cards> initList(){
		PositionList<Cards> bonos = new NodePositionList<Cards>();
		for(int i=1; i<220; i++) {
			bonos.addLast(new Cards(i));
		}
		return bonos;
	}
	
	public static void addASig(Position<Cards> cursor, PositionList<Cards> lista) {
		int i = cursor.element().sigCards();
		int repeats = cursor.element().repeats();
		//System.out.println(i);
		Position<Cards> next = lista.next(cursor);
		while(i>0) {
			next.element().addRepeats(1*repeats);
			//System.out.println(next.element().repeats());
			i--; next = lista.next(next);
		}
	}
	
	public static void main (String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day4/ej.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day4/input4.txt";
		String str1="";
	    FileReader f0 = new FileReader(archivo);
	    BufferedReader br = new BufferedReader(f0);
	    int ak;
	    while((ak=br.read())!=-1){  
	        str1+=(char)ak; 
	    }
	    br.close();
	    f0.close();
	    int suma = 0;
        String[] sections = str1.split("\n");
        // Parte 1
        PositionList<Integer> have = new NodePositionList<Integer>();
        // Parte 2
        PositionList<Cards> bonos = initList();
        Position<Cards> cursor = bonos.first();
        
        for(int i=0; i<sections.length; i++) {
        	
        	String linea = sections[i].split(":")[1];
        	System.out.println(linea);
        	
        	String[] strAux = linea.split("\\|");
        	String strHave = strAux[0];
        	String strWinners = strAux[1];
        	//System.out.println(strWinners);
        	
        	//strHave = strHave.replaceAll("\\s", "a");
        	//System.out.println(strHave);
        	//strWinners = strWinners.replaceAll("\\W", "a");
        	strAux = strHave.split("\\s");
        	//System.out.println("strHave split: ");
        	for(int j=0; j<strAux.length; j++) {
        		//System.out.println(strAux[j]);
        		if(strAux[j].toCharArray().length>0)
        		have.addLast(Integer.parseInt(strAux[j]));
        	}
        	//System.out.println(have.toString());
        	int producto = 0;
        	strAux = strWinners.split("\\s");
        	Position<Integer> pos = have.first();
        	//System.out.println(pos.element());
        	//System.out.println("strWinners split: ");
        	for(int j=0; j<strAux.length; j++) { 
        		//System.out.println(strAux[j]+" ");
        		if(strAux[j].toCharArray().length>0) {
        		pos = have.first();
        		int comp = Integer.parseInt(strAux[j]);
        		boolean fin = false;
        		while(pos!=null&&!fin) {
        			//System.out.println(pos.element()+" "+comp+" "+(pos.element()==comp)+" "+pos.element().equals(comp));
        			if(pos.element()==comp) {
        				fin = true;
        				have.remove(pos);
        				// Parte 1
//        				if(producto!=0) { 
//        					producto = producto*2;
//        				}
//        				else producto = 1;
        				
        				// Parte 2
        				producto++;
        			}
        			else pos = have.next(pos);
        			}
        		}
        		
        	}
        	cursor.element().setSigCards(producto);
        	addASig(cursor, bonos);
        	System.out.println("Producto: "+producto+"\n");
        	suma+=cursor.element().repeats(); have = new NodePositionList<Integer>();
        	//System.out.println(have.toString());
        	cursor = bonos.next(cursor);
        }
        System.out.println(bonos.toString());
        System.out.println(suma);
	}
}
