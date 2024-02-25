package game.Actors.StormveilEnemies;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Actors.Enemies.Enemies;
import game.utils.Status;

/**
 * This class represents an abstract base class for StormveilEnemy.
 * It defines a common constructor for all StormveilEnemy subclasses.
 *
 * @author Tan Yin Cheng
 */
public abstract class StormveilEnemy extends Enemies {

    /**
     * Initializes a new instance of the StormveilEnemy class.
     *
     * @param name The name of the enemy.
     * @param displayChar The character that will be used to display the enemy.
     * @param hitPoints The number of hit points that the enemy starts with.
     * @param minRunes The minimum number of runes that the enemy can drop.
     * @param maxRunes The maximum number of runes that the enemy can drop.
     */
    public StormveilEnemy(String name, char displayChar, int hitPoints, int minRunes, int maxRunes) {
        super(name, displayChar, hitPoints, minRunes, maxRunes);
        addCapability(Status.INSTORMVEIL);
    }

    
}
