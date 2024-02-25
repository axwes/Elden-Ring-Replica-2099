package game.Actors.Merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.SellWeaponItemAction;
import game.Weapons.*;
import game.utils.Status;

import java.util.List;

/**
 * An abstract class representing Merchant in the game.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public abstract class Merchant extends Actor {

    /**
     * Constructor for a Merchant.
     *
     * @param name the name of the Merchant
     * @param displayChar the character used to represent the Merchant on the map
     * @param hitPoints the number of hit points the Merchant has which is just a placeholder because the merchant cannot be attacked.
     */
    public Merchant(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public ActionList PlayerSellWeapons (Actor player) {
        ActionList actions = new ActionList();
        List<WeaponItem> inventory = player.getWeaponInventory();
        // looping through otherActor inventory to check what they can sell
        if (player.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (WeaponItem weapon : inventory) {
                if (weapon.getDisplayChar() == ')') {
                    actions.add(new SellWeaponItemAction(weapon, new Uchigatana().getSellingPrice()));
                } else if (weapon.getDisplayChar() == '/') {
                    actions.add(new SellWeaponItemAction(weapon, new GreatKnife().getSellingPrice()));
                } else if (weapon.getDisplayChar() == '!') {
                    actions.add(new SellWeaponItemAction(weapon, new Club().getSellingPrice()));
                } else if (weapon.getDisplayChar() == '?') {
                    actions.add(new SellWeaponItemAction(weapon, new Grossmesser().getSellingPrice()));
                } else if (weapon.getDisplayChar() == 's') {
                    actions.add(new SellWeaponItemAction(weapon, new Scimitar().getSellingPrice()));
                } else if (weapon.getDisplayChar() == 'T') {
                    actions.add(new SellWeaponItemAction(weapon, new AxeOfGodrick().getSellingPrice()));
                } else if (weapon.getDisplayChar() == 'N') {
                    actions.add(new SellWeaponItemAction(weapon, new GraftedDragon().getSellingPrice()));
                }
            }
        }
        return actions;
    }
    
}
