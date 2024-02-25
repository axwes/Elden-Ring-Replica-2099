package game.Actors.Enemies;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.AttackAction;
import game.Weapons.Grossmesser;
import game.Weapons.Scimitar;
import game.Managers.ResetManager;
import game.Interfaces.Resettable;
import game.utils.Status;

/**
 * A class representing the Piles of Bones in the game.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class PilesOfBones extends Enemies implements Resettable {
    /**
     * The number of turns until the Piles of Bones to revive.
     */
    private int turnsToRevive;

    /**
     * Instance of Actor
     */
    private final Actor actor;

    /**
     * Constructor for the Enemies class.
     */
    public PilesOfBones(Actor actor) {
        super("Piles Of Bones", 'X', 1, 0, 0);
        turnsToRevive = 0;
        this.actor = actor;
        if (actor.getDisplayChar() == 'q') {
            addWeaponToInventory(new Grossmesser());
        } else if (actor.getDisplayChar() == 'b') {
            addWeaponToInventory(new Scimitar());
        }
        ResetManager.getInstance().registerResettable(this);
    }
    

    /**
     * Returns the action to be performed during Piles of Bones' turn.
     *
     * @param actions collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Increment the turns to revive
        turnsToRevive += 1;

        // If turnsToRevive reaches 4, revive the HeavySkeletalSwordsman
        if (turnsToRevive == 4){
            Location location = map.locationOf(this);
            map.removeActor(this);
            if (actor.getDisplayChar() == 'q') {
                map.addActor(new HeavySkeletalSwordsman(), location);
            } else if (actor.getDisplayChar() == 'b') {
                map.addActor(new SkeletalBandit(), location);
            }
        }
        return new DoNothingAction();
    }

    /**
     * Returns the list of allowable actions that can be performed by other actors.
     *
     * @param otherActor the Actor that might interact with Piles of Bones
     * @param direction String representing the direction of the other Actor
     * @param map current GameMap
     * @return list of allowable actions
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        List<WeaponItem> playerWeapons = otherActor.getWeaponInventory();
        actions.add(new AttackAction(this, direction));

        if (playerWeapons.size() > 0) {
            for (int i = 0; i < playerWeapons.size(); i++) {
                WeaponItem currentWeapon = playerWeapons.get(i);
                actions.add(new AttackAction(this, direction, currentWeapon));
            }
        }

        //Allow enemies to attack Pile of Bones as well
        if (otherActor.hasCapability(Status.ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

}
