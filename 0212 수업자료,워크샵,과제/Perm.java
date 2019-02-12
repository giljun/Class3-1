import java.util.Stack;

// nPr 계산해보기(특정 원소를 선택한 이후에 또 선택하는거 안하고 싶어서 boolean 배열로 표시해놓으면서 순열 만들어보기)
public class Perm {
	static int[] arr = {1,2,3,4,5};
	static boolean[] used = new boolean[arr.length];
	static Stack<Integer> result = new Stack<>();
	static int cnt=0;
	public static void main(String[] args) {
		permutation(0, 2); // arr 에서 몇개 뽑아서 순열 만들지
		System.out.println("경우의수:"+cnt);
	}
	
	static void permutation(int d, int r) {
		if(d == r) { // r개만큼 재귀 호출해서 선택을 했으면 출력하기.
			System.out.println(result);
			cnt++;
			return;
		}
		
		for(int i=0; i<arr.length; i++) {
			if(!used[i]) { // i번째 원소가 만들고 있는 순열에 들어간 적이 없는 애면
				used[i] = true; // 지금 원소 스택에 넣을거니까 이후의 재귀들은 이 숫자는 쓰지 마라
				result.push(arr[i]); // 넣자! d==r이 아니어서 넣어본 거니까 그 이후는 알아서 해라
				permutation(d+1, r); // r개 뽑아서 출력한 후 이 문장이 종료 됬으면
				result.pop();
				used[i] = false; // 이후에 현재 넣었던 원소를 순열에 넣어보고 싶으면 해도 된다는 표시 해놓기				
			}
		}		
	}
}







