package 월말0329;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3 {
	public static final int 상 = 0;
	public static final int 하 = 1;
	public static final int 좌 = 2;
	public static final int 우 = 3;
	public static int[] di = {-1, 1, 0, 0}; // 상하좌우
	public static int[] dj = { 0, 0,-1, 1};
	public static char[][] map;
	public static int R;
	public static int C;
	public static char[] pipe = {'|','-','+','1','2','3','4'}; // 순서바꾸면 안됨
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken()); // 1 <= R, C <= 25 
			C = Integer.parseInt(st.nextToken());
			map = new char[R+2][C+2]; // map의 바깥 테두리에는 0 들어있음.
			int mr = -1;
			int mc = -1;
			for (int i = 1; i <= R; i++) {
				String s = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = s.charAt(j-1);
					if (map[i][j] == 'M') { // 출발점
						mr = i;
						mc = j;
					}
				}
			}
	
			int dir = -1; // 방향
	//		빈칸을 나타내는 '.'
	//		블록을 나타내는 '|','-','+','1','2','3','4' // 순서바꾸면 안됨 남는파이프가 생길수있음
	//		출발점 'M'과 도착점 'Z'. 두 글자는 한 번만 주어진다
			
			// 유효범위 내에서 출발점 M에서 인접한 '.'이 아니고 도착점이 아닌 블럭으로 방향 찾기. 지도 바깥은 0 문자로 둘러져 있음.
			for (int i = 0; i < 4; i++) {
				if (map[mr+di[i]][mc+dj[i]] != '.' && map[mr+di[i]][mc+dj[i]] != 0 && map[mr+di[i]][mc+dj[i]] != 'Z') {
					dir = i;
					break;
				}
			}
			int[] result;
			if (dir != -1) { // M에 인접한 칸이 존재
				result = go(mr, mc, dir); // 지워진 블럭 
				
//				if (result[0]==1) { // 지워진 블럭을 찾았을 때 해당 위치에 블럭 다 놔보기
					for (int i = 0; i < pipe.length; i++) {
						map[result[1]][result[2]] = pipe[i];
						int[] xx = go(mr, mc, dir);
						if (xx[0]==2) {
							System.out.println("#"+testCase+" "+result[1]+" "+result[2]+" "+pipe[i]);
							break;
						}
					}
//				}
			}
		} // end of for testCase
	} // end of main
	
	//  리턴 int[] : {0실패/1지워짐/2도착성공, r, c, dir} 
	public static int[] go(int i, int j, int dir) {
		while(true) {
			i =i+di[dir]; // 좌표 이동
			j =j+dj[dir];
			switch (map[i][j]) {
			case '1': // 우하 블럭
				if (dir==상) dir = 우;
				else if (dir==좌) dir = 하;
				else return new int[] {0, i,j, dir}; // 실패
				break;
			case '2': // 상우 블럭
				if (dir==하) dir = 우;
				else if (dir==좌) dir = 상;
				else return new int[] {0, i,j, dir}; // 실패
				break;
			case '3': // 상좌 블럭
				if (dir==하) dir = 좌;
				else if (dir==우) dir = 상;
				else return new int[] {0, i,j, dir}; // 실패
				break;
			case '4': // 좌하 블럭
				if (dir==상) dir = 좌;
				else if (dir==우) dir = 하;
				else return new int[] {0, i,j, dir}; // 실패
				break;
			case '|':
				if (dir==좌 || dir==우) return new int[] {0, i,j, dir}; // 실패
				break;
			case '-':
				if (dir==상 || dir==하) return new int[] {0, i,j, dir}; // 실패
				break;
			case '+': // 방향이 안바뀜
				break;
			case 'Z': 
				return new int[] {2, i,j, dir}; // 도착 성공 
			case '.':
				return new int[] {1, i,j, dir}; // 지워졌음
			case 0 :
				return new int[] {0, i,j, dir}; // 실패
			}
		}
	}
} // end of class