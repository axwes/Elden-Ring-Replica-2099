package game.Grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.RotDamageAction;

/**
 * A class that represents the Lake of Rot.
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 *
 */
public class LakeOfRot extends Ground {
    /**
     * Constructor of the LakeOfRot class.
     */
    public LakeOfRot() {
        super('!');
    }

    /**
     * Tick method for the LakeOfRot, which is called every game turn.
     * If there is an actor on the LakeOfRot, RotDamageAction is called.
     *
     * @param location The location of the LakeOfRot.
     */
    public void tick(Location location){
        Actor isActor = location.getActor();
        if (isActor != null){
            Action dmgovertime = new RotDamageAction();
            System.out.println(dmgovertime.execute(isActor, location.map()));
        }
    }
}
