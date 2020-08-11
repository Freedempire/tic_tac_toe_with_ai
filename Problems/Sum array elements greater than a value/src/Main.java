import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = input.nextInt();
        }

        int n = input.nextInt();
        int sum = 0;
        for (int num : array) {
            if (num > n) {
                sum += num;
            }
        }
        System.out.println(sum);
    }
}