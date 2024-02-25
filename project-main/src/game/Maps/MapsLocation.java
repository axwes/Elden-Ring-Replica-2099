package game.Maps;

import java.util.List;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
/**
 * Abstract base class for different locations in the game world. Each game location has its own map and a reference to
 * the world it belongs to.
 *
 * The constructor of this class initializes the map for the location, adds the map to the world, and stores the 
 * reference to the world and the location that is used to reach this map.
 *
 * @author Tan Yin Cheng
 */
public abstract class MapsLocation {
    /**
     * The ASCII representation of the map for this location.
     */
    protected List<String> map;
    /**
     * The location from where this map was reached.
     */
    protected Location formerLocation;
    
    /**
     * The world this location belongs to.
     */
    protected World globalWorld;
    /**
     * The game map corresponding to this location.
     */
    protected GameMap currentMap;
    /**
     * Constructor of MapsLocation.
     *
     * It initializes the given map using the provided groundFactory, adds the map to the world, and saves references to 
     * the former location and the world.
     *
     * @param world The world this location belongs to.
     * @param loca The location that was used to reach this map.
     * @param groundFactory The ground factory to use for creating the grounds for the map.
     * @param map The ASCII representation of the map.
     */
    public MapsLocation(World world, Location loca, FancyGroundFactory groundFactory, List<String> map){
        this.formerLocation = loca;
        this.globalWorld = world;
        this.map = map;

        this.currentMap = new GameMap(groundFactory, this.map);
        this.globalWorld.addGameMap(currentMap);
    }
}
