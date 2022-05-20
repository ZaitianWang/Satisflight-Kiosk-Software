package cardTest;

import card.BoardingPassCard;
import frame.MainFrame;
import main.State;

/**
 * This is the test class of BoardingPassCard.
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.0
 */

public class BoardingPassCardTest {
    public static void main(String[] args) {
        State.setSeatColumn(1);
        MainFrame frame = new MainFrame();
        frame.loadPanel(new BoardingPassCard("CA0001"));
        frame.setVisible(true);
    }
}
