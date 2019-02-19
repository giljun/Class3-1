package mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

// 1. 정점 중 아무거나 일단 시작 정점 고르기
// 2. 고른 정점에 연결된 간선들 중 최소비용 간선 고르기 -> 간선 따라가면 있는 정점을 방문한게 됨
// 3. 앞에서 선택한 모든 정점들에 연결된 간선 중 최소 비용 간선 찾아서 또 선택.
// 4. 모든 N 개의 정점이 다 선택될 때 까지 2-3 반복
public class MST_Prim {
	static int N; // 정점의 갯수
	static int E; // 간선의 갯수 
	static LinkedList<Edge>[] graph; // 리스트가 정점의 갯수만큼 만들어지는 객체배열
	static boolean visit[]; // 정점의 방문 여부를 저장할 배열
	static ArrayList<Edge> mst;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		
		visit = new boolean[N+1]; // 0번부터인 경우에는 N 까지만 만들면 됨.
		graph = new LinkedList[N+1]; // 각 정점 인덱스에 리스트 만들어서 연결된 간선정보 추가할 예정!
		
		for(int i=1; i<=N; i++)
			graph[i] = new LinkedList<>();
		
		for(int e=0; e<E; e++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int value = sc.nextInt();
			
			graph[st].add(new Edge(st, end, value)); // 방향 없는 그래프라 간선 하나가 두개 정점한테 추가되어야 함.
			graph[end].add(new Edge(end, st, value));
		}
		
		mst = new ArrayList<>();
		makeMST();
	}
	
	static void makeMST() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(new EdgeComparator()); // 간선 선택할 때 큐
		Queue<Integer> queue = new LinkedList<>(); // 방문할 정점 유지할 큐
		queue.add(1); // 일단 1번 정점을 큐에 넣고 작업 시작!
		
		while(!queue.isEmpty()) { // 큐에 아직 방문해야 할 정점이 있으면 계속 진행해라.
			int nowV = queue.poll(); // 큐에서 뽑은 정점으로 방문합시다.
			visit[nowV] = true;
			LinkedList<Edge> nowEdges = graph[nowV]; // 현재 정점에서 선택 가능한 정점 리스트
			
			for(Edge edge: nowEdges) { // 연결된 간선들 중 간선 끝에 있는 정점을 방문한 적 없는 간선만 선택해서
				if(!visit[edge.end]) // 우선순위 큐에 집어넣어 보자.
					pq.add(edge);
			}
			
			while(!pq.isEmpty()) {
				Edge edge = pq.poll(); // 후보 간선중 최소 가중치 간선 꺼내서
				if (!visit[edge.end]) { // 방문한 적 없는 정점으로 가는지 다시 확인한 후(큰 while 돌다 보면 이전에 선택 간선들이 누적되어서) 
					visit[edge.end] = true; // 거기 연결된 정점도 방문한거임.
					mst.add(edge); // 간선이 선택되었으므로 mst에 넣어 놓자.
					queue.add(edge.end); // 지금 선택된 정점도 방문 큐에 넣어 놓자.
				}
			}
		}
	}
	
	// 우선순위 큐가 간선들을 가중치 순으로 줄세울 수 있게 해주는 비교기능 내장 클래스
	static class EdgeComparator implements Comparator<Edge>{
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.value>o2.value ? 1 : -1;
		}
		
	}
	
	static class Edge{ // 하나의 간선정보를 하나의 객체에 묶기 위한 내부 클래스.
		int start, end, value;
		
		Edge(int s, int e, int v){
			start = s;
			end = e;
			value = v;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", value=" + value + "]";
		}		
	}
}

/*
7
11
1 2 2
2 3 5
1 3 20
1 4 10
4 5 1
5 6 23
3 6 3
3 5 6
7 6 9
7 3 2
2 7 7
 */






