package core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


public class Sorter{
	
	public static void main (String[] args){
		TreeMap<String, Integer> map = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER);
		map.put("A",4);
		map.put("B",7);
		map.put("C",2);
		map.put("D",1);
		map.put("E",1);
		
		
		selecSort(map);
		System.out.println(list);
	}

	static LinkedList<Object> list = new LinkedList<Object>();
	
	public static LinkedList<Object> selecSort(TreeMap<String, Integer> map){
		

		while(map.size()!=0){
			if(list.isEmpty()){
				list.add(map.firstKey());
			}else{
				
				for(int i=0; i<list.size();i++){
					if(map.get(map.firstKey() > list.get(i) )){
						
					}
					
				}
				
				list.add(map.firstKey());
			}
			map.remove(map.firstKey());
		}
		return list;
		
		
	}
	
	*/
	}
	

