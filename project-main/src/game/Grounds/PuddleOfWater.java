package game.Grounds;

import game.Interfaces.EnemyFactory;

/**
 * A subclass of SpawningGround representing a Puddle of Water where Crabs spawn.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class PuddleOfWater extends SpawningGround {
    /**
     * Constructor for PuddleOfWater
     * @param enemyFactory instance of EnemyFactory (East/West)
     */
    public PuddleOfWater(EnemyFactory enemyFactory) {
        super('~', enemyFactory);
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
            return 1;
        } else if (side == "West") {
            return 2;
        }
        return 0;
    }
}
