package main;

/**
 * @version 1.0
 * @author zaitian
 * @date 3/22
 * Designed to temporarily save data
 * before forwarding to json database
 * at the final page. Add static variables
 * as you need it. Do remember to
 * add getter/setter and avoid name-collision
 *
 * @author Zhang Zeyu
 * @date 2022/3/24
 * @version 1.1
 * Add temp vars.
 *
 * @author Liang Zhehao
 * @date 2022/3/24
 * @version 1.2
 * Modify some vars
 */
public abstract class State {
    private static int pc = 0;
    private static String passengerName = "passenger";
    private static String bookingNum;
    private static String idPassengerFlight;
//    private static String idFlight;
    private static int seatRow = -1;
    private static int seatColumn = -1;
    private static int seatPre; //[0, 1, 2, 3]
    private static char meal;    //[a, b, c]
    private static boolean[] selectedPrefFood = {false, false, false};
//    private static String[] prefSeat = new String[4];
//    private static int[] prefSeatPrice = new int[4];
//    private static String[] prefFood = new String[3];
//    private static int[] prefFoodPrice = new int[3];
    private static int bill = 0;

    /*
    test data for Control
     */
    private static String idFlight = "AC0001";
    private static String[] prefSeat = {"Normal", "Legroom Pro", "Legroom Max", "Legroom Ultra"};
    private static int[] prefSeatPrice = {0, 10, 20, 50};
    private static String[] prefFoodName = {"Extra", "Kweichow Moutai", "Ice-cream"};
    private static int[] prefFoodPrice = {5, 100, 10};

    public static int getPc() { return pc; }
    public static String getPassengerName() { return passengerName; }
    public static String getBookingNum() { return bookingNum; }
    public static String getIdPassengerFlight() { return idPassengerFlight; }
    public static String getIdFlight() { return idFlight; }
    public static int getSeatRow() { return seatRow; }
    public static int getSeatColumn() { return seatColumn; }
    public static int getSeatPre() { return seatPre; }
    public static char getMeal() { return meal; }
    public static boolean[] getSelectedPrefFood() { return selectedPrefFood; }
    public static String[] getPrefSeat() { return prefSeat; }
    public static int[] getPrefSeatPrice() { return prefSeatPrice; }
    public static String[] getPrefFoodName() { return prefFoodName; }
    public static int[] getPrefFoodPrice() { return prefFoodPrice; }
    public static int getBill() { return bill; }

    public static void setPc(int pcNext) { pc = pcNext; }
    public static void setPassengerName(String passengerName) { State.passengerName = passengerName; }
    public static void setBookingNum(String bookingNum) { State.bookingNum = bookingNum; }
    public static void setIdPassengerFlight(String idPassengerFlight) { State.idPassengerFlight = idPassengerFlight; }
    public static void setIdFlight(String idFlight) { State.idFlight = idFlight; }
    public static void setSeatRow(int seatRow) { State.seatRow = seatRow; }
    public static void setSeatColumn(int seatColumn) { State.seatColumn = seatColumn; }
    public static void setSeatPre(int seatPre) { State.seatPre = seatPre; }
    public static void setMeal(char meal) { State.meal = meal; }
    public static void setSelectedPrefFood(boolean[] selectedPrefFood) { State.selectedPrefFood = selectedPrefFood; }
    public static void setPrefSeat(String[] prefSeat) { State.prefSeat = prefSeat; }
    public static void setPrefSeatPrice(int[] prefSeatPrice) { State.prefSeatPrice = prefSeatPrice; }
    public static void setPrefFoodName(String[] prefFood) { State.prefFoodName = prefFood; }
    public static void setPrefFoodPrice(int[] prefFoodPrice) { State.prefFoodPrice = prefFoodPrice; }
    public static void setBill(int bill) { State.bill = bill; }
}
