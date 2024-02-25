package game.Characters;
import game.Weapons.Club;

/**
 * A class that represents Starting Character of Player - Wretch.
 * Player will start with 414 hit points and a Club as their weapon.
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class Wretch extends StartingCharacter {
    /**
     * Constructor
     */
    public Wretch() {
            super(414, new Club());
    }

}
