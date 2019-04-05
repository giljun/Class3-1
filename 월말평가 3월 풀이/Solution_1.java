package ¿ù¸»0329;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_1 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			int n = Integer.parseInt(br.readLine().trim());
			int cnt[] = new int[10];
			while(n>0) {
				cnt[n%10]++;
				n /= 10;
			}
			cnt[6] = cnt[9] = (cnt[6]+cnt[9]+1)/2;
			Arrays.sort(cnt);
			System.out.println("#"+testCase+" "+cnt[9]);
		} // end of of testCase
	} // end of main
} // end of class