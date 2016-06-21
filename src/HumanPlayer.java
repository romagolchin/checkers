

import java.util.Scanner;
import java.util.regex.Pattern;

import static java.lang.Character.isSpaceChar;


public class HumanPlayer implements Player {
    public HumanPlayer() {
    }

    //reads moves in the form a1 b2 ..
    //if format is incorrect MoveException is thrown
    public void makeMove(BoardConfiguration configuration) throws MoveException {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        String move = input.next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < move.length(); ++i) {
            if (!isSpaceChar(move.charAt(i)) && !Character.isWhitespace(move.charAt(i)))
                sb.append(move.charAt(i));
        }
        String trimmedMove = new String(sb);
        if((trimmedMove.length() % 2 != 0) || (trimmedMove.length() <= 2))
            throw new MoveException("invalid move format");
        for(int i = 0; i < trimmedMove.length() / 2 - 1; ++i) {
            int x = (int) trimmedMove.charAt(2 * i) - (int) 'a';
            int y = (int) trimmedMove.charAt(2 * i + 1) - (int) '1';
            int newX = (int) trimmedMove.charAt(2 * i + 2) - (int) 'a';
            int newY = (int) trimmedMove.charAt(2 * i + 3) - (int) '1';
            int dx = newX - x;
            int dy = newY - y;
            if(!configuration.isDirectionValid(dx, dy))
                throw new MoveException("invalid move from " + (char) ((int) 'a' + x) + " " + (char) ((int) 'a' + y) + " to " + newX + " " + newY);
            if(Math.abs(dx) == Math.abs(dy)) {
                if(Math.abs(dx) == 1 && configuration.board[x][y] == configuration.currentMove && configuration.isFree(x + dx, y + dy)) {
                    configuration.board[x][y] = CellState.NONE;
                    configuration.board[x + dx][y + dy] = configuration.currentMove;
                }
                else if(Math.abs(dx) == 2 && configuration.board[x][y] == configuration.currentMove
                        && configuration.board[x + dx / 2][y + dy / 2] == BoardConfiguration.opposite(configuration.currentMove)) {
                    configuration.board[x][y] = CellState.NONE;
                    configuration.board[x + dx / 2][y + dy / 2] = CellState.NONE;
                    configuration.board[x + dx][y + dy] = configuration.currentMove;
                }
                else
                    throw new MoveException("invalid move");
            }
        }

    }
}
