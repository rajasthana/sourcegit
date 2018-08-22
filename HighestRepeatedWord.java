package com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;

public class HighestRepeatedWord {

	public static void main(String[] args) {
		//We can Read From InputStream,File,Or n=any other input
		//Sorting a hashmap on Value basis and returing the first index of array list formed is the highest repeated word in file.
		Scanner sc = new Scanner(System.in);
		String token="";
		int count = 0;
		System.out.println("Enter Text");
		String s = sc.nextLine();
		StringTokenizer dd = new StringTokenizer(s, " ");
		HashMap<String,Integer> hm = new HashMap<>();
		System.out.println(dd);
		while(dd.hasMoreTokens())
			{
				token = dd.nextToken();
				if(hm.containsKey(token))
				{
					count = hm.get(token);
					hm.put(token, count+1);
				}
				else
					hm.put(token, 1);
			}
		
		ArrayList<Entry<String,Integer>> al = new ArrayList<>(hm.entrySet());
		Collections.sort(al,new Comparator<Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
			
		});
		
		System.out.println(al.get(0));
	}
}

