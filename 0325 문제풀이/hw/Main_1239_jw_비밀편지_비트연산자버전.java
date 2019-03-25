package hw;

import java.util.Scanner;

public class Main_1239_jw_�������_��Ʈ�����ڹ��� {
	static String[] sample= {"000000","001111","010011","011100","100110","101001","110101","111010"};
	static char[] decode=	{'A','B','C','D','E','F','G','H'};
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int N = sc.nextInt();
		
		String tmp = sc.next();
		String[] in = new String[N];
		int idx=0;
		for(int i=0; i<tmp.length(); i=i+6) { // ���ٷ� �� ���� ��ǲ ���ڿ��� 6���� ��� �迭�� ����
			in[idx++] = tmp.substring(i, i+6);
		}
		
		
		
		String ans="";
		for(int i=0; i<N; i++) { // �Է¹��� �ڵ��� ���� N ��
			boolean find = false;
			for(int j=0; j<decode.length; j++) { // �Է��ڵ� 1���� 8���� ���� �ڵ�� ���ؾ� ��.
//				int result = str2dec(in[i])^str2dec(sample[j]);
				int result = Integer.parseInt(in[i], 2)^Integer.parseInt(sample[j], 2);
				int cnt=0;
				for(int k=0; k<6; k++) { // XOR ������� ��Ʈ�� 1�ΰ� � �ִ��� ���� for
					if((result&(1<<k))!=0) // result�� k��° ��Ʈ�� 0�� �ƴϸ� ī��Ʈ ++
						cnt++;
				}
				if(cnt<2) {
					find = true;
					ans+=decode[j];
				}
			} // 8�� �ڵ� �� for ��
			
			if(!find) {
				ans = (i+1)+"";
				break;
			}
		}
		System.out.println(ans);
	}
	
	// 101100 ������ ���ڿ��� 44��� ������ ����� ����� �ϴ� �޼ҵ�.
	static int str2dec(String s) {
		int sum=0;
		
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(5-i) == '1') {
				sum+=(1<<i);
			}
		}
		return sum;
	}
}







