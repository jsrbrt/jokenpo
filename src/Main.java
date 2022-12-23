import javax.swing.*;
//import java.util.Scanner;

public class Main {
    public static JoKenPo startGame(){
        //Scanner scanner = new Scanner(System.in);

        //System.out.println("Please enter your name:");
        String playerName = JOptionPane.showInputDialog("Please enter how you want to be called:");
        Player user = new Player(playerName.toUpperCase(), 0);
        Player AI = new Player("AI", 0);

        //System.out.printf("Welcome, %s, to a game of JoKenPo!\n", user.getName());
        JOptionPane.showMessageDialog(null, "Welcome, " + user.getName() + ", to a game of JoKenPo!\n" );
        //System.out.println("Enter how many rounds do you want to play:");
        String howManyRounds = JOptionPane.showInputDialog("Enter how many rounds do you want to play:");
        try{
            int rounds = Integer.parseInt(howManyRounds);
            return new JoKenPo(user, AI, rounds);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid entry.");
            startGame();
        }
        return null;
    }
    public static void main(String[] args) {
        JoKenPo joKenPo = startGame();
        joKenPo.toPlay();
    }
}