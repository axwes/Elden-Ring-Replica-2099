package game.Weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Interfaces.SellableItem;
import game.Interfaces.PurchasableItem;
import game.utils.Status;


/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Choo Carmen & Tan Yin Cheng
 *
 */
public class Club extends WeaponItem implements PurchasableItem, SellableItem{

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
        this.addCapability(Status.ISWEAPON);
    }

    /**
     * Inform a carried Item of the passage of time.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {}

    @Override
    public int getBuyingPrice() {
        return 600;
    }

    public int getSellingPrice() {
        return 600;
    }

}
