package hw;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main_jw_2247_������{
    static int[] start;
    static int[] end;
    static int rs, re;
    static int N;
    static Scanner sc = new Scanner(System.in);
    static int breakT;
     
    public static void main(String[] args) {
        N = sc.nextInt();
         
        start = new int[N];
        end = new int[N];
         
        for(int n=0; n<N; n++) {
            start[n] = sc.nextInt();
            end[n] = sc.nextInt();
        }
         
        Arrays.sort(start);
        Arrays.sort(end);
         
        rs = start[N-1]; // �������� �Խ��Ѿ� 
        re = end[N-1];	 // �������� ����Ѿ�
        int tmp_re;
        for(int i=N-2; i>=0; i--) {
            if(rs<=end[i] && end[i]<=re) { // rs~re ���̿� ���� ����߳�?
                if(start[i]<rs)	 // �� ���� ���Դ�? ���� ���¾ָ� rs �����ؼ� rs~re ���� ������
                    rs = start[i];
            }else {	// ���� rs~re ���� ������ ����� �ְ� �ֳ�? �׷� �� ����ϰ� rs �ԽǱ��� �ƹ��� ������?
                tmp_re = end[i]; // �׷� �� ��� ���� ����ִ� �ð��� �������ϱ� ?
                while(i>=0) {
                    if(tmp_re - start[i] > re-rs) { // ���� rs~re �ð��� tmp_re���� ������ ���� ��
                        re = tmp_re;  // ����� �� ���� �ִ� �����̸� rs~re ����
                        rs = start[i];
                        break;
                    }
                    i--;
                }
            }
        }
         
        for(int i=N-1; i>0; i--) { // 
            if(start[i] > end[i-1] && start[i]-end[i-1] > breakT)
                breakT = start[i]-end[i-1];
        }
         
        System.out.println(re-rs+" "+breakT);
    }
}