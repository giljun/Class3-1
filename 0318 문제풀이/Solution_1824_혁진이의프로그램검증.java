package ssafy0318;
import java.util.Scanner;

public class Solution_1824_혁진이의프로그램검증  {
	static int T;
    static int n,m;
    static char[][] code;
    static int memory,dir;
    static int[] dx= {0,0,-1,1};
    static int[] dy= {1,-1,0,0};
     
    static boolean[][][][] check;
     
    static boolean finish;
    static void dfs(int x,int y) {
    	// 상하좌우 벗어난 좌표 반대편으로 이동시키기
        if(x==-1) x=n-1;
        if(x==n) x=0;
        if(y==-1) y=m-1;
        if(y==m) y=0;
        
        // 각 명령어에 대한 행동(방향, _, |, 메모리+-, 종료위치) 변수에 기록해두기.
        switch(code[x][y]) {
        case '>': dir=0; break;
        case '<': dir=1; break;
        case '^': dir=2; break;
        case 'v': dir=3; break;
        case '_': dir=(memory==0)? 0:1; break;
        case '|': dir=(memory==0)? 3:2; break;
        case '+': memory++; if(memory==16) memory=0; break; // memory > 16이면 0으로
        case '-': memory--; if(memory==-1) memory=15; break; // memory > -1이면 15로
        case '@': finish = true; break;
        }
        if(finish) return;
        if(code[x][y]>='0'&&code[x][y]<='9') memory=code[x][y]-'0'; // '0'~'9' 문자 나타났으면 int 0~9로 바꿔서 저장 
        if(code[x][y]=='?') { // ?를 만났을 때 상하좌우 4개 방향으로 다 보내보기.
            for(int i=0;i<4;i++) {
                dir=i;
                if(!check[x][y][i][0]) {
                    check[x][y][i][0]=true;
                    dfs(x+dx[i],y+dy[i]);
                }
            }
        }else { // 물음표만 아니면 그냥 읽은 대로 진행.
            if(!check[x][y][dir][memory]) {
                check[x][y][dir][memory]=true; // 해당 좌표에 현재 방향 현재 메모리로 방문했음을 기록
                dfs(x+dx[dir],y+dy[dir]); // 이동
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = in.nextInt();
            m = in.nextInt();
            in.nextLine();
            code=new char[n][m];
            finish=false;
            for(int i=0;i<n;i++) {
                String temp=in.nextLine();
                for(int j=0;j<m;j++) {
                    code[i][j]=temp.charAt(j);
                    if(code[i][j]=='@') finish=true; // 종료문자 없는경우를 대비해서 있으면 true 해놓기
                }
            }
            if(finish) { // 종료문자 있는게 확인됐으면 탐색 시작.
                finish=false; 
                memory=0;
                dir=0;
                check=new boolean[n][m][4][16]; // n,m좌표에 0~3방향 각각으로 0~15의 숫자들고 방문한 적 있는지 체크할 배열
                dfs(0,0);
            }
            String answer=(finish)?"YES":"NO";
            System.out.println("#" + tc +" "+answer);
        }
    }
 
}