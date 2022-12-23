import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.function.Function;

public class JoKenPo {
    Player user;
    Player AI;
    Integer rounds;

    public JoKenPo(Player user, Player AI, Integer rounds) {
        this.user = user;
        this.AI = AI;
        this.rounds = rounds;
    }
    public void toPlay() {
        for (int i = 0; i < rounds; i++) {
            try {
                int userNumber = Integer.parseInt(userChoice());

                //if (userNumber < 1 || userNumber > 3)
                if (userNumber != 1 && userNumber != 2 && userNumber != 3) {
                    JOptionPane.showMessageDialog(null, "Invalid choice! Point to the AI!");
                    JOptionPane.showMessageDialog(null, "Next Round!");
                    //System.out.println("Invalid choice! Point to the AI!");
                    //System.out.println("Next Round!");
                    AI.increaseScore();
                    showScore();
                    continue;
                }
                int AINumber = AIChoice();
                int result = userNumber - AINumber;
                showChoices(userNumber, AINumber);

                if (result == 0){
                    JOptionPane.showMessageDialog(null, "Tie! One more round!");
                    i--;
                    continue;
                }
                roundWinner(result);
                }catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid entry.");
                    int exit = JOptionPane.showConfirmDialog(null, "Exit game?");
                    if (exit == 0){
                        break;
                    }
                }
            showScore();
        }
        showFinalScore(user, AI);
    }
    public void roundWinner(int result){
        if(result == -1 || result == 2){
            JOptionPane.showMessageDialog(null, "Point to the AI!");
            //System.out.println("Point to the AI!");
            AI.increaseScore();
        }
        else {
            JOptionPane.showMessageDialog(null, "Point to " + user.getName() + "!");
            //System.out.printf("Point to %s!\n" , user().getName());
            user.increaseScore();
        }
    }
    JDialog choicesDialog = new JDialog(){
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
    };
    JPanel choicesOutputPanel = new JPanel();

    public void showChoices(int userNumber, int AINumber) {
        Function<Integer, String> options =
                choice -> choice==1 ? "Stone" : choice==2 ? "Paper" : choice==3 ? "Scissors" : null;

        String userChoice = options.apply(userNumber);
        String AIChoice = options.apply(AINumber);
        String output = userChoice + " X " + AIChoice;
        JLabel outputLabel = new JLabel(output);

        JOptionPane.showMessageDialog(null,
        user.getName() + " X " + AI.getName() + "\n" + output);

        choicesDialog.setTitle(user.getName() + " X " + AI.getName());
        choicesOutputPanel.add(outputLabel);
        choicesOutputPanel.setLayout(new BoxLayout(choicesOutputPanel, BoxLayout.Y_AXIS));

        choicesDialog.add(choicesOutputPanel);
        choicesDialog.pack();
        choicesDialog.setVisible(true);
        /*System.out.println("\t----------------------");
        System.out.println("\t\t" + user.getName() + "\t" + " X " + "\t" + AI.getName());
        System.out.println("\t\t" + userChoice + "\t" + AIChoice);
        System.out.println("\t----------------------");*/
    }
    public void showScore() {
        JOptionPane.showMessageDialog(null,
                user.getScore() + "    X   " + AI.getScore(), user.getName() +" X " + "" + AI.getName(), JOptionPane.INFORMATION_MESSAGE);
        /*System.out.println("\t----------------------");
        System.out.println("\t\t" + user.getName() + "\t" + " X " + "\t" + AI.getName());
        System.out.println("\t\t  " + user.getScore() + "\t\t" + AI.getScore());
        System.out.println("\t----------------------");*/
    }
    public void showFinalScore(Player user, Player IA) {
        JOptionPane.showMessageDialog(null, "And the final score is...");
        showScore();
        if (user.getScore().equals(IA.getScore()))
            JOptionPane.showMessageDialog(null, "It's a Tie! No Winners.");
        else if (user.getScore() > IA.getScore())
            JOptionPane.showMessageDialog(null, "The Winner is " + user.getName() + "!");
        else
            JOptionPane.showMessageDialog(null, "The Winner is The AI!");
        //scoreDialog.dispose();
        choicesDialog.dispose();
    }
    public static String userChoice(){
        //Scanner scanner = new Scanner(System.in);
        //return scanner.nextInt();
        return JOptionPane.showInputDialog(null, """
                Please enter one of the options below:
                1 - Stone
                2 - Paper
                3 - Scissors""");
    }
    public static int AIChoice() {
        return new Random().nextInt(3)+1;
    }
}
