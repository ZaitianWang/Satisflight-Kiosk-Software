package panel;

import card.MealInfoCard;
import card.MealPreferenceCard;
import dbReader.FlightReader;
import main.State;
import main.Theme;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * This class can return a meal selection panel.
 * @author Zhang Zeyu
 * @author Liang Zhehao
 *
 * @version 3.0
 * Bug fixed.
 * @date 2022/4/22
 *
 * @version 1.4
 * Appearance improved.
 * @date 2022/3/28
 *
 * @version 1.3
 * GUI appearance redesigned.
 * @date 2022/3/25
 *
 * @version 1.2
 * Add setters and remove parameters
 * @date 2022/3/25
 *
 * @version 1.1
 * @date 2022/3/23
 *
 * @version 1.0
 * @date 2022/3/19
 */

public class MealSelectionPanel extends JPanel {

    private MealInfoCard mealInfoCard;
    MealPreferenceCard mealPreferenceCard;

    private JButton btnOK = new JButton("OK");

    private Border tipBorder = BorderFactory
            .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,new Color(205,92,92))
                    , "Please select your meal", TitledBorder.CENTER
                    , TitledBorder.BOTTOM
                    , new Font("Arial", Font.PLAIN, 25)
                    , new Color(205,92,92));

    public MealSelectionPanel(boolean cheat) {}

    public MealSelectionPanel() {


        setBackground(Theme.getBackgroundColor());
        setForeground(Theme.getMinorFontColor());
        setLayout(null);
        setSize(1600, 880);

        mealInfoCard = new MealInfoCard();
        mealInfoCard.setLocation(10, 0);
        add(mealInfoCard);

        mealPreferenceCard = new MealPreferenceCard();
        mealPreferenceCard.setLocation(1220, 50);
        add(mealPreferenceCard);

//        JPanel panelPref = new JPanel();
//        panelPref.setBounds(1220, 50, 330, 220);
//        panelPref.setBackground(Theme.getCardColor());
//        add(panelPref);
//
//        JLabel lblTitle = new JLabel("Preference");
//        lblTitle.setForeground(Theme.getMainFontColor());
//        lblTitle.setFont(new Font("Arial", Font.BOLD, 45));
//        lblTitle.setBounds(34, 22, 250, 40);
//        panelPref.add(lblTitle);
//
//        JLabel lblPrice1 = new JLabel(":  $" + price[0]);
//        lblPrice1.setForeground(Theme.getMainFontColor());
//        lblPrice1.setFont(new Font("Arial", Font.PLAIN, 25));
//        lblPrice1.setBounds(260, 90, 90, 30);
//        panelPref.setLayout(null);
//        panelPref.add(lblPrice1);
//
//        JLabel lblPrice2 = new JLabel(":  $" + price[1]);
//        lblPrice2.setForeground(Theme.getMainFontColor());
//        lblPrice2.setFont(new Font("Arial", Font.PLAIN, 25));
//        lblPrice2.setBounds(260, 130, 90, 30);
//        panelPref.add(lblPrice2);
//
//        JLabel lblPrice3 = new JLabel(":  $" + price[2]);
//        lblPrice3.setForeground(Theme.getMainFontColor());
//        lblPrice3.setFont(new Font("Arial", Font.PLAIN, 25));
//        lblPrice3.setBounds(260, 170, 90, 30);
//        panelPref.add(lblPrice3);
//
//        rdbtnMeal[0].setText(foodName[0]);
//        rdbtnMeal[0].setForeground(Theme.getMainFontColor());
//        rdbtnMeal[0].setBackground(Theme.getCardColor());
//        rdbtnMeal[0].setFont(new Font("Arial", Font.PLAIN, 25));
//        rdbtnMeal[0].setBounds(32, 90, 240, 30);
//        panelPref.add(rdbtnMeal[0]);
//
//        rdbtnMeal[1].setText(foodName[1]);
//        rdbtnMeal[1].setForeground(Theme.getMainFontColor());
//        rdbtnMeal[1].setBackground(Theme.getCardColor());
//        rdbtnMeal[1].setFont(new Font("Arial", Font.PLAIN, 25));
//        rdbtnMeal[1].setBounds(32, 130, 240, 30);
//        panelPref.add(rdbtnMeal[1]);
//
//        rdbtnMeal[2].setText(foodName[2]);
//        rdbtnMeal[2].setForeground(Theme.getMainFontColor());
//        rdbtnMeal[2].setBackground(Theme.getCardColor());
//        rdbtnMeal[2].setFont(new Font("Arial", Font.PLAIN, 25));
//        rdbtnMeal[2].setBounds(32, 170, 240, 30);
//        panelPref.add(rdbtnMeal[2]);
//
//        PrefListener prefListener = new PrefListener();
//        for (int i=0 ; i<3 ; i++)
//            rdbtnMeal[i].addItemListener(prefListener);

        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1220, 760, 330, 70);
        btnOK.setForeground(Theme.getMinorFontColor());
        btnOK.setBackground(Theme.getThemeColor());
        btnOK.addActionListener(okListener);
        btnOK.setBorderPainted(false);
        add(btnOK);
    }

    public JButton getNormal_food(){return mealInfoCard.getNormal_food();}

    public JButton getVegetarian_food(){return mealInfoCard.getVegetarian_food();}

    public JButton getHalal_food(){return mealInfoCard.getHalal_food();}

    public  JButton getConfirm(){return btnOK;}

    public  JRadioButton[] getrdbtnMeal(){return mealPreferenceCard.getPref();}

    private class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (mealInfoCard.getChosen() != 'd') {
                State.setPc(State.getPc() + 1);
                State.setMeal(mealInfoCard.getChosen());
                State.setSelectedPrefFood(mealPreferenceCard.getSelection());
                State.setBill(State.smallBillCard.getPrice());
            } else {
                mealInfoCard.setBorder(tipBorder);
                State.setMeal('d');
            }
        }
    }
}
