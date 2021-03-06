
enum CellState {
    NONE, BLACK, WHITE;
}


public class BoardConfiguration {
    public CellState[][] board;
    public CellState currentMove;
    public static int[][] dirs = {{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};

    public static CellState opposite(CellState cs) {
        CellState res = CellState.NONE;
        switch (cs) {
            case WHITE:
                res = CellState.BLACK;
                break;
            case BLACK:
                res = CellState.WHITE;
                break;
            case NONE:
                res = CellState.NONE;
        }
        return res;
    }

    public BoardConfiguration() {
        board = new CellState[8][8];
        for (int x = 0; x < 8; ++x)
            for (int y = 0; y < 8; ++y)
                if (((x + y) % 2 != 0) || (y > 2 && y < 5))
                    board[x][y] = CellState.NONE;
                else if (y <= 2 && ((x + y) % 2 == 0))
                    board[x][y] = CellState.WHITE;
                else if (y >= 5 && ((x + y) % 2 == 0))
                    board[x][y] = CellState.BLACK;
    }

    public boolean inBoard(int x, int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    public boolean isFree(int x, int y) {
        return inBoard(x, y) && (board[x][y] == CellState.NONE);
    }

    public boolean isDirectionValid(int dx, int dy) {
        return !(Math.abs(dx) == 1 && Math.abs(dy) == 1
                && ((dy < 0 && currentMove == CellState.WHITE) || (dy > 0 && currentMove == CellState.BLACK)));
    }

    public boolean isPossibleToTake() {
        boolean res = false;
        for(int x = 0; x < 8; ++x)
            for(int y = 0; y < 8; ++y)
                if(board[x][y] == currentMove)
                    for(int i = 0; i < 4; ++i) {
                        int dx = dirs[i][0];
                        int dy = dirs[i][1];
                        if(inBoard(x + dx, y + dy) && board[x + dx][y + dy] == opposite(currentMove) && isFree(x + 2 * dx, y + 2 * dy)) {
                            res = true;
                            break;
                        }
                    }
        return res;
    }
}
