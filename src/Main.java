/**
 * Created by Roman on 20/06/2016.
 */
public class Main {
    public static void main(String[] args) {
        HumanPlayer black = new HumanPlayer();
        HumanPlayer white = new HumanPlayer();
        BoardConfiguration bc = new BoardConfiguration();
        GameServer gs = new GameServer(black, white, bc);
        gs.play();
    }
}
