package ssafy0318;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution_3143_����������ڿ�Ÿ����{
    static int T;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String A, B;
    static int cnt;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());
         
        for(int tc=1; tc<=T; tc++) {
            String[] tmp = br.readLine().split(" ");
            A = tmp[0]; // ���� ���ڿ�
            B = tmp[1]; // ����Ű �����س��� �ܾ�
             
            cnt=0;
             
            for(int i=0; i<A.length(); i++) {
                int idx = A.indexOf(B, i); // B �ܾ� ������ġ ã��.
                if(idx!=-1) { // B�� �����ϸ� 
                    cnt += idx-i+1;
                    i = idx+B.length()-1;
                }else {
                    cnt+=A.length()-i;
                    break;
                }
            }
             
            System.out.println("#"+tc+" "+cnt);
        }
    }
}