import java.io.IOException;
import java.util.Scanner;

public class Solution_2105_����Ʈ_ī�� {
	//     2105. [���� SW �����׽�Ʈ] ����Ʈ ī�� DFS
	static int[][] map = new int[20][20];
	static boolean[] v = new boolean[100+1];
	static int N=0;
	static int startI, startJ;
	//  �밢�̵� ����
	static int[] dx = {-1,-1,1,1};
	static int[] dy = {-1,1,-1,1};
	static int max = 0;
	public static void main(String[] args)  throws IOException{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			max = -1;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					v = new boolean[101];
					startI = i;
					startJ = j;
//					��� �������� ��Ž
					dfs(i,j, 1,-1, 0); 
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
//	curI : ���� I��ġ, curJ : ���� J��ġ, len: �ִ��̵�����, dir : ���� , cnt : ���� ȸ��
	static void dfs(int curI, int curJ, int len, int dir, int cnt) {
		if(cnt > 4) return; //������ Ƚ���� 4�� �̻�Ǹ� ���ƿ��� ����
		v[map[curI][curJ]] = true;
//		�밢 4���� Ž��
		for(int i = 0; i < 4; i++) {
			int nx = curI + dx[i];
			int ny = curJ + dy[i];
//			������������ ���ƿ԰�, ȥ�ڵ����� �ƴϰ� �ּ��� ������ ������� �̵��� (len>=4)
			if(nx == startI && ny == startJ && cnt >= 3 && len >=4) {
				max = Math.max(max, len);
				break;
			}
//			������ ����ų� �湮�ߴ� ��ǥ�� �ٽ� �������� ����
			if( nx < 0 || nx >= N || ny < 0 || ny>= N || v[map[nx][ny]] ) {
				continue;
			}			
			int newCnt = cnt;
			if(i != dir) { // ������ �޶����� ȸ�� Ƚ�� �����ؼ� ����
				newCnt = cnt + 1;
			}
			dfs(nx, ny, len+1, i, newCnt);
		}
		v[map[curI][curJ]] = false;
	}
}
