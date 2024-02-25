package game.Weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Interfaces.PurchasableItem;
import game.utils.Status;
import game.Actions.AreaAttackAction;
import game.Interfaces.SellableItem;

/**
 * A weapon with area attack capability.
 * It deals 118 damage with 88% hit rate.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */

public class Scimitar extends WeaponItem implements PurchasableItem, SellableItem {

    /**
     * Constructor for the Scimitar weapon.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88);
        addCapability(Status.ISWEAPON);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(direction, this);
    }

    public int getBuyingPrice() {
        return 600;
    }

    public int getSellingPrice() {
        return 100;
    }
}