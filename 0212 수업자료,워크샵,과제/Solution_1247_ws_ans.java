import java.util.ArrayList;
import java.util.Scanner;

public class Solution_1247 {
	static boolean[] check;
	static Point company;
	static Point home;
	static int result;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			company = new Point(sc.nextInt(), sc.nextInt()); // 회사 좌표 입력
			home = new Point(sc.nextInt(), sc.nextInt()); // 집 좌표 입력
			result = Integer.MAX_VALUE;
			Point[] customers = new Point[N]; 
			for (int i = 0; i < N; i++) {// N 개의 회사 좌표 입력받기
				customers[i] = new Point(sc.nextInt(), sc.nextInt());
			}
			check = new boolean[N]; // N 개의 고객 집 방문 순서 순열 만들 때 쓸 boolean 배열
			go(customers, 0, new ArrayList<Point>()); // 순열 만들기 시작
			System.out.println("#" + test_case + " " + result);
		}
		sc.close();
	}

	// 경로 하나가 만들어 졌을 때 해당 경로의 distance 계산하기 메소드(회사~{경로}~집)
	static void find(ArrayList<Point> list) {
		int x = company.x;
		int y = company.y;
		int distance = 0;
		for (int i = 0; i < list.size(); i++) {
			distance += Math.abs(x - list.get(i).x) + Math.abs(y - list.get(i).y);
			x = list.get(i).x;
			y = list.get(i).y;
		}
		distance += Math.abs(x - home.x) + Math.abs(y - home.y);
		result = result < distance ? result : distance;
	}

	// 고객 집들로 만들수 있는 순열 구하기 재귀 메소드.
	static void go(Point[] customers, int length, ArrayList<Point> list) {
		if (customers.length == length) { // 경로 하나가 만들어 졌음.
			find(list);
			return;
		}
		for (int i = 0; i < customers.length; i++) {
			if (check[i])
				continue;
			check[i] = true;
			list.add(customers[i]);
			go(customers, length + 1, list);
			list.remove(customers[i]);
			check[i] = false;
		}
	}

	// 고객 집 하나 좌표 x,y를 하나의 객체로 묶기 위한 클래스
	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object o) {
			Point p = (Point) o;
			return this.x == p.x && this.y == p.y;
		}
	}

}
