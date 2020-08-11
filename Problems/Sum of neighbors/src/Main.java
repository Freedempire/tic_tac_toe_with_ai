import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line;
        StringBuilder numStr = new StringBuilder();
        int countRows = 0;
        int countCols = 0;

        while (!input.hasNext("end"))
        {
            countRows++;
            line = input.nextLine();
            if (countRows == 1) {
                countCols = line.split(" ").length;
            } else {
                numStr.append(" ");
            }
            numStr.append(line);
        }

        int[][] firstMatrix = new int[countRows][countCols];
        int[][] secondMatrix = new int[countRows][countCols];
        String[] numStrs = numStr.toString().split(" ");

        for (int i = 0, k = 0; i < countRows; i++) {
            for (int j = 0; j < countCols; j++, k++) {
                firstMatrix[i][j] = Integer.parseInt(numStrs[k]);
            }
        }

        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countCols; j++) {
                secondMatrix[i][j] = firstMatrix[i - 1 > -1 ? i - 1 : countRows - 1][j] +
                        firstMatrix[i + 1 < countRows ? i + 1 : 0][j] +
                        firstMatrix[i][j - 1 > -1 ? j - 1 : countCols - 1] +
                        firstMatrix[i][j + 1 < countCols ? j + 1 : 0];
            }
        }

        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < countCols; j++) {
                System.out.print(secondMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}