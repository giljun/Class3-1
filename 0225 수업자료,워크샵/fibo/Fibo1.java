package fibo;

public class Fibo1 {
	static int cnt=0;
	public static void main(String[] args) {
		System.out.println(fibo(8));
		System.out.println("총 호출 횟수:" + cnt);
	}
	// 피보나치 수열의 n번째 항을 계산하는 메소드
	static int fibo(int n) {
		System.out.println("n:"+n);
		cnt++;
		if(n<2)
			return n;
		
		return fibo(n-1) + fibo(n-2);
	}
}
