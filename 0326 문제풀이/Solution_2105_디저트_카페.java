import java.io.IOException;
import java.util.Scanner;

public class Solution_2105_디저트_카페 {
	//     2105. [모의 SW 역량테스트] 디저트 카페 DFS
	static int[][] map = new int[20][20];
	static boolean[] v = new boolean[100+1];
	static int N=0;
	static int startI, startJ;
	//  대각이동 방향
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
//					모든 정점에서 완탐
					dfs(i,j, 1,-1, 0); 
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
//	curI : 현재 I위치, curJ : 현재 J위치, len: 최대이동길이, dir : 방향 , cnt : 꺽은 회수
	static void dfs(int curI, int curJ, int len, int dir, int cnt) {
		if(cnt > 4) return; //꺽어진 횟수가 4번 이상되면 돌아오지 못함
		v[map[curI][curJ]] = true;
//		대각 4방향 탐색
		for(int i = 0; i < 4; i++) {
			int nx = curI + dx[i];
			int ny = curJ + dy[i];
//			도착지점으로 돌아왔고, 혼자돈것이 아니고 최소한 마름모 모양으로 이동함 (len>=4)
			if(nx == startI && ny == startJ && cnt >= 3 && len >=4) {
				max = Math.max(max, len);
				break;
			}
//			범위를 벗어나거나 방문했던 좌표는 다시 접근하지 않음
			if( nx < 0 || nx >= N || ny < 0 || ny>= N || v[map[nx][ny]] ) {
				continue;
			}			
			int newCnt = cnt;
			if(i != dir) { // 방향이 달라지면 회전 횟수 누적해서 전달
				newCnt = cnt + 1;
			}
			dfs(nx, ny, len+1, i, newCnt);
		}
		v[map[curI][curJ]] = false;
	}
}
