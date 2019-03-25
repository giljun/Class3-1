package hw;

import java.util.Scanner;

public class Main_1239_jw_비밀편지_비트연산자버전 {
	static String[] sample= {"000000","001111","010011","011100","100110","101001","110101","111010"};
	static char[] decode=	{'A','B','C','D','E','F','G','H'};
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int N = sc.nextInt();
		
		String tmp = sc.next();
		String[] in = new String[N];
		int idx=0;
		for(int i=0; i<tmp.length(); i=i+6) { // 한줄로 쭉 들어온 인풋 문자열을 6개씩 끊어서 배열에 저장
			in[idx++] = tmp.substring(i, i+6);
		}
		
		
		
		String ans="";
		for(int i=0; i<N; i++) { // 입력받은 코드의 갯수 N 개
			boolean find = false;
			for(int j=0; j<decode.length; j++) { // 입력코드 1개를 8개의 샘플 코드랑 비교해야 함.
//				int result = str2dec(in[i])^str2dec(sample[j]);
				int result = Integer.parseInt(in[i], 2)^Integer.parseInt(sample[j], 2);
				int cnt=0;
				for(int k=0; k<6; k++) { // XOR 결과에서 비트가 1인게 몇개 있는지 세는 for
					if((result&(1<<k))!=0) // result의 k번째 비트가 0이 아니면 카운트 ++
						cnt++;
				}
				if(cnt<2) {
					find = true;
					ans+=decode[j];
				}
			} // 8개 코드 비교 for 끝
			
			if(!find) {
				ans = (i+1)+"";
				break;
			}
		}
		System.out.println(ans);
	}
	
	// 101100 형태의 문자열을 44라는 정수로 만드는 기능을 하는 메소드.
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







