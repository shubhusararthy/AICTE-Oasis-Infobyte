import javax.swing.*;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        int maxAttempts = 10;
        int rounds = 3;
        int score = 0;

        for (int current_round = 1; current_round <= rounds; current_round++) {
            int numberToGuess = new Random().nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;

            JOptionPane.showMessageDialog(null, "Round " + current_round + " of " + rounds + ". Start guessing!");

            while (attempts < maxAttempts && !guessed) {
                String input = JOptionPane.showInputDialog("Enter your guess (1-100):");
                int guess;

                try {
                    guess = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                    continue;
                }

                attempts++;

                if (guess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Try higher!");
                } else if (guess > numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Try lower!");
                } else {
                    guessed = true;
                    score += (maxAttempts - attempts + 1) * 10;
                    JOptionPane.showMessageDialog(null, "Correct! You guessed it in " + attempts + " attempts. Score: " + score);
                }
            }

            if (!guessed) {
                JOptionPane.showMessageDialog(null, "Out of attempts! The number was: " + numberToGuess);
            }
        }

        JOptionPane.showMessageDialog(null, "Game over! \n Your final score is: " + score);
    }
}
