import java.util.Scanner;

public class Solution_5656_�������� {
	static int T;
	static int ans;
	static int[][] map;
	static Scanner sc = new Scanner(System.in);
	static int N, W, H;
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt(); // ���� ������ Ƚ��
			W = sc.nextInt(); // cols
			H = sc.nextInt(); // rows
			
			map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ans = Integer.MAX_VALUE;
			
			dfs(map, N);
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void dfs(int[][] map, int n) { // W���� ���� �� �ѹ��� ���� ���ź���. 1�� ������ ���� �۾��� ���� ��Ϳ���.
		if(n==0) { // ������ �� ������ �� ���� ���� ���� ����
			int cnt=0;
			for(int i=0; i<H; i++) {
				for(int j=0; j<W; j++) {
					if(map[i][j]!=0)
						cnt++;
				}
			}			
			ans = Math.min(ans, cnt);
			return;
		}
		
		for(int j=0; j<W; j++) { // j�� ������ ����
			int[][] tmp = new int[H][W];
			deepCopy(map, tmp);
			
			for(int i=0; i<H; i++) { // i���� �Ʒ��� �̵��ϸ鼭
				if(tmp[i][j]!=0) { // 0�� �ƴ� ������ ã���� �ش� ��ġ�� ���� �ѹ� ������.
					bomb(tmp, i, j);
					break;
				}
			}
			// ������ ���� �ϳ� ������ �������� �߰��߰� ��������. �װ� �Ʒ��� ��������� �۾� �ʿ���.
			press(tmp);
			dfs(tmp, n-1); // ���� ������ ���� ������� ��Ű��
		}		
	}
	static void press(int[][] copy) { // �Ʒ��� ������?! �߰��� �ո� ���� �������.
		for(int i=H-1; i>=0; i--) { // �ؿ��� �Ʒ��� �ö���鼭 �ո����� 0 ã��
			for(int j=0; j<W; j++) {
				if(copy[i][j]==0) { // �շ��ֳ� ??
					for(int k=i; k>=0; k--) { // �׷� ���� i���� �����ִ� 0�� �ƴ� ���� �������!
						if(copy[k][j]!=0) { // ���� �����i �ຸ�� ���� k ���� ���� ã�Ҵ�
							copy[i][j] = copy[k][j];
							copy[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}
	
	static void bomb(int[][] copy, int i, int j) { // copy[i][j]�� �����ִ� ���ڸ�ŭ 4���� ���� ���߸���
		if(i<0 || j<0 || i>=H || j>=W) // ��ȿ���� ���� �ε����� �����߸�.
			return;
		
		int range = copy[i][j]; // ���� ���߸��� ĭ�� �����ִ� ���ڸ�ŭ ���� ��ġ���� �����¿� ���߸���
		copy[i][j] = 0;
		
		for(int d=1; d<range; d++) {
			bomb(copy, i-d, j); // ���� �������� ���߸���
			bomb(copy, i+d, j); // �Ʒ��� �������� ���߸���
			bomb(copy, i, j-d); // ����
			bomb(copy, i, j+d); // ������
		}		
		
	}
	
	static void deepCopy(int[][] arr, int[][] tmp){
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				tmp[i][j] = arr[i][j];
			}
		}
	}
	
}


