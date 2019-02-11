package ssafy0211;

import java.util.ArrayList;
import java.util.Arrays;

public class Test03_baby {
	static void permutation(ArrayList<Integer> list, int d) {
		if(d ==6) {
			System.out.print(list);
			if((triplet(list, 0) ||run(list,0)) && (triplet(list, 3) || run(list, 3))) {
				System.out.println("baby gin!!");
			}else {
				System.out.println();
			}
			return;			
		}
		int listSize = list.size();
		for(int i=d; i<listSize; i++) {
			swap(list, i, d);
			permutation(list, d+1);
			swap(list, i, d);
		}
	}
	
	static void swap(ArrayList<Integer> list, int i, int j) {
		int tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}
	
	static boolean triplet(ArrayList<Integer> list, int s) {
		if(list.get(s) == list.get(s+1) && list.get(s+1) == list.get(s+2))
			return true;
		else 
			return false;
	}
	
	static boolean run(ArrayList<Integer> list, int s) {
		if(list.get(s)+1 == list.get(s+1) && list.get(s+1)+1 == list.get(s+2))
			return true;
		else 
			return false;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		arr.add(6);
		
		permutation(arr, 0);
	}
	
	
}

