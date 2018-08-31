package com.day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumOfEleRepeatK {

	public static void main(String[] args) {
		System.out.println("Enter value of k");
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int count=0;
		int ktime =k;
		int sum =0;
		int arr[] = {9, 8, 8, 8, 10, 4,10,10,6,6,6};
		HashMap<Integer,Integer> hm = new HashMap<>();
		// put in hashmap and retreive all elements whose count is k
		// add all those element
		int len = arr.length;
		for(int i=0;i<len;i++)
		{
			if(hm.containsKey(arr[i]))
			{
				ktime = hm.get(arr[i]);
				hm.put(arr[i], ktime+1);
			}
			else
				hm.put(arr[i], 1);
		}
		ArrayList<Entry<Integer,Integer>> al = new ArrayList<>(hm.entrySet());
		al.stream().filter(a-> a.getValue()==k).forEach(System.out::println);
		List<Entry<Integer,Integer>> ga =al.stream().filter(a-> a.getValue()==k).collect(Collectors.toList());
		Iterator<Entry<Integer,Integer>> it = ga.stream().iterator();
		
		while(it.hasNext())
		{
			
			count = it.next().getKey();
			sum = sum+count;
			System.out.println("Value Repeated " + k +" times " + count);
		}
		System.out.println(sum);
	}
}
