package game.Runes;

import edu.monash.fit2099.engine.items.Item;
import game.utils.Status;

/**
 * An item scattered around the map that can be picked up by player, generated 200-10,000 runes when consumed
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */

public class GoldenRunes extends Item  {

    /***
     * Constructor.
     */
    public GoldenRunes() {
        super("Golden Runes", '*',true);
        this.addCapability(Status.GENERATE_RUNES);
    }


}
