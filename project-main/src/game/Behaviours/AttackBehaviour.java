package game.Behaviours;

import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Interfaces.Behaviour;
import game.utils.EnemyDetector;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import game.Actions.AreaAttackAction;
import game.Actions.AttackAction;


/**
 * A class representing the Attack Behaviour of Actors in the game.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Returns the attack action for the given actor based on the current game state.
     *
     * @param actor the Actor performing the attack action
     * @param map the current GameMap
     * @return the attack action to be performed or null if no valid action is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        double randomNumber = RandomNumberGenerator.getRandomInt(0, 100);

        // Find the nearest enemies for the actor
        List<Actor> nearestEnemies = EnemyDetector.findNearbyEnemies(actor, map);

        Actor actorenemy = actor;

        // Check if the actor is an Enemy 
        if (actor.hasCapability(Status.ENEMY)){
            actorenemy = actor;
        }

        // Get the weapon from the actor's inventory if it exists
        WeaponItem weapon = null;
        if (!actor.getWeaponInventory().isEmpty()) {
            weapon = actor.getWeaponInventory().get(0);
        }

        // If there are nearby enemies
        if (!nearestEnemies.isEmpty()) {
            int sameclass = 0;

            // Count the number of enemies with the same class
            for (Actor enemy : nearestEnemies){
                if (enemy.getDisplayChar() == actor.getDisplayChar()){
                    sameclass ++;
                }
            }
            Actor targetOn = nearestEnemies.get(0);
            Action attackAction = null; 
            
             // If the actor is not of the same class as the target, set attack action
            if (actor.getDisplayChar() != targetOn.getDisplayChar() && !(actor.hasCapability(Status.INSTORMVEIL) && targetOn.hasCapability(Status.INSTORMVEIL))) {
                attackAction = new AttackAction(targetOn, "at", weapon);
            }

            // If the actor has a special attack, and there are more than one enemy and enemy are from different classes, decide between area or normal attack
            if (actorenemy.hasCapability(Status.HAVESPECIALATTACK) && nearestEnemies.size() != sameclass && nearestEnemies.size() > 1) {
                if (randomNumber < 50){
                    attackAction = new AreaAttackAction("at", weapon);
                } else{
                    attackAction = new AttackAction(targetOn, "at", weapon);
                }   
                
            }
        
            return attackAction;
        }

        return null;
    }
}