package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import common.Pair;

public class TextAnalyser {

	public static TreeMap<String, Integer> getWordFrequencies(String text, Set<String> stopWords) {
		TreeMap<String, Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
		
		// Kick punctuations
		text = text.replaceAll("[!?,.')(]", "");
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
	
	public static TreeMap<String, Integer> getWordFrequencies (String text) {
		return getWordFrequencies(text, null);
	}
	
	public static List<Pair<String, Integer>> getMostFrequentWords(String text, int number) {
		return getMostFrequentWords(text, null, number);
	}
	
	public static List<Pair<String, Integer>> getMostFrequentWords(String text, Set<String> stopWords, int number) {
		TreeMap<String, Integer> map = getWordFrequencies(text, stopWords);
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
		return result;
	}
	
	public static Set<String> getIntercept(Set<String> s1, Set <String>s2){
	
		
		
		return null;
		
	}
	
	
}
