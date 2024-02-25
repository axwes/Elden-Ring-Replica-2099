package game.Managers;

import edu.monash.fit2099.engine.positions.Location;
import game.Runes.Runes;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages the total Runes in the game.
 *
 * Created by:
 * @author Tan Yin Cheng & Choo Carmen
 * Modified by:
 */
public class RunesManager {
    /**
     * List which contains location of drop runes
     */
    private List<Location> dropRunesLocation;

    /**
     * Add drop runes location into the dropRunesLocation list
     * @param location dropped runes location
     */
    public void addDropRunesLocation(Location location) {
        dropRunesLocation.add(location);
    }

    /**
     * Last location of player before they die
     */
    private Location lastLocation ;

    /**
     * Getter for lastLocation
     * @return Last location of player before they die
     */
    public Location getLastLocation() {
        return lastLocation;
    }

    /**
     * Setter for last location
     * @param lastLocation Last location of player before they die
     */
    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     * Retrieve location of last runes
     * @return location of last runes
     */
    public Location retrieveLastRunesLocation(){
        if(dropRunesLocation.size()>0){
            Location location = dropRunesLocation.get(0);
            dropRunesLocation.remove(0);
            return location;
        }
        else {
            return null;
        }

    }

    /**
     * Total Runes in the game.
     */
    private int totalRunes;

    /**
     * Singleton instance of RunesManager.
     */
    private static RunesManager instance;

    /**
     * Private constructor for RunesManager.
     */
    private RunesManager(){
        dropRunesLocation = new ArrayList<Location>();
    }

    /**
     * Retrieves the singleton instance of RunesManager.
     *
     * @return the singleton instance of RunesManager
     */
    public static RunesManager getInstance() {
        if (instance == null){
            instance = new RunesManager();
        }
        return instance;
    }

    /**
     * Adds Runes to the total.
     *
     * @param value the number of Runes to add
     */
    public void addRunes(int value){
        this.totalRunes += value;
    }
    /** Deducts Runes from the total.
     *
     * @param value the number of Runes to deduct
     */
    public void deductRunes (int value){
        this.totalRunes -= value;
    }

    /**Retrieve the total runes in the game
     *
     * @return total runes
     */
    public int getTotalRunes (){
        return this.totalRunes;
    }

    /**
     * Drop runes when player die or rest
     * @param dropLocation last location where player dies
     * @param runes drop runes
     */
    public void dropRunes (Location dropLocation, Runes runes){
        runes.setValue(totalRunes);
//        DroppedRunesGround.getInstance().setRunesAmount(getTotalRunes());
        totalRunes = 0;
        //dropLocation.setGround(DroppedRunesGround.getInstance());
        addDropRunesLocation(dropLocation);
    }

}
