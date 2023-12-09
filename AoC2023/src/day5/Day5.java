package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import es.upm.aedlib.Position;
import es.upm.aedlib.indexedlist.ArrayIndexedList;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.*;
import es.upm.aedlib.*;
import es.upm.aedlib.fifo.*;
import es.upm.aedlib.positionlist.*;

public class Day5 {
	
	public static void main(String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day5/ej.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day5/input5.txt";
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
	    PositionList<Rango> semillas = rangoLista(lineas[0]);
	    System.out.println(semillas.toString());
	    IndexedList<Mapa> listaMapa = new ArrayIndexedList<Mapa>();
	    //PositionList<Mapa> listaMapa = new NodePositionList<Mapa>();
	    for(int i=1; i<lineas.length; i++) {
	    	System.out.println(lineas[i]);
	    	String[] estaLinea = lineas[i].split("\n");
	    	
	    	// Añadir cada linea a la lista para poder trabajar con ella.
	    	for(int j=1; j<estaLinea.length; j++) {
	    		// Parte 1
	    		//listaMapa.addLast(new Mapa(estaLinea[j].split("\\s")));
	    		
	    		
	    		// Parte 2
	    		listaMapa = addOrdenado(new Mapa(estaLinea[j].split("\\s")), listaMapa);
	    	}
	    	listaMapa = rellenar(listaMapa);
	    	semillas = sourceToDestino2(semillas, listaMapa);
	    	//semillas = sourceToDestino(semillas, listaMapa);
	    	System.out.println(semillas.toString());
	    	// Parte 1
	    	// listaMapa = new NodePositionList<Mapa>();
	    	
	    	// Parte 2
	    	System.out.println(listaMapa.toString());
	    	listaMapa = new ArrayIndexedList<Mapa>();
	    }
	    //System.out.println(menor(semillas));
	    System.out.println("El menor de todos es: "+menorRango(semillas).getMin());
	    
	}
	
	private static PositionList<Long> aLista (String s) {
		String[] aux = s.split("\\s"); PositionList<Long> lista = new NodePositionList<Long>();
		for (int i=0; i<aux.length; i++) {
			String soloDigit = aux[i].replaceAll("[^0-9]", "");
			if(!soloDigit.equals("")) {
				lista.addLast(Long.parseLong(soloDigit));
			}
		}
		return lista;
	}
	
	private static PositionList<Rango> rangoLista (String s) {
		String[] aux = s.split("\\s"); PositionList<Rango> lista = new NodePositionList<Rango>();
		Rango ran = new Rango();
		for(int i = 0, conteo = 0; i<aux.length; i++) {
			String soloDigit = aux[i].replaceAll("[^0-9]", "");
			if(!soloDigit.equals("")) {
				conteo++;
				if(i%2!=1) {
					ran.addMax(ran.getMin()+Long.parseLong(soloDigit));
					lista.addLast(ran);
					ran = new Rango();
				}
				else ran.addMin(Long.parseLong(soloDigit));
			}
		}
		return lista;
	}
	
	private static PositionList<Long> sourceToDestino (PositionList<Long> source, PositionList<Mapa> transf) {
		PositionList<Long> destino = new NodePositionList<Long>();
		for(Long i : source) {
			boolean fin = false;
			Position<Mapa> pos = transf.first();
			while(!fin&&pos!=null) {
				long dest = pos.element().rodea(i);
				if(dest>0) {
					fin=true;
					destino.addLast(dest);
				}
				else pos=transf.next(pos);
			}
			if(!fin) {
				destino.addLast(i);
			}
		}
		return destino;
	}
	
	private static PositionList<Rango> sourceToDestino2 (PositionList<Rango> source, IndexedList<Mapa> transf) {
		PositionList<Rango> destino = new NodePositionList<Rango>();
		for(Rango r : source) {
			PositionList<Rango> aux = new NodePositionList<Rango>();
			int i = 0;
			boolean completo = false; Mapa cursor = transf.get(i);
			while(!completo) {
				Rango sourceRango = cursor.getSourceRango();
				if(sourceRango.enRango(r.getMin())) {
					if(!sourceRango.enRango(r.getMax())) {
						aux.addLast(cursor.sourceADestino(new Rango(r.getMin(),sourceRango.getMax())));
						r = new Rango(sourceRango.getMax(),r.getMax());
						i++;
						if(i==transf.size()) {
							aux.addLast(r); completo = true;
						}
						else cursor = transf.get(i);
					}
					else {
						aux.addLast(cursor.sourceADestino(r)); completo = true;
					}
				}
				else {
					i++;
					if(i==transf.size()) {
						aux.addLast(r); completo = true;
					}
					else cursor = transf.get(i);
				}
			}
			for (Rango r2 : aux) {
				destino.addLast(r2);
			}
		}
		return destino;
	}
	
	private static Long menor (PositionList<Long> lista){
		Long menor=null;
		for(Long i : lista) {
			if(!(menor!=null&&menor<=i)) {
				menor = i;
			}
		}
		return menor;
	}
	
	private static Rango menorRango(PositionList<Rango> lista) {
		Position<Rango> pos = lista.first();
		Position<Rango> menor = pos;
		pos = lista.next(pos);
		while(pos!=null) {
			if(pos.element().comparator(menor.element())<0){
				menor = pos;
			}
			pos = lista.next(pos);
		}
		return menor.element();
	}
	
	private static IndexedList<Mapa> addOrdenado (Mapa map, IndexedList<Mapa> lista){
		boolean added = false;
		for(int i=0; i<lista.size()&&!added; i++) {
			if(map.comparator(lista.get(i).getSource())<0) {
				lista.add(i, map);
				added = true;
			}
		}
		if(!added) {
			lista.add(lista.size(), map);
		}
		return lista;
	}
	
	private static IndexedList copia (IndexedList lista) {
		IndexedList copia = new ArrayIndexedList();
		for(int i=0; i<lista.size(); i++) {
			copia.add(i, lista.get(i));
		}
		return copia;
	}
	
	private static IndexedList<Mapa> rellenar (IndexedList<Mapa> lista){
		IndexedList<Mapa> listAux = copia(lista); int j=0;
		if(lista.get(0).getSourceRango().getMin()!=0) {
			listAux.add(j, new Mapa((long)0,(long)0,lista.get(0).getSourceRango().getMin()));
			j++;
		}
		for(int i=0; i<lista.size()-1; i++,j++) {
			//System.out.println("Bucle:"+ i);
			long max1 = lista.get(i).getSourceRango().getMax();
			//System.out.println(max1);
			long min2 = lista.get(i+1).getSourceRango().getMin();
			if(max1!=min2) {
				listAux.add(j, new Mapa(max1,max1,min2-max1));
				j++;
			}
		}
		return listAux;
	}
}
