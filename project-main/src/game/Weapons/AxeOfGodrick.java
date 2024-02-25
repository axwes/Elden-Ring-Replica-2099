package game.Weapons;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Interfaces.SellableItem;

/**
 * A class representing Weapon Axe Of Godrick
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 * @see SellableItem
 */
public class AxeOfGodrick extends WeaponItem implements SellableItem {
    /**
     * Constructor.
     */
    public AxeOfGodrick() {
        super("Axe Of Godrick", 'T',142, "slashes", 84);
    }

    /**
     * Indicates selling price of Axe Of Godrick
     * @return selling price of Axe Of Godrick
     */
    @Override
    public int getSellingPrice() {
        return 200;
    }

}
