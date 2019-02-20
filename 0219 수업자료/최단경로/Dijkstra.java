package 최단경로;

import java.util.Arrays;
import java.util.Scanner;

// 인접행렬을 가지고 있다. 즉 간선 정보 그래프를 가지고 있는 상태에서 최단거리 계산함.

// 1. 모든 정점까지의 거리 정보를 일단 줄 수 있는 최대 값으로 셋팅해놓고 시작!
// 2. 시작 정점이 결정되면 해당 정점까지의 거리는 0으로 표시, 그리고 시작 정점을 방문표시
// 3. 시작 노드와 인접한 노드들의 거리 값을 갱신
// 4. 아직 방문하지 않은 노드 중 거리가 가장 가까운 노드(minNode)를 찾기.
// 5. 4번에서 찾은 노드(minNode)에 방문한 다음 minNode에서 인접한 정점(min이웃)들의 거리 정보를 또 갱신
//	  distance[minNode]+(minNode~min이웃)랑 원래 distance[min이웃]에 기록되어 있던 값이랑 비교	 
//  -> 모든 정점을 방문할 때까지 4, 5 반복


// 시작점부터 제일 가까운애랑 걔의 이웃들까지의 거리를 걔를 찍고 가는게 빠른지 안찍고 가는게 빠른지 비교하며 갱신하는 작업 반복하기.
public class Dijkstra {
	static int N, E; // 정점수, 간선수
	static int[][] maps; // 정점에서 정점까지 인접 여부 및 인접할 때의 거리를 기록하는 행렬
	static int[] distance; // 처음에 무한대 설정 해놓고 점차 최단 거리로 갱신할 거리 정보 배열
	static boolean[] visit;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		
		maps = new int[N+1][N+1];
		distance = new int[N+1];
		visit = new boolean[N+1];
		
		for(int e=0; e<E; e++){
			int st = sc.nextInt();
			int end = sc.nextInt();
			int value = sc.nextInt();
			
			maps[st][end] =	value; // 테스트 할 그래프가 무향 그래프라서 대각선 대칭으로 입력해주기
			maps[end][st] = value;
		}
		
		dijkstra(2);
		System.out.println(Arrays.toString(distance));
	}
	
	static void dijkstra(int start) {
		// 1. 모든 거리정보를 최대값으로 초기화
		for(int i=1; i<=N; i++)
			distance[i] = Integer.MAX_VALUE;
		
		// 2. 시작 정점 정보 초기화. 자기자신까지의 거리는 0.
		distance[start] = 0;
		visit[start] = true;
		
		// 3. 시작 정점의 인접 정점들에 대한 거리 정보 갱신
		for(int i=1; i<=N; i++) {
			if(maps[start][i] != 0) {
				distance[i] = maps[start][i];
			}
		}
		
		// 4,5번 반복
		while(true) {
			if(isAllVisit()) // 모든 노드를 방문했다면 끝!
				break;
			
			// 4. 방문하지 않은 정점들 중 기록된 거리가 제일 가까운 정점 인덱스 찾기
			int min = Integer.MAX_VALUE;
			int minNode = -1; 
			for(int i=1; i<=N; i++) {
				if(!visit[i] && distance[i] != Integer.MAX_VALUE && distance[i]<min) {
					min = distance[i];
					minNode = i;
				}
			}
			
			// 5. 찾은 minNode를 방문 처리하고 걔의 인접 노드들 거리정보 갱신
			//    이때의 규칙. minNode 찍고  minNode에서 걔한테 가는 거리 더한거랑 기존 distance[걔] 값 비교해서 더 작은거리로 선택
			visit[minNode] = true;
			for(int i=1; i<=N; i++) {
				if(!visit[i] && // 방문하지 않은 노드들 중
						maps[minNode][i]!=0 && // minNode랑 인접한 애들중
						distance[i] > distance[minNode]+maps[minNode][i]) { // min을 찍고 가면 더 가까워 지는애들
					distance[i] = distance[minNode]+maps[minNode][i];
				}
			}
		}
	}
	
	static boolean isAllVisit() {
		for(int i=1; i<=N; i++) {
			if(visit[i] == false)
				return false;
		}
		return true;
	}
	
}

















