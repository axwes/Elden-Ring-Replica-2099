package game.Actors.StormveilEnemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.utils.Status;

/**
 * This class represents a specific type of StormveilEnemy, the Dog.
 * The Dog is initialized with a specific set of attributes.
 *
 * @author Tan Yin Cheng
 */
public class DogEnemy extends StormveilEnemy{
    /**
     * Constructor of the DogEnemy class.
     * The DogEnemy is assigned a specific name, display character, hit points, and rune range.
     * It is also tagged with the INSTORMVEIL status.
     */
    public DogEnemy() {
        super("Dog", 'a', 104, 52, 1390);
    }

    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(101, "bites", 93);
    }
    
}
