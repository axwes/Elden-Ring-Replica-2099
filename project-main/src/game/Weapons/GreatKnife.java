package game.Weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;
import game.Actions.QuickStepAction;
import game.Interfaces.SellableItem;
import game.Interfaces.PurchasableItem;

/**
 * Starting weapon for Bandit Class. Deals 75 damage with a 70% hit rate.
 * Created by:
 * @author Choo Carmen & Tan Yin Cheng
 * Modified by:
 *
 */
public class GreatKnife extends WeaponItem  implements PurchasableItem, SellableItem{

    /**
     * Constructor
     */
    public GreatKnife() {
        super("GreatKnife",'/',75,"hits",70);
        this.addCapability(Status.SPECIAL_WEAPON);
        this.addCapability(Status.ISWEAPON);
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
        return new QuickStepAction(target, direction, this);
    }

    @Override
    public int getBuyingPrice() {
        return 3500;
    }

    public int getSellingPrice() {
        return 350;
    }



}
