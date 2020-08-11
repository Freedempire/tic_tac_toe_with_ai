import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }

        int m = input.nextInt();
        int n = input.nextInt();
        int mIndex = -1;
        int nIndex = -1;

        for (int i = 0; i < size; i++) {
            if (m != n) {
                if (array[i] == m) {
                    mIndex = i;
                } else if (array[i] == n) {
                    nIndex = i;
                }
            } else {
                if (mIndex == -1 && array[i] == m) {
                    mIndex = i;
                } else if (array[i] == m) {
                    nIndex = i;
                }
            }

            if (mIndex != -1 && nIndex != -1 && Math.abs(mIndex - nIndex) == 1) {
                System.out.println("true");
                return;
            }
        }

        System.out.println("false");
    }
}