package game.Actors.Enemies;

/**
 * Dog is an abstract class representing all dog type Actors in the game. It extends the Enemies class and provides
 * capabilities for dogs.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public abstract class Dog extends Enemies {

    /**
     * Constructor for the Dog class.
     *
     * @param name        The name of the dog enemy
     * @param displayChar The character used to display the dog enemy on the map
     * @param hitPoints   The hit points of the dog enemy
     * @param minRunes    The minimum Runes generated upon defeat
     * @param maxRunes    The maximum Runes generated upon defeat
     */
    public Dog(String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, minRunes, maxRunes);
    }

}
