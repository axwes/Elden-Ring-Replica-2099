package game.Grounds;

import edu.monash.fit2099.engine.actors.Actor;
import game.Actors.Enemies.GiantCrayfish;
import game.Actors.Enemies.GiantDog;
import game.Actors.Enemies.SkeletalBandit;
import game.Interfaces.EnemyFactory;

/**
 * A class representing the East Spawn to spawn enemies on the east side.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class EastSpawn implements EnemyFactory {

    @Override
    public Actor createActor(SpawningGround ground) {
        if (ground.getDisplayChar() == 'n') {
            return new SkeletalBandit();
        } else if (ground.getDisplayChar() == '&') {
            return new GiantDog();
        } else if (ground.getDisplayChar() == '~') {
            return new GiantCrayfish();
        }
        return null;
    }

    @Override
    public String getSideOfMap() {
        return "East";
    }
}
