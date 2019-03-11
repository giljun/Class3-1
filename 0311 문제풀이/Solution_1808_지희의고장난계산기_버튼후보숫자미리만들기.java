

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution_1808_�����ǰ��峭����_��ư�ĺ����ڹ̸������ {
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
			for (int i = 0; i < 10; i++) { // ��ư �Է¹ޱ�
				int temp = sc.nextInt();
				if (temp == 1) {
					numpad[i] = true;
				}
			}
			list = new ArrayList<>(); // ��ư���� ���� �� �ִ� ���� ���� ����Ʈ
			N = sc.nextInt();
			init(); // ����Ʈ�� �ĺ����� ä���
			Collections.sort(list, new Comparator<Integer>() { // �������� ������ ���� ����
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
		for (int i = 1; i < 10; i++) { // �ϴ� ������ �ִ� ��ư 1���� ���� ���� �����س���
			if (numpad[i]) {
				list.add(i);
			}
		}
		for(int k=1; k<s.length(); k++) { // ������� X �ڸ������� ū �ڸ� �ĺ��� �����ʿ� ����
			int size=list.size();
			for(int i=0; i<size; i++) {
				for (int j = 0; j < 10; j++) {
					if (numpad[j]) { // ����Ʈ�� ����ִ� ���ڵ� �ڿ� ��ư ���� �ٿ��� �ĺ����� �����
						String temp = list.get(i)+""+j; 
						int input = Integer.parseInt(temp);
						if( input <= N) { // ���� ���ں��� ū ���ڴ� ���� �ʿ� ����.
							list.add(input); // ���� �ĺ����� �ֱ�		 
						}
					}
				}
			}
		}
	}
	public static void dfs(int N, int cnt, int index) {
		if ( min <= cnt ) { // �̹� ���� min ���� cnt�� �� ������ �� �غ� �ʿ� ����.
			return;
		}
		if (N == 1) { // N�� 1�̸� ���� �������. ���� min�� ������� cnt�� �ּҰ� ����.
			min = min < cnt ? min : cnt;
			return;
		}

		for (int i = index; i < list.size(); i++) { // ��ư���� ���� �� �ִ� �ĺ���
			int temp = list.get(i); // �ĺ� ����Ʈ�� ���� �̾Ƽ� temp
			if ((N / temp) * temp == N && temp != 1) { // N = n1*n2 = temp*(N/temp) 
				// temp�� �̹� ��ư���� ��������� ������. N/temp�� ��� �������� ��.
				dfs(N/temp, cnt + (temp+"".length() + 1), i);
			}
		}
	}
}
