import java.util.ArrayList;
import java.util.Scanner;

public class Solution_5644_�������� {
	static ArrayList<BC> p1List;
	static ArrayList<BC> p2List;
	static int[] p1_move, p2_move;
	// 0:�̵�x 1:�� 2:�� 3:�� 4:��
	static int[][] dir = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
	static int M, A;
	static int T;
	static Scanner sc = new Scanner(System.in);
	static int ans;

	public static void main(String[] args) {
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			M = sc.nextInt(); // �� �̵��ð�
			A = sc.nextInt(); // ������ ����
			p1_move = new int[M];
			p2_move = new int[M];

			int[] p1 = new int[2]; // 1����� (x,y) = 1,1
			p1[0] = p1[1] = 1;
			int[] p2 = new int[2]; // 2�����(x,y) = 10,10
			p2[0] = p2[1] = 10;

			for (int i = 0; i < M; i++) { // 1�� ��� ������
				p1_move[i] = sc.nextInt();
			}
			for (int i = 0; i < M; i++) { // 2�� ��� ������
				p2_move[i] = sc.nextInt();
			}

			BC[] bcs = new BC[A]; // ������ ���� �Է¹ޱ�
			for (int i = 0; i < A; i++) {
				BC b = new BC();
				b.x = sc.nextInt();
				b.y = sc.nextInt();
				b.cover = sc.nextInt();
				b.power = sc.nextInt();
				b.num = i;
				bcs[i] = b;
			}

			ans = charge(bcs, p1, p2); // 0���϶� �ϴ� ����

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

		if (p1Bc.size() == 0) { // 1�� ����� ���� ���Ҷ�
			for (int j = 0; j < p2Bc.size(); j++) { // 2�� �����ϸ� �ִ�����
				BC b2 = p2Bc.get(j);
				if (power < b2.power)
					power = b2.power;
			}
		}

		for (int i = 0; i < p1Bc.size(); i++) { // 1���� ���� �����ϴٸ�
			BC b1 = p1Bc.get(i);
			if (p2Bc.size() == 0) { // 2���� ���� ���ϸ� 1���� �ִ밪 ã��,
				if (power < b1.power)
					power = b1.power;
			}
			for (int j = 0; j < p2Bc.size(); j++) { // �Ѵ� ���� �����ϸ�
				BC b2 = p2Bc.get(j);
				int p = 0;
				if (b1.num == b2.num) { // 1���̶� ��ġ�°� �Ѹ� ����.
					p += b1.power;
				} else { // �Ȱ�ġ�� �Ѵ� ����
					p += b1.power;
					p += b2.power;
				}

				if (power < p) // 1���̳� 2���� ȥ�� ���� �Ѱͺ��� ū p���̸� ����
					power = p;
			}
		}
		return power;
	}

	static class BC {
		int num; // ������ ��ȣ
		int cover; // �����Ÿ�
		int power; // ������
		int x, y; // ��ǥ

		boolean isCharge(int x, int y) {
			return cover >= (Math.abs(this.x - x) + Math.abs(this.y - y));
		}
	}

}
