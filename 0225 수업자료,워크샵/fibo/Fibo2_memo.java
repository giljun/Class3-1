package fibo;

public class Fibo2_memo {
	static int cnt;
	static int[] memo;

	public static void main(String[] args) {
		int n = 8;
		memo = new int[n + 1];
		memo[0] = 0;
		memo[1] = 1;

		System.out.println(fibo(n)); // 8번항
		System.out.println("호출횟수:"+cnt);
	}

	static int fibo(int n) {
		cnt++;
		if(n<2 || memo[n] != 0)
			return memo[n];
		
		memo[n] = fibo(n-1) + fibo(n-2);
		return memo[n];
	}
}




