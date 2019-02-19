package ssafy0219;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Solution3124 {
	static int N, E;
	static int[] parent;
	static boolean[] visit;
	//static ArrayList<Edge> mst; // do not needed
	static PriorityQueue<Edge> pq;
	static long result = 0;

	static int find(int n) {
		if (parent[n] == n) {
			return n;
		} else {
			return parent[n] = find(parent[n]);
		}
	}

	public static void kruskal() {
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			if (find(edge.start) == find(edge.end))
				continue;
			union(edge.start, edge.end);
			result += (long)edge.value;
		}
	}

	static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		if (p1 == p2)
			return;
		parent[p1] = p2;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int test = 1; test <= T; test++) {
			N = sc.nextInt();
			E = sc.nextInt();

			parent = new int[N + 1];
			pq = new PriorityQueue<>(new EdgeComparator());

			for (int e = 0; e < E; e++) {
				int st = sc.nextInt();
				int end = sc.nextInt();
				int value = sc.nextInt();
				pq.add(new Edge(st, end, value));
			}
			kruskal();
			System.out.println("#"+test+" "+result);
			result = 0L;
		}
	}

	static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			// TODO Auto-generated method stub
			return Integer.compare(o1.value, o2.value);
		}

	}

	static class Edge {
		int start, end, value;

		Edge(int s, int e, int v) {
			this.start = s;
			this.end = e;
			this.value = v;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", value=" + value + "]";
		}

	}

}
