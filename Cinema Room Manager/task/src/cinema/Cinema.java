package cinema;
import java.util.*;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);

    String seat = "S";
    String booked = "B";
    boolean moreThanSixty = false;

    int price = 0;
    int priceFirst = 0;
    int priceSecond = 0;

    int firstHalf = 0;
    int secondHalf = 0;

    int bookedSeats = 0;
    double perc = 0;
    int currIncome = 0;
    int totalIncome = 0;
    double totalSeats = 0;

    String[][] cinema;

    public static void main(String[] args) {
        int action = -1;
        Cinema kino = new Cinema();

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        kino.initialize(rows, seats);

        while (action != 0 ) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            action = scanner.nextInt();
            switch (action) {
                case 1: kino.printRoom();
                    break;

                case 2: kino.buyTicket();
                    break;

                case 3: kino.printStatistics();
                    break;
            }
        }
    }


    public void printRoom() {
        System.out.println("Cinema:");
        System.out.print("  ");
        int count = 1;
        while(count <= cinema[0].length) {
            System.out.print(count);
            if (count != cinema[0].length) {
                System.out.print(" ");
            }
            count++;
        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            System.out.print(i+1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.print(cinema[i][j]);
                if (j != cinema[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void printStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", bookedSeats);
        System.out.printf("Percentage: %.2f%%%n", perc);
        System.out.printf("Current income: $%d%n", currIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public void buyTicket() {
        int rowNumber;
        int seatRow;
        do {
            System.out.println("Enter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatRow = scanner.nextInt();
        } while (!isAvailable(rowNumber - 1, seatRow - 1));


        if (moreThanSixty) {
            if (rowNumber <= firstHalf) {
                System.out.println("Ticket price: $" + priceFirst);
            } else {
                System.out.println("Ticket price: $" + priceSecond);
            }
        } else {
            System.out.println("Ticket price: $" + price);
        }

        cinema[rowNumber-1][seatRow-1] = booked;
        currentIncome(rowNumber);
        bookedSeats++;
        perc = bookedSeats / totalSeats * 100;
    }

    public void currentIncome(int row) {
        if (moreThanSixty) {
            if (row <= firstHalf) {
                currIncome += priceFirst;
            } else {
                currIncome += priceSecond;
            }
        } else {
            currIncome += price;
        }
    }

    public void initialize(int rows, int seats) {
        firstHalf = rows / 2;
        secondHalf = rows - firstHalf;
        if (rows * seats <= 60) {
            price = 10;
        } else {
            priceFirst = 10;
            priceSecond = 8;
            moreThanSixty = true;
        }

        cinema = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = seat;
            }
        }

        if(moreThanSixty) {
            totalIncome += countProfit(priceFirst, firstHalf, seats);
            totalIncome += countProfit(priceSecond, secondHalf, seats);
        } else {
            totalIncome = seats * rows * price;
        }
        totalSeats = rows * seats;
    }

    public boolean isAvailable(int row, int seat) {
        if (row < 0 || seat < 0 || row >= cinema.length || seat >= cinema[0].length) {
            System.out.println("Wrong input!");
            return false;
        }
        if (cinema[row][seat].equals(booked)) {
            System.out.println("That ticket has already been purchased!");
            return false;
        }
        return true;
    }

    public int countProfit(int price, int half, int seats) {
        int result = 0;
        for (int i = 1; i <= half; i++) {
            result += seats * price;
        }
        return result;
    }

}