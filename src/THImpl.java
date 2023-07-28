import java.util.ArrayList;
import java.util.Arrays;

public class THImpl extends java.rmi.server.UnicastRemoteObject implements THInterface {

    final int PRICEA = 45;
    final int PRICEB = 35;
    final int PRICEC = 25;
    final int PRICECENTER = 30;
    final int PRICESIDES = 20;
    public String[] zoneA = new String[100]; //ZA - 45 euro per seat
    public String[] zoneB = new String[200]; //ZB - 35 euro per seat
    public String[] zoneC = new String[400]; //ZC - 25 euro per seat
    public String[] center = new String[225]; //CE - 30 euro per seat
    public String[] sides = new String[75]; //SI - 20 euro per seat
    //"" = free seat
    public THImpl() throws java.rmi.RemoteException {
        super();

        //init all arrays with empty string
        Arrays.fill(zoneA, "");
        Arrays.fill(zoneB, "");
        Arrays.fill(zoneC, "");
        Arrays.fill(center, "");
        Arrays.fill(sides, "");
    }

    public String list() {

        String list = "";

        int emptyA = 0;
        int emptyB = 0;
        int emptyC = 0;
        int emptyCenter = 0;
        int emptySides = 0;

        for (String i: zoneA) {
            if (i.equals("")) {
                emptyA++;
            }
        }
        for (String i: zoneB) {
            if (i.equals("")) {
                emptyB++;
            }
        }
        for (String i: zoneC) {
            if (i.equals("")) {
                emptyC++;
            }
        }
        for (String i: center) {
            if (i.equals("")) {
                emptyCenter++;
            }
        }
        for (String i: sides) {
            if (i.equals("")) {
                emptySides++;
            }
        }

        list += emptyA + " Seats zone A (code ZA) - Price: 45 euro\n";
        list += emptyB + " Seats zone B (code ZA) - Price: 35 euro\n";
        list += emptyC + " Seats zone C (code ZA) - Price: 25 euro\n";
        list += emptyCenter + " Seats Center (code CE) - Price: 30 euro\n";
        list += emptySides + " Seats Side (code SI) - Price: 20 euro\n";

        return list;
    }

    public String book(String type, int number, String name) {

        int totalCost = 0;
        int tmpNumber = number;

        if (type.equals("")) {
            return "Please enter a seat type.";
        }
        if (name.equals("")) {
            return "Please enter a name.";
        }
        if (number <= 0) {
            return "Please enter a positive number of seats.";
        }

        if (type.equals("ZA")) {
            for (int i = 0; i < zoneA.length; i ++) {
                if (zoneA[i].equals("") && tmpNumber > 0) {
                    tmpNumber--;
                    zoneA[i] = name;
                    totalCost += PRICEA;
                }
            }
            if (tmpNumber == number) {
                return "Sorry, there are no available seats.";
            }
            if (tmpNumber > 0) {
                return "Not Enough available seats. Booked remaining " + (number - tmpNumber) +
                        " number of Zone A seats\n" + "Total cost: " + totalCost + " euro";
            }
            return "Booked " + number + " number of Zone A seats\n" +
                    "Total cost: " + totalCost + " euro";
        }

        if (type.equals("ZB")) {
            for (int i = 0; i < zoneB.length; i ++) {
                if (zoneB[i].equals("") && tmpNumber > 0) {
                    tmpNumber--;
                    zoneB[i] = name;
                    totalCost += PRICEB;
                }
            }
            if (tmpNumber == number) {
                return "Sorry, there are no available seats.";
            }
            if (tmpNumber > 0) {
                return "Not Enough available seats. Booked remaining " + (number - tmpNumber) +
                        " number of Zone B seats\n" + "Total cost: " + totalCost + " euro";
            }
            return "Booked " + (number - tmpNumber) + " number of Zone B seats\n" +
                    "Total cost: " + totalCost + " euro";
        }

        if (type.equals("ZC")) {
            for (int i = 0; i < zoneC.length; i ++) {
                if (zoneC[i].equals("") && tmpNumber > 0) {
                    tmpNumber--;
                    zoneC[i] = name;
                    totalCost += PRICEC;
                }
            }
            if (tmpNumber == number) {
                return "Sorry, there are no available seats.";
            }
            if (tmpNumber > 0) {
                return "Not Enough available seats. Booked remaining " + (number - tmpNumber) +
                        " number of Zone C seats\n" + "Total cost: " + totalCost + " euro";
            }
            return "Booked " + (number - tmpNumber) + " number of Zone C seats\n" +
                    "Total cost: " + totalCost + " euro";
        }

        if (type.equals("CE")) {
            for (int i = 0; i < center.length; i ++) {
                if (center[i].equals("") && tmpNumber > 0) {
                    tmpNumber--;
                    center[i] = name;
                    totalCost += PRICECENTER;
                }
            }
            if (tmpNumber == number) {
                return "Sorry, there are no available seats.";
            }
            if (tmpNumber > 0) {
                return "Not Enough available seats. Booked remaining " + (number - tmpNumber) +
                        " number of Center seats\n" + "Total cost: " + totalCost + " euro";
            }
            return "Booked " + (number - tmpNumber) + " number of Center seats\n" +
                    "Total cost: " + totalCost + " euro";
        }

        if (type.equals("SI")) {
            for (int i = 0; i < sides.length; i ++) {
                if (sides[i].equals("") && tmpNumber > 0) {
                    tmpNumber--;
                    sides[i] = name;
                    totalCost += PRICESIDES;
                }
            }
            if (tmpNumber == number) {
                return "Sorry, there are no available seats.";
            }
            if (tmpNumber > 0) {
                return "Not Enough available seats. Booked remaining " + (number - tmpNumber) +
                        " number of Sides seats\n" + "Total cost: " + totalCost + " euro";
            }
            return "Booked " + (number - tmpNumber) + " number of Sides seats\n" +
                    "Total cost: " + totalCost + " euro";
        }
        else {
            return "Invalid seat type. Must be: ZA, ZB, ZC, CE, SI";
        }
    }

    public String guests() {

        ArrayList<String> guestNames = new ArrayList<>(); //holds guest names
        ArrayList<int[]> guestSeats = new ArrayList<>(); //holds 5 ints (5 seat types) per guest

        //ZA
        for (String i : zoneA) {
            if (!guestNames.contains(i)) {
                guestNames.add(i);
                guestSeats.add(new int[]{0, 0, 0, 0, 0});
            }
            if (!i.equals("")) {
                guestSeats.get(guestNames.indexOf(i))[0] += 1;
            }
        }
        //ZB
        for (String i : zoneB) {
            if (!guestNames.contains(i)) {
                guestNames.add(i);
                guestSeats.add(new int[]{0, 0, 0, 0, 0});
            }
            if (!i.equals("")) {
                guestSeats.get(guestNames.indexOf(i))[1] += 1;
            }
        }
        //ZC
        for (String i : zoneC) {
            if (!guestNames.contains(i)) {
                guestNames.add(i);
                guestSeats.add(new int[]{0, 0, 0, 0, 0});
            }
            if (!i.equals("")) {
                guestSeats.get(guestNames.indexOf(i))[2] += 1;
            }
        }
        //CE
        for (String i : center) {
            if (!guestNames.contains(i)) {
                guestNames.add(i);
                guestSeats.add(new int[]{0, 0, 0, 0, 0});
            }
            if (!i.equals("")) {
                guestSeats.get(guestNames.indexOf(i))[3] += 1;
            }
        }
        //SI
        for (String i : sides) {
            if (!guestNames.contains(i)) {
                guestNames.add(i);
                guestSeats.add(new int[]{0, 0, 0, 0, 0});
            }
            if (!i.equals("")) {
                guestSeats.get(guestNames.indexOf(i))[4] += 1;
            }
        }

        String guests = "";

        for (String i : guestNames) {

            if (!i.equals("")) {
                int totalCost = 0;

                totalCost += guestSeats.get(guestNames.indexOf(i))[0] * PRICEA;
                totalCost += guestSeats.get(guestNames.indexOf(i))[1] * PRICEB;
                totalCost += guestSeats.get(guestNames.indexOf(i))[2] * PRICEC;
                totalCost += guestSeats.get(guestNames.indexOf(i))[3] * PRICECENTER;
                totalCost += guestSeats.get(guestNames.indexOf(i))[4] * PRICESIDES;

                guests += guestNames.get(guestNames.indexOf(i)) + " has booked:\n"
                        + " ZA = "+ guestSeats.get(guestNames.indexOf(i))[0]
                        + ", ZB = "+ guestSeats.get(guestNames.indexOf(i))[1]
                        + ", ZC = "+ guestSeats.get(guestNames.indexOf(i))[2]
                        + ", CE = "+ guestSeats.get(guestNames.indexOf(i))[3]
                        + ", SI = "+ guestSeats.get(guestNames.indexOf(i))[4]
                        + "\nTotal cost: "+ totalCost + " euro\n\n";
            }
        }

        if (guests.equals("")) {
            return "We currently have no guests.";
        }
        return guests;
    }

    public String cancel(String type, int number, String name) {

        int tmpNumber = number;
        int remaining = 0;

        if (type.equals("")) {
            return "Please enter a seat type.";
        }
        if (name.equals("")) {
            return "Please enter a name.";
        }
        if (number <= 0) {
            return "Please enter a positive number of seats.";
        }

        if (type.equals("ZA")) {
            for (int i = 0; i < zoneA.length; i++) {
                if (zoneA[i].equals(name)) {
                    if (tmpNumber == 0) {
                        remaining++;
                    } else {
                        tmpNumber--;
                        zoneA[i] = "";
                    }
                }
            }
            if (number == tmpNumber) {
                return "No bookings for Zone A found from " + name;
            }
            return "Cancelled " + (number - tmpNumber) + " Zone A seats for " + name
                    + "\nRemaining: " + remaining + " seats\n";
        }
        if (type.equals("ZB")) {
            for (int i = 0; i < zoneB.length; i++) {
                if (zoneB[i].equals(name)) {
                    if (tmpNumber == 0) {
                        remaining++;
                    } else {
                        tmpNumber--;
                        zoneB[i] = "";
                    }
                }
            }
            if (number == tmpNumber) {
                return "No bookings for Zone B found from " + name;
            }
            return "Cancelled " + (number - tmpNumber) + "Zone B seats for " + name
                    + "\nRemaining: " + remaining + " seats\n";
        }
        if (type.equals("ZC")) {
            for (int i = 0; i < zoneC.length; i++) {
                if (zoneC[i].equals(name)) {
                    if (tmpNumber == 0) {
                        remaining++;
                    } else {
                        tmpNumber--;
                        zoneC[i] = "";
                    }
                }
            }
            if (number == tmpNumber) {
                return "No bookings for Zone C found from " + name;
            }
            return "Cancelled " + (number - tmpNumber) + "Zone C seats for " + name
                    + "\nRemaining: " + remaining + " seats\n";
        }
        if (type.equals("CE")) {
            for (int i = 0; i < center.length; i++) {
                if (center[i].equals(name)) {
                    if (tmpNumber == 0) {
                        remaining++;
                    } else {
                        tmpNumber--;
                        center[i] = "";
                    }
                }
            }
            if (number == tmpNumber) {
                return "No bookings for Center found from " + name;
            }
            return "Cancelled " + (number - tmpNumber) + "Center seats for " + name
                    + "\nRemaining: " + remaining + " seats\n";
        }
        if (type.equals("SI")) {
            for (int i = 0; i < sides.length; i++) {
                if (sides[i].equals(name)) {
                    if (tmpNumber == 0) {
                        remaining++;
                    } else {
                        tmpNumber--;
                        sides[i] = "";
                    }
                }
            }
            if (number == tmpNumber) {
                return "No bookings for Sides found from " + name;
            }
            return "Cancelled " + (number - tmpNumber) + "Side seats for " + name
                    + "\nRemaining: " + remaining + " seats\n";
        } else {
            return "Invalid seat type. Must be: ZA, ZB, ZC, CE, SI";
        }
    }
}
