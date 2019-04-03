import java.util.Arrays;
import java.util.Scanner;

public class Solution_4672_���������Ӹ���� {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T ; tc++) {
			String s = sc.next();
			char [] arr = s.toCharArray();
			
			Arrays.sort(arr);
			
			// ���� �ֵ鳢�� ��� ����ϸ� ��
			int cnt = count(arr);
			System.out.println("#" + tc + " " + cnt);
		}
	}

	static int count(char[] arr) {
		int cnt = 0;
		for(int k = 0 ; k < arr.length ; k++) {
			for(int i = 0 ; i <= k ; i++) {
				if( isPal(arr, i, k) ) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	static boolean isPal(char [] ch, int i, int j) {
		while ( i < j ) {
			if ( ch[i] != ch[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
}
