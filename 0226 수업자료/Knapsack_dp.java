package knapsack;

import java.util.Arrays;
import java.util.Scanner;

public class Knapsack_dp {
	static int[][] maxPrice; // 가방의 크기를 점진적으로 증가시키면서 그때의 최대 값어치를 저장할 배열
	static Product[] productArr; // 가방에 담을 물건들
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = 10; // 가방 사이즈
		int cnt = 4; // 물건 갯수
		
		productArr = new Product[cnt+1]; // 물건 목록 (1번부터 사용)
		maxPrice = new int[cnt+1][size+1]; // 물건 갯수만큼의 행, 가방 사이즈 만큼의 열
		
		for(int i=1; i<productArr.length; i++) {
			int w = sc.nextInt(); // 무게 입력
			int p = sc.nextInt(); // 가격 입력
			
			productArr[i] = new Product(w, p);
		}
		
		for(int i=1; i<productArr.length; i++) { // 각 물건들에 대해 반복 (i=0인 경우는 물건을 하나도 넣지 않은 상태)
			for(int j=1; j<=size; j++) { // 가방의 크기를 점진적으로 늘려가면서 i번 물건을 넣는경우와 넣기 전의 가치 비교
				if(productArr[i].weight > j) { // i번째 물건이 현재 j 사이즈 가방에 넣지 못하게 무거움
					maxPrice[i][j] = maxPrice[i-1][j]; // 가방이 지금보다 1 작을때의 값을 그냥 넣음.(현상 유지. 변경없음)
				}else { // 현재 가방크기 j가 i번째 물건 수용 가능 상태이면 i번째 물건을 넣기 전과 억지로 공간을 확보해서 i 물건을 넣었을 때의 가치 비교.
					int p1 = maxPrice[i-1][j];
					int p2 = maxPrice[i-1][j-productArr[i].weight] + productArr[i].price;
					
					maxPrice[i][j] = Math.max(p1, p2);
				}
			}
		}
		
		System.out.println(Arrays.deepToString(maxPrice));
	}
	
	static class Product{
		int weight; // 물건의 무게
		int price; // 물건의 가격(가치)
		Product(int w, int p){
			weight = w;
			price = p;
		}
	}
}
