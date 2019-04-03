import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3378_��Ÿ�ϸ����鿩���� {
    static int p,q;
    static char[][] master,own;
    static int[] result; // �� �ҽ��ڵ��� �� ���ο� ��� '.'�� ������ �Ǵ��� ������ �迭
 
    public static boolean isAvailable(int r, int c, int s) { // ���� r,c,s�� �Է� ����� üũ�Ͽ� �������� �õ��غ�.
        int rCount=0,cCount = 0,sCount = 0;
         
        for (int i = 0; i < p; i++) { //������ ���κ��� ���� �о� ó���ϱ�
            int cnt = 0;
            // ������ ���� ���ۺκ��� '.' ���� ����
            for(char ch: master[i]) {
            	if(ch == '.') {
            		cnt++;
            	}else { // ���ӵǴ� .�� �ƴ� ���ο� ���� ������ break
            		break;
            	}
            }

            int indent = r*rCount + c*cCount + s*sCount; // r,c,s �̿��Ͽ� �鿩���� ���
            if(indent!=cnt) { // �鿩���� ������ ������ ������ ����ġ �ϸ� �ش� r,c,s�� �Ұ� �ϹǷ� ����
                return false;
            }
                         
            for(char ch: master[i]) { // �ش� ������ ��ȣ üũ�Ͽ� ī��Ʈ ó��
            	switch (ch) {
				case '(':	rCount++; break;
				case ')':	rCount--; break;
				case '{':	cCount++; break;
				case '}':	cCount--; break;
				case '[':	sCount++; break;
				case ']':	sCount--; break;
				}
            }
        }
        return true; // ��� ���� ó���Ͽ��ٸ� r,c,s�� ������ ���̹Ƿ� true ����
    }
 
    public static void processIndent(int r, int c, int s) { // �ش� r,c,s�� �Է� ����� üũ�Ͽ� �鿩���� �� ó��.
        int rCount=0,cCount = 0,sCount = 0;
         
        for (int i = 0; i < q; i++) { // �־��� rcs�� ������ �� �ҽ��ڵ� q���� �ٿ� ���� .�� � ������ �Ǵ��� ����Ұ���.
            if(result[i] == -2) { // �ش� ���� '.'�� ���� ����� ó���̸�
                result[i] = r*rCount + c*cCount + s*sCount;
            }else { // �ش� ���� '.'�� �ٸ� r,c,s�� ó���� ���� �־��ٸ�
                if(result[i]!=r*rCount + c*cCount + s*sCount) { // ���� r,c,s�� ó���� �ε�Ʈ�� ���� r,c,s�� ó���ϴ� �ε�Ʈ�� �ٸ��ٸ� �鿩���� �����Ҽ� �����Ƿ� -1
                    result[i] = -1;
                }
            }
            for(char ch: own[i]) {
            	switch (ch) {
				case '(':	rCount++; break;
				case ')':	rCount--;break;
				case '{':	cCount++;break;
				case '}':	cCount--;break;
				case '[':	sCount++;break;
				case ']':	sCount--;break;
				}
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
 
            st = new StringTokenizer(br.readLine(), " ");
 
            p = Integer.parseInt(st.nextToken()); //������ �ڵ���� ��
            q = Integer.parseInt(st.nextToken()); //�� �ڵ���� ��
 
            master = new char[p][];
            own = new char[q][];
             
            result = new int[q];
            Arrays.fill(result,-2); //�鿩���� ������ �ʾ����� ��Ÿ�� �÷��׷� -2�� ���
 
            for (int i = 0; i < p; i++) {
                master[i] = br.readLine().toCharArray();
            }
 
            for (int i = 0; i < q; i++) {
                own[i] = br.readLine().toCharArray();
            }
            // ������ ��� R,C,S�� �����غ��鼭 �������� �����ڵ尡 �������� üũ�� �����ϴٸ� �� �����ڵ忡 �ε�Ʈ ó��
            for(int r = 1; r<=20; r++) { // 1<=R<=20
                for(int c = 1; c<=20; c++) {// 1<=C<=20
                    for(int s = 1; s<=20; s++) {// 1<=S<=20
                        if(isAvailable(r,c,s)) { // �������� �ڵ忡  rcs �־ üũ
                            processIndent(r,c,s); // �� ���� �ڵ忡 �����ؼ� ���ٴ� '.'�� ���� �� ����س���!
                        }
                    }
                }
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#" + t + " ");
            for(int i=0; i<q; i++) {
                sb.append(result[i] + " ");
            }
            System.out.println(sb.toString());
        }
 
    }
}
