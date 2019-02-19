package mst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import mst.MST_Prim.Edge;

public class MST_Kruskal {
	static int N, E; // 정점수, 간선수
	static int[] parent; // disjoint-set 에서 각 정점의 부모(대표자) 정보 저장하는 배열
	static boolean[] visit; // 선택한 정점 재방문 안할 때 쓰는 배열
	static ArrayList<Edge> mst;
	static PriorityQueue<Edge> pq;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		E = sc.nextInt();
		
		visit = new boolean[N+1]; // 0번부터인 경우에는 N 까지만 만들면 됨.
		parent = new int[N+1];
		pq = new PriorityQueue<>(new EdgeComparator());
		mst = new ArrayList<>();
		
		for(int e=0; e<E; e++) {
			int st = sc.nextInt();
			int end = sc.nextInt();
			int value = sc.nextInt();
			
			pq.add(new Edge(st, end, value)); // 간선들을 가중치로 정렬하는 우선순위 큐에 다 집어넣기.
		}
		
		kruskal();
		System.out.println(mst);
	}
	
	static void kruskal() {
		for(int i=1; i<=N; i++) {
			parent[i] = i; // 모든 정점들을 일단 각각 다 대표로 만들어 놓고 시작
		}
		
		for(int i=0; i<E; i++) {
			Edge edge = pq.poll(); // 가중치 작은 순서대로 간선이 나오겠지?
			
			if(find(edge.start) == find(edge.end)) // 간선의 시작정점, 끝정점 대표자가 같아? 사이클 생기네! 스킵!
				continue; 
			
			union(edge.start, edge.end); // 사이클 안생기는 간선의 양 접점을 하나로 합병
			mst.add(edge); // 선택된 간선을 mst에 추가
		}
	}
	
	// 특정 원소가 포함된 집합의 대표자 찾기 메소드
	static int find(int n) { 
		if(parent[n] == n)
			return n;
		
		parent [n] = find(parent[n]); // find가 실행되는 시점의 소속집합 전체의 대표자를 부모로 기억시켜서 효율성 증가.
		return parent[n];
	}
	
	// n1과 n2 가 소속된 두 집합을 합병하는 메소드
	static void union(int n1, int n2) { 
		int p1 = find(n1); // n1 소속집합의 대표자
		int p2 = find(n2); // n2 소속집합의 대표자
		
		if(p1!=p2) { // 두 집합의 대표자가 다른 경우 합병 진행
			parent[p1] = p2; // 원래 parent[p1] == p1 이었지만 이제 p1의 대표는 자신이 아닌 p2로 저장해놓기.
		}
	}
	
	// 우선순위 큐가 간선들을 가중치 순으로 줄세울 수 있게 해주는 비교기능 내장 클래스
	static class EdgeComparator implements Comparator<Edge> {
		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.value > o2.value ? 1 : -1;
		}

	}
	
	// 하나의 간선정보를 하나의 객체에 묶기 위한 내부 클래스.
	static class Edge { 
		int start, end, value;

		Edge(int s, int e, int v) {
			start = s;
			end = e;
			value = v;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", value=" + value + "]\n";
		}
	}
}
