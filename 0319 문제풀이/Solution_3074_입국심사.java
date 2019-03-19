

import java.util.Arrays;
import java.util.Scanner;

public class Solution_3074_입국심사 {
	static int T, N, M;
	static Scanner sc = new Scanner(System.in);
	static long[] checker;

	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			checker = new long[N];
			for(int i=0; i<N; i++)
				checker[i] = sc.nextInt();
			
			Arrays.sort(checker);
			long maxT = checker[checker.length-1] * M; // 제일 오래걸리는 시간 10^9 * 10^9 = 10^18
			
			long left = 0;
			long right = maxT;
			long ans=maxT; // 이보다 작은 값이 나타나면 갱신하면서 최소 필요시간까지 내려가기
			
			while(left<=right) {
				long mid = (left+right)/2;
				long sum=0; 
				for(long ck: checker) { // 주어진 mid 시간동안 모든 심사관이 처리하는 인원의 총합 계산
					sum += mid/ck;
				}
				
				if(M<=sum) { // 현재 mid가 M명을 처리하기에 충분한 시간인 경우. 
					if(mid<ans) { // ans에 있는 값보다 작은 mid이네? M명 처리는 가능하고?? 그렇다면 갱신!
						ans = mid;
					}					
					right = mid-1; // 시간이 충분하니까 시간을 줄여보자
				}else {
					left = mid+1; // 시간이 부족하니까 시간을 mid보다 큰시간으로 늘려보자
				}
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
}






