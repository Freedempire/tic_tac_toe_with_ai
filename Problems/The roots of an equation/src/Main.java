import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();
        int d = input.nextInt();

        for (int x = 0, rootCount = 0; x < 1001 && rootCount < 3; x++) {
            if (a * x * x * x + b * x * x + c * x + d == 0) {
                rootCount++;
                System.out.println(x);
            }
        }
    }
}