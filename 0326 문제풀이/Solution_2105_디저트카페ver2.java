import java.util.Arrays;
import java.util.Scanner;

public class Solution_2105_디저트카페ver2 {
	static int[] dx = {1,-1,-1,1,1};
	static int[] dy = {1,1,-1,-1,1};
	static int N;
	static int[][] map;
	static boolean[] visit;
	static int res;
	static int startX;
	static int startY;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			visit = new boolean[101];
			res = -1;
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					map[y][x] = sc.nextInt();
				}
			}
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					Arrays.fill(visit, false);
					startX = x;
					startY = y;
					dfs(0,x,y,0);
				}
			}
			System.out.println("#"+tc+" "+res);
		}
	}
	public static void dfs(int depth,int x, int y,int cnt) {
		// depth가 방향값을 가짐. 0이라면 우하 // 1이라면 좌하 // 2라면 좌상 // 3이라면 우상
		if (depth!= 0 && startX == x && startY == y) { // depth가 0이 아니고 현재 좌표와 시작점이 같다면 사각형이 완성된 것
			res = Math.max(res, cnt);
		}
		if ( depth >= 4 ) {
			return ;
		}
		
		for(int dir=depth; dir<=depth+1; dir++) { // 방향 : 우하->좌하 좌하->좌상 좌상->우상 만 가능하게함.  
			int tempX = x+dx[dir];
			int tempY = y+dy[dir];
			if ( InRange(tempX,tempY) && !visit[map[tempY][tempX]] ) {
				visit[map[tempY][tempX]] = true;	
				if( dir == depth ) {
					 // 현재 진행 중인 방향과 진행 할 방향이 같다면 depth를 그대로 가져가서 방향을 유지해줌
					dfs(depth,tempX,tempY,cnt+1);
				} else {
					dfs(depth+1,tempX,tempY,cnt+1);
				}
				visit[map[tempY][tempX]] = false;	
			}
		}
		
	}
	
	public static boolean InRange(int x,int y) {
		if (x>=0 && y>=0 && x<N && y<N) {
			return true;
		}
		return false;
	}
}
