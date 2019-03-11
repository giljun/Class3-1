

import java.util.Scanner;

public class Solution_7208_����ĥ�ϱ�_���� {
	static int T,N; // ���� �� 
	static int[][] map; // ���� ���� ����
	static int[] color; // ���� ���� ����
	static int[] fill;  // ���� ĥ�� ����
	static int min; // �ּ� ����Ƚ�� ������ ����.
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			map = new int[N][N]; // ���� ���
			color = new int[N] ; // ���� ������ ����
			fill = new int[N];	 // ���� ĥ�غ� ���� �ĺ�
			
			for(int n=0; n<N; n++) { // ���� ���� �Է�
				color[n] = sc.nextInt();
			}
			
			for(int i=0; i<N; i++) { // ���� ���� ��� �Է�
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			min = Integer.MAX_VALUE;
			
			dfs(0);
			
			System.out.println("#"+tc+" "+min);
		}
	}
	
	static boolean isOk() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && fill[i] == fill[j]) { // ������ ���� ���� ���� ĥ���� ���
					return false; // �ȵ�. �� ���� �迭�� ����.
				}
			}
		}
		return true;
	}
	
	static void dfs(int cnt) {
		if(cnt==N) { // ���� ���� ���� == ���� ����
			if(isOk()) {// ������ ����ŭ �̾Ƴ��� ������ �ᵵ �������� �˻�
				int tmp=0; // �ᵵ �Ǵ� ��� ��� ���� ������ �����ؾ� �ϴ��� ���
				for(int i=0; i<N; i++) {
					if(color[i] != fill[i]) {
						tmp++;
					}
				}
				min = Math.min(tmp, min); // ������ ����س��� ���� Ƚ���� ���ؼ� ���� ī��Ʈ ����
			}
			return;
		}
		
		for(int i=1; i<=4; i++) { // ��밡���� ������ 1������ 4��������.
			fill[cnt] = i;
			dfs(cnt+1);
		}
	}
}









