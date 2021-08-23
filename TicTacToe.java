package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private StatusPlace[][] gride;

    public TicTacToe() {

        gride = new StatusPlace[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gride[i][j] = StatusPlace.EMP;
            }
        }

    }

    public void showGride() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf(gride[i][j] + " ");
            }
            System.out.printf("\n");
        }

    }

    public void showInstructions() {
        System.out.println("Existem 3 linhas e 3 colunas no jogo da velha.");
        System.out.println("Utilize numeros inteiros de 1 a 3 para fazer suas jogada.");
        System.out.printf("\n");
    }

    public boolean isPlayerOneTurn(int turnControl) {
        return turnControl % 2 == 0;
    }

    public boolean loss() {

        for (int i = 0; i < 3; i++) { // Verifica se houve uma derrota nas LINHAS e COLUNAS
            for (int j = 0; j < 1; j++) {
                if (gride[i][j] != StatusPlace.EMP && gride[i][j] == gride[i][j + 1]
                        && gride[i][j + 1] == gride[i][j + 2]) {
                    return true;
                }
                if (gride[j][i] != StatusPlace.EMP && gride[j][i] == gride[j + 1][i]
                        && gride[j + 1][i] == gride[j + 2][i]) {
                    return true;
                }
            }
        }

        for (int i = 0; i < 1; i++) { // Verifica se houve uma derrota nas DIAGONAIS
            if (gride[i][i] != StatusPlace.EMP && gride[i][i] == gride[i + 1][i + 1]
                    && gride[i + 1][i + 1] == gride[i + 2][i + 2]) {
                return true;
            }
            if (gride[i][i + 2] != StatusPlace.EMP && gride[i][i + 2] == gride[i + 1][i + 1]
                    && gride[i + 1][i + 1] == gride[i + 2][i]) {
                return true;
            }
        }

        return false;
    }

    public void play() {

        Scanner read = new Scanner(System.in);
        int row = 0, column = 0, turnControl = 0;

        showInstructions();

        do {
            showGride();

            if (isPlayerOneTurn(turnControl)) {
                System.out.println("É a vez do Jogador 1");
            } else {
                System.out.println("É a vez do Jogador 2");
            }

            do {
                System.out.printf("Informe a linha que deseja jogar: ");
                row = read.nextInt();
                if (row < 1 || row > 3) {
                    System.err.println("As linhas validas sao de 1 a 3. Tente novamente.");
                }
            } while (row < 1 || row > 3);

            do {
                System.out.printf("Informe a coluna que deseja jogar: ");
                column = read.nextInt();
                if (column < 1 || column > 3) {
                    System.err.println("As colunas validas sao de 1 a 3. Tente novamente.");
                }
            } while (column < 1 || column > 3);

            if (gride[row - 1][column - 1] == StatusPlace.EMP) {
                if (isPlayerOneTurn(turnControl)) {
                    gride[row - 1][column - 1] = StatusPlace.ONE;
                } else {
                    gride[row - 1][column - 1] = StatusPlace.TWO;
                }
                turnControl++;
            } else {
                System.err.println("Esta casa não está vazia. Tente novamente.");
            }

            System.out.printf("\n");

        } while (!loss() && turnControl != 9);

        if (turnControl == 9) {
            System.out.println("Houve um empate.");
        } else {
            System.out.println("Houve uma derrota.");
        }

        showGride();

    }
}
