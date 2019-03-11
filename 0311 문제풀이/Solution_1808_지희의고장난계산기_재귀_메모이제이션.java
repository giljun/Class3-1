import java.util.Arrays;
import java.util.Scanner;

public class Solution_1808_�����ǰ��峭����_���_�޸������̼� {
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
			for (int i = 0; i < 10; i++) { // ������ �ִ� ��ư ���
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
		if (makeCnt(sx)) { // �־��� ��ư���� ������ �ִ� ���� ã�Ҵ�!
			memo[x] = sx.length() + 1;
			return memo[x]; // ���� �ڸ����� ������ ������ Ƚ�� +1 ���ֱ�.
		}

		if (memo[x] != INF) // ��ϵ� ��ư Ƚ���� �ִ� ���ڴ� �ٷ� �����ϱ�.
			return memo[x];

		int result = -1; // �Ʒ� for ������ x�� ����� Ž���ϴµ� ����� ���ų� ������� ��ư���� �� ������..�׷�-1
		for (int i = x / 2; i > 1; i--) {
			if (x % i == 0 && makeCnt(i + "")) { // n1*n2 = x�� �Ǵ� n1, n2 ã��
				int r1 = find(i); 		// x = (i) * (x/i) = n1 * n2
				int r2 = find(x / i);   

				if (r2 != -1) { // i�� ��ư���� ���� �� �ִ� �����ΰ� �˻�����. (x/i)�� ��ư���� ����������� Ȯ�� 
					result = r1 + r2; // Ȯ�Ή����� �� ī��Ʈ ���ϱ�
					if (result < memo[x]) // X�� ����� �ִ� �ּ�ī��Ʈ ����
						memo[x] = result;
				}
			}
		}
		return result;
	}

	// �־��� ���� ��ư���� �ش� ���� ������ �ִ��� �˻� �Լ�
	static boolean makeCnt(String tmp) {
		for (char c : tmp.toCharArray()) {
			if (btn[c - '0'] == false)
				return false;
		}
		return true;
	}
}







