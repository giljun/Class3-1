import java.util.Scanner;

public class Main_1681_jw_해밀턴순환회로 {
	static int N;
	static boolean visit[];
	static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		int[][] map = new int[N + 1][N + 1];
		min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		visit = new boolean[N + 1];
		dfs(0, 1, map);
		System.out.println(min);
	}

	public static void dfs(int len, int n, int[][] map) {
		if (check()) { // 모든 고객집 방문 했는지 확인
			if (map[n][1] == 0) // 마지막 집에서 회사로 못돌아가면 의미없음.
				return;
			len += map[n][1]; // 마지막 배달지에서 회사로 돌아가는 거리 더하기.
			if (min > len) {
				min = len;
			}
			return;
		}
		if (min < len) {
			return;
		}
		for (int i = 2; i <= N; i++) { // 1번은 회사이므로 배달지 2번부터 순열 계산
			if (!visit[i] && map[n][i] != 0) {
				visit[i] = true;
				dfs(len+map[n][i], i, map);
				visit[i] = false;
			}
		}
	}

	public static boolean check() { // 모든 배달지 들렀는지 체크
		for (int i = 2; i <= N; i++) {
			if (!visit[i])
				return false;
		}
		return true;
	}

}
