package game.Actors.Enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.AttackAction;
import game.Behaviours.*;
import game.Interfaces.Behaviour;
import game.Interfaces.Resettable;
import game.Interfaces.Summoned;
import game.Managers.ResetManager;
import game.utils.EnemyDetector;
import game.utils.Status;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class which represents Invader which is is similar to Ally, except that they can
 * attack the player and also attack other hostile creatures.
 * Invader cannot attack another Invader.
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class Invader extends Enemies implements Summoned {
    /**
     * Constructor.
     */
    public Invader() {
        super("Invader", 'ඞ', 0, 1358 ,5578);
        this.setCharacter(this);
        this.behaviours.put(3, new WanderBehaviour());
        this.behaviours.put(1, new AttackOthersBehaviour());
        this.addCapability(Status.INVASIVE);
        this.removeCapability(Status.ENEMY);
        //ResetManager.getInstance().removeResettable(this);
    }

    /**
     * A map containing the behaviors for the enemy with their associated priority as the key.
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Method which allows Invader to perform action automatically in the game
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action which Invader will be performing
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Action action = new DoNothingAction();
        for (Behaviour behaviour : behaviours.values()){
            action=behaviour.getAction(this,map);
            if(action!= null){
                return action;
            }
        }
        return action;}

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        Behaviour followBehaviour = new FollowBehaviour(otherActor);
        //List<Actor> nearestEnemies = EnemyDetector.findNearbyEnemies(otherActor, map);
        List<WeaponItem> playerWeapons = otherActor.getWeaponInventory();

        //if a player is next to the enemy, perform FollowBehaviour until the player die or this enemy die.
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            behaviours.put(2, followBehaviour);
            actions.add(followBehaviour.getAction(otherActor, map));
        }

        // Iterate through the player's weapons, adding valid actions based on the weapons and the number of nearby enemies
        if (playerWeapons.size() > 0 && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            for (int i = 0; i < playerWeapons.size(); i++) {
                WeaponItem currentWeapon = playerWeapons.get(i);

                //check if the currentweapon have special skill and there is more than one enemy in the surrounding
                if (!(currentWeapon.getSkill(this, direction) instanceof DoNothingAction) ){
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
}
