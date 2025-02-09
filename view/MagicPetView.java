package view;

import java.util.Scanner;

public class MagicPetView {
    private Scanner scanner;

    public MagicPetView() {
        scanner = new Scanner(System.in);
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}