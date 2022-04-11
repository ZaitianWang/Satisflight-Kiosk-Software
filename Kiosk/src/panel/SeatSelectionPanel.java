package panel;


import card.PlaneInfoCard;
import dbReader.FlightReader;
import dbReader.PlaneReader;
import dbReader.SeatReader;
import main.State;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * This class can return a seat selection panel.
 *
 * @author Wang Chenyu
 * @author Liang Zhehao
 * @author Zhang Zeyu
 *
 * @version 2.0
 * Add PlaneInfoCard
 * @date 2022/4/11
 *
 * @version 1.4
 * Appearance improved.
 * @date 2022/3/28
 *
 * @version 1.3
 * Add bill and OK button
 * @date 2022/3/23
 *
 * @version 1.2
 * @date 2022/3/21
 *
 * @version 1.1
 * @date 2022/3/21
 *
 * @version 1.0
 * @date 2022/3/19
 */
public class SeatSelectionPanel extends JPanel {
    private int[] avail_seat = new int[6];
    private int row;
    private int totalrow;
    private String idFlight;
    private int temp_row = -1;
    private int temp_column = -1;
    private int bill = 0;
    private int[] price = new int[4];
    private String[] seatName = new String[4];
    //button init
    private JButton[] seatButton = new JButton[6];
    private JLabel row_num = new JLabel();
    private JPanel warn;
    private JScrollBar scrollBar = new JScrollBar();
    //icon loading
    private ImageIcon icon1_empty = new ImageIcon("Kiosk/icons/avail.png");
    private ImageIcon icon_empty = new ImageIcon(icon1_empty.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon1_occu = new ImageIcon("Kiosk/icons/occu.png");
    private ImageIcon icon_occu = new ImageIcon(icon1_occu.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private ImageIcon icon1_chonse = new ImageIcon("Kiosk/icons/chosen.png");
    private ImageIcon icon_chonse = new ImageIcon(icon1_chonse.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_SMOOTH));
    private JButton btnOK = new JButton("OK");

    private JRadioButton rdbtnSeat1 = new JRadioButton();
    private JRadioButton rdbtnSeat2 = new JRadioButton();
    private JRadioButton rdbtnSeat3 = new JRadioButton();
    private JRadioButton rdbtnSeat4 = new JRadioButton();

    public SeatSelectionPanel(boolean cheat) {}

    public SeatSelectionPanel() {

        for (int i = 0; i < 4; i++) {
            this.price[i] = State.getPrefSeatPrice()[i];
            this.seatName[i] = State.getPrefSeatName()[i];
        }
        row = 4;
        this.idFlight = State.getIdFlight();
        totalrow = PlaneReader.getCapacity(PlaneReader.indexOf(FlightReader.getIdPlane(FlightReader.indexOf(idFlight)))) / 6;

        setBackground(new Color(244, 244, 244));
        setLayout(null);
        setSize(1600, 880);
        //add bound icon
        JLabel right_label = new JLabel();
        right_label.setSize(75, 262);
        JLabel left_label = new JLabel();
        left_label.setSize(147, 206);
        right_label.setIcon(new ImageIcon("Kiosk/icons/rightbound.png"));
        left_label.setIcon(new ImageIcon("Kiosk/icons/leftbound.png"));
        add(right_label);
        add(left_label);
        right_label.setLocation(1460, 1);
        left_label.setLocation(145, 30);
        //add mid_line
        JLabel mright_label = new JLabel();
        mright_label.setSize(118, 199);
        JLabel mleft_label = new JLabel();
        mleft_label.setSize(118, 168);
        mright_label.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        mleft_label.setIcon(new ImageIcon("Kiosk/icons/mid.png"));
        add(mright_label);
        add(mleft_label);
        mright_label.setLocation(851, 30);
        mleft_label.setLocation(734, 45);

        SimpleListener ourListener = new SimpleListener();
        for (int i = 0; i < 6; i++) {
            seatButton[i] = new JButton();
            seatButton[i].setForeground(Color.WHITE);
            seatButton[i].setBackground(Color.WHITE);
            seatButton[i].setContentAreaFilled(false);
            seatButton[i].setBorderPainted(false);
            seatButton[i].addActionListener(ourListener);
            add(seatButton[i]);
        }
        seatButton[0].setBounds(165, 1, 221, 220);
        seatButton[1].setBounds(330, 1, 221, 220);
        seatButton[2].setBounds(495, 1, 221, 220);
        seatButton[3].setBounds(931, 1, 221, 220);
        seatButton[4].setBounds(1096, 1, 221, 220);
        seatButton[5].setBounds(1261, 1, 221, 220);
        //row number
        row_num.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        row_num.setText(String.valueOf(row));
        row_num.setBounds(88, 103, 41, 50);
        add(row_num);
        //seat label
        JLabel lblA = new JLabel("A");
        lblA.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblA.setBounds(265, 210, 25, 26);
        add(lblA);

        JLabel lblB = new JLabel("B");
        lblB.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblB.setBounds(430, 210, 25, 26);
        add(lblB);

        JLabel lblC = new JLabel("C");
        lblC.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblC.setBounds(595, 210, 25, 26);
        add(lblC);

        JLabel lblD = new JLabel("D");
        lblD.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblD.setBounds(1031, 210, 25, 26);
        add(lblD);

        JLabel lblE = new JLabel("E");
        lblE.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblE.setBounds(1196, 210, 25, 26);
        add(lblE);

        JLabel lblF = new JLabel("F");
        lblF.setFont(new Font("Eras Bold ITC", Font.PLAIN, 26));
        lblF.setBounds(1361, 210, 25, 26);
        add(lblF);

        Border tipBorder = BorderFactory
                .createTitledBorder(BorderFactory.createMatteBorder(5,5,5,5,Color.RED)
                        , "Please select your seat", TitledBorder.CENTER
                        , TitledBorder.BOTTOM
                        , new Font("Arial", Font.PLAIN, 25)
                        , Color.RED);
        warn = new JPanel();
        warn.setBounds(117, 15, 1413, 255);
        warn.setBackground(new Color(244, 244, 244));
        warn.setBorder(tipBorder);
        warn.setVisible(false);
        add(warn);

        int idPlane = FlightReader.getIdPlane(FlightReader.indexOf(State.getIdFlight()));
        PlaneInfoCard planeInfoCard = new PlaneInfoCard(PlaneReader.getModel(PlaneReader.indexOf(idPlane)),
                PlaneReader.getCapacity(PlaneReader.indexOf(idPlane)),
                PlaneReader.getAirline(PlaneReader.indexOf(idPlane)));
        planeInfoCard.setLocation(50, 280);
        add(planeInfoCard);

        scrollBar.setBounds(34, 59, 34, 157);
        scrollBar.setBlockIncrement(1);
        resetScrollBar(0);
        add(scrollBar);

        SeatReader seatReader = new SeatReader(idFlight);
        boolean[] seat = seatReader.getSeat(4);
        int[] s = new int[6];
        for (int i = 0; i < seat.length; i++) {
            if (seat[i])
                s[i] = 0;
            else
                s[i] = 1;
        }
        setAvail_seat(s);

        addSeatIcon(avail_seat);
        ScrollListener scrollListener = new ScrollListener();
        scrollBar.addAdjustmentListener(scrollListener);

        JPanel prefPanel = new JPanel();
        prefPanel.setBackground(Color.WHITE);
        prefPanel.setLayout(null);
        prefPanel.setBounds(50, 550, 388, 280);
        add(prefPanel);

        JLabel lblPref = new JLabel("Preference");
        lblPref.setForeground(Color.DARK_GRAY);
        lblPref.setFont(new Font("Arial", Font.BOLD, 45));
        lblPref.setBounds(34, 22, 250, 40);
        prefPanel.add(lblPref);

        JLabel lblPrice1 = new JLabel(":  $" + price[0]);
        lblPrice1.setForeground(Color.DARK_GRAY);
        lblPrice1.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice1.setBounds(260, 90, 90, 30);
        prefPanel.add(lblPrice1);

        JLabel lblPrice2 = new JLabel(":  $" + price[1]);
        lblPrice2.setForeground(Color.DARK_GRAY);
        lblPrice2.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice2.setBounds(260, 130, 90, 30);
        prefPanel.add(lblPrice2);

        JLabel lblPrice3 = new JLabel(":  $" + price[2]);
        lblPrice3.setForeground(Color.DARK_GRAY);
        lblPrice3.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice3.setBounds(260, 170, 90, 30);
        prefPanel.add(lblPrice3);

        JLabel lblPrice4 = new JLabel(":  $" + price[3]);
        lblPrice4.setForeground(Color.DARK_GRAY);
        lblPrice4.setFont(new Font("Arial", Font.PLAIN, 25));
        lblPrice4.setBounds(260, 210, 90, 30);
        prefPanel.add(lblPrice4);

        rdbtnSeat1.setText(seatName[0]);
        rdbtnSeat1.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat1.setForeground(Color.DARK_GRAY);
        rdbtnSeat1.setBackground(Color.WHITE);
        rdbtnSeat1.setBounds(16, 90, 240, 30);
        prefPanel.add(rdbtnSeat1);
        rdbtnSeat1.setSelected(true);

        rdbtnSeat2.setText(seatName[1]);
        rdbtnSeat2.setForeground(Color.DARK_GRAY);
        rdbtnSeat2.setBackground(Color.WHITE);
        rdbtnSeat2.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat2.setBounds(16, 130, 240, 30);
        prefPanel.add(rdbtnSeat2);

        rdbtnSeat3.setText(seatName[2]);
        rdbtnSeat3.setForeground(Color.DARK_GRAY);
        rdbtnSeat3.setBackground(Color.WHITE);
        rdbtnSeat3.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat3.setBounds(16, 170, 240, 30);
        prefPanel.add(rdbtnSeat3);

        rdbtnSeat4.setText(seatName[3]);
        rdbtnSeat4.setForeground(Color.DARK_GRAY);
        rdbtnSeat4.setBackground(Color.WHITE);
        rdbtnSeat4.setFont(new Font("Arial", Font.PLAIN, 25));
        rdbtnSeat4.setBounds(16, 210, 240, 30);
        prefPanel.add(rdbtnSeat4);

        ButtonGroup group = new ButtonGroup();
        group.add(rdbtnSeat4);
        group.add(rdbtnSeat3);
        group.add(rdbtnSeat1);
        group.add(rdbtnSeat2);

        PrefListener prefListener = new PrefListener();
        rdbtnSeat1.addItemListener(prefListener);
        rdbtnSeat2.addItemListener(prefListener);
        rdbtnSeat3.addItemListener(prefListener);
        rdbtnSeat4.addItemListener(prefListener);

        OKListener okListener = new OKListener();
        btnOK.setFont(new Font("Arial", Font.BOLD, 35));
        btnOK.setBounds(1200, 760, 330, 70);
        btnOK.setForeground(Color.WHITE);
        btnOK.setBackground(new Color(11, 89, 167));
        btnOK.addActionListener(okListener);
        add(btnOK);
    }

    private void addSeatIcon(int[] avail) {
        if (getTemp_row() == row) {
            avail[getTemp_column()] = 2;
            avail_seat[getTemp_column()] = 2;
        }
        for (int i = 0; i < 6; i++) {
            if (avail[i] == 1)
                seatButton[i].setIcon(icon_occu);
            else if (avail[i] == 2)
                seatButton[i].setIcon(icon_chonse);
            else
                seatButton[i].setIcon(icon_empty);
        }
    }

    private void setTemp_row(int temp_row) {
        this.temp_row = temp_row;
    }

    private void setTemp_column(int temp_column) {
        this.temp_column = temp_column;
    }

    private void setAvail_seat(int[] avail_seat) {
        System.arraycopy(avail_seat, 0, this.avail_seat, 0, 6);
    }

    private int getTemp_row() {
        return temp_row;
    }

    private int getTemp_column() {
        return temp_column;
    }

    public JRadioButton getRdbtnSeat1() {
        return rdbtnSeat1;
    }

    public JRadioButton getRdbtnSeat2() {
        return rdbtnSeat2;
    }

    public JRadioButton getRdbtnSeat3() {
        return rdbtnSeat3;
    }

    public JRadioButton getRdbtnSeat4() {
        return rdbtnSeat4;
    }

    public JButton[] getSeatButton() {
        return seatButton;
    }

    public JScrollBar getScrollBar() {
        return scrollBar;
    }

    public JButton getBtnOK() {
        return btnOK;
    }

    public int getTotalrow() {
        return totalrow;
    }

    public int[] getAvail_seat() {
        return avail_seat;
    }

    public JPanel getWarn() {
        return warn;
    }

    private class PrefListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            int n = -1;
            if (e.getSource() == rdbtnSeat1 && rdbtnSeat1.isSelected())
                n = 0;
            else if (e.getSource() == rdbtnSeat2 && rdbtnSeat2.isSelected())
                n = 1;
            else if (e.getSource() == rdbtnSeat3 && rdbtnSeat3.isSelected())
                n = 2;
            else if (e.getSource() == rdbtnSeat4 && rdbtnSeat4.isSelected())
                n = 3;

            if (n != -1) {
                resetScrollBar(n);
            }
        }
    }

    private class SimpleListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int p = row;
            if (p >= 4)
                p = 0;
            int click = -1;
            for (int i = 0; i < 6; i++) {
                if (e.getSource() == seatButton[i])
                    click = i;
            }

            if (avail_seat[click] == 0) {
                if (getTemp_row() == row) {
                    seatButton[getTemp_column()].setIcon(icon_empty);
                    avail_seat[getTemp_column()] = 0;
                    State.smallBillCard.subPrice(price[p]);
                } else if (getTemp_row() <= 3 && getTemp_row() >= 1) {
                    State.smallBillCard.subPrice(price[getTemp_row()]);
                }
                setTemp_row(row);
                setTemp_column(click);
                avail_seat[click] = 2;
                seatButton[click].setIcon(icon_chonse);
                warn.setVisible(false);
                State.smallBillCard.addPrice(price[p]);
            } else if (avail_seat[click] == 2) {
                setTemp_row(-1);
                setTemp_column(-1);
                avail_seat[click] = 0;
                seatButton[click].setIcon(icon_empty);
                State.smallBillCard.subPrice(price[p]);
            }
        }
    }
    private void resetScrollBar(int pref) {
        if (pref == 0) {
            scrollBar.setMinimum(4);
            scrollBar.setMaximum(24);
            int k = 24 - totalrow;
            scrollBar.setVisibleAmount(k);
            if (getTemp_row() >= 4) {
                scrollBar.setValue(getTemp_row());
                row = getTemp_row();
            } else {
                scrollBar.setValue(4);
                row = 4;
            }

        } else {
            scrollBar.setMinimum(pref);
            scrollBar.setMaximum(pref);
            scrollBar.setValue(pref);
            row = pref;
        }
    }

    private class ScrollListener implements AdjustmentListener {

        @Override
        public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
            row_num.setText(String.valueOf(adjustmentEvent.getValue()));
            row = adjustmentEvent.getValue();

            SeatReader seatReader = new SeatReader(idFlight);
            boolean[] seat = seatReader.getSeat(adjustmentEvent.getValue());
            int[] s = new int[6];
            for (int i = 0; i < seat.length; i++) {
                if (seat[i])
                    s[i] = 0;
                else
                    s[i] = 1;
            }
            setAvail_seat(s);
            addSeatIcon(s);
        }
    }

    public class OKListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (getTemp_row() != -1) {
                State.setPc(State.getPc() + 1);
                State.setSeatRow(temp_row);
                State.setSeatColumn(temp_column + 1);
                State.setBill(State.smallBillCard.getPrice());
                if (temp_row >= 4)
                    State.setSeatPre(0);
                else
                    State.setSeatPre(temp_row);
            } else {
                warn.setVisible(true);
            }
        }
    }
}