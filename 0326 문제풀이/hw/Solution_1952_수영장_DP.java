package hw;

import java.util.Scanner;

public class Solution_1952_수영장_DP {
	static int T;
	static int[] price;
	static int[] plan;
	static int[][] month;
	static int[] dp;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		T = sc.nextInt();
		for(int tc=1; tc<=T; tc++) {
			price = new int[4];
			month = new int[4][13];
			plan = new int[13];
			dp = new int[13];
			
			for(int i=0; i<4; i++) // 4개 이용권 가격
				price[i] = sc.nextInt();
			
			for(int i=1; i<=12; i++) // 12개월 사용 계획
				plan[i] = sc.nextInt();
			
			for(int i=1; i<=12; i++) { // 일간 이용권과 월간 이용권 가격 각각 저장해두기
				month[0][i] = plan[i]*price[0];
				month[1][i] = plan[i]>0?price[1]:0;
			}

			for(int i=1; i<=12; i++) {  // 전달까지 가격에 이번달 일일권&월간권중 더 싼 가격 선택 
				dp[i] = Math.min(dp[i-1]+month[0][i], dp[i-1]+month[1][i]);
				if(i>2) // 3월 이후부터는 3개월 이용권이 더 싸면 갱신
					dp[i] = Math.min(dp[i], dp[i-3]+price[2]);
			}
			
			if(dp[12]>price[3]) // 연간 이용권이 더 싸면 갱신
				dp[12] = price[3];
			
			System.out.println("#"+tc+" "+dp[12]);
		}
	}
}
