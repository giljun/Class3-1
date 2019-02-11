package ssafy0211;

public class Practice3_2 {

    public static void main(String[] args) {

        int[] array = {0x0,0x2,0x6,0x9,0xF,0xA,0xC,0x9,0xA,0x0};
        String[] array2 = {"001101", "010011", "111011", "110001", "100011", "110111", "001011", "111101", "011001", "101111"};
        String str = "";
        for (int i : array) {
            str = str + HexToBinary(i);
        }
        int startIndex = 0;
        while (startIndex + 5 <= str.length() - 1) {
            String a = str.substring(startIndex, startIndex + 6);
            boolean flag = false;
            for (int i = 0; i < 10; i++) {
                if (a.equals(array2[i])) {
                    System.out.print(i + " ");
                    flag = true;
                    break;
                }
            }
            if (flag) {
                startIndex += 6;
            } else {
                startIndex++;
            }
        }

    }

    private static String HexToBinary(int x) {
        String out = "";
        int value = x;
        for (int i = 0; i <= 3; i++) {
            out = value % 2 + out;
            value = value / 2;
        }

        return out;
    }
}
