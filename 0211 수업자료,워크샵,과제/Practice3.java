package ssafy0211;
import java.util.Scanner;

public class Practice3 {
    // 변환하고자 하는 16진수 1개와 해당 이진수를 같은 인덱스로 맞춘 2개의 배열을 이용해서 2진수로 바꾸기
	static String[] hexa2bin={
			"0000", "0001", "0010", "0011", "0100", "0101", 
					"0110", "0111", "1000", "1001", "1010", 
					"1011", "1100", "1101", "1110", "1111"	};
    static char[] num={
    		'0', '1', '2', '3', '4', '5', 
    			 '6', '7', '8', '9', 'a', 
    			 'b', 'c', 'd', 'e', 'f'};

    static String[] secret = 
    	{"001101","010011","111011","110001","100011","110111","001011","111101","011001","101111"};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputHex = sc.next().toLowerCase();
        String hex2bin = "";
        for(int i=0; i<inputHex.length(); i++){ // 입력받은 문자열의 길이만큼 돌면서
            for(int j=0; j<16; j++){ // 문자 하나씩 뜯어서 같은 인덱스의 이진수 찾아다 붙이기
                if(num[j]==inputHex.charAt(i)){
                    hex2bin += hexa2bin[j];
                }
            }
        }

        String result="";
        for(int i=hex2bin.length()-1; i>=0; i--){ // 오른쪽부터 왼쪽으로 이동하면서 1 찾아내기
            if(hex2bin.charAt(i)=='1'){ // 1을 찾았으면 거기부터 6개씩 짤라서 암호코드 일치여부 찾기
                String tempStr = hex2bin.substring(i-5,i+1);
                for(int j=0; j<10;j++){ // 암호코드에 해당하는 숫자 j 찾기
                    if(tempStr.equals(secret[j])){
                        result += j; // 암호코드에 해당하는 숫자 j 를 결과에 이어붙이기
                        i=i-5;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder(result);
        System.out.println(sb.reverse().toString());

    }
}
