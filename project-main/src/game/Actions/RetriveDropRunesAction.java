package game.Actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Runes.Runes;
import game.Managers.RunesManager;

public class RetriveDropRunesAction extends PickUpItemAction {

    private Runes runes;

    /**
     * Constructor.
     *
     * @param item the item to pick up
     */
    public RetriveDropRunesAction(Runes item) {
        super(item);
        this.runes = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        RunesManager.getInstance().addRunes(runes.getValue());
        map.locationOf(actor).removeItem(runes);
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " retrieves Runes (value: " + runes.getValue() + " )";
    }
}
