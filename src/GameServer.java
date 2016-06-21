public class GameServer {
    Player black;
    Player white;
    BoardConfiguration configuration;

    public GameServer(Player black, Player white, BoardConfiguration configuration) {
        this.black = black;
        this.white = white;
        this.configuration = configuration;
    }

    public void play() {
        configuration.currentMove = CellState.WHITE;
        while (true) {
            boolean isMoveCorrect = false;
            if (configuration.currentMove == CellState.BLACK)
                System.out.println("black move");
            else if (configuration.currentMove == CellState.WHITE)
                System.out.println("white move");
            if (!black.hasMoves(configuration) && !white.hasMoves(configuration)) {
                System.out.println("draw");
                break;
            }
            if (configuration.currentMove == CellState.BLACK) {

                if (black.hasMoves(configuration)) {
                    try {
                        black.makeMove(configuration);
                        isMoveCorrect = true;
                    } catch (MoveException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (white.hasMoves(configuration)) {
                    System.out.println("white win");
                    break;
                }
            } else {
                if (white.hasMoves(configuration)) {
                    try {
                        white.makeMove(configuration);
                        isMoveCorrect = true;
                    } catch (MoveException e) {
                        System.out.println(e.getMessage());
                    }
                } else if(black.hasMoves(configuration)){
                    System.out.println("black win");
                    break;
                }
            }
//            System.out.println(isMoveCorrect ? "y" : "n");
            if (isMoveCorrect)
                configuration.currentMove = BoardConfiguration.opposite(configuration.currentMove);
        }
    }
}
