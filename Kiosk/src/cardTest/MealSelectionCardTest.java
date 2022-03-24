package cardTest;

import card.MealSelectionCard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Liang Zhehao
 * @date 3/18
 * test for prefCard, with a panel embedded
 */
public class MealSelectionCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        MealSelectionCard mealSelectionCard = new MealSelectionCard();
        testMainFrame.loadPanel(mealSelectionCard);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}