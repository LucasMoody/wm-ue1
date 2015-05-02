package core;

import java.util.Map;
import java.util.Set;

public class TextComparer {
	
	public Set<String> getTextIntercept(String text1 , String text2) {
		TextAnalyser ta1 = new TextAnalyser(text1);
		TextAnalyser ta2 = new TextAnalyser(text2);
		Map<String, Integer> ta1map = ta1.getWordFrequencies(false);
		Map<String, Integer> ta2map = ta2.getWordFrequencies(false);
		ta1map.keySet().retainAll(ta2map.keySet());
		return ta1map.keySet();
	}

}
