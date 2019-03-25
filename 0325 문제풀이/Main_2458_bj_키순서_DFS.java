import java.util.Scanner;

public class Main_2458_bj_Å°¼ø¼­_DFS {
	static int[] count;
	static Scanner sc = new Scanner(System.in);
	static int N, M, ans;
	static int[][] map;
	static boolean[] visit;

	public static void main(String[] args) {
		N = sc.nextInt();
		M = sc.nextInt();
		count = new int[N + 1];
		map = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) { // 
			int a = sc.nextInt();
			int b = sc.nextInt();
			map[a][b] = 1;
		}
		ans = 0;

		for (int i = 1; i < N + 1; i++) {
			visit = new boolean[N + 1];
			dfs_up(i, i);
			visit = new boolean[N + 1];
			dfs_down(i, i);
		}

		for (int i = 1; i <= N; i++) {
			if (count[i] == N - 1)
				ans++;
		}
		System.out.println(ans);

	}

	static void dfs_up(int cur, int start) {
		for (int i = 1; i < map.length; i++) {
			if (map[i][cur] == 1 && !visit[i]) {
				visit[i] = true;
				count[start]++;
				dfs_up(i, start);
			}
		}
	}

	static void dfs_down(int cur, int start) {
		for (int i = 1; i < map.length; i++) {
			if (map[cur][i] == 1 && !visit[i]) {
				visit[i] = true;
				count[start]++;
				dfs_down(i, start);
			}
		}
	}

}