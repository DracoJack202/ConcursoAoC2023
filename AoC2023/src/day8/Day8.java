package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.map.HashTableMap;
import es.upm.aedlib.map.Map;
import es.upm.aedlib.positionlist.NodePositionList;
import es.upm.aedlib.positionlist.PositionList;

public class Day8 {

	public static void main(String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day8/testeo.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day8/input8.txt";
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day8/ej.txt";
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day8/ej2.txt";
		String str1="";
	    FileReader f0 = new FileReader(archivo);
	    BufferedReader br = new BufferedReader(f0);
	    int ak;
	    while((ak=br.read())!=-1){  
	        str1+=(char)ak; 
	    }
	    br.close();
	    f0.close();
	    
	    String[] lineas = str1.split("\n\n");   
	    char[] camino = lineas[0].toCharArray();
	    lineas = lineas[1].split("\n");
	    String cursor = "AAA";
	    Map<String, Pair<String,String>> mapa = new HashTableMap<String, Pair<String,String>>();
	    PositionList<String> lista = new NodePositionList<String>();
	    
	    for(int i=0; i<lineas.length; i++) {
	    	String esta = lineas[i];
	    	System.out.println(esta);
	    	Pair<String, Pair<String,String>> aux = coord(esta);
	    	mapa.put(aux.getLeft(), aux.getRight());
	    	if(aux.getLeft().toCharArray()[2]=='A') lista.addLast(aux.getLeft());
	    	//System.out.println(key+""+izq+""+der);
	    }
	    System.out.println(lista.toString());
//	    int i = 0; long cant = 0;
//	    while(!cursor.equals("ZZZ")) {
//	    	if(i==camino.length) i=0;
//	    	char dir = camino[i];
//	    	if(dir=='L') {
//	    		cursor = mapa.get(cursor).getLeft();
//	    	}
//	    	else cursor = mapa.get(cursor).getRight();
//	    	i++; cant++;
//	    }
	    PositionList<Long> bucle = new NodePositionList<Long>();
	    Position<String> pos = lista.first();
	    while(pos!=null) {
	    	int i = 0; long cant = 0;
	    	cursor = pos.element();
		    while(!cursor.contains("Z")) {
		    	if(i==camino.length) i=0;
		    	char dir = camino[i];
		    	if(dir=='L') {
		    		cursor = mapa.get(cursor).getLeft();
		    	}
		    	else cursor = mapa.get(cursor).getRight();
		    	i++; cant++;
		    }
		    bucle.addLast(cant);
		    pos = lista.next(pos);
	    }
	    long j = 1;
	    for(Long l : bucle) {
	    	j = mcm(j,l);
	    }
	    System.out.println(j);
	    
//	    while(!todasZ(lista)) {
//	    	if(i==camino.length) i=0;
//	    	char dir = camino[i];
//	    	PositionList<String> aux = new NodePositionList<String>();
//	    	if(dir=='L') {
//	    		for(String s : lista) {
//	    			aux.addLast(mapa.get(s).getLeft());
//	    		}
//	    	}
//	    	else for(String s: lista) {
//	    		aux.addLast(mapa.get(s).getRight());
//	    	}
//	    	i++; cant++; lista = aux;
//	    	if(cant%100000000==0) System.out.println(cant);
//	    }
	    	
	    System.out.println(bucle);
	}
	
	private static Pair<String, Pair<String,String>> coord(String linea) {
		String[] aux = linea.split("=");
    	String key = aux[0].replaceAll("[^A-Z]", "");
    	aux = aux[1].split(",");
    	String izq = aux[0].replaceAll("[^A-Z]", "");
    	String der = aux[1].replaceAll("[^A-Z]", "");
    	return new Pair<String, Pair<String,String>>(key, new Pair<String,String>(izq, der));
	}
	
//	private static boolean todasZ (PositionList<String> lista) {
//		//System.out.println("Todas Z con: " +lista);
//		boolean esZ = true; Position<String> pos = lista.first();
//		while(esZ&&pos!=null) {
//			esZ = pos.element().toCharArray()[2]=='Z';
//			pos = lista.next(pos);
//		}
//		return esZ;
//	}
	
	public static long mcm(long a, long b) {
		return (a*b)/mcd(a,b);
	}
	
	public static long mcd(long a, long b) {
		long temp;
		while (b!=0) {
			temp = b; b = a%b; a = temp;
		}
		return a;
	}
	
}
