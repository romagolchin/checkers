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
        int win;
        boolean gameContinues = true;
        configuration.currentMove = CellState.WHITE;
        while (gameContinues) {
            if(configuration.currentMove == CellState.BLACK)
                System.out.println("black move");
            else if(configuration.currentMove == CellState.WHITE)
                System.out.println("white move");
            if (configuration.currentMove == CellState.BLACK) {
                gameContinues &= black.hasMoves(configuration) && white.hasMoves(configuration);
                if (black.hasMoves(configuration)) {
                    try {
                        black.makeMove(configuration);
                    } catch (MoveException e) {
                        e.printStackTrace();
                    }
                } else {
                    configuration.currentMove = CellState.WHITE;
                    try {
                        white.makeMove(configuration);
                    } catch (MoveException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                gameContinues &= black.hasMoves(configuration) && white.hasMoves(configuration);
                if (white.hasMoves(configuration)) {
                    try {
                        white.makeMove(configuration);
                    } catch (MoveException e) {
                        e.printStackTrace();
                    }
                } else {
                    configuration.currentMove = CellState.BLACK;
                    try {
                        black.makeMove(configuration);
                    } catch (MoveException e) {
                        e.printStackTrace();
                    }
                }
            }
            configuration.currentMove = BoardConfiguration.opposite(configuration.currentMove);
        }
    }
}
