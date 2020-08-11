import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.nextInt();
            }
        }

        boolean backslashSymmetry = true;
//        boolean slashSymmetry = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                     backslashSymmetry = false;
                }
//                if (matrix[i][j] != matrix[n - 1 - j][n - 1 - i]) {
//                    slashSymmetry = false;
//                }
                if (!backslashSymmetry) {
                    System.out.println("NO");
                    return;
                }
//                if (!(backslashSymmetry || slashSymmetry)) {
//                    System.out.println("NO");
//                    return;
//                }
            }
        }

        System.out.println("YES");
    }
}