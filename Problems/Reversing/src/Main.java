import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[] digits = new int[3];

        for (int i = 0; i < 3; i++) {
            digits[i] = number % 10;
            number /= 10;
        }

        for (int i = 0; i <3 ; i++) {
            if (i == 0 & digits[i] == 0) {
                continue;
            }
            System.out.print(digits[i]);
        }

        System.out.println();
    }
}