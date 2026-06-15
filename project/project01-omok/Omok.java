package omok;

public class Omok {
    public static void main(String[] args) {
        Player user = new Player("사용자", "O");
        Player computer = new Player("컴퓨터", "X");

        Board board = new Board(19);

        play(board, user, computer);
    }

    private static void play(Board board, Player user, Player computer) {
        boolean status = false;

        board.print();

        Player turn = user;

        while (!status) {
            board.setStone(turn.name, turn.stone);

            board.print();

            status = board.checkOmok(turn.stone);

            if (status) {
                System.out.println(turn.name + " 이김");
                break;
            }

            turn = (turn == user) ? computer : user;
        }
    }
}
