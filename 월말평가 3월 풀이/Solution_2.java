package 월말0329;
import java.util.LinkedList;
import java.util.Scanner;
 
public class Solution_2 {
	static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) throws Exception {
        int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
	        int N = sc.nextInt(); // 학생의 수, 2 <= N <= 100
	        LinkedList<Integer> line = new LinkedList<Integer>();
	        for (int i = 0; i < N; i++) {
				int order = sc.nextInt(); // 0 <= num < N
				line.add(i-order, i); // 4번째(i==3)가 2번을 뽑았으면 0,1,2번 줄서있을 때 1번 앞으로 끼어들면 됨.-> (3-2)  
			}
	        int sum = 0;
	        for (int i = 0; i < line.size()-1; i++) {
	        	sum += line.get(i) - line.get(i+1);
	        }
	        System.out.println("#"+tc+" "+sum);
		} // end of of testCase
    } // end of main
} // end of class