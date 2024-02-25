package game.utils;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.AttackAction;

/**
 * A utility class to detect nearby enemies in the game map.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class EnemyDetector {

    /**
     * Finds all nearby enemies of a given actor on the game map.
     *
     * @param actor The actor whose nearby enemies should be found.
     * @param map The game map containing the actors.
     * @return A list of nearby enemy actors.
     */
    public static List<Actor> findNearbyEnemies(Actor actor, GameMap map) {

        List<Actor> nearestEnemies = new ArrayList<>();
        Location here = map.locationOf(actor);

      // Iterate through all exits from the current location.    
        for (Exit exit : here.getExits()){
            Location destination = exit.getDestination();
            
            // Check if there is an actor at exit.
            if(destination.containsAnActor()){
                Actor target = destination.getActor();
                nearestEnemies.add(target);
            }
        }

        return nearestEnemies;
    }
}