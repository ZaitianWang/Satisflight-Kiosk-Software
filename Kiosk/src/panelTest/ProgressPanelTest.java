package panelTest;

import javax.swing.JPanel;

import card.SeatSelectionCard;
import frame.*;
import panel.*;

public class ProgressPanelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame testMainFrame = new MainFrame(1);
        ProgressPanel testProgressPanel = new ProgressPanel(0);
        testProgressPanel.loadCards(new SeatSelectionCard( "AC0001",
                "Normal", "Legroom Pro",
                "Legroom Max", "Legroom Ultra",
                0, 10, 20, 50));
        testMainFrame.loadPanel(testProgressPanel);
        testMainFrame.displayComponents(true, true, true);
        testMainFrame.setVisible(true);
	}

}
