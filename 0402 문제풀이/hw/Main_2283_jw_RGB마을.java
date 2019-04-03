package hw;

import java.util.Scanner;

public class Main_2283_jw_RGB마을 {
	static int N;
	static int[][] price;
	static int[][] dp;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		N = sc.nextInt();
		
		price= new int[N+1][3];
		dp = new int[N+1][3];
		
		for(int i=1; i<=N; i++) { // 집번호 i
			for(int j=0; j<3; j++) { // RGB j
				price[i][j] = sc.nextInt();
			}
		}
		
		for(int i=1; i<=N; i++) { // i번 집들 하나씩 증가시키면서 누적값 계산해나가기.
			for(int j=0; j<3; j++) { // 현재 i번째 집에 j 색상을 칠하고자 한다면
				int min = Integer.MAX_VALUE;
				for(int k=0; k<3; k++) { // 앞집의 색상 3가지중 j를 제외한 색상 k를 돌면서 작은값 찾기
					if(k!=j && dp[i-1][k]<min) {
						min = dp[i-1][k];
					}
				}
				dp[i][j] = min+price[i][j];
			}
		}
		
		// 마지막 N번집까지 칠한 누적금액 3가지 중에서 가장 작은 값 찾기. 색깔이 많으면 for지만 3개밖에 없어서 걍 Math.min 두번.
		int ans = Math.min(dp[N][0], dp[N][1]);
		ans = Math.min(ans, dp[N][2]);
		System.out.println(ans);
	}
	
}




