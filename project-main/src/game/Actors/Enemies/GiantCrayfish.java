package game.Actors.Enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class representing the Giant Crayfish enemy.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public class GiantCrayfish extends Crab {
    /**
     * Constructor for the Giant Crayfish enemy.
     */
    public GiantCrayfish(){
        super("Giant Crayfish", 'R', 4803, 500, 2374);
    }

    /**
     * Returns the Giant Crayfish's intrinsic weapon.
     *
     * @return the Giant Crayfish's intrinsic weapon
     */
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(527, FIXED_VERB, 100);
    }

}