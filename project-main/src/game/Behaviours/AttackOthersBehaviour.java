package game.Behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actions.AttackAction;
import game.Interfaces.Behaviour;
import game.utils.EnemyDetector;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import java.util.List;

/**
 * A class representing the Attack Behaviour of Ally or Invader ( which doesn't attack player of same classes )
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class AttackOthersBehaviour implements Behaviour {
    /**
     * Returns the attack action for the given actor ( ally or invader ) based on the current game state.
     *
     * @param actor the Actor performing the attack action
     * @param map   the current GameMap
     * @return the attack action to be performed or null if no valid action is found
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Find the nearest enemies for the actor
        List<Actor> nearestEnemies = EnemyDetector.findNearbyEnemies(actor, map);
        int randomNumber = RandomNumberGenerator.getRandomInt(0, 100);
        List<WeaponItem> playerWeapons = actor.getWeaponInventory();
        // Check if the actor is an ally
        if (actor.hasCapability(Status.ALIES)) {
            for (Actor enemy : nearestEnemies) {
                // Check if the nearby player are enemy or Invader or player, if yes perform attack
                if (enemy.hasCapability(Status.ENEMY) || enemy.hasCapability(Status.INVASIVE)) {
                    for (WeaponItem weapon : playerWeapons) {
                        if (!(weapon.getSkill(enemy, "nearby") instanceof DoNothingAction)) {
                            // if random number is less than 50 perform special skill of weapon
                            if (randomNumber <= 50) {
                                //System.out.println("PLAYER IS DETECTEDDDDD ");
                                return weapon.getSkill(enemy, "nearby");
                            }
                            // if random number is more than 50, attack using weapon
                            else {
                                return new AttackAction(enemy, "nearby", weapon);
                            }
                        }
                    }
                }
            }
        }
        // Check if the actor is an invader
        else if (actor.hasCapability(Status.INVASIVE)) {
            for (Actor enemy : nearestEnemies) {
                // Check if nearby player are enemy or ally, if yes perform attack
                if (enemy.hasCapability(Status.ENEMY) || enemy.hasCapability(Status.ALIES) || enemy.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    for (WeaponItem weapon : playerWeapons) {
                        // if weapon has special skill
                        if (!(weapon.getSkill(enemy, "nearby") instanceof DoNothingAction)) {
                            // if random number is less than 50 perform special skill of weapon
                            if (randomNumber <= 50) {
                                //System.out.println("PLAYER IS DETECTEDDDDD ");
                                return weapon.getSkill(enemy, "nearby");
                            }
                            // if random number is more than 50, attack using weapon
                            else {
                                return new AttackAction(enemy, "nearby", weapon);
                            }
                        }
                    }
                }
            }

        }
        return null;
    }
}
