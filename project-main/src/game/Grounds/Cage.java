package game.Grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Actors.StormveilEnemies.DogEnemy;
/**
 * Represents a specific type of SpawningGround, the Cage, which can spawn DogEnemies.
 * The spawn chance of DogEnemies is set to a specific value.
 *
 * @author Tan Yin Cheng
 */
public class Cage extends SpawningGround {
    /**
     * Constructor of the Cage class.
     */
    public Cage(){
        super('<', null);
    }
    /**
     * Provides the spawn chance for this Cage, which is a fixed value.
     *
     * @param nullString This parameter is not used in this implementation.
     * @return The spawn chance, represented as a percentage.
     */
    @Override
    public double getSpawnChance(String nullString) {
        return 37;
    }
    
    /**
     * Creates a new DogEnemy.
     *
     * @return A new DogEnemy.
     */
    @Override
    protected Actor createActor() {
        return new DogEnemy();
    }
    
}
