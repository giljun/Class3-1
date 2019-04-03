import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3378_스타일리쉬들여쓰기 {
    static int p,q;
    static char[][] master,own;
    static int[] result; // 내 소스코드의 각 라인에 몇개의 '.'을 찍으면 되는지 저장할 배열
 
    public static boolean isAvailable(int r, int c, int s) { // 현재 r,c,s로 입력 문장들 체크하여 가능한지 시도해봄.
        int rCount=0,cCount = 0,sCount = 0;
         
        for (int i = 0; i < p; i++) { //마스터 라인별로 문자 읽어 처리하기
            int cnt = 0;
            // 마스터 문장 시작부분의 '.' 개수 세기
            for(char ch: master[i]) {
            	if(ch == '.') {
            		cnt++;
            	}else { // 연속되는 .이 아닌 새로운 문자 만나면 break
            		break;
            	}
            }

            int indent = r*rCount + c*cCount + s*sCount; // r,c,s 이용하여 들여쓰기 계산
            if(indent!=cnt) { // 들여쓰기 개수와 온점의 개수가 불일치 하면 해당 r,c,s로 불가 하므로 리턴
                return false;
            }
                         
            for(char ch: master[i]) { // 해당 라인의 괄호 체크하여 카운트 처리
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
        return true; // 모든 라인 처리하였다면 r,c,s는 가능한 해이므로 true 리턴
    }
 
    public static void processIndent(int r, int c, int s) { // 해당 r,c,s로 입력 문장들 체크하여 들여쓰기 수 처리.
        int rCount=0,cCount = 0,sCount = 0;
         
        for (int i = 0; i < q; i++) { // 주어진 rcs를 가지고 내 소스코드 q개의 줄에 전부 .을 몇개 찍으면 되는지 기록할거임.
            if(result[i] == -2) { // 해당 라인 '.'의 갯수 계산이 처음이면
                result[i] = r*rCount + c*cCount + s*sCount;
            }else { // 해당 라인 '.'가 다른 r,c,s로 처리된 적이 있었다면
                if(result[i]!=r*rCount + c*cCount + s*sCount) { // 기존 r,c,s로 처리된 인덴트와 현재 r,c,s로 처리하는 인덴트가 다르다면 들여쓰기 결정할수 없으므로 -1
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
 
            p = Integer.parseInt(st.nextToken()); //마스터 코드라인 수
            q = Integer.parseInt(st.nextToken()); //내 코드라인 수
 
            master = new char[p][];
            own = new char[q][];
             
            result = new int[q];
            Arrays.fill(result,-2); //들여쓰기 계산되지 않았음을 나타낼 플래그로 -2를 사용
 
            for (int i = 0; i < p; i++) {
                master[i] = br.readLine().toCharArray();
            }
 
            for (int i = 0; i < q; i++) {
                own[i] = br.readLine().toCharArray();
            }
            // 범위의 모든 R,C,S를 조합해보면서 마스터의 라인코드가 가능한지 체크후 가능하다면 내 라인코드에 인덴트 처리
            for(int r = 1; r<=20; r++) { // 1<=R<=20
                for(int c = 1; c<=20; c++) {// 1<=C<=20
                    for(int s = 1; s<=20; s++) {// 1<=S<=20
                        if(isAvailable(r,c,s)) { // 마스터의 코드에  rcs 넣어서 체크
                            processIndent(r,c,s); // 내 라인 코드에 대입해서 한줄당 '.'의 갯수 다 기록해놓자!
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
