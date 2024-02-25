package game.Actors.Enemies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Behaviours.FollowBehaviour;
import game.Behaviours.WanderBehaviour;
import game.utils.EnemyDetector;
import game.utils.RandomNumberGenerator;
import game.Managers.ResetManager;
import game.Interfaces.Resettable;
import game.utils.Status;
import game.Actions.AttackAction;
import game.Actions.DespawnAction;
import game.Behaviours.AttackBehaviour;
import game.Interfaces.Behaviour;


/**
 * Enemies is an abstract class representing all enemy Actors in the game. It extends the Actor class and provides
 * additional behaviors and capabilities for enemies.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public abstract class Enemies extends Actor implements Resettable {

    /**
     * A map containing the behaviors for the enemy with their associated priority as the key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * The minimum Runes generated upon defeat.
     */
    protected int minRunes;

    /**
     * The maximum Runes generated upon defeat.
     */
    protected int maxRunes;

    /**
     * Constructor for the Enemies class.
     *
     * @param name        The name of the enemy
     * @param displayChar The character used to display the enemy on the map
     * @param hitPoints   The hit points of the enemy
     * @param minRunes    The minimum Runes generated upon defeat
     * @param maxRunes    The maximum Runes generated upon defeat
     */

    public Enemies(String name, char displayChar, int hitPoints, int minRunes, int maxRunes){
        super(name, displayChar, hitPoints);
        this.behaviours.put(3, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
        addCapability(Status.ENEMY);
        this.minRunes = minRunes;
        this.maxRunes = maxRunes;
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Iterate through the enemy's behaviors, executing the first valid one
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null) {
                //if enemy is not following player, they will have a 10% chance of despawning
                if (!this.hasCapability(Status.ENGAGE)) {
                    DespawnAction despawnAction = new DespawnAction(10);
                    String despawnResult = despawnAction.execute(this, map);
                    if (!despawnResult.isEmpty()) {
                        System.out.println(despawnResult); // Print the despawn message to the console
                        return new DoNothingAction();
                    } else {
                        return action;
                    }
                }
                return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return           A list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //if a player is next to the enemy, perform FollowBehaviour until the player die or this enemy die.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.put(2,new FollowBehaviour(otherActor));
            this.addCapability(Status.ENGAGE);

        }

        List<Actor> nearestEnemies = EnemyDetector.findNearbyEnemies(otherActor, map);
        List<WeaponItem> playerWeapons = otherActor.getWeaponInventory();

        //allow otheractor to perform intrinsic attack
        actions.add(new AttackAction(this, direction));

        // Iterate through the player's weapons, adding valid actions based on the weapons and the number of nearby enemies
        if (playerWeapons.size() > 0) {
            for (int i = 0; i < playerWeapons.size(); i++) {
                WeaponItem currentWeapon = playerWeapons.get(i);

                //check if the currentweapon have special skill and there is more than one enemy in the surrounding
                if (!(currentWeapon.getSkill(this, direction) instanceof DoNothingAction) && nearestEnemies.size() > 1){

                    //allow otheractor to perform the special skill or a normal attackaction
                    actions.add(currentWeapon.getSkill(this, direction));
                    actions.add(new AttackAction(this, direction, currentWeapon));
                } else {
                    //if no special skill then perform normal attack
                    actions.add(new AttackAction(this, direction, currentWeapon));
                }
            }
        }
        return actions;
    }

    /**
     * Generates a random amount of Runes to be awarded upon defeating the enemy, within the range of minRunes and maxRunes.
     *
     * @return the random amount of Runes generated
     */
    public int generateRunes() {
        int randomNumber = RandomNumberGenerator.getRandomInt(minRunes, maxRunes);
        return randomNumber;
    }

    /**
     * Called by reset manager to remove all enemies in map.
     * @param map which represents the current game map
     */
    @Override
    public void reset(GameMap map) {
        if(!this.hasCapability(Status.INVASIVE)){
        map.removeActor(this);}
    }

}
