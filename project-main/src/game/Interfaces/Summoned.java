package game.Interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import game.Characters.*;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

public interface Summoned {

    default void setCharacter(Actor actor){
        actor.addCapability(Status.SPAWNED);
        int randomNumber = RandomNumberGenerator.getRandomInt(0, 100);
        if (0<randomNumber && randomNumber<=25){
            StartingCharacter.updatePlayer(actor, new Samurai());
        }
        else if (25<randomNumber && randomNumber<=50){
            StartingCharacter.updatePlayer(actor, new Wretch());
        }
        else if (50<randomNumber && randomNumber<=75){
            StartingCharacter.updatePlayer(actor, new Bandit());
        }
        else{
            StartingCharacter.updatePlayer(actor, new Astrologer());
        }}
}
