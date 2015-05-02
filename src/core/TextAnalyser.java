package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import common.Pair;

public class TextAnalyser {
	
	private String text;
	private Set<String> stopWords;
	private static final String STOP_CHARACTERS = "[!?,.'/)(\"\\p{C}]";


	public TextAnalyser(String text) {
		this.text = text;
	}
	
	public TextAnalyser(String text, Set<String> stopWords) {
		this.text = text;
		this.stopWords = stopWords;
	}

	public TreeMap<String, Integer> getWordFrequencies(boolean lowerCase) {
		TreeMap<String, Integer> map = new TreeMap<String,Integer>();
		Set<String> stopWords = new HashSet<String>();
		if (lowerCase && this.stopWords != null) {
			for (String stwo : this.stopWords) {
				stopWords.add(stwo.toLowerCase());
			}
		} else {
			stopWords = this.stopWords;
		}
		
		// Kick punctuations
		text = text.replaceAll(STOP_CHARACTERS, " ");
		
		// Extract words
		String[] words = text.split("\\s+");
		
		int count;
		for (int i = 0; i < words.length; i++) {
			String curWord = lowerCase ? words[i].toLowerCase() : words[i];
			if(stopWords != null && stopWords.contains(curWord)){
				continue;
			}	
			if(!map.containsKey(curWord)){
				map.put(curWord, 1);
			}else{
				count = map.get(curWord);
				count++;
				map.put(curWord, count);
			}
		}
		
		return map;
	}
	
	public TreeMap<Character, Integer> getCharFrequencies(boolean lowerCase) {
		//TreeMap<Character, Integer> map = new TreeMap<Character, Integer>(String.CASE_INSENSITIVE_ORDER);
		TreeMap<Character, Integer> map = new TreeMap<>();
		text = text.replaceAll("[\\s"+STOP_CHARACTERS+"]", "");
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
		text = text.replaceAll(STOP_CHARACTERS, "");
		String[] words = text.split("\\s+");
		String s = "";
		int count = 0;
		for (int i = 0; i<words.length; i++) {
			String curWord = words[i];
			if (curWord == null || curWord.equals(""))
				continue;
			for (int j = 0; j<curWord.length() -1; j++) {
				s = lowerCase ? curWord.substring(j, j + 2).toLowerCase() : curWord.substring(j, j + 2);
				if(!map.containsKey(s)){
					map.put(s, 1);
				}else{
					count = map.get(s);
					count++;
					map.put(s, count);
				}
			}
		}
		return map;
	}
	
	public List<Pair<String, Integer>> getMostFrequentWords(int number, boolean lowerCase) {
		int endPosition = number;
		TreeMap<String, Integer> map = getWordFrequencies(lowerCase);
		if (number == -1) {
			endPosition = map.keySet().size();
		}
		List<Pair<String, Integer>> result = new ArrayList<>();
		for (String word : map.keySet()) {
			result.add(new Pair<String, Integer>(word, map.get(word)));
		}
		Collections.sort(result, new Comparator<Pair<String, Integer>>() {

			@Override
			public int compare(Pair<String, Integer> o1,
					Pair<String, Integer> o2) {
				return o2.getSecond() - o1.getSecond();
			}
		});
		return result.subList(0, endPosition);
	}
	
	public List<Pair<Character, Integer>> getMostFrequentChars(int number, boolean lowerCase) {
		int endPosition = number;
		TreeMap<Character, Integer> map = getCharFrequencies(lowerCase);
		if (number == -1) {
			endPosition = map.keySet().size();
		}
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
		return result.subList(0, endPosition);
	}
	
	public List<Pair<String, Integer>> getMostFrequentCharPairs(int number, boolean lowerCase) {
		int endPosition = number;
		TreeMap<String, Integer> map = getCharPairFrequencies(lowerCase);
		if (number == -1) {
			endPosition = map.keySet().size();
		}
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
		return result.subList(0, endPosition);
	}
	
}
