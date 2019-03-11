

import java.util.Scanner;

public class Solution_7208_지도칠하기_순열 {
	static int T,N; // 국가 수 
	static int[][] map; // 국가 인접 정보
	static int[] color; // 기존 색상 정보
	static int[] fill;  // 새로 칠할 색상
	static int min; // 최소 변경횟수 저장할 변수.
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		T = sc.nextInt();
		
		for(int tc=1; tc<=T; tc++) {
			N = sc.nextInt();
			map = new int[N][N]; // 인접 행렬
			color = new int[N] ; // 기존 국가들 색깔
			fill = new int[N];	 // 새로 칠해볼 색깔 후보
			
			for(int n=0; n<N; n++) { // 기존 색깔 입력
				color[n] = sc.nextInt();
			}
			
			for(int i=0; i<N; i++) { // 국가 인접 행렬 입력
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			min = Integer.MAX_VALUE;
			
			dfs(0);
			
			System.out.println("#"+tc+" "+min);
		}
	}
	
	static boolean isOk() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && fill[i] == fill[j]) { // 인접한 나라에 같은 색이 칠해진 경우
					return false; // 안됨. 이 색상 배열은 못씀.
				}
			}
		}
		return true;
	}
	
	static void dfs(int cnt) {
		if(cnt==N) { // 뽑은 색깔 갯수 == 국가 갯수
			if(isOk()) {// 국가의 수만큼 뽑아놓은 색깔을 써도 괜찮은지 검사
				int tmp=0; // 써도 되는 경우 몇개의 국가 색깔을 변경해야 하는지 계산
				for(int i=0; i<N; i++) {
					if(color[i] != fill[i]) {
						tmp++;
					}
				}
				min = Math.min(tmp, min); // 예전에 계산해놓은 변경 횟수랑 비교해서 작은 카운트 선택
			}
			return;
		}
		
		for(int i=1; i<=4; i++) { // 사용가능한 색깔은 1번부터 4번까지임.
			fill[cnt] = i;
			dfs(cnt+1);
		}
	}
}









