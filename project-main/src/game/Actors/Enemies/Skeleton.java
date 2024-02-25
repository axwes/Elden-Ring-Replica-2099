package game.Actors.Enemies;

import game.utils.Status;
/**
 * Skeleton is an abstract class representing all skeleton type Actors in the game. It extends the Enemies class and provides
 * capabilities for skeletons.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public abstract class Skeleton extends Enemies {

    /**
     * Constructor for the Skeleton class.
     *
     * @param name        The name of the skeleton enemy
     * @param displayChar The character used to display the skeleton enemy on the map
     * @param hitPoints   The hit points of the skeleton enemy
     * @param minRunes    The minimum Runes generated upon defeat
     * @param maxRunes    The maximum Runes generated upon defeat
     */
    public Skeleton(String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, minRunes, maxRunes);
        addCapability(Status.BECOMEPILESOFBONES);
        addCapability(Status.HAVESPECIALATTACK);
    }

}
