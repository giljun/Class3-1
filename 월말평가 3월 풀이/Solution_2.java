package ����0329;
import java.util.LinkedList;
import java.util.Scanner;
 
public class Solution_2 {
	static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) throws Exception {
        int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
	        int N = sc.nextInt(); // �л��� ��, 2 <= N <= 100
	        LinkedList<Integer> line = new LinkedList<Integer>();
	        for (int i = 0; i < N; i++) {
				int order = sc.nextInt(); // 0 <= num < N
				line.add(i-order, i); // 4��°(i==3)�� 2���� �̾����� 0,1,2�� �ټ����� �� 1�� ������ ������ ��.-> (3-2)  
			}
	        int sum = 0;
	        for (int i = 0; i < line.size()-1; i++) {
	        	sum += line.get(i) - line.get(i+1);
	        }
	        System.out.println("#"+tc+" "+sum);
		} // end of of testCase
    } // end of main
} // end of class