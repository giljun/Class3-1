import java.util.ArrayList;
import java.util.Scanner;

public class Solution_5644_무선충전 {
	static ArrayList<BC> p1List;
	static ArrayList<BC> p2List;
	static int[] p1_move, p2_move;
	// 0:이동x 1:상 2:우 3:하 4:좌
	static int[][] dir = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int M, A;
	static int T;
	static Scanner sc = new Scanner(System.in);
	static int ans;

	public static void main(String[] args) {
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // 총 이동시간
			A = sc.nextInt(); // 충전기 갯수
			p1_move = new int[M];
			p2_move = new int[M];

			int[] p1 = new int[2]; // 1번사람 (x,y) = 1,1
			p1[0] = p1[1] = 1;
			int[] p2 = new int[2]; // 2번사람(x,y) = 10,10
			p2[0] = p2[1] = 10;

			for (int i = 0; i < M; i++) { // 1번 사람 움직임
				p1_move[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) { // 2번 사람 움직임
				p2_move[i] = sc.nextInt();
			}

			BC[] bcs = new BC[A]; // 충전기 정보 입력받기
			for (int i = 0; i < A; i++) {
				BC b = new BC();
				b.x = sc.nextInt();
				b.y = sc.nextInt();
				b.cover = sc.nextInt();
				b.power = sc.nextInt();
				b.num = i;
				bcs[i] = b;
			}

			ans = charge(bcs, p1, p2); // 0초일때 일단 충전

			for (int m = 0; m < M; m++) {
				p1[0] = p1[0] + dir[p1_move[m]][0]; // p1 move x
				p1[1] = p1[1] + dir[p1_move[m]][1]; // p1 move y
				p2[0] = p2[0] + dir[p2_move[m]][0]; // p2 move x
				p2[1] = p2[1] + dir[p2_move[m]][1]; // p2 move y

				ans += charge(bcs, p1, p2);
			}
			System.out.println("#" + tc + " " + ans);
		}

	}

	static int charge(BC[] bcs, int[] p1, int[] p2) {
		ArrayList<BC> p1Bc = new ArrayList<>();
		ArrayList<BC> p2Bc = new ArrayList<>();
		for (int i = 0; i < bcs.length; i++) {
			BC b = bcs[i];
			if (b.isCharge(p1[0], p1[1]))
				p1Bc.add(b);
			if (b.isCharge(p2[0], p2[1]))
				p2Bc.add(b);
		}
		int power = 0;

		if (p1Bc.size() == 0) { // 1번 사람이 충전 못할때
			for (int j = 0; j < p2Bc.size(); j++) { // 2번 가능하면 최대충전
				BC b2 = p2Bc.get(j);
				if (power < b2.power)
					power = b2.power;
			}
		}

		for (int i = 0; i < p1Bc.size(); i++) { // 1번이 충전 가능하다면
			BC b1 = p1Bc.get(i);
			if (p2Bc.size() == 0) { // 2번이 충전 못하면 1번만 최대값 찾고,
				if (power < b1.power)
					power = b1.power;
			}
			for (int j = 0; j < p2Bc.size(); j++) { // 둘다 충전 가능하면
				BC b2 = p2Bc.get(j);
				int p = 0;
				if (b1.num == b2.num) { // 1번이랑 겹치는건 한명만 충전.
					p += b1.power;
				} else { // 안겹치면 둘다 충전
					p += b1.power;
					p += b2.power;
				}

				if (power < p) // 1번이나 2번이 혼자 충전 한것보다 큰 p값이면 갱신
					power = p;
			}
		}
		return power;
	}

	static class BC {
		int num; // 충전기 번호
		int cover; // 충전거리
		int power; // 충전량
		int x, y; // 좌표

		boolean isCharge(int x, int y) {
			return cover >= (Math.abs(this.x - x) + Math.abs(this.y - y));
		}
	}

}
