package hw;

import java.util.Scanner;

public class Solution_1952_수영장_DFS {
	static int day;
	static int month;
	static int month3;
	static int year;
	static int[] arr;
	static int tot;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			day = sc.nextInt();
			month = sc.nextInt();
			month3 = sc.nextInt();
			year = sc.nextInt();
			arr = new int[13];
			for(int j=1; j<=12; j++) {
				arr[j] = sc.nextInt();
			}
			tot = year;
			dfs(1,0);
			System.out.println("#"+tc+" "+tot);
		}
	}
	
	static void dfs(int m,int money) {
		if ( m > 12 ) {
			tot = Math.min(tot, money);
			return ;
		}
		dfs(m+1,day*arr[m]+money); 	// 현재 월을 일일권으로 사용한 상태로 다음달을 재귀 넘기기
		dfs(m+1,month+money);	   	// 현재 월을 월간권으로 사용한 상태로 다음달을 재귀 넘기기
		dfs(m+3,month3+money);		// 현재월 포함 3개월은 3개월권 사용하고 그 이후를 재귀 넘기기
	}
}
