import java.util.Random;
import java.util.Scanner;

public class TextRPG {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Declare variables outside the loop
        int playerWins = 0;
        int monsterWins = 0;
        char playerChoiceChar;
        String playerChoice;
        String monsterChoice;
        int result;

        System.out.println("Welcome to the Text Adventure RPG!");

        while (true) {
            System.out.println("\nYou enter a mysterious room...");

            // Randomly decide encounter
            boolean isTreasure = random.nextBoolean();

            if (isTreasure) {
                System.out.println("You find a treasure chest! Congratulations!");
                break; // End the game after finding treasure
            } else {
                System.out.println("A monster appears! Prepare for battle!");

                // Rock, Paper, Scissors combat
                playerWins = 0;
                monsterWins = 0;

                while (playerWins < 2 && monsterWins < 2) {
                    System.out.print("Choose your weapon (r for rock, p for paper, s for scissors): ");
                    
                    // Check for available input before reading
                    if (!scanner.hasNextLine()) {
                        System.out.println("Invalid input. Please try again.");
                        continue;
                    }

                    playerChoiceChar = scanner.next().charAt(0);

                    // Validate input
                    if (playerChoiceChar != 'r' && playerChoiceChar != 'p' && playerChoiceChar != 's') {
                        System.out.println("Invalid choice. Please enter r, p, or s.");
                        continue;
                    }

                    // Transform single character to full string
                    playerChoice = transformChoice(playerChoiceChar);

                    monsterChoice = getRandomChoice();

                    System.out.println("The monster chooses " + monsterChoice + ".");

                    result = determineWinner(playerChoice, monsterChoice, playerWins, monsterWins); // Pass playerWins and monsterWins by reference

                    showRoundWinner(result); // Call function to show round winner
                }

                // Show the winner and exit the loop
                showWinner(playerWins, monsterWins); // Pass playerWins and monsterWins as arguments                
            }
        }

        System.out.println("\nThanks for playing!");
    }

    // Function to determine and show the winner of a round
    private static void showRoundWinner(int result) {
        if (result == 1) {
            System.out.println("You win this round!");
        } else if (result == -1) {
            System.out.println("The monster wins this round!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // Function to show the overall winner of the battle
    private static void showWinner(int playerWins, int monsterWins) { // Receive playerWins and monsterWins as parameters
        if (playerWins > monsterWins) {
            System.out.println("You defeat the monster! Congratulations!");            
        } else {
            System.out.println("The monster defeats you! Game over.");            
        }
    }

    // Function to transform the player's choice
    private static String transformChoice(char choiceChar) {
        switch (choiceChar) {
            case 'r':
                return "rock";
            case 'p':
                return "paper";
            case 's':
                return "scissors";
            default:
                return ""; // Should never happen due to input validation
        }
    }

    private static String getRandomChoice() {
        String[] choices = {"rock", "paper", "scissors"};
        return choices[new Random().nextInt(choices.length)];
    }

    // Function to determine the winner of a round and update scores by reference
    private static int determineWinner(String playerChoice, String monsterChoice, int playerWins, int monsterWins) {
        // Define winning combinations
        win_conditions = {
            "rock": {"scissors": True},
            "paper": {"rock": True},
            "scissors": {"paper": True},
        }

        // Check if player wins
        if win_conditions[playerChoice].get(monsterChoice):
            playerWins += 1
            return 1

        // Check if monster wins
        elif win_conditions[monsterChoice].get(playerChoice):
            monsterWins += 1
            return -1

        // Tie
        else:
            return 0
    }
}