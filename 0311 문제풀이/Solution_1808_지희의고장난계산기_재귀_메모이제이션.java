import java.util.Arrays;
import java.util.Scanner;

public class Solution_1808_지희의고장난계산기_재귀_메모이제이션 {
	static Scanner sc = new Scanner(System.in);
	static int X;
	static boolean[] btn;
	static int T;
	static int[] memo;
	static final int INF = Integer.MAX_VALUE; // Infinity

	public static void main(String[] args) {
		T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			btn = new boolean[10];
			for (int i = 0; i < 10; i++) { // 누를수 있는 버튼 기록
				if (sc.nextInt() == 1)
					btn[i] = true;
			}
			X = sc.nextInt();

			memo = new int[X + 1];
			Arrays.fill(memo, INF);
			find(X);

			System.out.println("#" + t + " " + (memo[X] == INF ? -1 : memo[X]));
		}
	}

	static int find(int x) {
		String sx = x + "";
		if (makeCnt(sx)) { // 주어진 버튼으로 누를수 있는 숫자 찾았다!
			memo[x] = sx.length() + 1;
			return memo[x]; // 숫자 자릿수에 연산자 누르는 횟수 +1 해주기.
		}

		if (memo[x] != INF) // 기록된 버튼 횟수가 있는 숫자는 바로 리턴하기.
			return memo[x];

		int result = -1; // 아래 for 돌려서 x의 약수들 탐색하는데 약수가 없거나 약수들을 버튼으로 다 못눌러..그럼-1
		for (int i = x / 2; i > 1; i--) {
			if (x % i == 0 && makeCnt(i + "")) { // n1*n2 = x가 되는 n1, n2 찾기
				int r1 = find(i); 		// x = (i) * (x/i) = n1 * n2
				int r2 = find(x / i);   

				if (r2 != -1) { // i는 버튼으로 만들 수 있는 숫자인거 검사했음. (x/i)도 버튼으로 만들어지는지 확인 
					result = r1 + r2; // 확인됬으면 두 카운트 더하기
					if (result < memo[x]) // X를 만들수 있는 최소카운트 저장
						memo[x] = result;
				}
			}
		}
		return result;
	}

	// 주어진 계산기 버튼으로 해당 숫자 누를수 있는지 검사 함수
	static boolean makeCnt(String tmp) {
		for (char c : tmp.toCharArray()) {
			if (btn[c - '0'] == false)
				return false;
		}
		return true;
	}
}







