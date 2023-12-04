package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;

public class day3 {
	
	public static void recorrer(Pair<Integer, Integer> coord, PositionList<Num> elem) {
		Position<Num> pos = elem.first();
		
		while(pos!=null) {
			if(!pos.element().signo()) {
				pos.element().utilizar(coord);

			}
			pos = elem.next(pos);
		}
		
	}
	
	public static Pair<Integer, PositionList<Num>> recorrer2(Pair<Integer, Integer> coord, PositionList<Num> elem) {
		Position<Num> pos = elem.first(); int suma =0;
		PositionList<Num> lista = new NodePositionList<Num>();
		int listar = 0;
		while(pos!=null) {
			if(!pos.element().signo()) {
				listar+=pos.element().utilizar2(coord);
				if(listar!=0) {
					lista.addLast(pos.element());
					suma++; listar=0;
				}
			}
			pos = elem.next(pos);
		}
		return new Pair<Integer, PositionList<Num>>(suma,lista);
	}
	
	public static boolean containsDigit(String input) {
        // Use contains to check if the string contains any digit from 0 to 9
        for (char c = '0'; c <= '9'; c++) {
            if (input.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }
	
	public static boolean containsNonDigit(String input) {
        // Use contains to check if the string contains any non-digit character
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return true;  // Found a non-digit character
            }
        }
        return false;  // No non-digit characters found
    }
	
	public static boolean containsNonA(String input) {
        // Use contains to check if the string contains any non-digit character
        for (char c : input.toCharArray()) {
            if (c!='a') {
                return true;  // Found a non-digit character
            }
        }
        return false;  // No non-digit characters found
    }
	
	public static void main (String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day3/ej.txt";
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day2/input.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day3/input3.txt";
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
        PositionList<PositionList<Num>> lista = new NodePositionList<PositionList<Num>>();
        for(int i=0; i<sections.length; i++) {
        	
        	// Ciclo principal en cada línea
        	
        	System.out.println(sections[i]);
        	PositionList<Num> linea = new NodePositionList<Num>();
        	String este = sections[i].replaceAll("\\.", "a");
        	char[] chars = este.toCharArray();
        	Num aux = new Num();
        	String str = "";
        	
        	
        	for(int j=0; j<chars.length; j++) {
        		str += ""+ chars[j];
        		//System.out.println(str+" "+containsDigit(str));
        		if(containsDigit(str)) {
        			String num = ""; int j2 = j;
        			boolean fin = false;
        			
        			while(!fin&&j2<chars.length) {
        				num = num + "" + chars[j2];
        				//System.out.println(num+" "+containsNonDigit(num));
        				fin = (containsNonDigit(num));
        				if(!fin) {
        					aux.addCoord(new Pair<Integer, Integer>(i,j2));
        					j2++;
        				}
        				else {
        					num = num.replaceAll("[^0-9]", "");
        					//System.out.println(num);
        					
        				}
        			}
        			j2--; j=j2; str=""; linea.addLast(aux); aux.darValor(Integer.parseInt(num)); 
        			//System.out.println("Valor "+ aux.getValor()); 
        			aux = new Num();
        		}
        		//System.out.println(containsNonA(str));
        		
        		// Parte 1
//        		else if(containsNonA(str)&&str.toCharArray().length>0) {
//        			//System.out.println("Signo");
//        			aux = new Num(true, new Pair<Integer, Integer>(i,j));
//        			str=""; linea.addLast(aux); aux = new Num();
//        		}
        		
        		// Parte 2
        		else if(str.contains("*")) {
        			aux = new Num(true, new Pair<Integer, Integer>(i,j));
        			str=""; linea.addLast(aux); aux = new Num();
        		}
        		
        		
        	}
        	lista.addLast(linea);
        }
        
        // Paso 2: leer la lista y hacer cosas con los simbolos
        System.out.println('\n'+"Leer lista");
        
        Position<PositionList<Num>> pos = lista.first();
        
        while(pos!=null) {
        	
        	PositionList<Num> elem = pos.element();
        	Position<Num> pospos = elem.first();
        	
        	while(pospos!=null) {
        		
        		int cant = 0;
        		Num elemelem = pospos.element();
        		
        		if(elemelem.signo()) {
        			
        			Pair<Integer, Integer> coord = elemelem.coord1();
        			PositionList<Num> ant = lista.prev(pos).element();
        			PositionList<Num> post = lista.next(pos).element();
        			double prod = 1;
        			Pair<Integer, PositionList<Num>> antP = null;
        			Pair<Integer, PositionList<Num>> elemP = null;
        			Pair<Integer, PositionList<Num>> postP = null;
        			Position<Num> posAux = null;
        			
        			// Parte 2
        			if(ant!=null) antP=recorrer2(coord, ant);
        			elemP=recorrer2(coord, elem);
        			if(post!=null) postP=recorrer2(coord, post);
        			
        			if(antP!=null) {
        				//System.out.println(antP.getLeft()+", "+antP.getRight().toString());
        				cant+=antP.getLeft();
        				posAux = antP.getRight().first();
        				while(posAux!=null) {
        					prod = prod*posAux.element().getValor();
        					posAux = antP.getRight().next(posAux);
        				}
        			}
        			//System.out.println(elemP.getLeft()+", "+elemP.getRight().toString());
        			cant+=elemP.getLeft();
    				posAux = elemP.getRight().first();
    				while(posAux!=null) {
    					prod = prod*posAux.element().getValor();
    					posAux = elemP.getRight().next(posAux);
    				}
    				if(postP!=null) {
    					//System.out.println(postP.getLeft()+", "+postP.getRight().toString());
        				cant+=postP.getLeft();
        				posAux = postP.getRight().first();
        				while(posAux!=null) {
        					prod = prod*posAux.element().getValor();
        					posAux = postP.getRight().next(posAux);
        				}
        			}
    				if(cant==2) {
    					suma+=prod;
    				}
        			
        			// Parte 1
//        			if(ant!=null) recorrer(coord, ant);
//        			recorrer(coord, elem);
//        			if(post!=null) recorrer(coord,post);
        			
        		}
        		pospos = elem.next(pospos);
        	}
        	pos = lista.next(pos);
        }
        
        System.out.println('\n'+"Sumar");
//        pos = lista.first();
//        while(pos!=null) {
//        	PositionList<Num> elem = pos.element();
//        	Position<Num> pospos = elem.first();
//        	
//        	while(pospos!=null) {
//        		Num elemelem = pospos.element();
//        		
//        		if(elemelem.getValor()!=null) 
//        			System.out.println(elemelem.getValor() +" "+ elemelem.utilizado());
//        		else System.out.println("*");
//        		
//        		if(!elemelem.signo()&&elemelem.utilizado()) {
//        			suma+=elemelem.getValor();
//        		}
//        		pospos = elem.next(pospos);
//        	}
//        	pos = lista.next(pos);
//        }
        
        System.out.println("\n"+suma);
	}
}
