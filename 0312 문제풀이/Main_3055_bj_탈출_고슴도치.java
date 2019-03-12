
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_3055_bj_Ż��_����ġ {
	static int R, C;
	static char[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Queue<Point> queueW, queueS;
	static int sd;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static boolean[][] visitS, visitW;

	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] read = br.readLine().split(" ");
		R = Integer.parseInt(read[0]);
		C = Integer.parseInt(read[1]);

		map = new char[R][C];
		visitW = new boolean[R][C]; // ���� �湮 ���
		visitS = new boolean[R][C]; // ����ġ�� �湮 ���
		queueW = new LinkedList<>();
		queueS = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') { // ����ġ �ʱ� ��ǥ
					visitS[i][j] = true;
					queueS.add(new Point(i, j, 0));
				} else if (map[i][j] == '*') { // �� �ʱ���ǥ�� enqueue
					visitW[i][j] = true;
					queueW.add(new Point(i, j, 0));
				}
			}
		}

		sd = 0;
		bfsS();

		if (sd == 0)
			System.out.println("KAKTUS");
		else
			System.out.println(sd);
	}

	static void bfsS() {
		while (!queueS.isEmpty()) {
			int waterCnt = queueW.size();
			for (int wc = 0; wc < waterCnt; wc++) { // ���� ���� �������� ����ġ �̵�
				Point water = queueW.poll();

				for (int d = 0; d < 4; d++) { // ���� �����¿��� ����� �ƴϰ� ���� �ƴ� ������ ������
					int ni = water.i + di[d];
					int nj = water.j + dj[d];

					if (ni >= 0 && ni < R && nj >= 0 && nj < C && map[ni][nj] != 'D' && map[ni][nj] != 'X'
							&& !visitW[ni][nj]) { // ��������θ� �� ����
						visitW[ni][nj] = true;
						map[ni][nj] = '*';
						queueW.add(new Point(ni, nj, 0));
					}
				}
			}
			////////////// �� ���� ���� �Ϸ�
			int moveCnt = queueS.size();
			for (int mc = 0; mc < moveCnt; mc++) { // ����ġ�� �̹� ���ʿ� �̵� ������ �� Ž��
				Point scott = queueS.poll();

				if (map[scott.i][scott.j] == 'D') { // ����� ���������� �Ÿ� ����ϰ� ��
					sd = scott.dist;
					return;
				}

				for (int d = 0; d < 4; d++) { // ������̳� .���θ� �̵� ����.
					int ni = scott.i + di[d];
					int nj = scott.j + dj[d];

					if (ni >= 0 && ni < R && nj >= 0 && nj < C && (map[ni][nj] == 'D' || map[ni][nj] == '.')
							&& !visitS[ni][nj]) {
						visitS[ni][nj] = true;
						queueS.add(new Point(ni, nj, scott.dist + 1));
					}
				}
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
