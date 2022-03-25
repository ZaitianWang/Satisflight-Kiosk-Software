package main;

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
        WelcomePanel welcomePanel;
        BookingLoginPanel bookingLoginPanel;
        ProgressPanel flightsPanel, seatPanel, mealPanel, billPanel, payPanel;
        FinalPanel finalPanel;
        SeatSelectionPanel seatSelectionPanel = new SeatSelectionPanel();
        MealSelectionPanel mealSelectionPanel = new MealSelectionPanel();
        /*
        main frame
         */
        kiosk = new MainFrame();
        kiosk.displayComponents(true, true, true);
        /*
        welcoming panel
         */
        welcomePanel = new WelcomePanel();
        /*
        booking number login panel
         */
        bookingLoginPanel = new BookingLoginPanel();
        /*
        flight choosing panel
         */
        flightsPanel = new ProgressPanel(1);
        /*
        seat choosing panel
         */
        seatPanel = new ProgressPanel(2);
//        seatPanel.loadCards(new SeatSelectionCard());
        /*
        meal choosing panel
         */
        mealPanel = new ProgressPanel(3);
//        mealPanel.loadCards(new MealSelectionCard());
        /*
        final panel
         */
        finalPanel = new FinalPanel();
        /*
        control flow
         */
        int currentPC = -1;
        State.setPc(0);
        State.setIsReady(new boolean[]{true, true, true,
                false, false, false, false, true, true});
        while (true){
            kiosk.setVisible(true);
            while (currentPC == State.getPc()){
                sleep(1);
            }
            switch (State.getPc()) {

                case 0: {    //welcome
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.hideBars(true);
                    kiosk.loadPanel(welcomePanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 1:{    //enter booking number
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.hideBars(false);
                    kiosk.displayComponents(true, true, false);
                    kiosk.resetWelcomeText(1);
                    kiosk.loadPanel(bookingLoginPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 2:{    //enter or scan ID
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.displayComponents(true, true, true);
                }
                case 3:{    //flights
                    if (!State.getIsReady()[3]) {
                        flightsPanel.loadCardsPanel(new FlightSelectionPanel());
                    }
                    kiosk.displayComponents(true, true, false);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(flightsPanel);
                    currentPC = State.getPc();
                    kiosk.setWelcomeText();
                    kiosk.repaint();
                    break;
                }
                case 4:{    //seat
                    if (!State.getIsReady()[4]) {
                        seatSelectionPanel = new SeatSelectionPanel();
                        seatPanel.loadCardsPanel(seatSelectionPanel);
                    }
                    seatSelectionPanel.add(State.smallBillCard);
                    kiosk.displayComponents(true, true, true);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(seatPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 5:{    //food
                    if (!State.getIsReady()[5]) {
                        mealSelectionPanel = new MealSelectionPanel();
                        mealPanel.loadCardsPanel(mealSelectionPanel);
                    }
                    mealSelectionPanel.add(State.smallBillCard);
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(mealPanel);
                    currentPC  = State.getPc();
                    kiosk.repaint();
                    break;
                }
                case 6: {    //bill

                }
                case 7: {   //pay

                }
                case 8:{    //finish
                    kiosk.unloadPanel(kiosk.getLoadedPanel());
                    kiosk.loadPanel(finalPanel);
                    currentPC = State.getPc();
                    kiosk.repaint();
                    break;
                }
            }
        }
    }
}
