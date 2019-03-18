package ssafy0318;
import java.util.Scanner;

public class Solution_1824_�����������α׷�����  {
	static int T;
    static int n,m;
    static char[][] code;
    static int memory,dir;
    static int[] dx= {0,0,-1,1};
    static int[] dy= {1,-1,0,0};
     
    static boolean[][][][] check;
     
    static boolean finish;
    static void dfs(int x,int y) {
    	// �����¿� ��� ��ǥ �ݴ������� �̵���Ű��
        if(x==-1) x=n-1;
        if(x==n) x=0;
        if(y==-1) y=m-1;
        if(y==m) y=0;
        
        // �� ��ɾ ���� �ൿ(����, _, |, �޸�+-, ������ġ) ������ ����صα�.
        switch(code[x][y]) {
        case '>': dir=0; break;
        case '<': dir=1; break;
        case '^': dir=2; break;
        case 'v': dir=3; break;
        case '_': dir=(memory==0)? 0:1; break;
        case '|': dir=(memory==0)? 3:2; break;
        case '+': memory++; if(memory==16) memory=0; break; // memory > 16�̸� 0����
        case '-': memory--; if(memory==-1) memory=15; break; // memory > -1�̸� 15��
        case '@': finish = true; break;
        }
        if(finish) return;
        if(code[x][y]>='0'&&code[x][y]<='9') memory=code[x][y]-'0'; // '0'~'9' ���� ��Ÿ������ int 0~9�� �ٲ㼭 ���� 
        if(code[x][y]=='?') { // ?�� ������ �� �����¿� 4�� �������� �� ��������.
            for(int i=0;i<4;i++) {
                dir=i;
                if(!check[x][y][i][0]) {
                    check[x][y][i][0]=true;
                    dfs(x+dx[i],y+dy[i]);
                }
            }
        }else { // ����ǥ�� �ƴϸ� �׳� ���� ��� ����.
            if(!check[x][y][dir][memory]) {
                check[x][y][dir][memory]=true; // �ش� ��ǥ�� ���� ���� ���� �޸𸮷� �湮������ ���
                dfs(x+dx[dir],y+dy[dir]); // �̵�
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
                    if(code[i][j]=='@') finish=true; // ���Ṯ�� ���°�츦 ����ؼ� ������ true �س���
                }
            }
            if(finish) { // ���Ṯ�� �ִ°� Ȯ�ε����� Ž�� ����.
                finish=false; 
                memory=0;
                dir=0;
                check=new boolean[n][m][4][16]; // n,m��ǥ�� 0~3���� �������� 0~15�� ���ڵ�� �湮�� �� �ִ��� üũ�� �迭
                dfs(0,0);
            }
            String answer=(finish)?"YES":"NO";
            System.out.println("#" + tc +" "+answer);
        }
    }
 
}