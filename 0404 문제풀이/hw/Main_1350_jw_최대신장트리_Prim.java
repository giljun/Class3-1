package hw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main_1350_jw_최대신장트리_Prim{
    static int[][] graph;
    static int N,M,res;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        graph = new int[N+1][N+1];
         
         
        for(int i = 0; i <M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            if(graph[start][end] < val) {
                graph[start][end] = val;
                graph[end][start] = val;    
            }
             
        }
        prim();
        System.out.println(res);
    }
    private static void prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0)); // 임의의 시작정점. 아무 정점에서나 시작해도 상관없음.
        int cnt = 0; 
         
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.end]) { // pq에서 나온게 이미 방문한적 있는 정점으로 가라는거면 건너뛰기.
                continue;
            }
            visited[node.end] = true; // 현재 정점 방문처리.
            res+= node.val; // 최대신장트리 전체 이동거리 합 출력을 위해 가중치 누적해놓기.
            cnt++; // 방문한 정점의 갯수
            if(cnt == N) { // N개 정점에 모두 방문했으면 pq에 간선이 남아있어도 더 볼 필요 없음.
                break;
            }
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && graph[node.end][i] != 0 ) { // 현재 node에서 방문가능한 모든 정점에 간선들 pq에 추가.
                    pq.add(new Node(i,graph[node.end][i]));
                }
            }
        }
         
    }
    public static class Node implements Comparable<Node>{
        int end, val;
        Node(int end, int val){
            this.end = end;
            this.val = val;
             
        }
        @Override
        public int compareTo(Node o) {
            return o.val - this.val;
        }
    }
}








