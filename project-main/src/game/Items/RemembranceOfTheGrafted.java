package game.Items;

import edu.monash.fit2099.engine.items.Item;
import game.utils.Status;

public class RemembranceOfTheGrafted extends Item {
    /***
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance Of The Grafted", 'o', true);
        this.addCapability(Status.EXCHANGABLE);
    }
}
