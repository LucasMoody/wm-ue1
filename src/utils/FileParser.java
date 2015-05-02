package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileParser {

	public static Set<String> parseStopWords(File file) throws FileNotFoundException, IOException{
		Set<String> stopwords = new HashSet<>();
		   try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       stopwords.add(line);
			    }
			}
		   return stopwords;
		   
	   }
	
	public static Set<String> parseStopWords(String fileName) throws FileNotFoundException, IOException {
		return parseStopWords(new File(fileName));
	}
	
	public static String parseFile(File file) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}
	
	public static String parseFile(String fileName) throws IOException {
		return parseFile(new File(fileName));
	}
	
	
}
