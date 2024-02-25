package game.Grounds;

import game.Interfaces.EnemyFactory;

/**
 * A subclass of SpawningGround representing a Gust of Wind where Dogs spawn.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class GustOfWind extends SpawningGround {
    /**
     * Constructor for GustOfWind.
     * @param enemyFactory instance of EnemyFactory (East/West)
     */
    public GustOfWind(EnemyFactory enemyFactory) {
        super('&', enemyFactory);
        if (enemyFactory.getSideOfMap() == "East") {
            super.spawnChance = getSpawnChance(enemyFactory.getSideOfMap());
        }if (enemyFactory.getSideOfMap() == "West") {
            super.spawnChance = getSpawnChance(enemyFactory.getSideOfMap());
        }
    }
    @Override
    public double getSpawnChance(String side) {
        if (side == "East") {
            return 2;
        } else if (side == "West") {
            return 33;
        }
        return 0;
    }
}

