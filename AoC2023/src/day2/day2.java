package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.upm.aedlib.*;


public class day2 {
	
	private static Pair<Integer,Integer> check2(String a){
		Pair<Integer, Integer> res=new Pair<Integer, Integer>(0, 0);;
		boolean fin = false;
		char[] chars = a.toCharArray(); int i = chars.length-1;
		String aux ="";
		while(i>0&&!fin) {
			aux+=chars[i];
			if(aux.contains("de")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				res = new Pair<Integer, Integer>(1, Integer.parseInt(a));
			}
			if(aux.contains("ne")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				res = new Pair<Integer, Integer>(2, Integer.parseInt(a));
			}
			if(aux.contains("eu")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				res = new Pair<Integer, Integer>(3, Integer.parseInt(a));
			}
			i--;
		}
		return res;
	}
	
	private static boolean check(String a) {
		boolean correcto = true; boolean fin = false; 
		char[] chars = a.toCharArray(); int i = chars.length-1;
		String aux ="";
		while(i>0&&!fin) {
			aux+=chars[i];
			if(aux.contains("de")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				correcto = Integer.parseInt(a)<=12;
			}
			if(aux.contains("ne")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				correcto = Integer.parseInt(a)<=13;
			}
			if(aux.contains("eu")) {
				fin = true;
				a = a.replaceAll("[^0-9]", ""); 
				correcto = Integer.parseInt(a)<=14;
			}
			i--;
		}
		return correcto;
	}
	
	public static void main (String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day2/ej.txt";
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day2/input.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day2/input.txt";
		String str1="";
        FileReader f0 = new FileReader(archivo);
        BufferedReader br = new BufferedReader(f0);
        int j;
        while((j=br.read())!=-1){  
            str1+=(char)j; 
        }
        br.close();
        f0.close();
        int suma = 0;
        String[] sections = str1.split("\n");
        for(int i=0; i<sections.length; i++) {
        	String linea = sections[i];
        	String[] game = linea.split(":");
        	boolean posible = true; 
        	int red = 0; int blue = 0; int green = 0;
        	System.out.println(linea);
        	
        	String num = game[0].replaceAll("[^0-9]", "");
        	String[] posib = game[1].split(";");
        	
        	for(int k = 0; k<posib.length&&posible; k++) {
        		String[] bolas = posib[k].split(",");
        		for(int l = 0; l<bolas.length&&posible; l++) {
        			// Primera parte
        			//posible = check(bolas[l]);
        			
        			// Segunda parte
        			Pair<Integer, Integer> este = check2(bolas[l]);
        			if(este.getLeft()==1) {
        				if(red<este.getRight()) red = este.getRight();
        			}
        			if(este.getLeft()==2) {
        				if(blue<este.getRight()) blue = este.getRight();
        			}
        			if(este.getLeft()==3) {
        				if(green<este.getRight()) green = este.getRight();
        			}
        		}
        	}
        	//if(posible) suma += Integer.parseInt(num);
        	int producto = red*blue*green;
        	System.out.println("Producto: "+producto);
        	suma += producto;
        	System.out.println("Suma: "+suma);
        }
    }
}
