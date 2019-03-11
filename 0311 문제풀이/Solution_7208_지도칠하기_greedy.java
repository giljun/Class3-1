

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7208_����ĥ�ϱ�_greedy {
	public static int[] country;
	public static int[][] map;
	public static int changeCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			country = new int[N];
			for (int i = 0; i < N; i++) {
				country[i] = Integer.parseInt(st.nextToken());
			}
			map = new int[N][N];
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			changeCnt = 0;
			findMax();
			System.out.println("#" + tc + " " + changeCnt);
		}
	}

	public static void findMax() {
		int maxIdx = 0;
		int max = 0;
		boolean[] isUsed = new boolean[country.length];
		for (int k = 0; k < isUsed.length; k++) {
			for (int i = 0; i < isUsed.length; i++) {
				int tmp = 0;
				for (int j = 0; j < isUsed.length; j++) { // �ֺ��� ���������� ���� ���� ���� ã��
					if (map[i][j] == 1)
						tmp++;
				}
				if (isUsed[i] == false && max <= tmp) { // ó������ ���� ���� �ִ��� ���� ã������
					max = tmp;
					maxIdx = i;
				}
			}
			isUsed[maxIdx] = true; // �ش� ������ ó���Ϸ� ���.
			changeColor(maxIdx); // �ش� ������ ���� �����۾� ����
		}

	}

	public static void changeColor(int ii) {
		int[] isColored = new int[4];
		boolean flag = false;
		for (int j = 0; j < map.length; j++) {
			if (map[ii][j] == 1 && country[ii] == country[j]) { // �̿��� ���� ii ���� ���� ������ ���� j�� ���� ã��
				flag = true;
				break;
			}
		}

		if (flag) { // ii�� ������ �̿��� ������ �� ���� ���� ��ġ�� ������ �־��ٸ�.
			changeCnt++;
			for (int j = 0; j < country.length; j++) {
				if (map[ii][j] == 1) { // j�� ������ ����� ������ ���� �� ���� ī��Ʈ �س���.
					isColored[country[j]]++;
				}
			}
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			for (int i = 0; i < isColored.length; i++) { // ���� �� ������ ���� �Ƚ�� ���� ã�Ƽ�
				if (min > isColored[i]) {
					min = isColored[i];
					minIdx = i;
				}
			}
			country[ii] = minIdx; // �̿��� ���� ii ������ ������ �� �Ⱦ� ����� ĥ�ϱ�.
		}
	}
}
