package game.Actors.StormveilEnemies;

import game.Weapons.GreatKnife;
import game.Weapons.Grossmesser;
import game.utils.Status;

/**
 * This class represents a specific type of StormveilEnemy, the GodrickSoldier.
 * The GodrickSoldier is initialized with a specific set of attributes, and is given a Grossmesser weapon.
 * It is also tagged with the INSTORMVEIL status.
 *
 * @author Tan Yin Cheng
 */
public class GodrickSoldier extends StormveilEnemy {
    /**
     * Constructor of the GodrickSoldier class.
     * The GodrickSoldier is assigned a specific name, display character, hit points, and rune range.
     * It is also given a Grossmesser weapon and tagged with the INSTORMVEIL status.
     */
    public GodrickSoldier() {
        super("Godrick Solider", 'p', 198, 38, 70);
        addWeaponToInventory(new GreatKnife());
    }
    
}
