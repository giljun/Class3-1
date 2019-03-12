import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_7250_��Ʈ��_Ż��_������  {
	static int T;
	static int N, M, K;
	static char[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Point> queueF, queueS; // �ҹ��� ť, ��ı�̵� ť 
	static int vd, sd; // �Ǵ� �̵� �Ÿ�, ��ı �̵� �Ÿ� 
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static boolean[][] visitV, visitS; // �Ǵ� �湮 ��ǥ, ��ı �湮 ��ǥ 
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
					if (map[i][j] == 'S') { // ��ı �ʱ� ��ǥ
						visitS[i][j] = true;
						queueS.add(new Point(i,j,0));
					} else if (map[i][j] == 'F') { // �� enqueue
						queueF.add(new Point(i, j,0));
					} else if (map[i][j] == 'V') { // �Ǵ� �ʱ� ��ǥ
						vi = i;
						vj = j;
						visitV[vi][vj] = true;
					}
				}
			}
			vd = Integer.MAX_VALUE; // �Ǵ��� �ʱ� �̵��Ÿ�
			dfsV(vi, vj, 0); // �Ǵ� �ִܰŸ� ���ϱ�

			sd = 0;
			bfsS();
			
			int ans = (vd<=sd || sd==0) ? -1 : sd;
			System.out.println("#"+tc+" "+ans);
		}
	}

	static void bfsS() {
		while (!queueS.isEmpty()) {
			int fireCnt = queueF.size();
			for (int fc = 0; fc < fireCnt; fc++) { // ���� ���� �������� ��ı �̵�
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
			/////////////////�ҹ��� ���� �� 
			int moveCnt = queueS.size();
			for (int mc = 0; mc < moveCnt; mc++) { // ��ı �̵� ����. �̵� ������ �� �ʺ�켱 Ž��
				Point scott = queueS.poll();

				if(map[scott.i][scott.j] == 'E') {// ��ıŻ�� 
					sd = scott.dist;
					return;
				}
				
				if(map[scott.i][scott.j]=='W') // ���� �ִ� ���� W�̸� ��ų����Ʈ �Һ�
					sk--;
				
				if(map[scott.i][scott.j]=='A') // ���� �ִ� ���� A�̸� ��ų����Ʈ ���� 
					sk = K;
				
				for (int d = 0; d < 4; d++) {
					int ni = scott.i + di[d];
					int nj = scott.j + dj[d];

					if (ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] != 'X'
							&& map[ni][nj] != 'F') { // X�� F�� �ƴ� �����θ� �̵� ����.
						if(map[ni][nj] == 'A' || (map[ni][nj]=='W' && sk>0) && !visitS[ni][nj]){ // A or W �̵�����. W�̵��� ���� ��ų����Ʈ ������ Ȯ���ϰ� ��
							visitS[ni][nj] = true;
							queueS.add(new Point(ni, nj, scott.dist+1));
						}else if(map[ni][nj]=='W' && map[scott.i][scott.j]=='A'){ // A ���� W�� �̵��� �� ���� ���� ������ �ؼ� visit �˻����.
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
