package hw;

import java.util.Scanner;

public class Main_2283_jw_RGB���� {
	static int N;
	static int[][] price;
	static int[][] dp;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		N = sc.nextInt();
		
		price= new int[N+1][3];
		dp = new int[N+1][3];
		
		for(int i=1; i<=N; i++) { // ����ȣ i
			for(int j=0; j<3; j++) { // RGB j
				price[i][j] = sc.nextInt();
			}
		}
		
		for(int i=1; i<=N; i++) { // i�� ���� �ϳ��� ������Ű�鼭 ������ ����س�����.
			for(int j=0; j<3; j++) { // ���� i��° ���� j ������ ĥ�ϰ��� �Ѵٸ�
				int min = Integer.MAX_VALUE;
				for(int k=0; k<3; k++) { // ������ ���� 3������ j�� ������ ���� k�� ���鼭 ������ ã��
					if(k!=j && dp[i-1][k]<min) {
						min = dp[i-1][k];
					}
				}
				dp[i][j] = min+price[i][j];
			}
		}
		
		// ������ N�������� ĥ�� �����ݾ� 3���� �߿��� ���� ���� �� ã��. ������ ������ for���� 3���ۿ� ��� �� Math.min �ι�.
		int ans = Math.min(dp[N][0], dp[N][1]);
		ans = Math.min(ans, dp[N][2]);
		System.out.println(ans);
	}
	
}




