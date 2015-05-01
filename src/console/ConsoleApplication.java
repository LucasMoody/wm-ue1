package console;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import utils.FileParser;
import utils.WebDocumentParser;
import common.Pair;
import core.TextAnalyser;

public class ConsoleApplication {

	public static void main(String[] args) throws IOException {
		String fileName = null, webAdress = null, command = null, stopWordFileName = null, text = null;
		boolean lowerCase = false;
		int numberOfUnits = -1;
		TextAnalyser ta;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-file")) {
				if (args.length - 1 >= i + 1)
					fileName = args[i + 1];
				i++;
			} else if (args[i].equals("-web")) {
				if (args.length - 1 >= i + 1)
					webAdress = args[i + 1];
				i++;
			} else if (args[i].equals("-command")) {
				if (args.length - 1 >= i + 1)
					command = args[i + 1];
				i++;
			} else if (args[i].equals("-stopWordFileName")) {
				if (args.length - 1 >= i + 1)
					stopWordFileName = args[i + 1];
				i++;
			} else if (args[i].equals("-lowerCase")) {
				lowerCase = true;
			} else if (args[i].equals("-maxListNumber")) {
				if (args.length - 1 >= i + 1)
					numberOfUnits = Integer.valueOf(args[i + 1]);
				i++;
			}
		}
		if (fileName != null) {
			text = FileParser.parseFile(fileName);
		} else if (webAdress != null) {
			text = WebDocumentParser.parseWebDocument(webAdress);
		}

		if (stopWordFileName != null) {
			Set<String> stopWords = FileParser.parseStopWords(stopWordFileName);
			ta = new TextAnalyser(text, stopWords);
		} else {
			ta = new TextAnalyser(text);
		}
		if (command != null && command.equals("getWordFrequencies")) {
			List<Pair<String, Integer>> pairs = ta
					.getMostFrequentWords(numberOfUnits);
			for (Pair<String, Integer> pair : pairs) {
				System.out.println(pair.getFirst() + "," + pair.getSecond());
			}
		} else if (command != null && command.equals("getCharFrequencies")) {
			List<Pair<Character, Integer>> pairs = ta.getMostFrequentChars(
					numberOfUnits, lowerCase);
			for (Pair<Character, Integer> pair : pairs) {
				System.out.println(pair.getFirst() + "," + pair.getSecond());
			}
		} else if (command != null && command.equals("getCharPairFrquencies")) {
			List<Pair<String, Integer>> pairs = ta.getMostFrequentCharPairs(
					numberOfUnits, lowerCase);
			for (Pair<String, Integer> pair : pairs) {
				System.out.println(pair.getFirst() + "," + pair.getSecond());
			}
		} if (command != null && command.equals("getWordFrequenciesPercentage")) {
			List<Pair<String, Integer>> pairs = ta
					.getMostFrequentWords(numberOfUnits);
			int sum = 0;
			for (Pair<String, Integer> pair : pairs) {
				sum += pair.getSecond();
			}
			System.out.println("word, absfrequency, relfrequency");
			for (Pair<String, Integer> pair : pairs) {
				System.out.println(pair.getFirst() + "," + pair.getSecond() + "," + String.format( "%.2f",100.d * (double) pair.getSecond()/ (double) sum));
			}
		}
	}
}
