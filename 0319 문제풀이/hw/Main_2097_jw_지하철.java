package hw;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_2097_jw_����ö {
	static int N, E, Goal; // ������, ������
	static int[][] maps; // �������� �������� ���� ���� �� ������ ���� �Ÿ��� ����ϴ� ���
	static int[] distance; // ó���� ���Ѵ� ���� �س��� ���� �ִ� �Ÿ��� ������ �Ÿ� ���� �迭
	static int[] path; // � �������� �������� �� ��ġ�� �Ǵ� ����.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Goal = sc.nextInt();

		maps = new int[N + 1][N + 1];
		distance = new int[N + 1];

		for(int i=1; i<=N; i++) { // ���Ī. ���⼺�ִ� ������� �Է¹���.
			for(int j=1; j<=N; j++) {
				maps[i][j] = sc.nextInt();
			}
		}

		dijkstra(1); // �׻� ���Ҵ� 1�� ��
		
		// path�� ��ϵ� Ư�� ������ �������� �� �湮�ؾ� �ϴ� ���� ������ ��� ���� ���
		int idx = Goal;
		String ansPath = "";
		while(idx!=1) { // 1 3 5
			ansPath = idx +" "+ ansPath;
			idx = path[idx];
		}
		System.out.println(distance[Goal]); // Goal ���� �ִ� �Ÿ�
		System.out.println("1"+ansPath); // ����� �׻� 1��.
	}

	static class NextV implements Comparable<NextV>{
		int vNum, len; // �湮�� ���� ��ȣ, start���� vNum ������ �Ÿ�

		NextV(int vn, int len) {
			this.vNum = vn;
			this.len = len;
		}
		
		@Override
		public int compareTo(NextV o) {
			return this.len - o.len;
		}
	}


	static void dijkstra(int start) {
		PriorityQueue<NextV> pq = new PriorityQueue<>();

		// 1. ��� ���������� �Ÿ������� �ִ밪���� �ʱ�ȭ
		for (int i = 1; i <= N; i++)
			distance[i] = Integer.MAX_VALUE;

		// 2. ���� ���� ���� �ʱ�ȭ. �ڱ��ڽű����� �Ÿ��� 0.
		distance[start] = 0;
		pq.add(new NextV(start, 0));

		while (!pq.isEmpty()) {
			NextV v = pq.poll(); // ������ ������ ���� ����� ������ poll��.(���� 4.���� �ϴ� �ּҰ� ã�Ⱑ �˾Ƽ���.)

			// v������ �Ÿ����� distance[v.vNum]�� �̹� ���� ���� ����ִ� ��� ���� ���ʿ�
			if (v.len > distance[v.vNum])
				continue;

			// ���� 5.������ �ϴ� �۾�
			for (int i = 1; i <= N; i++) {
				// v�� ������ ������ �� v�� ��� �湮�ؾ� �ϴ� �������� �Ÿ����� �����ϰ� heap�� �ֱ�
				if (maps[v.vNum][i] != 0 && distance[i] > distance[v.vNum] + maps[v.vNum][i]) {
					distance[i] = distance[v.vNum] + maps[v.vNum][i];
					path[i] = v.vNum; // i���� ������ �ݵ�� ���ľ� ���� ������ v ��.
					pq.add(new NextV(i, distance[i]));
				}
			}
		}

	}

}
