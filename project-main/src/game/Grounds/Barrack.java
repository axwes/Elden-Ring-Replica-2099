package game.Grounds;

import edu.monash.fit2099.engine.actors.Actor;
import game.Actors.StormveilEnemies.GodrickSoldier;

/**
 * Represents a specific type of SpawningGround, the Barrack, which can spawn GodrickSoldiers.
 * The spawn chance of GodrickSoldiers is set to a specific value.
 *
 * @author Tan Yin Cheng
 */
public class Barrack extends SpawningGround {
    /**
     * Constructor of the Barrack class.
     */
    public Barrack(){
        super('B', null);
    }

    /**
     * Provides the spawn chance for this Barrack, which is a fixed value.
     *
     * @param nullString This parameter is not used in this implementation.
     * @return The spawn chance, represented as a percentage.
     */
    @Override
    public double getSpawnChance(String nullString) {
        return 45;
    }
    
    /**
     * Creates a new GodrickSoldier.
     *
     * @return A new GodrickSoldier.
     */
    @Override
    protected Actor createActor() {
        return new GodrickSoldier();
    }
    
}
