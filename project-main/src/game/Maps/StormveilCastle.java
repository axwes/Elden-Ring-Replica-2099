package game.Maps;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.Grounds.GoldenFogDoor;
/**
 * Represents the StormveilCastle in the game world. This class holds all the functionality and attributes of the StormveilCastle.
 *
 * @author Tan Yin Cheng
 */
public class StormveilCastle extends MapsLocation {
    /**
     * The ASCII representation of the StormveilCastle.
     */
    private static final List<String> map = Arrays.asList(
        "...........................................................................",
        "..................<...............<........................................",
        "...........................................................................",
        "##############################################...##########################",
        "............................#................#.......B..............B......",
        ".....B...............B......#................#.............................",
        "...............................<.........<.................................",
        ".....B...............B......#................#.......B..............B......",
        "............................#................#.............................",
        "#####################..#############...############.####..#########...#####",
        "...............#++++++++++++#................#++++++++++++#................",
        "...............#++++++++++++...<.........<...#++++++++++++#................",
        "...............#++++++++++++..................++++++++++++#................",
        "...............#++++++++++++#................#++++++++++++#................",
        "#####...##########.....#############...#############..#############...#####",
        ".._______........................B......B........................B.....B...",
        "_____..._..____..............<..............<..............................",
        ".........____..............................................................",
        "...._______..................<..............<....................<.....<...",
        "#####....##...###..#####...##########___###############......##.....####...",
        "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
        "+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

    /**
     * Constructor of the StormveilCastle class.
     *
     * @param world The global world.
     * @param loca The former location of the actor.
     * @param groundFactory The factory to create the ground.
     */
    public StormveilCastle(World world, Location loca, FancyGroundFactory groundFactory){
        super(world, loca, groundFactory, map);

        this.formerLocation.setGround(new GoldenFogDoor(currentMap.at(34, 23), "Stormveil Castle"));

        currentMap.at(34, 23).setGround(new GoldenFogDoor(this.formerLocation, "Limgrave"));

        new Bossroom(world, currentMap.at(6, 0), groundFactory);

        // currentMap.at(6, 0).setGround(new GoldenFogDoor(this.formerLocation, "Boos Room"));

    }
    
}
