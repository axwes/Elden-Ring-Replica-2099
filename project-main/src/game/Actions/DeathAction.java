package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Managers.ResetManager;
import game.Runes.Runes;
import game.Managers.SiteOfLostGraceManager;
import game.utils.FancyMessage;
import game.utils.Status;
import game.Actors.Enemies.Enemies;
import game.Managers.RunesManager;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Choo Carmen & Tan Yin Cheng
 *
 */
public class DeathAction extends Action {
    /**
     * An Actor which represents the attacker
     */
    private Actor attacker;

    /**
     * Constructor
     * @param actor An Actor which represents the attacker
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the following happens :
     * 1. If the target is enemy - the items & weapons carried by target
     * will be dropped to the location in the game map where the target was.
     * 2. If the target is a player, their weapons & items will not be dropped,
     * their runes will be respawn at last location they died, and the game will
     * be reset and player will respawn at last site of lost grace.
     * @param target The actor which the death action is performed on.
     * @param map The map the dead actor is on.
     * @return result of the action to be displayed on the UI
     * @see RunesManager
     * @see ResetManager
     */

    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        ActionList dropActions = new ActionList();
        // ONLY EXECUTE THIS IF ACTOR IS NOT A PLAYER ( DROP ITEM, WEAPON AND ADD RUNES TO ACTOR )
        if (target.hasCapability(Status.RESPAWNABLE) == false) {
            for (Item item : target.getItemInventory()) {
                dropActions.add(item.getDropAction(target));
            }
            for (WeaponItem weapon : target.getWeaponInventory()) {
                dropActions.add(weapon.getDropAction(target));
            }
            // Execute alL drop actions, dropping items and weapons at the target's location
            for (Action drop : dropActions){
                drop.execute(target, map);}
            // If the actor is hostile to the enemy which means actor is a Player, generate and add runes to the RunesManager
            if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY) && ( target.hasCapability(Status.ENEMY) || target.hasCapability(Status.INVASIVE))){
                Enemies targets = (Enemies) target;
                int runes = targets.generateRunes();
                RunesManager.getInstance().addRunes(runes);
                result += "\n" + attacker + " received " + runes + " Runes ";

            }
            map.removeActor(target);
        }

        // Remove ally or invader
        else if (target.hasCapability(Status.SPAWNED)){
            for (WeaponItem weapon : target.getWeaponInventory()) {
                dropActions.add(weapon.getDropAction(target));
            }
            map.removeActor(target);
        }


        // If actor is respawnable, no weapon or item will be dropped and they will be respawn at site of lost grace
        // with maximum hitpoint & flask of crimson tears, runes will be dropped at last location before the player die
        else {
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            Runes newRunes = new Runes(500);
            RunesManager.getInstance().getLastLocation().addItem(newRunes);
            ResetManager.getInstance().resetRunes(map);
            RunesManager.getInstance().dropRunes(RunesManager.getInstance().getLastLocation(), newRunes);
            ResetManager.getInstance().run(map);
            map.moveActor(target, SiteOfLostGraceManager.getLastSiteOfLostGraceLocation());
        }
        result += System.lineSeparator() + menuDescription(target);

        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon and whether special skill of the weapon is used
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        String output;
        if (actor.hasCapability(Status.RESPAWNABLE)){
            output = "YOU DIED" ;
        }
        else {
            output = actor + " is killed.";
        }
        return output;
    }
}

