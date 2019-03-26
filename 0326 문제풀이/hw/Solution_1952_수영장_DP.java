package hw;

import java.util.Scanner;

public class Solution_1952_������_DP {
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
			
			for(int i=0; i<4; i++) // 4�� �̿�� ����
				price[i] = sc.nextInt();
			
			for(int i=1; i<=12; i++) // 12���� ��� ��ȹ
				plan[i] = sc.nextInt();
			
			for(int i=1; i<=12; i++) { // �ϰ� �̿�ǰ� ���� �̿�� ���� ���� �����صα�
				month[0][i] = plan[i]*price[0];
				month[1][i] = plan[i]>0?price[1]:0;
			}

			for(int i=1; i<=12; i++) {  // ���ޱ��� ���ݿ� �̹��� ���ϱ�&�������� �� �� ���� ���� 
				dp[i] = Math.min(dp[i-1]+month[0][i], dp[i-1]+month[1][i]);
				if(i>2) // 3�� ���ĺ��ʹ� 3���� �̿���� �� �θ� ����
					dp[i] = Math.min(dp[i], dp[i-3]+price[2]);
			}
			
			if(dp[12]>price[3]) // ���� �̿���� �� �θ� ����
				dp[12] = price[3];
			
			System.out.println("#"+tc+" "+dp[12]);
		}
	}
}
