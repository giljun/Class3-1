
// 사용여부 배열(boolean)을 이용하여 특정 원소를 넣을지 뺄지 경우의 수 계산하기 -> 부분집합 구하기
public class PowerSet {
	public static void main(String[] args) {
		char[] arr = {'a','b','c','d'};
		boolean[] use = new boolean[arr.length];
		
		powerSet(arr, use, 0);
	}
	
	static void powerSet(char[] arr, boolean[] use, int d) {
		if(d == arr.length) {
			// 배열의 크기만큼 선택을 하였음 (넣을지 뺄지)
			// 지금까지 선택해온 내용들은 use 배열에 저장되어 있음. 읽어서  출력만 하면 됨.
			System.out.print("{");
			for(int i=0; i<arr.length; i++) {
				if(use[i]) {
					System.out.print(arr[i]+" ");
				}
			}
			System.out.println("}");
			return;
		}
		use[d] = false;
		powerSet(arr, use, d+1);
		use[d] = true;
		powerSet(arr, use, d+1);
		
	}
}





