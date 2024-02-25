package game.Characters;
import game.Weapons.GreatKnife;
/**
 * A class that represents Starting Character of Player - Bandit.
 * Player will have starting hit point of 414, and Great Knife in
 * their weapon inventory.
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class Bandit extends StartingCharacter {

    /**
     * Constructor
     */
    public Bandit(){
        super(414,new GreatKnife()); //changed the hitpoint for testing purposes, remember to chnage it back
    }

}
