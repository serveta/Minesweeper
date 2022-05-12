import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    String[][] map;
    String[][] userMap;
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

    void createMap() {
        int mineLocation;
        int mineCount = this.mine;
        Random randomLocation = new Random();

        for (int i = 0; i < this.map.length; i++) {
            int newRow = 0;
            for (int j = 0; j < this.map[i].length; j++) {
                mineLocation = randomLocation.nextInt(100);

                if (mineCount-- > 0 && mineLocation < 50){
                    this.map[i][j] = this.mineMark;
                } else {
                    this.map[i][j] = this.boxMark;
                }
            }
        }

    }

    void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
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
        this.userMap = new String[this.row][this.column];
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
}
