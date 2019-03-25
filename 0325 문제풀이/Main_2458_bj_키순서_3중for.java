import java.util.Scanner;

public class Main_2458_bj_Ű����_3��for {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		boolean[][] H = new boolean[N+1][N+1];
		for(int i=0; i<M; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			H[A][B] = true;  // A���� B�� �� ũ��!
		}
		
		for(int a=1; a<N+1; a++) {
			for(int b=1; b<N+1; b++) {
				for(int c=1; c<N+1; c++) { // ��Ž
					if ( H[b][a] && H[a][c] ) { // b�� a���� �۰� a�� c���� �۴ٸ�
						H[b][c] = true; // b�� c���� �۴�.
					}
				}
			}
		}
		int res = 0;
		for(int i=1; i<N+1; i++) {
			int cnt =0;
			for(int j=1; j<N+1; j++) {
				if ( H[i][j] || H[j][i] ) { // ������ ũ�ų� �۰ų��� �� �� �ִٸ� 
					cnt++;
				}
			}
			if ( N-1 == cnt ) {
				res++;
			}
		}
		System.out.println(res);
	}
}
