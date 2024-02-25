package game.Actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Actors.Ally;
import game.Actors.Enemies.Invader;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * Class to summon Guest from another Realm
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class SummonAction extends Action {

    /**
     * Summon Ally or Invader if exit within summon sign is not occupied with player
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return whether and ally or invader is summoned or all exit is full hence nothing can be summon
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Ally ally = new Ally();
        Location SummonSignExit = null;
        int randomNumber = RandomNumberGenerator.getRandomInt(0, 100);
        String statement = "";

        // check if current ground is SummonSign
        if(map.locationOf(actor).getGround().hasCapability(Status.SPAWNED)){
            SummonSignExit = map.locationOf(actor);
        }

        // else check the exit within location of actor and see which one is summon sign location
        else{
            for (Exit exit : map.locationOf(actor).getExits()){
            if (exit.getDestination().getGround().hasCapability(Status.SPAWNED)) {
                // Set the new exit as summon sign's exit
                SummonSignExit = exit.getDestination();
            }}}

                for (Exit summonExit : SummonSignExit.getExits()) {
                    // If there is no actor within summon sign's exit and ally/ invader can enter then summon guest from another realm
                    if (!summonExit.getDestination().containsAnActor() && summonExit.getDestination().canActorEnter(ally)) {
                        // There's 50-50 chance to summon ally or invader
                        if (randomNumber <= 1) {
                            map.addActor(new Ally(), summonExit.getDestination());
                            statement = "Ally is summoned by Player";
                            break;
                        } else {
                            map.addActor(new Invader(), summonExit.getDestination());
                            statement = "Invader is summoned by Player";
                            break;
                        }
                    }
                    statement = "No Exits to Summon Guest From Another Realm";
                }
        return statement;
            }


    /**
     * String to be displayed on UI to allow player to perform summon
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Summon Action";
    }
}
