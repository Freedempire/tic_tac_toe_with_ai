import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] gradeGroup = {0, 0, 0, 0};

        for (int i = 0; i < n; i++) {
            switch (input.nextInt()) {
                case 2:
                    gradeGroup[0]++;
                    break;
                case 3:
                    gradeGroup[1]++;
                    break;
                case 4:
                    gradeGroup[2]++;
                    break;
                case 5:
                    gradeGroup[3]++;
                    break;
                default:
                    System.out.println("Something is wrong!");
            }
        }

        for (int group : gradeGroup) {
            System.out.print(group + " ");
        }

        System.out.println();
    }
}