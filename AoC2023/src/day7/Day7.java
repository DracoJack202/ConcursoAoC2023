package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.upm.aedlib.Entry;
import es.upm.aedlib.priorityqueue.PriorityQueue;
import es.upm.aedlib.priorityqueue.SortedListPriorityQueue;

public class Day7 {
	
	public static void main(String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day7/ej.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day7/input7.txt";
		String str1="";
	    FileReader f0 = new FileReader(archivo);
	    BufferedReader br = new BufferedReader(f0);
	    int ak;
	    while((ak=br.read())!=-1){  
	        str1+=(char)ak; 
	    }
	    br.close();
	    f0.close();
	    String[] lineas = str1.split("\n");
	    PriorityQueue<Mano, Integer> manos = new SortedListPriorityQueue<Mano, Integer>();
	    long res = 0; int rank = 1;
	    for(int i=0; i<lineas.length; i++) {
	    	String[] esto = lineas[i].split("\\s");
	    	Mano m = new Mano(esto[0]);
	    	manos.enqueue(m, Integer.parseInt(esto[1]));
	    	//System.out.println(manos.toString());
	    	System.out.println(m+", "+esto[1]);
	    }
	    
	    for(Entry<Mano, Integer> e : manos) {
	    	//System.out.println(e.getValue()*rank);
	    	res += e.getValue()*rank;
	    	rank++;
	    }
	    //System.out.println(manos.toString());
	    System.out.println("El resultado es: "+res);
	    
	}
}
