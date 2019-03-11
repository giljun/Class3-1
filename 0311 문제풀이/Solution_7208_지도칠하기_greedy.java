

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_7208_지도칠하기_greedy {
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
				for (int j = 0; j < isUsed.length; j++) { // 주변에 인접국가가 제일 많은 국가 찾기
					if (map[i][j] == 1)
						tmp++;
				}
				if (isUsed[i] == false && max <= tmp) { // 처리한적 없는 인접 최대인 국가 찾았으면
					max = tmp;
					maxIdx = i;
				}
			}
			isUsed[maxIdx] = true; // 해당 국가에 처리완료 기록.
			changeColor(maxIdx); // 해당 국가의 색상 변경작업 시작
		}

	}

	public static void changeColor(int ii) {
		int[] isColored = new int[4];
		boolean flag = false;
		for (int j = 0; j < map.length; j++) {
			if (map[ii][j] == 1 && country[ii] == country[j]) { // 이웃이 많은 ii 국가 옆에 색깔이 같은 j번 국가 찾기
				flag = true;
				break;
			}
		}

		if (flag) { // ii번 국가가 이웃이 많은데 그 옆에 색깔 겹치는 국가가 있었다면.
			changeCnt++;
			for (int j = 0; j < country.length; j++) {
				if (map[ii][j] == 1) { // j번 국가가 사용한 색깔이 뭔지 그 색깔 카운트 해놓기.
					isColored[country[j]]++;
				}
			}
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			for (int i = 0; i < isColored.length; i++) { // 여태 쓴 색깔중 제일 안썼던 색깔 찾아서
				if (min > isColored[i]) {
					min = isColored[i];
					minIdx = i;
				}
			}
			country[ii] = minIdx; // 이웃이 많은 ii 국가의 색깔은 잘 안쓴 색깔로 칠하기.
		}
	}
}
