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
 * Represents the Roundtablehold in the game world. This class holds all the functionality and attributes of the Roundtablehold.
 *
 * @author Tan Yin Cheng
 */
public class Roundtablehold extends MapsLocation {
    /**
     * The ASCII representation of the Roundtablehold.
     */
    private static final List<String> map = Arrays.asList(
        "##################",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "#________________#",
        "########___#######"); 
    
    /**
     * Constructor of the Roundtablehold class.
     *
     * @param world The global world.
     * @param loca The former location of the actor.
     * @param groundFactory The factory to create the ground.
     */
    public Roundtablehold(World world, Location loca, FancyGroundFactory groundFactory){
        super(world, loca, groundFactory, map);
        this.formerLocation.setGround(new GoldenFogDoor(currentMap.at(9, 10), "Roundtable Hold"));

        currentMap.at(9, 10).setGround(new GoldenFogDoor(this.formerLocation, "Limgrave"));
        
        SiteOfLostGrace tableOfLostGrace = new SiteOfLostGrace(9,5, "Table of Lost Grace");
		currentMap.at(tableOfLostGrace.getX(), tableOfLostGrace.getY()).setGround(tableOfLostGrace);
		//Location location = new Location(gameMap,theFirstStep.getX(),theFirstStep.getY());
		SiteOfLostGraceManager.setLastSiteOfLostGraceLocation(currentMap.at(tableOfLostGrace.getX(),tableOfLostGrace.getY()));
    }
    
}
