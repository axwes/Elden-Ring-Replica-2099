package game;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Actors.Enemies.HeavySkeletalSwordsman;
import game.Actors.Merchants.FingerReaderEnia;
import game.Actors.Merchants.MerchantKale;
import game.Actors.Player;
import game.Managers.CharacterManager;
import game.Grounds.*;
import game.Managers.SiteOfLostGraceManager;
import game.Maps.Roundtablehold;
import game.Maps.StormveilCastle;
import game.Runes.GoldenRunes;
import game.Items.RemembranceOfTheGrafted;
import game.utils.FancyMessage;

import java.util.Arrays;
import java.util.List;


/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Choo Carmen, Tan Yin Cheng & Muhammad Ibrahim bin Mohd Yusni
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());
		FancyGroundFactory groundFactory = new FancyGroundFactory(
				new Dirt(),
				new Wall(),
				new Floor(),
				new Cliff(),
				new Cage(),
				new Barrack(),
				new SummonSign(),
				new LakeOfRot()
		);

		List<String> limgrave = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"=.....................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"..........................................................................!",
				"..................=.....................................................!!!",
				"........++++......................###___###...........................!!!!!",
				"........+++++++..............=....___=____#..........................!!!!!!",
				"..........+++.....................#________...........................!!!!!",
				"............+++...................#_______#.............................!!!",
				".............+.........=..........###___###...........................!!!!!",
				"............++......................#___#..............................!!!!",
				"..............+...............................................=..........!!",
				"..............++...........................................................",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..............=...........+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");


		GameMap limgraveGameMap = new GameMap(groundFactory, limgrave);
		world.addGameMap(limgraveGameMap);
		new Roundtablehold(world, limgraveGameMap.at(6, 23), groundFactory);
		new StormveilCastle(world, limgraveGameMap.at(30, 0), groundFactory);

		SiteOfLostGrace theFirstStep = new SiteOfLostGrace(39,11, "The First Stop");
		limgraveGameMap.at(theFirstStep.getX(), theFirstStep.getY()).setGround(theFirstStep);
		//Location location = new Location(gameMap,theFirstStep.getX(),theFirstStep.getY());
		SiteOfLostGraceManager.setLastSiteOfLostGraceLocation(limgraveGameMap.at(theFirstStep.getX(),theFirstStep.getY()));

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// SpawningGround being set
		for (int x = 2; x < 6; x++) {
			limgraveGameMap.at(x, 0).setGround(new Graveyard(new WestSpawn()));
		}

		for (int x = 2; x < 6; x++) {
			limgraveGameMap.at(x, 2).setGround(new Graveyard(new WestSpawn()));
		}

		for (int x = 60; x < 64; x++) {
			limgraveGameMap.at(x, 11).setGround(new Graveyard(new EastSpawn()));
		}

		for (int x = 60; x < 64; x++) {
			limgraveGameMap.at(x, 13).setGround(new Graveyard(new EastSpawn()));
		}

		int loopIter1 = 0;
		for (int y = 0; y < 4; y++) {
			loopIter1 ++;
			for (int x = 74; x > 60 + loopIter1; x--) {
				limgraveGameMap.at(x, y).setGround(new PuddleOfWater(new EastSpawn()));
			}
		}

		loopIter1 = 0;
		for (int y = 9; y < 13; y++) {
			loopIter1 ++;
			for (int x = 0; x < 5 + loopIter1; x++) {
				limgraveGameMap.at(x, y).setGround(new PuddleOfWater(new WestSpawn()));
			}
		}

		loopIter1 = 0;
		for (int y = 20; y < 23; y++) {
			loopIter1 ++;
			for (int x = 19; x < 22 ; x++) {
				limgraveGameMap.at(x, y).setGround(new GustOfWind(new WestSpawn()));
			}
		}

		loopIter1 = 0;
		for (int y = 18; y < 21; y++) {
			loopIter1 ++;
			for (int x = 53; x < 56 ; x++) {
				limgraveGameMap.at(x, y).setGround(new GustOfWind(new EastSpawn()));
			}
		}

		// Spawn MerchantKale
		limgraveGameMap.at(40, 12).addActor(new MerchantKale());
		limgraveGameMap.at(38,9).addActor(new FingerReaderEnia());

		// Spawn Player and run world
		Player player = new Player("Tarnished", '@', 455);

		// Assume player has 1 remembrance Of The Grafted at the start of game since Boss is not implemented
		player.addItemToInventory(new RemembranceOfTheGrafted());
		//world.addPlayer(player, limgraveGameMap.at(37, 11));
		world.addPlayer(player, limgraveGameMap.at(35, 10));

		// Add Golden Runes to Map
		limgraveGameMap.at(31,2).addItem(new GoldenRunes());
		limgraveGameMap.at(8,2).addItem(new GoldenRunes());
		limgraveGameMap.at(60,5).addItem(new GoldenRunes());
		limgraveGameMap.at(54,9).addItem(new GoldenRunes());
		limgraveGameMap.at(3,12).addItem(new GoldenRunes());
		limgraveGameMap.at(38,20).addItem(new GoldenRunes());
		limgraveGameMap.at(35,11).addItem(new GoldenRunes());

		// Add Heavy Skeletal Swordsman to Map
		// limgraveGameMap.at(2,4).addActor(new HeavySkeletalSwordsman());
		// limgraveGameMap.at(27,1).addActor(new HeavySkeletalSwordsman());
		// limgraveGameMap.at(28,1).addActor(new HeavySkeletalSwordsman());

		// gameMap.at(17, 4).addActor(new SkeletalBandit());
		// gameMap.at(18, 4).addActor(new HeavySkeletalSwordsman());
		CharacterManager.createCharacter(player);
		world.run();

	}
}

