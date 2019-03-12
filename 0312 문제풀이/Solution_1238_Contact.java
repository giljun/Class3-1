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
            N = sc.nextInt(); // N/2���� ��
            begin = sc.nextInt(); // ���۳�� ��ȣ
             
            map = new int[101][101]; // ������ �ȵ���. �ִ밪 100
            visit = new boolean[101];
            Queue<Integer> queue = new LinkedList<>();
             
            for(int n=0; n<(N/2); n++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                map[s][e] = 1;
                if(s==begin) {
                    queue.add(s); // �������� ť�� �־����
                    visit[s] = true;
                }
            }
             
            while(!queue.isEmpty()) {
                int cnt = queue.size();
                lastMax = 0;
                for(int i=0; i<cnt; i++) { // ���� depth�� ���鸸 ���� ó���ϱ�.
                    int curr = queue.poll(); 
                    
                    for(int j=1; j<=100; j++) { //���� ��߳�� curr���� ���� �ִ� ���� �湮ó�� �� enqueue 
                        if(map[curr][j]==1 && !visit[j]){
                            queue.add(j);
                            visit[j] = true;
                        }
                    }
                     
                    if(lastMax<curr) // ���� depth ������ �ִ� ��� ����صα�.
                        lastMax = curr;
                }
            }
             
            System.out.println("#"+tc+" "+lastMax);
        }
    }
     
}