import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        long seed = a + b;
        Random random = new Random(seed);
        int lengthInterval = b - a + 1;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += random.nextInt(lengthInterval) + a;
        }

        System.out.println(sum);
    }
}