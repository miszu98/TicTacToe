package com.company;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    String[][] allPicks;
    String player1;
    String aiPlayer;
    int row;
    int col;
    Scanner scanner;
    boolean game = true;


    protected Game() {
        this.allPicks = new String[3][3];
        for (int i=0; i<3; i++) {
            allPicks[i][0] = " ";
            allPicks[i][1] = " ";
            allPicks[i][2] = " ";
        }
    }

    protected void play() throws InterruptedException {
        System.out.println("Tic Tac Toe");
        initializePlayers();
        draw();
        while (game) {
            System.out.print("Your turn " + getPlayer1() + ":\n");
            System.out.println("Which row: ");
            row = scanner.nextInt();
            System.out.println("Which col: ");
            col = scanner.nextInt();
            if (!selectPosition(row, col, getPlayer1())) {
                System.out.println("You choose a wrong position try again.");
                continue;
            } else {
                draw();
            }
            TimeUnit.SECONDS.sleep(1);
            aiBot();

            System.out.println(checkBoard());
        }
    }

    protected void aiBot() throws InterruptedException {
        Random random = new Random();

        while (allPicks[row][col].equals("X") || allPicks[row][col].equals("O")) {
            row = random.ints(0, 3).findFirst().getAsInt();
            col = random.ints(0, 3).findFirst().getAsInt();
        }
        selectPosition(row, col, getAiPlayer());
        System.out.println(String.format("%s choose [%d, %d]", getAiPlayer(), row, col));
        draw();
    }


    protected void initializePlayers() {
        scanner = new Scanner(System.in);
        System.out.print("What's your name: ");
        setPlayer1(scanner.nextLine());
        setAiPlayer("Jeff");
        System.out.println("Okay, now we can play. Let's start...");
    }

    protected void setPlayer1(String player1) {
        this.player1 = player1;
    }

    protected void setAiPlayer(String aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    protected String getPlayer1() {
        return player1;
    }

    protected String getAiPlayer() {
        return aiPlayer;
    }

    protected boolean selectPosition(int x, int y, String whichPlayer) {
        if (allPicks[x][y].equals(" ")) {
            allPicks[x][y] =  whichPlayer.equals("Jeff") ? "O" : "X";
            return true;
        }
        return false;
    }

    protected void draw() {
        System.out.println("-------------");
        for (int i=0; i<3; i++) {
            System.out.println(String.format("| %s | %s | %s |", allPicks[i][0], allPicks[i][1], allPicks[i][2]));
            System.out.println("-------------");
        }
    }

    protected String checkBoard() {
        for (int i=0; i<3; i++) {
            if (allPicks[i][0].equals("X") && allPicks[i][1].equals("X") && allPicks[i][2].equals("X")) {
                game = false;
                return "Winner is " + getPlayer1();
            } else if (allPicks[i][0].equals("O") && allPicks[i][1].equals("O") && allPicks[i][2].equals("O")) {
                game = false;
                return "Winner is " + getAiPlayer();
            }

            if (allPicks[0][i].equals("X") && allPicks[1][i].equals("X") && allPicks[2][i].equals("X")) {
                game = false;
                return "Winner is " + getPlayer1();
            } else if (allPicks[0][i].equals("O") && allPicks[1][i].equals("O") && allPicks[2][i].equals("O")) {
                game = false;
                return "Winner is " + getAiPlayer();
            }

            if (allPicks[0][0].equals("X") && allPicks[1][1].equals("X") && allPicks[2][2].equals("X")) {
                game = false;
                return "Winner is " + getPlayer1();
            } else if (allPicks[0][0].equals("O") && allPicks[1][1].equals("O") && allPicks[2][2].equals("O")) {
                game = false;
                return "Winner is " + getAiPlayer();
            }

            if (allPicks[0][2].equals("X") && allPicks[1][1].equals("X") && allPicks[2][0].equals("X")) {
                game = false;
                return "Winner is " + getPlayer1();
            } else if (allPicks[0][2].equals("O") && allPicks[1][1].equals("O") && allPicks[2][0].equals("O")) {
                game = false;
                return "Winner is " + getAiPlayer();
            }
        }
        return "";
    }
}
