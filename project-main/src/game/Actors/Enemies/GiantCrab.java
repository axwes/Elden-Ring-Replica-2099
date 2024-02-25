package game.Actors.Enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class representing the Giant Crab enemy.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class GiantCrab extends Crab {
    /**
     * Constructor for the Giant Crab enemy.
     */
    public GiantCrab(){
        super("Giant Crab", 'C', 407, 318, 4961 );
    }

    /**
     * Returns the Giant Crab's intrinsic weapon.
     *
     * @return the Giant Crab's intrinsic weapon
     */
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(208, FIXED_VERB, 90);
    }
    
}
