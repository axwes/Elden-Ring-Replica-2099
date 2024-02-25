package game.Managers;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class to set last site of lost grace location visited and retrived last site of lost grace location
 *
 * Created by:
 * @author Choo Carmen
 * Modified by:
 */
public class SiteOfLostGraceManager {

    public SiteOfLostGraceManager() {
    }

    private static Location lastSiteOfLostGraceLocation;

    public static Location getLastSiteOfLostGraceLocation() {
        return lastSiteOfLostGraceLocation;
    }

    public static void setLastSiteOfLostGraceLocation(Location lastSiteOfLostGraceLocation) {
        SiteOfLostGraceManager.lastSiteOfLostGraceLocation = lastSiteOfLostGraceLocation;
    }

    private Actor actor;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


}
