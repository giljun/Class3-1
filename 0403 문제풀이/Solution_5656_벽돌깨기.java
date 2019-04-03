import java.util.Scanner;

public class Solution_5656_벽돌깨기 {
	static int T;
	static int ans;
	static int[][] map;
	static Scanner sc = new Scanner(System.in);
	static int N, W, H;
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt(); // 구슬 던지는 횟수
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
	
	static void dfs(int[][] map, int n) { // W개의 열에 다 한번씩 구슬 떨궈보기. 1번 떨구면 다음 작업은 다음 재귀에게.
		if(n==0) { // 구슬을 다 떨궜을 때 남은 벽돌 갯수 세기
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
		
		for(int j=0; j<W; j++) { // j열 선택한 이후
			int[][] tmp = new int[H][W];
			deepCopy(map, tmp);
			
			for(int i=0; i<H; i++) { // i행을 아래로 이동하면서
				if(tmp[i][j]!=0) { // 0이 아닌 벽돌을 찾으면 해당 위치에 구슬 한번 떨구기.
					bomb(tmp, i, j);
					break;
				}
			}
			// 위에서 구슬 하나 던져서 벽돌들이 중간중간 깨져있음. 그거 아래로 가라앉히는 작업 필요함.
			press(tmp);
			dfs(tmp, n-1); // 다음 구슬은 다음 재귀한테 시키기
		}		
	}
	static void press(int[][] copy) { // 아래로 누르기?! 중간에 뚫린 공간 있을까봐.
		for(int i=H-1; i>=0; i--) { // 밑에서 아래로 올라오면서 뚫린공간 0 찾기
			for(int j=0; j<W; j++) {
				if(copy[i][j]==0) { // 뚫려있네 ??
					for(int k=i; k>=0; k--) { // 그럼 현재 i보다 위에있는 0이 아닌 벽돌 끌어내리기!
						if(copy[k][j]!=0) { // 현재 빈공간i 행보다 위의 k 에서 벽돌 찾았다
							copy[i][j] = copy[k][j];
							copy[k][j] = 0;
							break;
						}
					}
				}
			}
		}
	}
	
	static void bomb(int[][] copy, int i, int j) { // copy[i][j]에 적혀있는 숫자만큼 4방향 벽돌 깨뜨리기
		if(i<0 || j<0 || i>=H || j>=W) // 유효하지 않은 인덱스는 못깨뜨림.
			return;
		
		int range = copy[i][j]; // 현재 깨뜨리는 칸에 적혀있는 숫자만큼 현재 위치에서 상하좌우 깨뜨리기
		copy[i][j] = 0;
		
		for(int d=1; d<range; d++) {
			bomb(copy, i-d, j); // 위쪽 방향으로 깨뜨리기
			bomb(copy, i+d, j); // 아래쪽 방향으로 깨뜨리기
			bomb(copy, i, j-d); // 왼쪽
			bomb(copy, i, j+d); // 오른쪽
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


