// main.java
// importing necessary libraries
import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

// main class for the Craps game
public class main {

    // making the main frame/gui for the game to be on
    public final JFrame frame = new JFrame("Craps Game");
    // log area
    private final JTextArea log = new JTextArea();
    // betting area
    private final JLabel moneyLabel = new JLabel();
    // bet input field
    private final JTextField betField = new JTextField("50", 6);
    // random number generator for dice rolls with secure randomness for better simulation
    private final SecureRandom rng = new SecureRandom();

    // basic craps rules text
    private static final String RULES = String.join("\n",
        "HOW TO PLAY CRAPS (Quick Guide)",
        "",
        "Round starts with a Come-Out Roll:",
        " • 7 or 11 = WIN immediately (natural).",
        " • 2, 3, or 12 = LOSE immediately (craps).",
        " • 4, 5, 6, 8, 9, 10 = that number becomes the POINT.",
        "",
        "Point Phase:",
        " • Keep rolling until you either:",
        "   - Re-roll the POINT → WIN",
        "   - Roll a 7 → LOSE (seven-out)",
        "",
        "Pass Line bet (this demo):",
        " • Place a bet before the come-out roll.",
        " • If you win the round, you’re paid even money.",
        " • If you lose the round, you lose your bet.",
        ""
    );

    // making the money and bet variables
    private int money = 1000;   // bankroll
    // current active bet
    private int currentBet = 0; // 0 means no active bet

    // -------- main method --------
    public static void main(String[] args) {
        // launch the UI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new main().UI());
    }

    // -------- UI setup --------
    private void UI() {
        // frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // size and layout
        frame.setSize(420, 360);
        frame.setLayout(new BorderLayout());

        // log area
        log.setEditable(false);
        // Setting line wrap so it doesn't go off the edge
        log.setLineWrap(true);
        // Setting word wrap so it doesn't cut words in half
        log.setWrapStyleWord(true);
        // Makes the font monospaced for better alignment of dice faces
        log.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // add log with scroll pane to center
        frame.add(new JScrollPane(log), BorderLayout.CENTER);

        // Bottom area: controls + betting
        JPanel south = new JPanel(new BorderLayout());
        south.add(makeControls(), BorderLayout.NORTH);
        south.add(makeBetPanel(), BorderLayout.SOUTH);
        frame.add(south, BorderLayout.SOUTH);

        // set frame visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // initial log message
        log.append("Welcome to Craps!\n");

        // Displaying the rules at startup
        log.append("\n" + RULES + "\n");
    }

    // -------- top control row  --------
    private JPanel makeControls() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Rules button
        JButton rulesBtn = new JButton("Show Rules");
        // Add action listener for the rules button
        rulesBtn.addActionListener(e ->
        // show rules dialog
            JOptionPane.showMessageDialog(frame, RULES, "Craps Rules", JOptionPane.INFORMATION_MESSAGE)
        );

        // Take spot button
        JButton takeSpotButton = new JButton("Take a spot at the table");
        // Add action listener for the take spot button
        takeSpotButton.addActionListener(e ->
        // log message for taking a spot
            log.append("Spot taken at the table. You may now begin betting.\n")
        );

        // Roll dice button
        JButton rollBtn = new JButton("Roll Dice");
        // Add action listener for the roll button
        rollBtn.addActionListener(e -> {
            // rolling two dice
            int d1 = rollDie();
            int d2 = rollDie();
            // calculating sum of dice
            int sum = d1 + d2;
            // logging the roll result
            log.append(String.format("You rolled: %d + %d = %d%n", d1, d2, sum));
            // logging the dice faces
            log.append("\n" + diceFace(d1) + "\n" + diceFace(d2) + "\n\n");

            //  If this is the come-out roll, 7/11 win; 2/3/12 lose; else set point.
            //  If in point phase, hitting point wins; rolling 7 loses.

            // Demo settle: instant win on 7/11, instant loss on 2/3/12
            //win on 7 or 11
            if (sum == 7 || sum == 11) {
                settleBet(true);

            //lose on 2, 3, or 12
            } else if (sum == 2 || sum == 3 || sum == 12) {
                settleBet(false);

            // point establishment (4,5,6,8,9,10)
            } else {
                log.append("Point established. No settle yet.\n");
            }
        });

        // adding buttons to panel
        p.add(takeSpotButton);
        p.add(rollBtn);
        p.add(rulesBtn);

        return p;
    }

    // -------- helper method to roll a single die --------
    private int rollDie() {
        // returns a number between 1 and 6
        return rng.nextInt(6) + 1;
    }

    // -------- renders an ASCII dice face for numbers 1..6 --------
    private String diceFace(int n) {
        // switch case for each die face
        switch (n) {
            case 1: return
                "+-----+\n" +
                "|     |\n" +
                "|  •  |\n" +
                "|     |\n" +
                "+-----+";
            case 2: return
                "+-----+\n" +
                "|•    |\n" +
                "|     |\n" +
                "|    •|\n" +
                "+-----+";
            case 3: return
                "+-----+\n" +
                "|•    |\n" +
                "|  •  |\n" +
                "|    •|\n" +
                "+-----+";
            case 4: return
                "+-----+\n" +
                "|•   •|\n" +
                "|     |\n" +
                "|•   •|\n" +
                "+-----+";
            case 5: return
                "+-----+\n" +
                "|•   •|\n" +
                "|  •  |\n" +
                "|•   •|\n" +
                "+-----+";
            case 6: return
                "+-----+\n" +
                "|•   •|\n" +
                "|•   •|\n" +
                "|•   •|\n" +
                "+-----+";
            default:
                return "[invalid die: " + n + "]";
        }
    }

    // -------- betting row --------
    private JPanel makeBetPanel() {
        // creating the panel with flow layout
        JPanel betPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // setting initial money label
        moneyLabel.setText(String.valueOf(money));
        // place bet button
        JButton placeBetBtn = new JButton("Place Pass Bet");

        // Add action listener for the place bet button
        placeBetBtn.addActionListener(e -> {
            // parse bet amount
            try {
                // get bet amount from text field
                int bet = Integer.parseInt(betField.getText().trim());
                // validate bet
                if (bet <= 0) {
                    // let user know bet must be positive
                    log.append("Bet must be > 0.\n");
                    return;
                }
                // check if enough bankroll
                if (bet > money) {
                    // let user know not enough money
                    log.append("Not enough bankroll.\n");
                    return;
                }
                // check if there's already an active bet
                if (currentBet != 0) {
                    // let user know there's already a bet
                    log.append("You already have an active bet.\n");
                    return;
                }

                // place the bet
                currentBet = bet;
                // deduct bet from bankroll immediately
                money -= bet; 
                // update money label
                updateMoneyLabel();
                // Clarify bankroll movement + phase hint
                log.append("Placed $" + currentBet + " Pass Line bet (stake deducted). Awaiting come-out roll.\n");
                // clear bet field
                betField.setText("");
                // catch invalid number format
            } catch (NumberFormatException ex) {
                // let user know to enter a valid whole number
                log.append("Enter a whole number bet.\n");
            }
        });

        // adding components to bet panel
        // bankroll label, money label, bet label, bet field, place bet button
        betPanel.add(new JLabel("Bankroll: $"));
        betPanel.add(moneyLabel);
        betPanel.add(new JLabel("  Bet: $"));
        betPanel.add(betField);
        betPanel.add(placeBetBtn);
        return betPanel;
    }

    // -------- even-money demo --------
    // settles the current bet, win or lose
    private void settleBet(boolean won) {
        // check if there's an active bet
        if (currentBet == 0) {
            // let user know no bet to settle
            log.append("No active bet to settle.\n");
            return;
        }
        // settle the bet
        if (won) {
            // player wins
            money += currentBet * 2; // return stake + winnings
            // lets user know they won and adds winnings to bankroll
            log.append("You won $" + currentBet + ". ");

        // player loses
        } else {
            // lets user know they lost the bet and deducts bet from bankroll   
            log.append("You lost $" + currentBet + ". ");
        }

        // reset current bet
        currentBet = 0;
        // update money label
        updateMoneyLabel();
        // log updated bankroll
        log.append("Bankroll now $" + money + ".\n");
    }

    //  method to update money label 
    private void updateMoneyLabel() {
        moneyLabel.setText(String.valueOf(money));
    }
}
