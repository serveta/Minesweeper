import java.util.Scanner;

public class App {
    static void howToPlay() {
        System.out.println("*** WELCOME to MINESWEEPER ***");
        System.out.println("* Before you start...");
        System.out.println("- You have to indicate the row and column of the game map.");
        System.out.println("- The game map's min size can be 2x2 and the max size can be 10x10.");
        System.out.println("- Let's start the game.");
        System.out.println("************ START ************");
    }

    public static void main(String[] args) {
        howToPlay();

        Minesweeper minesweeper = new Minesweeper();

        minesweeper.startGame();
        minesweeper.printInfos();

        minesweeper.createMap();


        // It's the answer map...
        /*
            If you want to test the algorithm you can open these lines below.
         */
/*
        System.out.println("===== |MINES MAP ======");
        minesweeper.printMinesMap();
        System.out.println("===== MINES MAP| ======");
*/

        minesweeper.gameZone();

    }
}
