package hw;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1350_jw_최대신장트리_Kruskal {
	static int N, M;
	static ArrayList<Edge> mst;
	static Scanner sc = new Scanner(System.in);
	static int[] parent;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) {
		N = sc.nextInt();
		M = sc.nextInt();
		
		pq = new PriorityQueue<>();
		mst = new ArrayList<>();
		parent = new int[N+1];
		
		for(int m=0; m<M; m++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int w = sc.nextInt();
			
			pq.add(new Edge(st, end, w));
		}
		
		kruskal();
		
		int ans = 0;
		for(Edge e: mst) {
			ans += e.weight;
		}
		System.out.println(ans);
	}
	
	static void kruskal() {
		for(int i=1; i<=N; i++) // 정점들이 각각 전부다 그룹의 대표라고 초기설정.
			parent[i] = i;
		
		while(mst.size()<(N-1)) { // N-1개 간선 뽑았는지
			// 일단 길이 짧은 간선 큐에서 뽑아서
			Edge edge = pq.poll();
			
			// 양끝 정점의 대표 같은지 다른지 검사. 같으면 해당 간선 안씀.
			if(find(edge.st) == find(edge.end))
				continue;
			
			union(edge.st, edge.end); // 간선을 선택하는 경우 두 정점을 연결해서 한그룹으로 합치기
			mst.add(edge);
		}
	}	
	
	
	static int find(int n) {
		if(parent[n] == n)
			return n;
		
		int p = find(parent[n]); // 나중에 n 가지고 또 find 할 때 재귀 덜돌으라고 해두는 작업.
		parent[n] = p;
		return p;
	}
	
	static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 != p2)
			parent[p1] = p2;
	}
	
	static class Edge implements Comparable<Edge>{
		int st, end, weight;
		
		Edge(int st, int end, int weight){
			this.st = st;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return o.weight - this.weight;
		}
	}
}
