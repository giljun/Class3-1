package yangyu;

import java.util.Scanner;

public class Main_1053_jw_피보나치 {
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int N = sc.nextInt();
			if (N == -1)
				break;
			int[][] ans = fibo(N+1);
			System.out.println(ans[1][1]);
		}
	}

	static int[][] fibo(int n) {
		System.out.println(n);
		if (n == 1)
			return new int[][] { { 1, 1 }, { 1, 0 } };

		if (n % 2 == 1) {
			int[][] r = fibo(n / 2);
			return multiple(multiple(r, r),fibo(1));
		} else {
			int[][] r = fibo(n / 2);
			return multiple(r, r);
		}
	}

	static int[][] multiple(int[][] m1, int[][] m2) {
		int[][] mul = new int[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					mul[i][j] += (m1[i][k] * m2[k][j]);
					mul[i][j] %= 10000;
				}
			}
		}
		return mul;
	}
}
