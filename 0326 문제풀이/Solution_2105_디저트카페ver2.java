import java.util.Arrays;
import java.util.Scanner;

public class Solution_2105_����Ʈī��ver2 {
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
		// depth�� ���Ⱚ�� ����. 0�̶�� ���� // 1�̶�� ���� // 2��� �»� // 3�̶�� ���
		if (depth!= 0 && startX == x && startY == y) { // depth�� 0�� �ƴϰ� ���� ��ǥ�� �������� ���ٸ� �簢���� �ϼ��� ��
			res = Math.max(res, cnt);
		}
		if ( depth >= 4 ) {
			return ;
		}
		
		for(int dir=depth; dir<=depth+1; dir++) { // ���� : ����->���� ����->�»� �»�->��� �� �����ϰ���.  
			int tempX = x+dx[dir];
			int tempY = y+dy[dir];
			if ( InRange(tempX,tempY) && !visit[map[tempY][tempX]] ) {
				visit[map[tempY][tempX]] = true;	
				if( dir == depth ) {
					 // ���� ���� ���� ����� ���� �� ������ ���ٸ� depth�� �״�� �������� ������ ��������
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
