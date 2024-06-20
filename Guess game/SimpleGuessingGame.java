import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SimpleGuessingGame {
    private int secretNumber;
    private int attempts;

    public SimpleGuessingGame() {
        Random random = new Random();
        secretNumber = random.nextInt(10) + 1;
        attempts = 0;
        
        // Create the frame
        JFrame frame = new JFrame("Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create components
        JLabel titleLabel = new JLabel("Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34));

        JLabel promptLabel = new JLabel("Guess a number between 1 and 10:");
        promptLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField guessTextField = new JTextField(10);
        guessTextField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.setBackground(new Color(30, 144, 255));
        guessButton.setForeground(Color.WHITE);

        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackLabel.setForeground(new Color(255, 69, 0));

        // Add action listener to the button
        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess(guessTextField, feedbackLabel);
            }
        });

        // Create a panel and add components to it
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(promptLabel, gbc);

        gbc.gridx++;
        panel.add(guessTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(guessButton, gbc);

        gbc.gridy++;
        panel.add(feedbackLabel, gbc);

        // Add panel to the frame
        frame.getContentPane().add(panel);

        // Display the frame
        frame.setVisible(true);
    }

    private void checkGuess(JTextField guessTextField, JLabel feedbackLabel) {
        String guessText = guessTextField.getText();
        try {
            int guess = Integer.parseInt(guessText);
            attempts++;
            if (guess < secretNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > secretNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText(String.format("Congratulations! You guessed the number in %d attempts.", attempts));
                guessTextField.setEditable(false);
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Please enter a valid number.");
        }
        guessTextField.setText("");
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleGuessingGame();
            }
        });
    }
}
