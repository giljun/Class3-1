

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution_1808_지희의고장난계산기_버튼후보숫자미리만들기 {
	static boolean[] visit;
	static int N;
	static ArrayList<Integer> list;
	static int min;
	static boolean[] numpad;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			numpad = new boolean[10];
			for (int i = 0; i < 10; i++) { // 버튼 입력받기
				int temp = sc.nextInt();
				if (temp == 1) {
					numpad[i] = true;
				}
			}
			list = new ArrayList<>(); // 버튼으로 만들 수 있는 숫자 저장 리스트
			N = sc.nextInt();
			init(); // 리스트에 후보숫자 채우기
			Collections.sort(list, new Comparator<Integer>() { // 내림차순 정렬을 위한 비교자
				@Override
				public int compare(Integer o1, Integer o2) {
					if ( o1 < o2 ) {
						return 1;
					} else if (o1>o2) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			min = Integer.MAX_VALUE;
			dfs(N, 0, 0);
			if (min == Integer.MAX_VALUE) {
				min = -1;
			}
			if ( N == 1 ) {
				min = 2;
			}
			System.out.println("#" + tc + " " + min);
		}
	}
	public static void init() {
		String s = String.valueOf(N);
		for (int i = 1; i < 10; i++) { // 일단 누를수 있는 버튼 1개로 만들 숫자 저장해놓고
			if (numpad[i]) {
				list.add(i);
			}
		}
		for(int k=1; k<s.length(); k++) { // 만들숫자 X 자릿수보다 큰 자리 후보는 만들필요 없음
			int size=list.size();
			for(int i=0; i<size; i++) {
				for (int j = 0; j < 10; j++) {
					if (numpad[j]) { // 리스트에 들어있는 숫자들 뒤에 버튼 숫자 붙여서 후보숫자 만들기
						String temp = list.get(i)+""+j; 
						int input = Integer.parseInt(temp);
						if( input <= N) { // 만들 숫자보다 큰 숫자는 넣을 필요 없음.
							list.add(input); // 만든 후보숫자 넣기		 
						}
					}
				}
			}
		}
	}
	public static void dfs(int N, int cnt, int index) {
		if ( min <= cnt ) { // 이미 구한 min 보다 cnt가 더 갔으면 더 해볼 필요 없음.
			return;
		}
		if (N == 1) { // N이 1이면 숫자 만들었음. 기존 min과 현재까지 cnt중 최소값 저장.
			min = min < cnt ? min : cnt;
			return;
		}

		for (int i = index; i < list.size(); i++) { // 버튼으로 누를 수 있는 후보들
			int temp = list.get(i); // 후보 리스트의 숫자 뽑아서 temp
			if ((N / temp) * temp == N && temp != 1) { // N = n1*n2 = temp*(N/temp) 
				// temp는 이미 버튼으로 만들어지는 숫자임. N/temp만 재귀 돌려보면 됨.
				dfs(N/temp, cnt + (temp+"".length() + 1), i);
			}
		}
	}
}
