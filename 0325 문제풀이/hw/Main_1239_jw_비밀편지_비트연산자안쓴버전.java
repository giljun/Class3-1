package hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1239_jw_비밀편지_비트연산자안쓴버전 {
	static String[] a = {"000000","001111","010011","011100","100110","101001","110101","111010"};
	static char[] b = {'A','B','C','D','E','F','G','H'};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] input = new String[N];
		String str = br.readLine();
		int index=0;
		for (int i = 0; i < N; i++) {
			input[i] = str.substring(index, index+6);
			index+=6;
		}
		ArrayList<Character> res = new ArrayList<>();
		boolean abc = false;
		for (int j = 0; j < input.length; j++) {
			boolean c = false;
			for (int i = 0; i < a.length; i++) {
				int count = 0;
				for (int j2 = 0; j2 < 6; j2++) {
					if(a[i].charAt(j2)!=input[j].charAt(j2)) {
						count++;
					}
				}
				if(count<=1) {
					res.add(b[i]);
					c=true;
					break;
				}
			}
			if(!c) {
				System.out.println(j+1);
				abc = true;
				break;
			}
		}
		if(!abc) {
			for(char tmp : res) {
				System.out.print(tmp);
			}
		}
	}
}
