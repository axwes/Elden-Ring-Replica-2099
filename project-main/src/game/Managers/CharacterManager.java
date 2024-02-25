package game.Managers;
import game.Actors.Player;
import game.Characters.*;

import java.util.Scanner;

/**
 * Menu which is prompt during the start of game which allows player to choose their starting class
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class CharacterManager {
    /**
     * Integer which represents the SAMURAI_CLASS
     */
    public static final int SAMURAI_CLASS = 1;
    /**
     * Integer which represents the BANDIT_CLASS
     */
    public static final int BANDIT_CLASS = 2;
    /**
     * Integer which represents the WRETCH_CLASS
     */
    public static final int WRETCH_CLASS = 3;
    /**
     * Integer which represents the ASTROLOGER_CLASS
     */
    public static final int ASTROLOGER_CLASS = 4;

    /**
     * Menu which allows player to choose their starting character
     * @return choices of player
     */
    public static int menuItem() {
        Scanner sel = new Scanner(System.in);
        System.out.println("1) Samurai Class");
        System.out.println("2) Bandit Class");
        System.out.println("3) Wretch Class");
        System.out.println("4) Astrologer Class");
        System.out.print("Select one:");
        int choice = Integer.parseInt(sel.nextLine());
        if ( choice == 1 || choice ==2 || choice ==3 || choice ==4){
        System.out.println("Your choice:"+choice);
        return choice;}
        else {
            throw new IllegalArgumentException("Invalid selection ! The selection should be within 1 to 4 !");
        }
    }

    /**
     * Used to created new starting character and update the player's hitpoint
     * and starting weapon into weapon inventory based on the choice returned by menuItem(()
     * @param player whcih represents the player of the game @ Tarnished
     */
    public static void createCharacter(Player player) {
        int selection;
        selection = CharacterManager.menuItem();
        switch (selection) {
            case SAMURAI_CLASS:
                StartingCharacter.updatePlayer(player, new Samurai());
                break;
            case BANDIT_CLASS:
                StartingCharacter.updatePlayer(player, new Bandit());
                break;
            case WRETCH_CLASS:
                StartingCharacter.updatePlayer(player, new Wretch());
                break;
            case ASTROLOGER_CLASS:
                StartingCharacter.updatePlayer(player, new Astrologer());
                break;
        }
    }
}
