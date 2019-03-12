import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_7250_앤트맨_탈출_양유진  {
	static int T;
	static int N, M, K;
	static char[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Point> queueF, queueS; // 불번짐 큐, 스캇이동 큐 
	static int vd, sd; // 악당 이동 거리, 스캇 이동 거리 
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static boolean[][] visitV, visitS; // 악당 방문 좌표, 스캇 방문 좌표 
	static int vi,vj;
	static int sk;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String[] read = br.readLine().split(" ");
			N = Integer.parseInt(read[0]);
			M = Integer.parseInt(read[1]);
			K = Integer.parseInt(read[2]);
			sk = K;
			
			map = new char[N][M];
			visitV = new boolean[N][M];
			visitS = new boolean[N][M];
			queueF = new LinkedList<>();
			queueS = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 'S') { // 스캇 초기 좌표
						visitS[i][j] = true;
						queueS.add(new Point(i,j,0));
					} else if (map[i][j] == 'F') { // 불 enqueue
						queueF.add(new Point(i, j,0));
					} else if (map[i][j] == 'V') { // 악당 초기 좌표
						vi = i;
						vj = j;
						visitV[vi][vj] = true;
					}
				}
			}
			vd = Integer.MAX_VALUE; // 악당의 초기 이동거리
			dfsV(vi, vj, 0); // 악당 최단거리 구하기

			sd = 0;
			bfsS();
			
			int ans = (vd<=sd || sd==0) ? -1 : sd;
			System.out.println("#"+tc+" "+ans);
		}
	}

	static void bfsS() {
		while (!queueS.isEmpty()) {
			int fireCnt = queueF.size();
			for (int fc = 0; fc < fireCnt; fc++) { // 불이 먼저 번진다음 스캇 이동
				Point fire = queueF.poll();

				for (int d = 0; d < 4; d++) {
					int ni = fire.i + di[d];
					int nj = fire.j + dj[d];

					if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != 'W' && map[ni][nj] != 'X'
							&& map[ni][nj] != 'F' && map[ni][nj]!='E') {
						map[ni][nj] = 'F';
						queueF.add(new Point(ni, nj,0));
					}
				}
			}
			/////////////////불번짐 한턴 끝 
			int moveCnt = queueS.size();
			for (int mc = 0; mc < moveCnt; mc++) { // 스캇 이동 차례. 이동 가능한 곳 너비우선 탐색
				Point scott = queueS.poll();

				if(map[scott.i][scott.j] == 'E') {// 스캇탈출 
					sd = scott.dist;
					return;
				}
				
				if(map[scott.i][scott.j]=='W') // 현재 있는 곳이 W이면 스킬포인트 소비
					sk--;
				
				if(map[scott.i][scott.j]=='A') // 현재 있는 곳이 A이면 스킬포인트 리셋 
					sk = K;
				
				for (int d = 0; d < 4; d++) {
					int ni = scott.i + di[d];
					int nj = scott.j + dj[d];

					if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != 'X'
							&& map[ni][nj] != 'F') { // X와 F가 아닌 곳으로만 이동 가능.
						if(map[ni][nj] == 'A' || (map[ni][nj]=='W' && sk>0) && !visitS[ni][nj]){ // A or W 이동가능. W이동시 현재 스킬포인트 여유분 확인하고 가
							visitS[ni][nj] = true;
							queueS.add(new Point(ni, nj, scott.dist+1));
						}else if(map[ni][nj]=='W' && map[scott.i][scott.j]=='A'){ // A 에서 W로 이동할 때 갔던 곳도 가봐야 해서 visit 검사안함.
							queueS.add(new Point(ni, nj, scott.dist+1));
						}else if(map[ni][nj]=='E'){
							queueS.add(new Point(ni, nj, scott.dist+1));
						}
					}
				}
			}
		}
	}

	static void dfsV(int i, int j, int dist) {
		if (map[i][j] == 'E') {
			if (dist < vd)
				vd = dist;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nexti = i + di[d];
			int nextj = j + dj[d];
			if (nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && !visitV[nexti][nextj]
					&& (map[nexti][nextj] == 'A' || map[nexti][nextj] == 'F' || map[nexti][nextj] == 'E')) {
				visitV[nexti][nextj] = true;
				dfsV(nexti, nextj, dist + 1);
				visitV[nexti][nextj] = false;
			}
		}
	}

	static class Point {
		int i, j, dist;

		Point(int i, int j, int dist) {
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
	}
}
