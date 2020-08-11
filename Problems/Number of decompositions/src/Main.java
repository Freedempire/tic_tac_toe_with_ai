import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        decompose(n);
    }

    public static void decompose(int n, int max, String prefix) {
        if (n == 0) {
            System.out.println(prefix);
            return;
        }

//        for (int i = Math.min(max, n); i >= 1; i--) {
//            decompose(n - i, i, prefix + i + " ");
//        }
        for (int i = 1; i <= Math.min(n, max); i++) {
            decompose(n - i, i, prefix + i + " ");
        }

    }

    public static void decompose(int n) {
        decompose(n, n, "");
    }
}