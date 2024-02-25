package game.Grounds;

import game.Interfaces.EnemyFactory;

/**
 * A subclass of SpawningGround representing Graveyard where Skeletons spawn.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class Graveyard extends SpawningGround {
    /**
     * Constructor for Graveyard.
     * @param enemyFactory instance of EnemyFactory (East/West)
     */
    public Graveyard(EnemyFactory enemyFactory) {
        super('n', enemyFactory);
        if (enemyFactory.getSideOfMap() == "East") {
            super.spawnChance = getSpawnChance(enemyFactory.getSideOfMap());
        }
        if (enemyFactory.getSideOfMap() == "West") {
            super.spawnChance = getSpawnChance(enemyFactory.getSideOfMap());
        }
    }
    @Override
    public double getSpawnChance(String side) {
        if (side == "East") {
            return 27;
        } else if (side == "West") {
            return 27;
        }
        return 0;
    }
}