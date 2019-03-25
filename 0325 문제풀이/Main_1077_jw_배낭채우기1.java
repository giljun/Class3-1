

import java.util.Scanner;

public class Main_1077_jw_¹è³¶Ã¤¿ì±â1 {
	static int N, W;
	static Scanner sc = new Scanner(System.in);
	static int[][] dp;
	static Jewel[] jewels;
	
	public static void main(String[] args) {
		N = sc.nextInt();
		W = sc.nextInt();
		
		dp = new int[N+1][W+1];
		jewels = new Jewel[N+1];
		
		for(int i=1; i<=N; i++) {
			jewels[i] = new Jewel(sc.nextInt(), sc.nextInt());
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=0; j<=W; j++) {
				if(j<jewels[i].weight)
					dp[i][j] = dp[i-1][j];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-jewels[i].weight]+jewels[i].price);
			}
		}
		
//		for(int i=0; i<=N; i++) {
//			for(int j=0; j<=W; j++) {
//				System.out.printf("%3d ",dp[i][j]);
//			}
//			System.out.println();
//		}
		
		System.out.println(dp[N][W]);
	}
	
	static class Jewel{
		int weight, price;
		Jewel(int weight, int price){
			this.weight = weight;
			this.price = price;			
		}
	}
}
