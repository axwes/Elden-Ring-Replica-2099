package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Managers.RunesManager;

/**
 * BuyWeaponItemAction is a class that extends the Action class and represents the action of an Actor purchasing a
 * WeaponItem from a shop. The class handles checking whether the actor has enough Runes to purchase the item and
 * updates the actor's inventory and Runes balance accordingly.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class BuyWeaponItemAction extends Action {

    /**
	 * The WeaponItem to be purchased
	 */
    private final WeaponItem item; 

    /**
	 * The price of the WeaponItem 
	 */
    protected int price; 

    /**
     * Constructor for the BuyWeaponItemAction class.
     *
     * @param item  The WeaponItem to be purchased
     * @param price The price of the WeaponItem 
     */
    public BuyWeaponItemAction(WeaponItem item, int price){
        this.item = item; 
        this.price = price;
    }



    /**
     * Executes the BuyWeaponItemAction by checking if the actor has enough Runes to purchase the item.
     * If so, the item is added to the actor's inventory, and the Runes are deducted from the actor's balance.
     *
     * @param actor The actor performing the action
     * @param map   The map the actor is on
     * @return A message describing the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
          // Check if the actor has enough Runes to purchase the item
        if (RunesManager.getInstance().getTotalRunes() >= price){
            // Add the item to the actor's inventory and deduct the Runes
            actor.addWeaponToInventory(item);
            RunesManager.getInstance().deductRunes(price);

            return actor + " has purchased " + item + " for Runes: " + price;}
        else{
            return "You do not have enough Runes!";
        }
        
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " buy " + item + " = " + price;
    }
    
}
