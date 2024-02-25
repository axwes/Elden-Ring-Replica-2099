package game.Items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Managers.ResetManager;
import game.Interfaces.Resettable;
import game.utils.Status;

/**
 * An item which heal player hitpoint by 250
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class FlaskOfCrimsonTears extends Item implements Resettable {
    /**
     * Maximum amount of consume time
     */
    final int maxAmount = 2;

    /**
     * Consumed amount
     */
    private int consumeAmount;

    /**
     * Getter for consumed amount
     * @return number of consume amount
     */
    public int getConsumeAmount() {
        return consumeAmount;
    }

    /**
     * Setter for consume amount
     * @param consumeAmount
     */
    public void setConsumeAmount(int consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    /***
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("FlaskOfCrimsonTears", 'c', false);
        this.consumeAmount = maxAmount;
        this.addCapability(Status.HEAL);
        ResetManager.getInstance().registerResettable(this);
    }

    /**
     * Called by reset manager to reset to maximum number of flask of crimson tears - 2
     * @param map which represents the current game map
     */
    public void reset(GameMap map){
        setConsumeAmount(maxAmount);
    }
}
