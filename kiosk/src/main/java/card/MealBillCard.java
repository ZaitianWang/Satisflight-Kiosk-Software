package card;

import main.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * a card of meal bill
 *
 * @author Liang Zhehao
 * @author Wang Chenyu
 * @author Zhang Zeyu
 *
 * @version 5.1
 * Layout and appearance improvement
 * @date 2022/5/20
 *
 * @version 5.0
 * Appearance improvement
 * @date 2022/5/19
 *
 * @version 1.1
 * @date 2022/3/26
 *
 * @version 1.0
 * @date 3/23
 *
 */
public class MealBillCard extends JPanel {

    private final JLabel headline;
    private final JLabel headText;
    private final JLabel[] extr = new JLabel[3];
    private final JLabel[] bill = new JLabel[3];
    private final JLabel[] line = new JLabel[3];

    private final String[] pref = new String[3];
    private final int[] pay = new int[3];

    /**
     *
     * @param basis 'a' Normal, 'b' Vegetarian, 'c' Halal
     * @param extr1 preference food name 1
     * @param extr2 preference food name 2
     * @param extr3 preference food name 3
     * @param bill1 preference price name 1
     * @param bill2 preference price name 2
     * @param bill3 preference price name 3
     */
    public MealBillCard(char basis, String extr1, String extr2, String extr3, int bill1, int bill2, int bill3) {

        pref[0] = extr1;
        pref[1] = extr2;
        pref[2] = extr3;
        pay[0] = bill1;
        pay[1] = bill2;
        pay[2] = bill3;

        setSize(1120, 295);
        setBackground(Theme.getCardColor());
        setLayout(null);

        for (int i = 0; i < 2; i++) {
            for (int j = 2; j > i; j--) {
                if (pay[j] < pay[j - 1]) {
                    int temp1 = pay[j];
                    pay[j] = pay[j - 1];
                    pay[j - 1] = temp1;
                    String temp2 = pref[j];
                    pref[j] = pref[j - 1];
                    pref[j - 1] = temp2;
                }
            }
        }
        String food = "";
        if (basis == 'a')
            food = "Normal";
        else if (basis == 'b')
            food = "Vegetarian";
        else if (basis == 'c')
            food = "Halal";

        headline = new JLabel();
        headline.setText("MEAL");
        headline.setBounds(0, 0, 100, 40);
        headline.setFont(new Font("Arial", Font.BOLD, 25));
        headline.setForeground(Theme.getMinorFontColor());
        headline.setBackground(Theme.getThemeColor());
        headline.setVerticalAlignment(SwingConstants.CENTER);
        headline.setHorizontalAlignment(SwingConstants.CENTER);
        headline.setOpaque(true);
        add(headline);

        headText = new JLabel(food);
        headText.setBounds(140, 10, 600, 70);
        headText.setFont(new Font("Arial", Font.BOLD, 40));
        headText.setForeground(Theme.getMainFontColor());
        headText.setVerticalAlignment(SwingConstants.CENTER);
        add(headText);

        JLabel lblTopLine = new JLabel();
        lblTopLine.setBounds(0, 0, 1120, 10);
        lblTopLine.setOpaque(true);
        lblTopLine.setBackground(Theme.getThemeColor());
        add(lblTopLine);

        int gain = 65, base = 80, x = 30, wid = 1060;

        JLabel line1 = new JLabel();
        line1.setBounds(x, base, wid, 2);
        line1.setOpaque(true);
        line1.setBackground(Theme.getTertiaryFontColor());
        add(line1);

        JLabel line2 = new JLabel();
        line2.setBounds(x, base + gain, wid, 2);
        line2.setOpaque(true);
        line2.setBackground(Theme.getTertiaryFontColor());
        add(line2);

        JLabel line3 = new JLabel();
        line3.setBounds(x, base + 2 * gain, wid, 2);
        line3.setOpaque(true);
        line3.setBackground(Theme.getTertiaryFontColor());
        add(line3);

        JLabel line4 = new JLabel();
        line4.setBounds(x, base + 3 * gain, wid, 2);
        line4.setOpaque(true);
        line4.setBackground(Theme.getTertiaryFontColor());
        add(line4);

        int n = 0;
        for (int i = 0; i < 3; i++) {
            if (pay[i] == -1) {
                continue;
            }
            extr[n] = new JLabel();
            extr[n].setText("· " + pref[i]);
            extr[n].setLocation(150, base + 2 + gain * n);
            extr[n].setFont(new Font("Arial", Font.PLAIN, 30));
            extr[n].setForeground(Theme.getSecondaryFontColor());
            extr[n].setVerticalAlignment(SwingConstants.CENTER);
            extr[n].setSize(350, gain);
            add(extr[n]);

            bill[n] = new JLabel();
            bill[n].setLocation(955, base + 2 + gain * n);
            bill[n].setSize(100, gain);
            bill[n].setText("$" + pay[i]);
            bill[n].setHorizontalAlignment(SwingConstants.RIGHT);
            bill[n].setForeground(new Color(255,69,0));
            bill[n].setVerticalAlignment(SwingConstants.CENTER);
            bill[n].setFont(new Font("Arial", Font.BOLD, 30));
            add(bill[n]);

            line[n] = new JLabel("  ·····························");
            line[n].setLocation(495, base + 2 + gain * n);
            line[n].setSize(413, gain);
            line[n].setForeground(Theme.getTertiaryFontColor());
            line[n].setVerticalAlignment(SwingConstants.CENTER);
            line[n].setFont(new Font("Arial", Font.PLAIN, 38));
            add(line[n]);

            n++;
        }

        if (pay[0] == -1 && pay[1] == -1 && pay[2] == -1) {
            extr[0] = new JLabel();
            extr[0].setText("· No preference selected");
            extr[0].setLocation(150, base + 1);
            extr[0].setFont(new Font("Arial", Font.PLAIN, 30));
            extr[0].setForeground(Theme.getSecondaryFontColor());
            extr[0].setVerticalAlignment(SwingConstants.CENTER);
            extr[0].setSize(700, gain);
            add(extr[0]);
        }

    }

    public JLabel getHeadText() {
        return headText;
    }

    public JLabel[] getExtr() {
        return extr;
    }

    public JLabel[] getBill() {
        return bill;
    }
}
