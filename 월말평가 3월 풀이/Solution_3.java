package ����0329;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3 {
	public static final int �� = 0;
	public static final int �� = 1;
	public static final int �� = 2;
	public static final int �� = 3;
	public static int[] di = {-1, 1, 0, 0}; // �����¿�
	public static int[] dj = { 0, 0,-1, 1};
	public static char[][] map;
	public static int R;
	public static int C;
	public static char[] pipe = {'|','-','+','1','2','3','4'}; // �����ٲٸ� �ȵ�
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken()); // 1 <= R, C <= 25 
			C = Integer.parseInt(st.nextToken());
			map = new char[R+2][C+2]; // map�� �ٱ� �׵θ����� 0 �������.
			int mr = -1;
			int mc = -1;
			for (int i = 1; i <= R; i++) {
				String s = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = s.charAt(j-1);
					if (map[i][j] == 'M') { // �����
						mr = i;
						mc = j;
					}
				}
			}
	
			int dir = -1; // ����
	//		��ĭ�� ��Ÿ���� '.'
	//		����� ��Ÿ���� '|','-','+','1','2','3','4' // �����ٲٸ� �ȵ� ������������ ���������
	//		����� 'M'�� ������ 'Z'. �� ���ڴ� �� ���� �־�����
			
			// ��ȿ���� ������ ����� M���� ������ '.'�� �ƴϰ� �������� �ƴ� ������ ���� ã��. ���� �ٱ��� 0 ���ڷ� �ѷ��� ����.
			for (int i = 0; i < 4; i++) {
				if (map[mr+di[i]][mc+dj[i]] != '.' && map[mr+di[i]][mc+dj[i]] != 0 && map[mr+di[i]][mc+dj[i]] != 'Z') {
					dir = i;
					break;
				}
			}
			int[] result;
			if (dir != -1) { // M�� ������ ĭ�� ����
				result = go(mr, mc, dir); // ������ �� 
				
//				if (result[0]==1) { // ������ ���� ã���� �� �ش� ��ġ�� �� �� ������
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
	
	//  ���� int[] : {0����/1������/2��������, r, c, dir} 
	public static int[] go(int i, int j, int dir) {
		while(true) {
			i =i+di[dir]; // ��ǥ �̵�
			j =j+dj[dir];
			switch (map[i][j]) {
			case '1': // ���� ��
				if (dir==��) dir = ��;
				else if (dir==��) dir = ��;
				else return new int[] {0, i,j, dir}; // ����
				break;
			case '2': // ��� ��
				if (dir==��) dir = ��;
				else if (dir==��) dir = ��;
				else return new int[] {0, i,j, dir}; // ����
				break;
			case '3': // ���� ��
				if (dir==��) dir = ��;
				else if (dir==��) dir = ��;
				else return new int[] {0, i,j, dir}; // ����
				break;
			case '4': // ���� ��
				if (dir==��) dir = ��;
				else if (dir==��) dir = ��;
				else return new int[] {0, i,j, dir}; // ����
				break;
			case '|':
				if (dir==�� || dir==��) return new int[] {0, i,j, dir}; // ����
				break;
			case '-':
				if (dir==�� || dir==��) return new int[] {0, i,j, dir}; // ����
				break;
			case '+': // ������ �ȹٲ�
				break;
			case 'Z': 
				return new int[] {2, i,j, dir}; // ���� ���� 
			case '.':
				return new int[] {1, i,j, dir}; // ��������
			case 0 :
				return new int[] {0, i,j, dir}; // ����
			}
		}
	}
} // end of class