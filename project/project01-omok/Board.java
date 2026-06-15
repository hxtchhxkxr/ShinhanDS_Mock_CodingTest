package omok;

import java.util.Scanner;

public class Board {
    int size;
    String[][] map;
    Scanner sc = new Scanner(System.in);

    Board(int size) {
        this.size = size;
        map = new String[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                map[row][col] = ".";
            }
        }
    }

    public void print() {
        for (int row = 0; row < size; row++) {
            System.out.printf("%2d", row);

            for (int col = 0; col < size; col++) {
                System.out.print(" " + map[row][col]);
            }

            System.out.println();
        }

        System.out.print("   ");

        for (int i = 65; i < 65 + size; i++) {
            System.out.printf("%1c ", (char) i);
        }

        System.out.println();
    }

    public void setStone(String turn, String stone) {
        while (true) {
            try {
                System.out.print(turn + "> ");
                String input = sc.nextLine();

                int idx = input.indexOf(" ");

                String colStr = input.substring(0, idx).toUpperCase();
                int col = colStr.charAt(0) - 'A';

                int row = Integer.parseInt(input.substring(idx + 1));

                if (row < 0 || row >= size || col < 0 || col >= size) {
                    System.out.println("범위를 벗어났습니다.");
                    continue;
                }

                if (!".".equals(map[row][col])) {
                    System.out.println("이미 놓아진 위치");
                    continue;
                }

                map[row][col] = stone;
                break;

            } catch (Exception e) {
                System.out.println("입력 형식 오류. 예: A 3");
            }
        }
    }

    public boolean checkOmok(String stone) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                // 가로
                if (col + 4 < size) {
                    if (
                        map[row][col].equals(stone) &&
                        map[row][col + 1].equals(stone) &&
                        map[row][col + 2].equals(stone) &&
                        map[row][col + 3].equals(stone) &&
                        map[row][col + 4].equals(stone)
                    ) {
                        return true;
                    }
                }

                // 세로
                if (row + 4 < size) {
                    if (
                        map[row][col].equals(stone) &&
                        map[row + 1][col].equals(stone) &&
                        map[row + 2][col].equals(stone) &&
                        map[row + 3][col].equals(stone) &&
                        map[row + 4][col].equals(stone)
                    ) {
                        return true;
                    }
                }

                // 우하 대각선
                if (row + 4 < size && col + 4 < size) {
                    if (
                        map[row][col].equals(stone) &&
                        map[row + 1][col + 1].equals(stone) &&
                        map[row + 2][col + 2].equals(stone) &&
                        map[row + 3][col + 3].equals(stone) &&
                        map[row + 4][col + 4].equals(stone)
                    ) {
                        return true;
                    }
                }

                // 우상 대각선
                if (row - 4 >= 0 && col + 4 < size) {
                    if (
                        map[row][col].equals(stone) &&
                        map[row - 1][col + 1].equals(stone) &&
                        map[row - 2][col + 2].equals(stone) &&
                        map[row - 3][col + 3].equals(stone) &&
                        map[row - 4][col + 4].equals(stone)
                    ) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
