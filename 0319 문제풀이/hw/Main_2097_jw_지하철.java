package hw;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_2097_jw_지하철 {
	static int N, E, Goal; // 정점수, 간선수
	static int[][] maps; // 정점에서 정점까지 인접 여부 및 인접할 때의 거리를 기록하는 행렬
	static int[] distance; // 처음에 무한대 설정 해놓고 점차 최단 거리로 갱신할 거리 정보 배열
	static int[] path; // 어떤 정점까지 가기위해 꼭 거치게 되는 정점.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		Goal = sc.nextInt();

		maps = new int[N + 1][N + 1];
		distance = new int[N + 1];

		for(int i=1; i<=N; i++) { // 비대칭. 방향성있는 인접행렬 입력받음.
			for(int j=1; j<=N; j++) {
				maps[i][j] = sc.nextInt();
			}
		}

		dijkstra(1); // 항상 숙소는 1번 역
		
		// path에 기록된 특정 정점에 가기위해 꼭 방문해야 하는 정점 정보로 경로 정보 얻기
		int idx = Goal;
		String ansPath = "";
		while(idx!=1) { // 1 3 5
			ansPath = idx +" "+ ansPath;
			idx = path[idx];
		}
		System.out.println(distance[Goal]); // Goal 까지 최단 거리
		System.out.println("1"+ansPath); // 출발은 항상 1번.
	}

	static class NextV implements Comparable<NextV>{
		int vNum, len; // 방문할 정점 번호, start에서 vNum 까지의 거리

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

		// 1. 모든 정점까지의 거리정보를 최대값으로 초기화
		for (int i = 1; i <= N; i++)
			distance[i] = Integer.MAX_VALUE;

		// 2. 시작 정점 정보 초기화. 자기자신까지의 거리는 0.
		distance[start] = 0;
		pq.add(new NextV(start, 0));

		while (!pq.isEmpty()) {
			NextV v = pq.poll(); // 인접한 정점중 가장 가까운 정점이 poll됨.(원래 4.에서 하는 최소값 찾기가 알아서됨.)

			// v까지의 거리보다 distance[v.vNum]에 이미 작은 값이 들어있는 경우 갱신 불필요
			if (v.len > distance[v.vNum])
				continue;

			// 원래 5.번에서 하던 작업
			for (int i = 1; i <= N; i++) {
				// v에 인접한 정점들 중 v를 찍고 방문해야 하는 정점들의 거리정보 갱신하고 heap에 넣기
				if (maps[v.vNum][i] != 0 && distance[i] > distance[v.vNum] + maps[v.vNum][i]) {
					distance[i] = distance[v.vNum] + maps[v.vNum][i];
					path[i] = v.vNum; // i한테 가려면 반드시 거쳐야 빠른 정점이 v 임.
					pq.add(new NextV(i, distance[i]));
				}
			}
		}

	}

}
