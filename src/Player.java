public interface Player {


    void makeMove(BoardConfiguration configuration) throws MoveException;

    default boolean hasMoves(BoardConfiguration configuration) {
        boolean res = false;
        for(int x = 0; x < 8; ++x)
            for(int y = 0; y < 8; ++y) {
                for(int k = 0; k < 4; ++k) {
                    int dx = BoardConfiguration.dirs[k][0];
                    int dy = BoardConfiguration.dirs[k][1];
                    res |= (configuration.isFree(x + dx, y + dy));
                    res |= (configuration.inBoard(x + dx, y + dy) &&
                            configuration.board[x + dx][y + dy] ==
                            BoardConfiguration.opposite(configuration.currentMove)
                    && configuration.isFree(x + dx * 2, y + dy * 2));
                }
            }
        return res;
    }
}
