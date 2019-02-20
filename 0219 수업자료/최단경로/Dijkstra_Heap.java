package 최단경로;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra_Heap {
	static int V, E; // 정점수, 간선수
	static int[][] maps; // 정점에서 정점까지 인접 여부 및 인접할 때의 거리를 기록하는 행렬
	static int[] distance; // 처음에 무한대 설정 해놓고 점차 최단 거리로 갱신할 거리 정보 배열
	static boolean[] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();

		maps = new int[V + 1][V + 1];
		distance = new int[V + 1];
		visit = new boolean[V + 1];

		for (int e = 0; e < E; e++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int value = sc.nextInt();

			maps[st][end] = value; // 테스트 할 그래프가 무향 그래프라서 대각선 대칭으로 입력해주기
			maps[end][st] = value;
		}

		dijkstra(2);
		System.out.println(Arrays.toString(distance));
	}

	static class NextV {
		int vNum, len; // 방문할 정점 번호, start에서 vNum 까지의 거리

		NextV(int vn, int len) {
			this.vNum = vn;
			this.len = len;
		}
	}

	static class NextVComparator implements Comparator<NextV> {
		@Override
		public int compare(NextV o1, NextV o2) {
			return o1.len - o2.len;
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<NextV> pq = new PriorityQueue<>(new NextVComparator());

		// 1. 모든 정점까지의 거리정보를 최대값으로 초기화
		for (int i = 1; i <= V; i++)
			distance[i] = Integer.MAX_VALUE;

		// 2. 시작 정점 정보 초기화. 자기자신까지의 거리는 0.
		distance[start] = 0;
		visit[start] = true;
		pq.add(new NextV(start, 0));

		while (!pq.isEmpty()) {
			NextV v = pq.poll(); // 인접한 정점중 가장 가까운 정점이 poll됨.(원래 4.에서 하는 최소값 찾기가 알아서됨.)

			// v까지의 거리보다 distance[v.vNum]에 이미 작은 값이 들어있는 경우 갱신 불필요
			if (v.len > distance[v.vNum])
				continue;

			// 원래 5.번에서 하던 작업
			for (int i = 1; i <= V; i++) {
				// v에 인접한 정점들 중 v를 찍고 방문해야 하는 정점들의 거리정보 갱신하고 heap에 넣기
				if (maps[v.vNum][i] != 0 && distance[i] > distance[v.vNum] + maps[v.vNum][i]) {
					distance[i] = distance[v.vNum] + maps[v.vNum][i];
					pq.add(new NextV(i, distance[i]));
				}
			}
		}

	}

}
