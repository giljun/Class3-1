import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
  
public class Solution_1238_Contact {
    static int T, N, begin;
    static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static boolean[] visit;
    static int lastMax;
     
    public static void main(String[] args) {
         
        for(int tc=1; tc<=10; tc++) {
            N = sc.nextInt(); // N/2개의 쌍
            begin = sc.nextInt(); // 시작노드 번호
             
            map = new int[101][101]; // 노드수가 안들어옴. 최대값 100
            visit = new boolean[101];
            Queue<Integer> queue = new LinkedList<>();
             
            for(int n=0; n<(N/2); n++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                map[s][e] = 1;
                if(s==begin) {
                    queue.add(s); // 시작정점 큐에 넣어놓기
                    visit[s] = true;
                }
            }
             
            while(!queue.isEmpty()) {
                int cnt = queue.size();
                lastMax = 0;
                for(int i=0; i<cnt; i++) { // 같은 depth의 노드들만 빼서 처리하기.
                    int curr = queue.poll(); 
                    
                    for(int j=1; j<=100; j++) { //현재 출발노드 curr에서 갈수 있는 인접 방문처리 및 enqueue 
                        if(map[curr][j]==1 && !visit[j]){
                            queue.add(j);
                            visit[j] = true;
                        }
                    }
                     
                    if(lastMax<curr) // 같은 depth 내에서 최대 노드 기억해두기.
                        lastMax = curr;
                }
            }
             
            System.out.println("#"+tc+" "+lastMax);
        }
    }
     
}