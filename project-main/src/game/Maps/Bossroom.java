package game.Maps;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.Grounds.GoldenFogDoor;
import game.Grounds.SiteOfLostGrace;
import game.Managers.SiteOfLostGraceManager;

/**
 * Represents the Bossroom in the game world. This class holds all the functionality and attributes of the Bossroom.
 *
 * @author Tan Yin Cheng
 */
public class Bossroom extends MapsLocation{
    /**
     * The ASCII representation of the Bossroom.
     */    
    private static final List<String> map = Arrays.asList(
        "+++++++++++++++++++++++++",
        ".........................",
        "....=....................",  
        ".........................",
        ".........................",
        ".........................",
        ".........................",
        ".........................",
        "+++++++++++++++++++++++++");
 
    /**
     * Constructor of the Bossroom class.
     *
     * @param world The global world.
     * @param loca The former location of the actor.
     * @param groundFactory The factory to create the ground.
     */
    public Bossroom(World world, Location loca, FancyGroundFactory groundFactory){
        super(world, loca, groundFactory, map);
        this.formerLocation.setGround(new GoldenFogDoor(currentMap.at(0, 5), "BossRoom"));

    }
    
}
