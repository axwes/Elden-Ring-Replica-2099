package game.Weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.Actions.UnsheatheAction;
import game.Interfaces.SellableItem;
import game.Interfaces.PurchasableItem;


/**
 * Starting weapon for Samurai class. Deals 115 damage with 80% attack accuracy.
 * Created by:
 * @author Choo Carmen & Tan Ying Cheng
 * Modified by:
 *
 */
public class Uchigatana extends WeaponItem implements PurchasableItem, SellableItem{

    /**
     * Constructor
     */
    public Uchigatana() {
        super("Uchigatana",')',115,"hits",80);
        this.addCapability(Status.SPECIAL_WEAPON);
    }

    /**
     * Inform a carried Item of the passage of time.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {}

    /**
     * Get an active skill action from the weapon. Use this method if you want to use a weapon skill
     * against one targeted Actor (i.e, special attack, heal, stun, etc.).
     * @param target target actor
     * @return a special Action that can be performed by this weapon (perform special attack on the enemy, etc.)
     */
    public Action getSkill(Actor target, String direction){
        return new UnsheatheAction(target, direction, this);
    }

    @Override
    public int getBuyingPrice() {
        return 5000;
    }

    @Override
    public int getSellingPrice() {
        return 500;
    }

}
