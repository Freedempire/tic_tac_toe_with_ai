package tictactoe;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    enum GameState {
        UNFINISHED("Game not finished"),
        DRAW("Draw"),
        X_WINS("X wins"),
        O_WINS("O wins");

        String msg;

        GameState(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return msg;
        }
    }

    enum Players {
        USER, EASY, MEDIUM, HARD;

        public static boolean contains(String str) {
            for (Players p : values()) {
                if (p.name().equals(str.toUpperCase())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public static void main(String[] args) {
        boolean run = true;

        while (run) {
            String[] commands = getCommands();

            if (commands[0].equals("exit")) {
                run = false;
                continue;
            }

            Players[] players = {Players.valueOf(commands[1].toUpperCase()), Players.valueOf(commands[2].toUpperCase())};
            GameState gameState;

            Board board = new Board();

            while ((gameState = checkState(board)) == GameState.UNFINISHED) {
                outputField(board.getField());
                makeMove(board, players);
            }

            outputField(board.getField());

            System.out.println(gameState);
        }
    }

    public static String[] getCommands() {
        Scanner input = new Scanner(System.in);
        String commandLine;
        String[] commands;

        while (true) {
            System.out.print("\nInput command: ");
            commandLine = input.nextLine();
            commands = commandLine.split(" ");

            if (commands[0].equals("exit") && commands.length == 1) {
                return commands;
            }
            if (commands.length == 3 && commands[0].equals("start") &&
                Players.contains(commands[1]) && Players.contains(commands[2])) {
                return commands;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void outputField(char[][] field) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");
    }

    public static void readInCoords(Board board) {
        Scanner input = new Scanner(System.in);
        int[] coords = new int[2];
        int num;

        for (int i = 0; i < 2;) {
            if (i == 0)
                System.out.print("Enter the coordinates: ");
            if (!input.hasNextInt()) {
                System.out.println("You should enter numbers!");
                i = 0;
                input.nextLine();
                continue;
            }
            if ((num = input.nextInt()) < 1 || num > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                i = 0;
                input.nextLine();
                continue;
            }

            coords[1 - i] = i == 0 ? num - 1 : 3 - num;

            if (i == 1 && board.getField()[coords[0]][coords[1]] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                i = 0;
                input.nextLine();
                continue;
            }

            i++;
        }

        board.getField()[coords[0]][coords[1]] = board.getTurn();
    }

    public static void aiMove(Board board, Players ai) {
        switch (ai) {
            case EASY:
                easyMove(board);
                break;
            case MEDIUM:
                mediumMove(board);
                break;
            case HARD:
                hardMove(board);
                break;
        }

        System.out.printf("Making move level \"%s\"\n", ai);
    }

    public static void easyMove(Board board) {
        Random random = new Random();
        int randomRow = random.nextInt(board.getECount());
        int i = board.getAvailableCoords()[randomRow][0];
        int j = board.getAvailableCoords()[randomRow][1];
        board.getField()[i][j] = board.getTurn();
//        System.out.printf("%d %d, %c\n", i, j, board.getTurn());
    }

    public static void mediumMove(Board board) {
        char turn = board.getTurn();
        if (mustMove(board, turn));
        else if (mustMove(board, turn == 'X' ? 'O' : 'X'));
        else {
            easyMove(board);
        }
    }

    public static void hardMove(Board board) {
        char xo = board.getTurn();
        int[] score = miniMax(board, xo);

        int i = board.getAvailableCoords()[score[1]][0];
        int j = board.getAvailableCoords()[score[1]][1];
        board.getField()[i][j] = xo;
    }

    public static int[] miniMax(Board board, char xo) {
        int[] scores = new int[board.getECount()];

        for (int k = 0; k < board.getECount(); k++) {
            Board newBoard = new Board(board);
            int i = board.getAvailableCoords()[k][0];
            int j = board.getAvailableCoords()[k][1];
            newBoard.getField()[i][j] = newBoard.getTurn();
            newBoard.update();
            switch (checkState(newBoard)) {
                case DRAW:
                    scores[k] = 0;
                    break;
                case X_WINS:
                    if (xo == 'X') {
                        scores[k] = 10;
                    } else {
                        scores[k] = -10;
                    }
                    break;
                case O_WINS:
                    if (xo == 'X') {
                        scores[k] = -10;
                    } else {
                        scores[k] = 10;
                    }
                    break;
                case UNFINISHED:
                    newBoard.nextTurn();
                    scores[k] = miniMax(newBoard, xo)[0];
                    break;
                default:
                    System.out.println("Error!");
                    break;
            }
        }

        int max = -10;
        int index_max = 0;
        int min = 10;
        int index_min = 0;
        for (int i = 0; i < scores.length; i++) {
            if (i == 0) {
                max = scores[i];
                min = scores[i];
            } else {
                if (max < scores[i]) {
                    max = scores[i];
                    index_max = i;
                }
                if (min > scores[i]) {
                    min = scores[i];
                    index_min = i;
                }
            }
        }
        if (board.getTurn() == xo) {
            return new int[] {max, index_max};
        } else {
            return new int[] {min, index_min};
        }

    }

    public static boolean mustMove(Board board, char xo) {
        char[][] field = board.getField();
        char[][] expandField = board.getExpandField();

        for (int i = 0; i < 8; i++) {
            for (int j = 0, count = 0; j < 3; j++) {
                if (expandField[i][j] == xo) {
                    count++;
                }

                if (count == 2) {
                    for (j = 0; j < 3; j++) {
                        if (expandField[i][j] == ' ') {
                            if (i < 3) {
                                field[i][j] = board.getTurn();
                            } else if (i < 6) {
                                field[j][i - 3] = board.getTurn();
                            } else if (i == 6) {
                                field[j][j] = board.getTurn();
                            } else {
                                field[j][2 - j] = board.getTurn();
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static GameState checkState(Board board) {
        char[][] expandField = board.getExpandField();
        char[] fullXRow = {'X', 'X', 'X'};
        char[] fullORow = {'O', 'O', 'O'};
        int fullXRowCount = 0;
        int fullORowCount = 0;

        for (char[] row : expandField) {
            if (Arrays.equals(row, fullXRow))
                fullXRowCount++;
            else if (Arrays.equals(row, fullORow))
                fullORowCount++;
        }

        if (fullXRowCount > 0)
            return GameState.X_WINS;
        if (fullORowCount > 0)
            return GameState.O_WINS;
        if (board.getECount() > 0)
            return GameState.UNFINISHED;
        return GameState.DRAW;
    }

    public static void makeMove(Board board, Players[] players) {
        if (board.getTurn() == 'X') {
            if (players[0] == Players.USER) {
                readInCoords(board);
            } else {
                aiMove(board, players[0]);
            }
        } else {
            if (players[1] == Players.USER) {
                readInCoords(board);
            } else {
                aiMove(board, players[1]);
            }
        }

        board.nextTurn();
    }
}

class Board {
    private final char[][] field = new char[3][3];
    private final char[][] expandField = new char[8][3];
    private int eCount;
    private int[][] availableCoords;
    private char turn;

    Board() {
        initField();
        update();
        turn = 'X';
    }

    Board(Board board) {
        for (int i = 0; i < board.field.length; i++) {
            field[i] = Arrays.copyOf(board.field[i], 3);
        }

        for (int i = 0; i < board.expandField.length; i++) {
            expandField[i] = Arrays.copyOf(board.expandField[i], 3);
        }

        eCount = board.eCount;

        availableCoords = new int[eCount][2];
        for (int i = 0; i < board.availableCoords.length; i++) {
            availableCoords[i] = Arrays.copyOf(board.availableCoords[i], 2);
        }

        turn = board.turn;
    }

    public void initField() {
        for (char[] row : field) {
            Arrays.fill(row, ' ');
        }
    }

    public void update() {
        eCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                expandField[i][j] = field[i][j];
                expandField[j + 3][i] = field[i][j];
                if (i == j)
                    expandField[6][i] = field[i][j];
                if (i + j == 2)
                    expandField[7][i] = field[i][j];
                if (field[i][j] == ' ')
                    eCount++;
            }
        }

        availableCoords = new int[eCount][2];
        for (int i = 0, k = 0; i < 3 && k < eCount; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' '){
                    availableCoords[k][0] = i;
                    availableCoords[k][1] = j;
                    k++;
                }
            }
        }
    }

    public char[][] getField() {
        return field;
    }

    public char[][] getExpandField() {
        return expandField;
    }

    public int getECount() {
        return eCount;
    }

    public int[][] getAvailableCoords() {
        return availableCoords;
    }

    public char getTurn() {
        return turn;
    }

    public void nextTurn() {
        update();

        if (turn == 'X') {
            turn = 'O';
        } else {
            turn = 'X';
        }
    }
}
