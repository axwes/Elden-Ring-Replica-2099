package game.Actors.Enemies;

import game.utils.Status;
/**
 * Crab is an abstract class representing all crab type Actors in the game. It extends the Enemies class and provides
 * capabilities for crabs.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public abstract class Crab extends Enemies {
    /**
     * Fixed verb for the crab type enemies
     */
    protected final String FIXED_VERB = "slams";

    /**
     * Constructor for the crab class.
     *
     * @param name        The name of the skeleton enemy
     * @param displayChar The character used to display the skeleton enemy on the map
     * @param hitPoints   The hit points of the skeleton enemy
     * @param minRunes    The minimum Runes generated upon defeat
     * @param maxRunes    The maximum Runes generated upon defeat
     */
    public Crab (String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, minRunes, maxRunes);
        addCapability(Status.HAVESPECIALATTACK);
    }

}