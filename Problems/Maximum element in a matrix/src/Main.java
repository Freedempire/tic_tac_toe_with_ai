import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] indexes = new int[2];
        int max = 0;
        int temp;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp = input.nextInt();

                if (i == 0 && j == 0 || max < temp) {
                    max = temp;
                    indexes[0] = i;
                    indexes[1] = j;
                }
            }
        }

        System.out.printf("%d %d", indexes[0], indexes[1]);
    }
}