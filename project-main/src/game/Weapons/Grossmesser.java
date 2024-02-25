package game.Weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.Actions.AreaAttackAction;
import game.Interfaces.SellableItem;

/**
 * A weapon with area attack capability.
 * It deals 115 damage with 85% hit rate.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class Grossmesser extends WeaponItem implements SellableItem{
    
    /**
     * Constructor for the Grossmesser weapon.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slashes", 85);
        addCapability(Status.ISWEAPON);
    }

    @Override
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(direction, this);
    }

    public int getSellingPrice() {
        return 100;
    }
}
