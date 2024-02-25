package game.Actors.Enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class representing the Lone Wolf enemy.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public class LoneWolf extends Dog {
    /**
     * Constructor for the Lone Wolf enemy.
     */
    public LoneWolf(){
        super("Lone Wolf", 'h', 102, 55, 1470 );
    }

    /**
     * Returns the Lone Wolf's intrinsic weapon.
     *
     * @return the Lone Wolf's intrinsic weapon
     */
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(97, "bites", 95);
    }

}
