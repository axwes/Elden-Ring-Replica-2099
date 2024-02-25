package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Grounds.SiteOfLostGrace;
import game.utils.FancyMessage;

/**
 * This class represents an action where an actor activates and touches the site of lost grace they visited
 * The action triggers a sequence of messages to be printed to the display, with a delay between each message.
 *
 * @author Tan Yin Cheng
 */
public class TouchAction extends Action {

    /**
     * The name of the lost site of grace that the actor touches.
     */
    private String siteName;
    private SiteOfLostGrace site;

    /**
     * Constructor for the Touch Action
     *
     * @param siteName The name of the lost site of grace that the actor touches.
     */
    public TouchAction(String siteName, SiteOfLostGrace site){
        this.siteName = siteName;
        this.site = site;

    }

    /**
     * Executes the action of the actor touching the lost of grace site. 
     * A sequence of lines from the FancyMessage.LOST_GRACE string is printed to the display, 
     * 
     *
     * @param actor The actor who will perform the action.
     * @param map The game map, which is not used in this method but is required because this method overrides 
     *            the execute method in the Action class.
     * @return An empty string because this action does not produce a result message.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (String line : FancyMessage.LOST_GRACE.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
        this.site.touch();
        return "";
        
    }

    /**
     * Provides a description of this action for display in a menu.
     *
     * @param actor The actor who will perform the action.
     * @return A string describing the action, in the form "Actor touch the siteName".
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " touch the " + this.siteName;
       
    }
    
}
