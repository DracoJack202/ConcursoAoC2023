package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class Day6 {
	
	public static void main(String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day6/ej.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day6/input6.txt";
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
	    
	    String[] tiempos = lineas[0].split("\\s");
	    System.out.println(lineas[0]);
	    String[] distancias = lineas[1].split("\\s");
	    System.out.println(lineas[1]);
	    
	    String tiempo = lineas[0].replaceAll("[^0-9]", "");
	    System.out.println(tiempo);
	    String distancia = lineas[1].replaceAll("[^0-9]", "");
	    System.out.println(distancia);
	    
	    int res = 1;
	    int cant = 0;
	    
	    // Parte 1
//	    for(int i=1, z=1; i<tiempos.length&&(z<distancias.length); i++, z++) {
//	    	while(!(tiempos[i].toCharArray().length>0)&&i<tiempos.length) {
//	    		//System.out.println(tiempos[i]);
//	    		i++;
//	    	}
//	    	while(!(distancias[z].toCharArray().length>0)&&z<distancias.length) {
//	    		//System.out.println(distancias[z]);
//	    		z++;
//	    	}
//	    	int ant = 0; int j = 1; 
//	    	int t = Integer.parseInt(tiempos[i].replaceAll("[^0-9]", ""));
//	    	int record = Integer.parseInt(distancias[z].replaceAll("[^0-9]", ""));
//	    	System.out.println("\n"+t+" "+record);
//	    	PositionList<Integer> lista = new NodePositionList<Integer>();
//	    	boolean fin = false;
//	    	while(j<t&&!fin) {
//	    		int dist = j*(t-j);
//	    		if(record<dist) {
//	    			lista.addLast(j);
//	    		}
//	    		else if(record>dist&&ant>dist) {
//	    			fin = true;
//	    		}
//	    		j++;
//	    	}
//	    	res = res*lista.size();
//	    	System.out.println(lista.toString());
//	    }
	    
	    // Parte 2
	    int ant = 0; 
    	long t = Long.parseLong(tiempo);
    	long record = Long.parseLong(distancia);
    	long j = record/t;
    	System.out.println("\n"+t+" "+record+" j: "+j);
    	boolean fin = false;
	    while(j<t&&!fin) {
    		long dist = j*(t-j);
    		if(record<dist) {
    			cant++;
    		}
    		else if(record>dist&&ant>dist) {
    			fin = true;
    		}
    		j++;
    	}
	    
	    System.out.println("\nResultado final: "+cant);
	}
}
