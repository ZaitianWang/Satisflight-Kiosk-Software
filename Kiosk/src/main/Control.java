package main;
import card.MealSelectionCard;
import card.SeatSelectionCard;
import frame.*;
import panel.*;

import static java.lang.Thread.sleep;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * the core class that run with main, managing all frames and panels
 */
public class Control {
    public static void main(String[] args) throws InterruptedException {
        MainFrame kiosk;
        BookingLoginPanel bookingLoginPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        /*
        main frame
         */
        kiosk = new MainFrame(1);
        kiosk.displayComponents(true, true, true);
        /*
        booking number login panel
         */
        bookingLoginPanel = new BookingLoginPanel();
        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);
        seatPanel.loadCards(new SeatSelectionCard( "AC0001",
                "Normal", "Legroom Pro",
                "Legroom Max", "Legroom Ultra",
                0, 10, 20, 50));
        /*
        meal choosing panel
         */
        mealPanel = new ProgressPanel(3);
        mealPanel.loadCards(new MealSelectionCard("Extra","Kweichow Moutai",
                "Ice-cream", 5, 100, 10));
        /*
        control flow
         */
        int currentPC = 1;
        State.setPc(currentPC);
        currentPC = 0;
        while (true){
            kiosk.setVisible(true);
            while (currentPC == State.getPc()){
                sleep(1000);
            }
            switch (State.getPc()) {
                case 1:{    //enter ID
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(bookingLoginPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 3:{    //flights
                    break;
                }
                case 4:{    //seat
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(seatPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 5: {    //food
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(mealPanel);
                    currentPC  = State.getPc();
                    kiosk.repaint();
                    break;
                }


            }
        }
//            kiosk.loadPanel(new FlightInfoCard("idFlight",
//                    "date",
//                    "departureTime",
//                    "arrivalTime",
//                    "departure",
//                    "arrival"));

    }
}
