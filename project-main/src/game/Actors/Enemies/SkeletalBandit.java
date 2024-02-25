package game.Actors.Enemies;

import game.Weapons.Scimitar;

/**
 * A class representing the Skeletal Bandit enemy.
 *
 * Created by:
 * @author Muhammad Ibrahim bin Mohd Yusni
 * Modified by:
 */
public class SkeletalBandit extends Skeleton {

    /**
     * Constructor for the SkeletalBandit enemy.
     */
    public SkeletalBandit(){
        super("Skeletal Bandit", 'b', 184, 35, 892);
        addWeaponToInventory(new Scimitar());
    }

}

