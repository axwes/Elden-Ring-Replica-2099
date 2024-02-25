package game.Managers;

import edu.monash.fit2099.engine.positions.GameMap;
import game.Interfaces.Resettable;
import game.Runes.Runes;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Choo Carmen
 */
public class ResetManager {
    private List<Resettable> resettables;
    private static ResetManager instance;
    private List<Runes> runes;

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        /**
         * List of resettables which will be reset when player dies or rest
         */
        this.resettables = new ArrayList<>();
        /**
         * Dropped runes
         */
        this.runes = new ArrayList<>();
    }
    /**
     * Reset all resettables in the map when player dies or rest
     * @param map gameMap where player is in
     */
    public void run(GameMap map) {
        for (Resettable resettable: resettables){
            resettable.reset(map);
            this.removeResettable(resettable);
        }
    }

    /**
     * Register resettable
     * @param resettable classes that will be reset when player dies or rest
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Remove resettable from the list
     * @param resettable
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettables);
    }

    /**
     * Return instance of reset manager
     * @return instance of reset manager
     */
    public static ResetManager getInstance() {
        if (instance==null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Register drop runes
     * @param rune dropped runes
     */
    public void registerRunes(Runes rune){
        runes.add(rune);
    }

    /**
     * Reset runes when player dies or die
     * @param map GameMap where the player is located
     */
    public void resetRunes(GameMap map) {
        for (Runes rune : runes){
            rune.reset(map);
        }
    }

}