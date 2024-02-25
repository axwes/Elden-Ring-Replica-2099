package game.Runes;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actions.RetriveDropRunesAction;
import game.Managers.RunesManager;
import game.Managers.ResetManager;
import game.Interfaces.Resettable;
import game.utils.Status;

/**
 * A class representing Runes item in the game.
 * Created by:
 * @author Tan Yin Cheng
 * Modified by:
 */
public class Runes extends Item implements Resettable {
    /**
     * Value of the Runes.
     */
    private int value;

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Constructor for Runes.
     *
     * @param value the value of the Runes
     */
    public Runes(int value) {
        super("Runes", '$', true);
        this.value = value;
        ResetManager.getInstance().registerRunes(this);
        this.addCapability(Status.RECOVERABLE);
        this.addAction(new RetriveDropRunesAction(this));
    }

    /**
     * Retrieves the value of the Runes.
     *
     * @return the value of the Runes
     */
    public int getValue(){
        return value;
    }

    /**
     * Called by reset manager to remove runes in previous location
     * @param map which represents the current game map
     */
    public void reset(GameMap map){
        Location location = RunesManager.getInstance().retrieveLastRunesLocation();
        if (location!=null){
            location.removeItem(this);
        }
    }
}
