package com.company;
import java.util.Scanner;

public class Main {

    private static Scanner user = new Scanner(System.in);
    public static int rows;
    public static int seats;
    public static String[][] arr;

    public static int totalCost() {
        int area = rows * seats;

        if(area <= 60) {
            return area * 10;
        }else if (area > 60){
            return ((rows / 2) * seats * 10 + (rows - rows/2) * 8 * seats);
        } else {
            return 0;
        }
    }

    public static String[][] basicGrid(int rows, int seats) {

        arr = new String[rows + 1][seats +1];
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= seats; col++) {
                if (row == 0 && col == 0) {
                    arr[row][col] = " ";
                } else if (row == 0) {
                    arr[0][col] = String.valueOf(col);
                } else if (col == 0) {
                    arr[row][0] = String.valueOf(row);
                } else {
                    arr[row][col] = "S";
                }
            }
        }
        return arr;
    }

    public static void showArray(String[][] arr) {
        System.out.println("\nCinema:");
        for (int i = 0; i < Main.rows+1; i++) {
            for (int j = 0; j < Main.seats+1; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void bookedSeat( int rowsEntered, int seatsEntered) {

        for (int i = 0; i < Main.rows; i++) {
            for (int j = 0; j < Main.seats; j++) {
                arr[rowsEntered][seatsEntered] = "B";
            }
        }
    }

    public static int bookedSeatCost( int rowsEntered) {
        int area = rows * seats;

        if (area <= 60) {
            return 10;
        } else {
            if (rows % 2 == 0) {
                return 10;
            } else {
                if (rows / 2 < rowsEntered) {
                    return 8;
                } else if (rowsEntered <= rows/2) {
                    return 10;
                }
            }
            return 0;
        }
    }


    public static void main(String[] args) {
        System.out.print("Enter the number of rows: ");
        Main.rows = user.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        Main.seats = user.nextInt();
        int ticketsBought = 0;
        int sum = 0;

        basicGrid(rows, seats);

        boolean isRun = false;
        while(!isRun) {

            System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
            int entered = user.nextInt();

            if (entered == 1) {

                showArray(arr);

            } else if (entered == 2) {

                boolean isOk = false;
                while(!isOk){
                    System.out.print("Enter a row number: ");
                    int rowsEntered = user.nextInt();
                    System.out.print("Enter a seat number in that row: ");
                    int seatsEntered = user.nextInt();
                    System.out.println();
                    if (rowsEntered > rows || seatsEntered > seats) {
                        System.out.println("Wrong input!");
                    } else {
                        if (arr[rowsEntered][seatsEntered] == "S") {
                            bookedSeat(rowsEntered, seatsEntered);
                            ticketsBought++;
                            System.out.printf("Ticket cost: $%d\n", bookedSeatCost(rowsEntered));
                            sum = sum + bookedSeatCost(rowsEntered);
                            isOk = true;
                        } else if (arr[rowsEntered][seatsEntered] != "S") {
                            System.out.println("That ticket has already been purchased!");
                        }

                    }
                }

            } else if(entered == 3) {

                System.out.printf("Number of purchased tickets: %d\n", ticketsBought);
                System.out.printf("Percentage: %.2f%% \n",((float)ticketsBought/(Main.rows*Main.seats)) * 100 );
                System.out.printf("Current income: $%d\n", sum);
                System.out.printf("Total income: $%d\n",totalCost());

            } else if (entered == 0) {
                isRun = true;
            }
        }
    }
}




