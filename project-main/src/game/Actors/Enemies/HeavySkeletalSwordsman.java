package game.Actors.Enemies;

import game.Weapons.Grossmesser;

/**
 * A class representing the Heavy Skeletal Swordsman enemy.
 *
 * Created by:
 * @author Tan Yin Cheng
 * Modified by: Muhammad Ibrahim bin Mohd Yusni
 */
public class HeavySkeletalSwordsman extends Skeleton {

    /**
     * Constructor for the Heavy Skeletal Swordsman enemy.
     */
    public HeavySkeletalSwordsman(){
        super("Heavy Skeletal Swordsman", 'q', 153, 35, 892);
        addWeaponToInventory(new Grossmesser());
    }

}

