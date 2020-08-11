import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        input = new Scanner(line);
        String firstString = input.next();
        while(input.hasNext()) {
            String secondString = input.next();
            if (firstString.compareTo(secondString) > 0) {
                System.out.println("false");
                return;
            }
            firstString = secondString;
        }
        System.out.println("true");
    }
}