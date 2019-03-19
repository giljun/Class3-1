

import java.util.Arrays;
import java.util.Scanner;

public class Solution_3074_�Ա��ɻ� {
	static int T, N, M;
	static Scanner sc = new Scanner(System.in);
	static int[] checker;

	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			checker = new int[N];
			for(int i=0; i<N; i++)
				checker[i] = sc.nextInt();
			
			Arrays.sort(checker);
			long maxT = checker[checker.length-1] * M; // ���� �����ɸ��� �ð� 10^9 * 10^9 = 10^18
			
			long left = 0;
			long right = maxT;
			long ans=maxT; // �̺��� ���� ���� ��Ÿ���� �����ϸ鼭 �ּ� �ʿ�ð����� ��������
			
			while(left<=right) {
				long mid = (left+right)/2;
				long sum=0; 
				for(int ck: checker) { // �־��� mid �ð����� ��� �ɻ���� ó���ϴ� �ο��� ���� ���
					sum += mid/ck;
				}
				
				if(M<=sum) { // ���� mid�� M���� ó���ϱ⿡ ����� �ð��� ���. 
					if(mid<ans) { // ans�� �ִ� ������ ���� mid�̳�? M�� ó���� �����ϰ�?? �׷��ٸ� ����!
						ans = mid;
					}					
					right = mid-1; // �ð��� ����ϴϱ� �ð��� �ٿ�����
				}else {
					left = mid+1; // �ð��� �����ϴϱ� �ð��� mid���� ū�ð����� �÷�����
				}
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
}






