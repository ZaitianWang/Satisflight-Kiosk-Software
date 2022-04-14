package cardTest;

import card.MealBillcard;
import frame.MainFrame;

/**
 * @version 1.0
 * @author Wang Chenyu
 * @date 3/23
 * a test of meal bill
 */
public class MealBillCardTest {
    public static void main(String[] args) {
        MainFrame testMainFrame = new MainFrame();
        MealBillcard h = new MealBillcard('a',"Kweichow Moutai","French meal","French meal",100,90,5);
        testMainFrame.loadPanel(h);
        testMainFrame.displayComponents(true, true, false);
        testMainFrame.setVisible(true);
    }
}