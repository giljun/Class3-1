import java.util.Scanner;

public class Main_1681_jw_�ع��ϼ�ȯȸ�� {
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
		if (check()) { // ��� ���� �湮 �ߴ��� Ȯ��
			if (map[n][1] == 0) // ������ ������ ȸ��� �����ư��� �ǹ̾���.
				return;
			len += map[n][1]; // ������ ��������� ȸ��� ���ư��� �Ÿ� ���ϱ�.
			if (min > len) {
				min = len;
			}
			return;
		}
		if (min < len) {
			return;
		}
		for (int i = 2; i <= N; i++) { // 1���� ȸ���̹Ƿ� ����� 2������ ���� ���
			if (!visit[i] && map[n][i] != 0) {
				visit[i] = true;
				dfs(len+map[n][i], i, map);
				visit[i] = false;
			}
		}
	}

	public static boolean check() { // ��� ����� �鷶���� üũ
		for (int i = 2; i <= N; i++) {
			if (!visit[i])
				return false;
		}
		return true;
	}

}
