package day1;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class day1 {
	
	public static void main (String[] args) throws IOException {
		String archivo = "/Users/xiao/git/ConcursoAoC2023/AoC2023/src/day1/input.txt";
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
            String[] sections = str1.split("\n");
            for(int i=0; i<sections.length; i++) {
            	System.out.println(sections[i]);
            }
	}
}