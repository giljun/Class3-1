import java.util.Arrays;

public class NQueen {
	static int N = 8;
	static int[] result = new int[N];
	static int count=0;

	// N * N 체스판에 퀸을 배열하는 (서로 잡히지 않게) 경우의 수를 찾아보자
	public static void main(String[] args) {
		backTracking(0);
		System.out.println("카운트 = "+count);
	}
	
	static void backTracking(int d) {
		if(d == N) { // N개의 퀸 배치가 완료된 경우 실행되는 조건문
			System.out.println("퀸 배치 완료 : "+Arrays.toString(result));
			count++;
			return;
		}
		
		for(int i=0; i<N; i++) { // 현재 퀸을 0~N-1 까지 다 배치해보기			
			// 현재 i 값이 
			result[d] = i; // d번째 퀸의 위치를 i 칸으로 지정하고
			if(isOk(d)) { // 방금 위치시킨  i 칸이 적당하면 다음 퀸을 배치하러 가기
				backTracking(d+1);
			}
		}
	}
	
	static boolean isOk(int d) {
		for(int i=0; i<d; i++) { // 지금 테스트할 퀸 이전 퀸들의 위치들 보면서 괜찮은지 판단하기
			if(result[i] == result[d] || Math.abs(d-i) == Math.abs(result[d]-result[i]) ) {
				// 안되는 경우
				// 지금 퀸이랑 이전퀸의 열번호? 세로로 같은줄? 이면 안됨.
				// 지금 퀸이랑 대각선 위치상에 있으면 안됨.
				return false;
			}
		}		
		
		return true;
	}
}











