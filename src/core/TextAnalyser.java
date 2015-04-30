package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import common.Pair;

public class TextAnalyser {
	
	private String text;
	private Set<String> stopWords;


	public TextAnalyser(String text) {
		this.text = text;
	}
	
	public TextAnalyser(String text, Set<String> stopWords) {
		this.text = text;
		this.stopWords = stopWords;
	}

	public TreeMap<String, Integer> getWordFrequencies() {
		TreeMap<String, Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
		
		// Kick punctuations
		text = text.replaceAll("[!?,.\\-')(]", "");
		text = text.replaceAll("\"", "");
		
		// Extract words
		String[] words = text.split("\\s+");
		
		int count;
		for (int i = 0; i < words.length; i++) {
			if(stopWords != null && stopWords.contains(words[i])){
				continue;
			}	
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
	
	public TreeMap<Character, Integer> getCharFrequencies(boolean lowerCase) {
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
	
	public TreeMap<String, Integer> getCharPairFrequencies(boolean lowerCase) {
		//TreeMap<Character, Integer> map = new TreeMap<Character, Integer>(String.CASE_INSENSITIVE_ORDER);
		TreeMap<String, Integer> map = new TreeMap<>();
		text = text.replaceAll("\\s", "");
		int count;
		String s = "";
		for(int i = 0; i<text.length() - 1; i++) {
			s = lowerCase ? text.substring(i, i + 2).toLowerCase() : text.substring(i, i + 2);
			if(!map.containsKey(s)){
				map.put(s, 1);
			}else{
				count = map.get(s);
				count++;
				map.put(s, count);
			}
		}
		return map;
	}
	
	public List<Pair<String, Integer>> getMostFrequentWords(int number) {
		TreeMap<String, Integer> map = getWordFrequencies();
		List<Pair<String, Integer>> result = new ArrayList<>();
		for (String word : map.keySet()) {
			result.add(new Pair<String, Integer>(word, map.get(word)));
		}
		Collections.sort(result, new Comparator<Pair<String, Integer>>() {

			@Override
			public int compare(Pair<String, Integer> o1,
					Pair<String, Integer> o2) {http://www.gutenberg.org/
				return o2.getSecond() - o1.getSecond();
			}
		});
		return result.subList(0, number - 1);
	}
	
	public static Set<String> getIntercept(Set<String> s1, Set <String>s2){
	
		
		
		return null;
		
	}
	
	public List<Pair<Character, Integer>> getMostFrequentChars(int number, boolean lowerCase) {
		TreeMap<Character, Integer> map = getCharFrequencies(lowerCase);
		List<Pair<Character, Integer>> result = new ArrayList<>();
		for (Character word : map.keySet()) {
			result.add(new Pair<Character, Integer>(word, map.get(word)));
		}
		Collections.sort(result, new Comparator<Pair<Character, Integer>>() {

			@Override
			public int compare(Pair<Character, Integer> o1,
					Pair<Character, Integer> o2) {
				return o2.getSecond() - o1.getSecond();
			}
		});
		return result.subList(0, number - 1);
	}
	
	public List<Pair<String, Integer>> getMostFrequentCharPairs(int number, boolean lowerCase) {
		TreeMap<String, Integer> map = getCharPairFrequencies(lowerCase);
		List<Pair<String, Integer>> result = new ArrayList<>();
		for (String charPair : map.keySet()) {
			result.add(new Pair<String, Integer>(charPair, map.get(charPair)));
		}
		Collections.sort(result, new Comparator<Pair<String, Integer>>() {

			@Override
			public int compare(Pair<String, Integer> o1,
					Pair<String, Integer> o2) {
				return o2.getSecond() - o1.getSecond();
			}
		});
		return result.subList(0, number - 1);
	}
	
}
