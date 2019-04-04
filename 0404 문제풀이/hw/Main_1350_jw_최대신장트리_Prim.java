package hw;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main_1350_jw_�ִ����Ʈ��_Prim{
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
        pq.add(new Node(1,0)); // ������ ��������. �ƹ� ���������� �����ص� �������.
        int cnt = 0; 
         
        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(visited[node.end]) { // pq���� ���°� �̹� �湮���� �ִ� �������� ����°Ÿ� �ǳʶٱ�.
                continue;
            }
            visited[node.end] = true; // ���� ���� �湮ó��.
            res+= node.val; // �ִ����Ʈ�� ��ü �̵��Ÿ� �� ����� ���� ����ġ �����س���.
            cnt++; // �湮�� ������ ����
            if(cnt == N) { // N�� ������ ��� �湮������ pq�� ������ �����־ �� �� �ʿ� ����.
                break;
            }
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && graph[node.end][i] != 0 ) { // ���� node���� �湮������ ��� ������ ������ pq�� �߰�.
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








