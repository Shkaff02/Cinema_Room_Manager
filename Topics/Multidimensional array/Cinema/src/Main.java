import java.util.*;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int seats = scanner.nextInt();
        int count = 1;
        int[][] cinema = new int[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                cinema[i][j] = scanner.nextInt();
            }
        }
        int k = scanner.nextInt();
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length - 1; j++) {
                if (cinema[i][j] == 0 && cinema[i][j+1] == 0) {
                    count++;
                }
            }
            if (count == k) {
                System.out.print(i+1);
                return;
            } else {
                count = 1;
            }
        }
        System.out.print(0);
    }
}
