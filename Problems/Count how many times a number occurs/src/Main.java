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
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] == n) {
                count++;
            }
        }
        System.out.println(count);
    }
}