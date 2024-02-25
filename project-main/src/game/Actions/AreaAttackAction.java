package game.Actions;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.EnemyDetector;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import game.Actors.Enemies.Enemies;
import game.Actors.Enemies.PilesOfBones;
import game.Managers.RunesManager;

/**
 * Represents an action that allows an actor to attack all nearby enemies in a specified direction.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class AreaAttackAction extends Action {

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Constructs an AreaAttackAction with a specified direction and weapon.
     *
     * @param direction The direction in which the attack occurs.
     * @param weapon    The weapon used for the attack.
     */

    public AreaAttackAction(String direction, Weapon weapon) {
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructs an AreaAttackAction with a specified direction and no specific weapon.
     *
     * @param direction The direction in which the attack occurs.
     */
    public AreaAttackAction(String direction) {
        this.direction = direction;
    }

    /**
     * Executes the AreaAttackAction for the given actor and game map.
     *
     * @param actor The actor performing the area attack.
     * @param map   The game map containing the actor and enemies.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = null;

         // Find all nearby enemies to the actor
        List<Actor> nearestEnemies = EnemyDetector.findNearbyEnemies(actor, map);

        // If no weapon is specified, use the actor's intrinsic weapon
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        String result = actor + "";
        List<String> targetResults = new ArrayList<>();
        List<String> deathResults = new ArrayList<>();

         // Loop through each enemy thats near the actor to attack each enemy 
        for (Actor target : nearestEnemies) {
            // Generate a random number to determine if the attack hits or misses
            double randomNumber = RandomNumberGenerator.getRandomInt(0, 100);

            // if random number is smaller than weapon's chance to hit
            if (randomNumber <= weapon.chanceToHit()) {
                //get weapon damage 
                int damage = weapon.damage();
                targetResults.add(weapon.verb() + " " + target + " for " + damage + " damage ");

                //deduct damage from target's hitpoint 
                target.hurt(damage);

                // If the target is not conscious after taking damage
                if (!target.isConscious()) {

                    // If the target has the capability to become Piles of Bones
                    if (target.hasCapability(Status.BECOMEPILESOFBONES)){
                        currentLocation = map.locationOf(target); //store location of target to be used later 
                        map.removeActor(target); //remove target from location
                        currentLocation.addActor(new PilesOfBones(target));
                        targetResults.add("\n"+ target + " has turned into Piles of Bones ");

                         // If the actor is hostile to the enemy which means actor is a Player, generate and add runes to the RunesManager 
                        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                            Enemies targets = (Enemies) target;
                            int runes = targets.generateRunes(); //generate random runes to be given to player 
                            RunesManager.getInstance().addRunes(runes); //add runes to player 
                            targetResults.add( "\n" + actor + " received " + runes + " Runes ");
                        }

                    } else { //if target does not have the capability to become Piles of Bones
                        //Perform normal DeathAction
                        deathResults.add(new DeathAction(actor).execute(target, map));
                    }
                }
                // If the random number is greater than the weapon's hit chance, the attack misses
            } else {
                targetResults.add("misses " + target);
            }
        }

        result += " " + String.join(" and ", targetResults);

        if (!deathResults.isEmpty()) {
            result += "\n" + String.join("\n", deathResults);
        }

        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon which direction
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks all creatures " + direction + " with " + weapon;
    }

}