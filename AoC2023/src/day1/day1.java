package day1;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class day1 {
	private static boolean contiene(String a) {
		return a.contains("one")||a.contains("two")||a.contains("three")||
				a.contains("four")||a.contains("five")||a.contains("seven")||
				a.contains("eight")||a.contains("six")||a.contains("nine");
	}
	private static boolean especial(String a) {
		return a.contains("oneight")||a.contains("fiveight")||a.contains("nineight")||
				a.contains("eightwo")||a.contains("eighthree")||a.contains("twone");
	}
	
	private static String cambiar(String a) {
		String[] num = new String[]{"one","two","three","four","five","six","seven","eight","nine"};
		for(int i=0; i<num.length; i++) {
			char xd = (char)(i+49);
			if(a.contains(num[i])) {
				String[] hola = a.split(num[i]);
				a=hola[0]+xd;
				if(hola.length>1) a+=hola[1];
			}
		}
		return a;
	}
	private static String cambiarEsp(String a) {
		String[] num = new String[]{"oneight","fiveight","nineight","eightwo","eighthree","twone"};
		String[] s = new String[]{"18","58","98","82","83","21"};
		for(int i=0; i<num.length; i++) {
			String xd = s[i];
			if(a.contains(num[i])) {
				String[] hola = a.split(num[i]); a=hola[0]+xd;
				if(hola.length>1) a+=hola[1];
			}
		}
		return a;
	}
	
	
	
	public static void main (String[] args) throws IOException {
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day1/ej.txt";
		//String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day1/inputomy.txt";
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day1/input2.txt";
		String str1="";
            try{
                FileReader f0 = new FileReader(archivo);
                BufferedReader br = new BufferedReader(f0);
                int i;
                while((i=br.read())!=-1){  
                    str1+=(char)i; 
                }
                br.close();
                f0.close();

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            int suma = 0;
            String[] sections = str1.split("\n");
            for(int i=0; i<sections.length; i++) {
            	System.out.println("\n"+sections[i]);
            	String esta = sections[i];
            	//String[] num = new String[]{"one","two","three","four","five","six","seven","eight","nine"};
            	char[] div = esta.toCharArray();
            	String unido = " ";
            	for(int j=0; j<div.length; j++) {
            		unido+=div[j];
	            	if(especial(unido)) {
	            		unido = cambiarEsp(unido);
	            	}
            	}
            	esta = unido;
            	div = esta.toCharArray();
            	unido = " ";
            	for(int j=0; j<div.length; j++) {
            		unido+=div[j];
	            	if(contiene(unido)) {
	            		unido = cambiar(unido);
	            	}
            	}
            	esta = unido;
            	char[] chars = esta.replaceAll("[^0-9]", "").toCharArray();
            	String hola = esta.replaceAll("[^0-9]", "");
            	int d = (int)chars[0]-48;
            	int u = (int)chars[chars.length-1]-48;
            	suma += 10*d+u;
            	System.out.println("Valores a int "+ d +""+u);
            	System.out.println("Suma "+suma+" Hola "+hola);
            	System.out.println("Esta "+ esta);
            }
	}
}