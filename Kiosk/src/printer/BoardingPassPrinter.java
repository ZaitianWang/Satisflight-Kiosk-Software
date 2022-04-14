package printer;

import dbReader.FlightReader;
import dbReader.PassengerFlightReader;
import dbReader.PassengerReader;
import dbReader.PlaneReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * test for Print ticket
 *
 * @author wcy
 * @version 1.0
 * @date 3/20
 */

public abstract class BoardingPassPrinter {
    static String ticket_temp;
    public static void creatBoardingPass(int idPassengerFlight_index, String seat) throws IOException {
        String idFlight = PassengerFlightReader.getIdFlight(idPassengerFlight_index);
        int idFlight_index = FlightReader.indexOf(idFlight);
        String idPassenger = PassengerFlightReader.getIdPassenger(idPassengerFlight_index);
        int idPlane = FlightReader.getIdPlane(idFlight_index);
        int idPlane_index = PlaneReader.indexOf(idPlane);
        String date = FlightReader.getDate(idFlight_index);
        String departure = FlightReader.getDeparture(idFlight_index);
        String arrival = FlightReader.getArrival(idFlight_index);
        String gate = FlightReader.getGate(idFlight_index);
        String board_time = FlightReader.getBoardingTime(idFlight_index);
        int idPassenger_index = PassengerReader.indexOf(idPassenger);
        String surname = PassengerReader.getSurname(idPassenger_index);
        String airLine = PlaneReader.getAirline(idPlane_index);
        String ticket_file = idPassenger + "-" + idFlight + "-" + date;
        String ticket_path = "printerOutPut/";
        ticket_temp = ticket_path + ticket_file + ".txt";
        File ticket = new File(ticket_temp);
        ticket.createNewFile();
        FileWriter bucket = new FileWriter(ticket_temp);
        BufferedWriter buffer = new BufferedWriter(bucket);
        String side_bound = "|";
        buffer.write("_________________________________________________________________________________________________________________________________________________________");
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("    ");
        buffer.write(airLine);
        for (int i = 0; i < 148 - airLine.length(); i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("________________________________________________________________________________________________________________________________________________________");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("           Name                                                                                         Flight/Date                                     ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("           " + surname);
        for (int i = 0; i < 93 - surname.length(); i++) {
            buffer.write(" ");
        }
        buffer.write(idFlight + "  " + date + "                              ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write(" From " + departure);
        for (int i = 0; i < 91 - departure.length(); i++) {
            buffer.write(" ");
        }
        buffer.write("To " + arrival);
        for (int i = 0; i < 52 - arrival.length(); i++) {
            buffer.write(" ");
        }
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("  ____________________________________________       ____________________________________________         ____________________________________________  ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write(" | Gate                                       |     | Boarding Time                              |       | Seat No.                                   | ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write(" | " + gate);
        for (int i = 0; i < 43 - gate.length(); i++) {
            buffer.write(" ");
        }
        buffer.write("|     | " + board_time);
        for (int i = 0; i < 43 - board_time.length(); i++) {
            buffer.write(" ");
        }
        buffer.write("|       | " + seat);
        for (int i = 0; i < 43 - seat.length(); i++) {
            buffer.write(" ");
        }
        buffer.write("| |");
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write(" | __________________________________________ |     | __________________________________________ |       | __________________________________________ | ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("                                                                    HAVE A GOOD TRIP                                                                    ");
        buffer.write(side_bound);
        buffer.newLine();
        buffer.write(side_bound);
        buffer.write("________________________________________________________________________________________________________________________________________________________");
        buffer.write(side_bound);
        buffer.close();
    }

    public static String getFilePath(){
        return ticket_temp;
    }
}
