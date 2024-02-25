package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RandomNumberGenerator;

/**
 * DespawnAction is a class that extends the Action class and represents the action of an Actor despawning
 * from the game map based on a given probability.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class DespawnAction extends Action {

    /**
	 * The probability of the actor despawning
	 */
    private final double probability;

 
    /**
     * Constructor for the DespawnAction class.
     *
     * @param probability The probability of the actor despawning
     */    

    public DespawnAction(double probability) {
        this.probability = probability;
    }

    /**
     * Executes the DespawnAction by generating a random number and comparing it to the despawn probability.
     * If the random number is less than the probability, the actor is removed from the map and a message
     * is returned indicating the actor has despawned.
     *
     * @param actor The actor that will potentially despawn
     * @param map   The map the actor is on
     * @return A message describing the result of the action or an empty string if the actor does not despawn
     */    
    @Override
    public String execute(Actor actor, GameMap map) {

        // Generate a random number between 0 and 100
        double randomNumber = RandomNumberGenerator.getRandomInt(0, 100);

        // Compare the random number to the despawn probability
        if (randomNumber < probability) {
            // Remove the actor from the map and return a message indicating the actor has despawned
            map.removeActor(actor);
            return actor + " has despawned.";
        }
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "has despawned";
    }
}
    
