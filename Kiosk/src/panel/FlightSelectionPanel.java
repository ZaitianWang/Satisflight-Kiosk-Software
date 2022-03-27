package panel;

import card.FlightInfoCard;
import dbReader.PassengerFlightReader;
import main.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * This is the panel that a passenger can select a flight to check in.
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.0
 *
 * @author Ni Ruijie
 * @date 2022/3/27
 * @version 1.1
 * Adjusted actions in mouseReleased(MouseEvent e).
 */

public class FlightSelectionPanel extends JPanel {

    private String bookingNum;
    private String idFlight;
    private List<String> idFlightList;

    public FlightSelectionPanel() {
        bookingNum = State.getBookingNum();
        idFlightList = PassengerFlightReader.getIdFlightByBookingNum(bookingNum);

        setBounds(new Rectangle(0, 0, 1600, 980));
        setBackground(new Color(244, 244, 244));
        setLayout(null);

        for(int cardNum = 0; cardNum < idFlightList.size(); cardNum++) {
            JPanel flightInfoCard = new FlightInfoCard(idFlightList.get(cardNum));
            flightInfoCard.setBounds(500, 150 + 250 * cardNum, 530, 150);
            int finalCardNum = cardNum;
            flightInfoCard.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    flightInfoCard.setBackground(new Color(235, 235, 235));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    flightInfoCard.setBackground(Color.WHITE);
                    idFlight = idFlightList.get(finalCardNum);
                    State.setIdFlight(idFlight);
                    State.setPassengerFlight_index(PassengerFlightReader.getPassengerFlight_index(bookingNum,idFlight));
                    System.out.println(State.getPassengerFlight_index());
                    State.setPc(State.getPc() + 1);
                }

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
            add(flightInfoCard);
        }
    }
}
