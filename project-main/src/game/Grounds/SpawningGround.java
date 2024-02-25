package game.Grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Interfaces.EnemyFactory;
import game.utils.RandomNumberGenerator;

/**
 * An abstract class representing Spawning Grounds for different types of enemies.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public abstract class SpawningGround extends Ground {
    /**
     * Chance of spawning an enemy on the ground.
     */
    protected double spawnChance;

    /**
     * Instance of EnemyFactory interface
     */
    private final EnemyFactory enemyFactory;

    /**
     * Constructor for SpawningGround.
     *
     * @param displayChar the character to display for the ground
     * @param enemyFactory the instance of EnemyFactory (East / West)
     */
    public SpawningGround(char displayChar, EnemyFactory enemyFactory) {
        super(displayChar);
        this.spawnChance = getSpawnChance(null);
        this.enemyFactory = enemyFactory;
    }

    /**
     * Method to create an Actor for the Spawning Ground.
     *
     * @return the new Actor to be spawned
     */
    protected Actor createActor(){
        return enemyFactory.createActor(this);
    }

    /**
     * Gets the spawn chance of the enemy
     *
     * @param side East or West Side
     * @return spawn chance of enemy
     */
    public abstract double getSpawnChance(String side);
    
    /**
     * Tick method to check and spawn an Actor on the ground based on spawnChance.
     *
     * @param location the Location of the ground
     */
    @Override
    public void tick(Location location) {
        Actor isActor = location.getActor();
        double randomNumber = RandomNumberGenerator.getRandomInt(0, 100);
        if (randomNumber <= spawnChance && isActor == null) {
            Actor actor = createActor();
            location.addActor(actor);
        }
    }
}
