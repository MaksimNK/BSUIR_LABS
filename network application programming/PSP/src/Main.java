import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размерность матрицы");

        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.println("Введите " + (j + 1) + " элемент " + (i + 1) + "-го столбца");
                matrix[i][j] = scanner.nextInt();
            }
        }

        int mainDiagonalSum = 0;
        int secondDiagonalSum = 0;

        for (int i = 0; i < n; i++) {
            mainDiagonalSum += matrix[i][i];
            secondDiagonalSum += matrix[i][n - 1 - i];
        }

        System.out.println("Среднее значение элементов на главной диагонали: " + (double)(mainDiagonalSum / n));
        System.out.println("Среднее значение элементов на побочной диагонали: " + (double)(secondDiagonalSum / n));

    }
}