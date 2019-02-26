package lis;

import java.util.Arrays;

// 최장증가수열(Longest Increasing Subsequence)의 길이 구하기
public class LIS_bin {
	
	public static void main(String[] args) {
		int[] arr = {8,2,4,3,6,11,7,10,5};
		
		System.out.println("최장증가수열의 길이:"+LIS(arr));
	}
	
	static int LIS(int[] arr) {
		int[] tail = new int[arr.length];

		int len = 1; //최장증가수열의 크기를 기록할 변수
		tail[0] = arr[0]; // 증가수열 만드는 첫 시점에는 뒤로 증가가 얼마나 있을지 없을지 몰라서 일단 처음값 넣기
		
		for(int i=1; i<arr.length; i++) {
			if(arr[i] < tail[0]) { // 어라 ? 새로운 최소 시작값이다.
				tail[0] = arr[i];
			}else if(arr[i] > tail[len-1]) { // len 변수는 크기를 기록하다 보니 배열 인덱스보다 1 큼.
				tail[len] = arr[i]; // 앞에 등장하던 수들보다 가장 큰 arr[i] 이면 최장증가수열의 크기를 1 늘리면 됨.
				len++;
			}else { // 가장 작은값도 가장 큰값도 아닌 어떤 arr[i]의 경우 lis 크기에 영향을 미치지 않는 선에서 중간위치에 덮어씌우기
				tail[findIdx(tail, 0, len-1, arr[i])] = arr[i];				
			}
			System.out.println(Arrays.toString(tail));
		}
		
		return len;		
	}
	
	static int findIdx(int[] arr, int left, int right, int key) {
		while(right - left > 1) {
			int mid = (right+left)/2;
			if(arr[mid] < key)
				left = mid;
			else 
				right = mid;
		}
		return right;
	}
	
}








