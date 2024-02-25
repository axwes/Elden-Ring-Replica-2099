package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Managers.ResetManager;
/**
 * An action executed if an actor is in site of lost grace and choose to rest
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class RestAction extends Action {
    private String siteName;


    public RestAction(String siteName){
        this.siteName = siteName;

    }

    /**
     * Perform the rest action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Menu Description on UI which implies game is reset.
     * @see ResetManager
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run(map);
        return menuDescription(actor);
    }

    /**
     * Return a descriptive string for rest action.
     * @param actor The actor performing the action.
     * @return the text we put on menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rest at " + this.siteName;
    }
}
