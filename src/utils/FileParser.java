package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileParser {

	public static Set<String> parseStopwords(File file) throws FileNotFoundException, IOException{
		Set<String> stopwords = new HashSet<>();
		   try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       System.out.println(line);
			       stopwords.add(line);
			    }
			}
		   return stopwords;
		   
	   }
	
	
}
