package game.Actors.Enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * A class representing the Giant Dog enemy.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public class GiantDog extends Dog {
    /**
     * Constructor for the Giant Dog enemy.
     */
    public GiantDog(){
        super("Giant Dog", 'G', 693, 313, 1808);
        addCapability(Status.HAVESPECIALATTACK);
    }

    /**
     * Returns the Giant Dog's intrinsic weapon.
     *
     * @return the Giant Dog's intrinsic weapon
     */
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(314, "slams", 90);
    }

}
