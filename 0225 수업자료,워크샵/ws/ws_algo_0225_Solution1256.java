
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution1256 {

	public static void main(String[] args) throws NumberFormatException,IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1;test_case<=T;test_case++) {
			ArrayList list = new ArrayList<>();
			int N = Integer.parseInt(br.readLine());
			StringBuilder str = new StringBuilder(br.readLine());
			for(int i=0; i<str.length(); i++) {
				for(int j=i+1;j<=str.length();j++) {
				list.add(str.substring(i,j));
				}
			}
			list.sort(null);
			System.out.println("#"+test_case+" "+list.get(N-1));
		}

	}

}
