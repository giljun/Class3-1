package hw;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_1350_jw_�ִ����Ʈ��_Kruskal {
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
		for(int i=1; i<=N; i++) // �������� ���� ���δ� �׷��� ��ǥ��� �ʱ⼳��.
			parent[i] = i;
		
		while(mst.size()<(N-1)) { // N-1�� ���� �̾Ҵ���
			// �ϴ� ���� ª�� ���� ť���� �̾Ƽ�
			Edge edge = pq.poll();
			
			// �糡 ������ ��ǥ ������ �ٸ��� �˻�. ������ �ش� ���� �Ⱦ�.
			if(find(edge.st) == find(edge.end))
				continue;
			
			union(edge.st, edge.end); // ������ �����ϴ� ��� �� ������ �����ؼ� �ѱ׷����� ��ġ��
			mst.add(edge);
		}
	}	
	
	
	static int find(int n) {
		if(parent[n] == n)
			return n;
		
		int p = find(parent[n]); // ���߿� n ������ �� find �� �� ��� ��������� �صδ� �۾�.
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
