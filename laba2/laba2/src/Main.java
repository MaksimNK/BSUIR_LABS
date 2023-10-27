import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String inputText = scanner.nextLine();

        System.out.print("Введите слово для поиска: ");
        String searchWord = scanner.nextLine();


        Thread wordCountThread = new Thread(() -> {
            int count = countWordOccurrences(inputText, searchWord);
            writeResultToFile("wordCount.txt", "Количество вхождений слова '" + searchWord + "': " + count);
        });

        // Создание и запуск потока для реверса текста
        Thread reverseTextThread = new Thread(() -> {
            String reversedText = reverseText(inputText);
            writeResultToFile("reversedText.txt", reversedText);
        });

        // Создание и запуск потока для подсчета символов
        Thread characterCountThread = new Thread(() -> {
            int characterCount = inputText.length();
            writeResultToFile("characterCount.txt", "Количество символов в тексте: " + characterCount);
        });


        wordCountThread.start();
        reverseTextThread.start();
        characterCountThread.start();

        try {
            wordCountThread.join();
            reverseTextThread.join();
            characterCountThread.join();
            System.out.println("Завершено.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int countWordOccurrences(String text, String word) {
        String[] words = text.split("\\s+");
        int count = 0;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    private static String reverseText(String text) {
        StringBuilder reversed = new StringBuilder(text);
        return reversed.reverse().toString();
    }

    private static void writeResultToFile(String fileName, String result) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
