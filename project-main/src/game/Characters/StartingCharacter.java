package game.Characters;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Actors.Player;

/**
 * An abstract class which sets the HP of player and add weapon as starting weapon
 * into inventory based on starting character selected ( Samurai, Bandit or Wretch ).
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public abstract class StartingCharacter {

    /**
     * Constructor
     * @param hitPoint represents hitpoint of player
     * @param newWeapon represent starting weapon of player
     */
    public StartingCharacter(int hitPoint, WeaponItem newWeapon){
        this.hitPoint=hitPoint;
        this.newWeapon=newWeapon;
    }

    /**
     * Hitpoint of player according to the starting classes choosen
     * ( Samurai, Bandit or Wretch ).
     */
    public int hitPoint;

    /**
     * Starting weapon of player according to starting classes choosen
     * ( Samurai, Bandit or Wretch ).
     */
    public WeaponItem newWeapon;

    /**
     * Getter for hit point
     * @return hitpoint that will be assigned to player
     * according to starting classes choosen
     */
    public int getHitPoint() {
        return hitPoint;
    }

    /**
     * Setter for hitpoint
     * @param hitPoint representing hitpoint of player that will be set
     * according to starting classes choosen
     */
    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    /**
     * Getter for starting weapon of Player according to starting classes choosen
     * @return starting weapon
     */
    public WeaponItem getNewWeapon() {
        return newWeapon;
    }

    /**
     * Setter for starting weapon of Player according to starting classes choosen
     * @param newWeapon which represents the starting weapon of player
     */
    public void setNewWeapon(WeaponItem newWeapon) {
        this.newWeapon = newWeapon;
    }

    /**
     * Method which sets the player hp and add starting weapon into player's weapon inventory
     * according to starting character selected
     * @param player representing Tarnished which is player of the game
     * @param startingCharacter representing the starting character choosen
     */
    public static void updatePlayer(Actor player, StartingCharacter startingCharacter){
        player.resetMaxHp(startingCharacter.getHitPoint());
        player.addWeaponToInventory(startingCharacter.getNewWeapon());
    }
}
