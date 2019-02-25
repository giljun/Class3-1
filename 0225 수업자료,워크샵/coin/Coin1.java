package coin;

public class Coin1 {
	static int[] coin = { 1, 4, 6 };
	static int[] coinCnt;

	public static void main(String[] args) {
		int change = 8;
		coinCnt = new int[change + 1]; // 인덱스 0~8까지 사용하기 위해서.
		makeCnt(8);
	}

	static void makeCnt(int change) {
		int min;

		for (int i = 1; i <= change; i++) { // 0원은 그냥 0개이고 1원부터 동전갯수 최적으로 세서 저장해보자.
			min = Integer.MAX_VALUE;

			for (int j = 0; j < coin.length; j++) { // 1,4,6 원만큼 뺐을 때의 갯수 각각 보기.
				// j번째 동전을 사용 가능한 금액 i 인지 &&
				// 현재 i원 금액에서 j번째 동전만큼  뺀 상태의 최적(최소) 갯수
				if (i>=coin[j] && coinCnt[i-coin[j]]<min) {
					min = coinCnt[i-coin[j]];
				}
			}
			coinCnt[i] = min + 1;
			System.out.println(i+"원 최적 동전 갯수:"+coinCnt[i]);
		} // end for i
	}
}




