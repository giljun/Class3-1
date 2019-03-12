package hw;
import java.util.Arrays;
import java.util.Scanner;
 
public class Main_jw_2247_도서관{
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
         
        rs = start[N-1]; // 마지막에 입실한애 
        re = end[N-1];	 // 마지막에 퇴실한애
        int tmp_re;
        for(int i=N-2; i>=0; i--) {
            if(rs<=end[i] && end[i]<=re) { // rs~re 사이에 누가 퇴실했네?
                if(start[i]<rs)	 // 얘 언제 들어왔니? 일찍 들어온애면 rs 갱신해서 rs~re 구간 넓히자
                    rs = start[i];
            }else {	// 현재 rs~re 구간 이전에 퇴실한 애가 있네? 그럼 걔 퇴실하고 rs 입실까지 아무도 없었네?
                tmp_re = end[i]; // 그럼 걔 퇴실 전에 사람있는 시간은 어디까지일까 ?
                while(i>=0) {
                    if(tmp_re - start[i] > re-rs) { // 기존 rs~re 시간과 tmp_re이전 구간의 길이 비교
                        re = tmp_re;  // 사람이 더 오래 있던 구간이면 rs~re 갱신
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