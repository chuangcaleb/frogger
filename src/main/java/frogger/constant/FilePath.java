package frogger.constant;

import java.io.File;

/**
 * {@code FilePath} is a class that stores Strings representing the file paths of resources.
 */
public class FilePath {

	// ################################### MISC ########################################

	/** The path to the background music's sound file */
	public static final String MUSIC_BG = "src/main/resources/frogger/music/FroggerTheme_loop.mp3";

	/** The path to the permanent high score text files */
	public static final String HISCORES_DIR = "hiscore_data" + File.separator;

	// ################################### VIEWS ########################################

	/** The path to for the home view */
	public static final String FXML_HOME = "/frogger/view/home.fxml";
	/** The path to the info view */
	public static final String FXML_INFO = "/frogger/view/info.fxml";
	/** The path to the game view */
	public static final String FXML_GAME = "/frogger/view/game.fxml";
	/** The path to the high score pop-up view */
	public static final String FXML_SCORE = "/frogger/view/scorepopup.fxml";

	// ################################### FROG ########################################

	/** The path to the default Frog sprite */
	public static final String IMG_FROG_DEFAULT = "/frogger/images/frog/froggerUp.png";
	/** The directory for the Frog's normal sprites */
	public static final String IMG_FROG_ROOT = "/frogger/images/frog/frogger";
	/** The directory for the Frog death sprites */
	public static final String IMG_FROG_DEATH_ROOT = "/frogger/images/death/";

	// ################################### END ########################################

	/** The path to the default End sprite */
	public static final String IMG_END = "/frogger/images/bg/End.png";
	/** The path to the activated End sprite */
	public static final String IMG_END_A = "/frogger/images/bg/FrogEnd.png";

	// ################################### CAR ########################################

	/** The path to the leftwards Car sprite */
	public static final String IMG_CAR_L = "/frogger/images/car/carLeft.png";
	/** The path to the rightwards Car sprite */
	public static final String IMG_CAR_R = "/frogger/images/car/carRight.png";

	/** The path to the leftwards short truck sprite */
	public static final String IMG_TRUCK_SHORT_L = "/frogger/images/truck/truck1Left.png";
	/** The path to the rightwards short truck sprite */
	public static final String IMG_TRUCK_SHORT_R = "/frogger/images/truck/truck1Right.png";
	/** The path to the leftwards long truck sprite */
	public static final String IMG_TRUCK_LONG_L = "/frogger/images/truck/truck2Left.png";
	/** The path to the rightwards long truck sprite*/
	public static final String IMG_TRUCK_LONG_R = "/frogger/images/truck/truck2Right.png";

	// ################################### LOG ########################################

	/** The path to the long Log sprite */
	public static final String IMG_LOG_LONG = "/frogger/images/log/log_long.png";
	/** The path to the medium Log sprite */
	public static final String IMG_LOG_MED = "/frogger/images/log/log_med.png";
	/** The path to the short Log sprite */
	public static final String IMG_LOG_SHORT = "/frogger/images/log/log_short.png";

	// ################################### TURTLE ########################################

	/** The path to the default Turtle sprite */
	public static final String IMG_TURTLE_DEFAULT = "/frogger/images/turtle/TurtleAnimation1.png";
	/** The directory for the Turtle sprites */
	public static final String IMG_TURTLE_ROOT = "/frogger/images/turtle/TurtleAnimation";

	// ################################# WET TURTLES ######################################

	/** The path to the default WetTurtle sprite */
	public static final String IMG_WET_TURTLE_DEFAULT = "/frogger/images/turtle/WetTurtleAnimation2.png";
	/** The directory for the WetTurtle sprites */
	public static final String IMG_WET_TURTLE_ROOT = "/frogger/images/turtle/WetTurtleAnimation";


}
