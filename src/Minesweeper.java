import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    String[][] map;
    String[][] minesMap;
    int row;
    int column;
    int mine;
    String mineMark = " ✦ "; //"** ";//"☠ "; //
    String boxMark = "██ ";

    Minesweeper() {

    }

    int mineRatio() {
        return (this.row * this.column) / 4;
    }

    void createMinesMap() {
        int mineCount = this.mine;

        while (mineCount > 0) {
            int row = (int) (Math.random() * this.row);
            int column = (int) (Math.random() * this.column);

            if (!this.minesMap[row][column].equals(this.mineMark)) {
                this.minesMap[row][column] = this.mineMark;
                mineCount--;
            }
        }
    }

    void createMap() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.map[i][j] = this.boxMark;
                this.minesMap[i][j] = this.boxMark;
            }
        }

        createMinesMap();
    }

    void printMinesMap() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.minesMap[i][j]);
            }
            System.out.println();
        }
    }

    void printMap() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean isSuitable(int num, int minNum, int maxNum) {
        return num >= minNum && num <= maxNum;
    }

    void startGame() {
        Scanner input = new Scanner(System.in);

        int minRowAndColumn = 2;
        int maxRowAndColumn = 10;

        while (this.row > maxRowAndColumn || this.row < minRowAndColumn) {
            System.out.print("Row: ");
            this.row = input.nextInt();
            if (!isSuitable(this.row, minRowAndColumn, maxRowAndColumn)) {
                System.out.println("* The row must be in the range of 2-10.");
            }
        }
        while (this.column > maxRowAndColumn || this.column < minRowAndColumn) {
            System.out.print("Column: ");
            this.column = input.nextInt();
            if (!isSuitable(this.column, minRowAndColumn, maxRowAndColumn)) {
                System.out.println("* The column must be in the range of 2-10.");
            }
        }

        this.map = new String[this.row][this.column];
        this.minesMap = new String[this.row][this.column];
        this.mine = mineRatio();

    }

    void printInfos() {
        System.out.println();
        System.out.println("*** INFOS ***");
        System.out.println("Mine: " + this.mine);
        System.out.println("Mine Mark: " + this.mineMark);
        System.out.println("Box Mark : " + this.boxMark);
        System.out.println("*************");
    }

    void gameZone() {
        Scanner input = new Scanner(System.in);
        int openRow = 0;
        int openCol = 0;
        int winCounter = (this.row * this.column) - this.mine;
        boolean isWin = false;
        boolean isGameOver = false;

        while (!isWin && !isGameOver) {
            openRow = 0;
            openCol = 0;
            System.out.println("==================");
            printMap();

            while (openRow <= 0 || openRow > this.row) {
                System.out.print("Row: ");
                openRow = input.nextInt();
                if (openRow <= 0 || openRow > this.row) {
                    System.out.println("The row cannot be outside of 1-" + this.row);
                }
            }
            while (openCol <= 0 || openCol > this.column) {
                System.out.print("Column: ");
                openCol = input.nextInt();
                if (openCol <= 0 || openCol > this.column) {
                    System.out.println("The column cannot be outside of 1-" + this.column);
                }
            }

            openRow -= 1;
            openCol -= 1;

            if (this.minesMap[openRow][openCol].equals(this.mineMark)) {
                isGameOver = true;
                this.map[openRow][openCol] = this.mineMark;
            } else if (this.minesMap[openRow][openCol].equals(this.boxMark) && this.map[openRow][openCol].equals(this.boxMark)) {
                this.map[openRow][openCol] = minesControl(openRow, openCol);
                winCounter--;
            } else {
                System.out.println("* (" + (++openRow) + "," + (++openCol) + ") is already open!");
            }

            if (winCounter == 0) {
                isWin = true;
                System.out.println("*** YOU WIN ***");
                printMap();
            } else if (isGameOver) {
                System.out.println("*** GAME OVER ***");
                printMap();
            }
        }
    }

    private String minesControl(int openRow, int openCol) {
        int count = 0;
        String countStr = "";

        int startI = openRow == 0 ? 0 : openRow - 1;
        int endI = openRow == (this.row - 1) ? (this.row - 1) : openRow + 1;
        int startJ = openCol == 0 ? 0 : openCol - 1;
        int endJ = openCol == (this.column - 1) ? (this.column - 1) : openCol + 1;

        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                if (this.minesMap[i][j].equals(this.mineMark)) {
                    count++;
                }
            }
        }

        countStr = Integer.toString(count);
        countStr = " " + countStr + " ";

        return countStr;
    }
}
