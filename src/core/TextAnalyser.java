package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import common.Pair;

public class TextAnalyser {

	public static TreeMap<String, Integer> getWordFrequencies(String text, List<String> stopWords) {
		TreeMap<String, Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
		
		// Kick punctuations
		text = text.replaceAll("[!?,.-')(]", "");
		text = text.replaceAll("\"", "");
		
		// Extract words
		String[] words = text.split("\\s+");
		
		int count;
		for (int i = 0; i < words.length; i++) {
			if(!map.containsKey(words[i])){
				map.put(words[i], 1);
			}else{
				count = map.get(words[i]);
				count++;
				map.put(words[i], count);
			}
		}
		
		return map;
	}
	
	public static TreeMap<String, Integer> getWordFrequencies (String text) {
		return getWordFrequencies(text, null);
	}
	
	public static TreeMap<Character, Integer> getCharFrequencies(String text, boolean lowerCase) {
		//TreeMap<Character, Integer> map = new TreeMap<Character, Integer>(String.CASE_INSENSITIVE_ORDER);
		TreeMap<Character, Integer> map = new TreeMap<>();
		text = text.replaceAll("\\s", "");
		int count;
		for(int i = 0; i<text.length(); i++) {
			Character c = lowerCase ? Character.toLowerCase(text.charAt(i)) : text.charAt(i);
			if(!map.containsKey(c)){
				map.put(c, 1);
			}else{
				count = map.get(c);
				count++;
				map.put(c, count);
			}
		}
		return map;
	}
	
	public static List<Pair<String, Integer>> getMostFrequentWords(String text, int number) {
		return getWordFrequencies(text, null, number);
	}
	
	public static List<Pair<String, Integer>> getWordFrequencies(String text, List<String> stopWords, int number) {
		TreeMap<String, Integer> map = getWordFrequencies(text, stopWords);
		List<Pair<String, Integer>> result = new ArrayList<>();
		for (String word : map.keySet()) {
			result.add(new Pair<String, Integer>(word, map.get(word)));
		}
		Collections.sort(result, new Comparator<Pair<String, Integer>>() {

			@Override
			public int compare(Pair<String, Integer> o1,
					Pair<String, Integer> o2) {
				return o1.getSecond() - o2.getSecond();
			}
		});
		return result;
	}
	
	public static List<Pair<Character, Integer>> getCharFrequencies(String text, int number, boolean lowerCase) {
		TreeMap<Character, Integer> map = getCharFrequencies(text, lowerCase);
		List<Pair<Character, Integer>> result = new ArrayList<>();
		for (Character word : map.keySet()) {
			result.add(new Pair<Character, Integer>(word, map.get(word)));
		}
		Collections.sort(result, new Comparator<Pair<Character, Integer>>() {

			@Override
			public int compare(Pair<Character, Integer> o1,
					Pair<Character, Integer> o2) {
				return o1.getSecond() - o2.getSecond();
			}
		});
		return result;
	}
	
}
