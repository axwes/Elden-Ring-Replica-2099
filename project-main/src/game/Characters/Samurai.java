package game.Characters;
import game.Weapons.Uchigatana;

/**
 * A class that represents Starting Character of Player - Samurai.
 * Player will start the game with Uchigatana as their starting weapon.
 * Starting hit point will be 455.
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class Samurai extends StartingCharacter {

    /**
     * Constructor
     */
    public Samurai(){
        super(455, new Uchigatana());
    }

}
