import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2458_bj_키순서_BFS {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int res;
	public static boolean[][] ST; // short tall
	public static boolean[][] TS; // tall short
	public static int N;
	public static boolean[][] map;
	
	
	public static void run() {
		//자기자신을 체크
		for(int i =1; i<N+1; i++) {
			map[i][i] = true;
		}
		
		Queue<Integer> q = new LinkedList<>();
		int idx;
		boolean[] check;
		//여기 나보다 키 큰 사람 있어?
		for(int i =1; i<N+1; i++) {
			q.add(i);
			check = new boolean[N+1];
			check[i] = true;
			while(!q.isEmpty()) {
				idx = q.poll();
				for(int t=1;t<N+1; t++) { //target
					if(ST[idx][t] && !check[t]) {
						map[i][t] = true;
						check[t] = true;
						q.add(t);
					}
				}
			}
		}
		
		//여기 나보다 키 작은 사람 있어?
		for(int i =1; i<N+1; i++) {
			q.add(i);
			check = new boolean[N+1];
			check[i] = true;
			while(!q.isEmpty()) { //target
				idx = q.poll();
				for(int t=1; t<N+1; t++) {
					if(TS[idx][t] && !check[t]) {
						map[i][t] = true;
						check[t]=true;
						q.add(t);
					}
				}
			}
		}
		
		//모두 체크되어있다면 자신의 순위를 정확하게 알 수 있다.
		boolean flag;
		for(int i =1; i<N+1; i++) {
			flag = true;
			for(int j=1; j<N+1; j++) {
				if(!map[i][j]) {
					flag = false;
					break;
				}
			}
			if(flag) res++;
		}
	}
	
	public static void init() throws IOException{
		res=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ST = new boolean[N+1][N+1];
		TS = new boolean[N+1][N+1];
		map = new boolean[N+1][N+1];
		
		for(int i =0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			ST[s][t] = true;
			TS[t][s] = true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		run();
		System.out.println(res);
	}
	
}
