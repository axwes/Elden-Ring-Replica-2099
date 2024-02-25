package game.Weapons;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Interfaces.SellableItem;

/**
 * An class representing Grafted Dragon
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 * @see SellableItem
 */
public class GraftedDragon extends WeaponItem implements SellableItem {
    /**
     * Constructor.
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "Burn", 90);
    }

    /**
     * Indicates selling price of Grafted Dragon
     * @return selling price of Grafted Dragon
     */
    @Override
    public int getSellingPrice() {
        return 200;
    }


}
