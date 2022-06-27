package Configuration

object DefaultConfig {

  val PROJECT_NAME: String = Configuration.getString("ProjectName", "Tower Defense the GAME")
  val CELL_SIZE = 64

  //Wave music
  val WAVE_MUSIC: String = "music/wave_music.mp3"

  //Default enemy image
  val BASE_ENEMY_IMAGE: String = "enemies/baseEnemy.png"
  val MEDIUM_ENEMY_IMAGE: String = "enemies/mediumEnemy.png"
  val HARD_ENEMY_IMAGE: String = "enemies/superEnemy.png"

  /** ***********************************************************************************************************
   * *********************************************  MODEL - GRID  ***********************************************
   * ************************************************************************************************************ */

  val TILE_HEIGHT_PX = 64
  val TILE_WIDTH_PX = 64

  val TILE_START_POSITION_ID = 2
  val TILE_END_POSITION_ID = 3

  /** ***********************************************************************************************************
   * ****************************************  GAME WINDOW DIMENSIONS  ******************************************
   * ************************************************************************************************************ */

  val GAME_WINDOW_WIDTH = 1580
  val GAME_WINDOW_HEIGHT = 1060

  val GAME_CANVAS_WIDTH: Int = Configuration.getInt("CanvasWidth", 1280)
  val GAME_CANVAS_HEIGHT: Int = Configuration.getInt("CanvasHeight", 960)

  /** ***********************************************************************************************************
   * ************************************  VIEW THINGS (BUTTON, LABEL ..)  **************************************
   * ************************************************************************************************************ */

  // MainMenuViewModel.scala
  val START_GAME_BTN = "Start Game!"
  val START_GAME_BTN_ID = "StartGameBtn"

  val DIFFICULTY_COMBO_BOX_EASY = "Easy"
  val DIFFICULTY_COMBO_BOX_NORMAL = "Normal"
  val DIFFICULTY_COMBO_BOX_HARD = "Hard"
  val DIFFICULTY_COMBO_BOX_ID = "DifficultyComboBox"

  val UPLOAD_MAP_TEXT_BOX = "Map path ..."

  val EXIT_GAME_BTN = "Exit Game"
  val EXIT_GAME_BTN_ID = "ExitGameBtn"

  val ADD_MAP_BTN = "Add Custom Map"
  val ADD_MAP_BTN_ID = "AddCustomMap"

  val OPTIONS_ID = "difficultBtn"

  val START_WAVE_BTN = "Start Wave!"
  val START_WAVE_BTN_ID = "StartWaveBtn"

  val GO_MAIN_MENU_BTN = "Back to main menu"
  val GO_MAIN_MENU_BTN_ID = "BackToMainMenuBtn"

  val RESTART_GAME_BTN = "Restart Game!"
  val RESTART_GAME_BTN_ID = "RestartGameBtn"

  val PLAYER_HEALTH_LABEL = "Player Health"
  val PLAYER_HEALTH_LABEL_ID = "PlayerHealthLabel"

  val Player_MONEY_LABEL = "Player Money"
  val PLAYER_MONEY_LABEL_ID = "PlayerMoneyLabel"

  val NOT_IMPLEMENTED_YET = "Not implemented yet"

  /** ***********************************************************************************************************
   * ********************************************  GENERIC ERRORS  **********************************************
   * ************************************************************************************************************ */

  val ACTION_LISTENER_ERROR = "An action listener doesn't work properly"
  val STAGE_ERROR = "The Primary Stage isn't set properly"
  val GENERIC_GOOD_EXIT_STATUS = 0
  val NOTHING_MESSAGE = "I'm do nothing"
  val CACHE_GENERIC_ERROR = "Difficulty not correctly setted"

  /** ***********************************************************************************************************
   * *******************************************  FILE SYSTEM INFO  *********************************************
   * ************************************************************************************************************ */

  val SIMPLE_PATH_FILE_NAME = "DefaultPaths/SimplePath.json"
  val NORMAL_PATH_FILE_NAME = "DefaultPaths/NormalPath.json"
  val HARD_PATH_FILE_NAME = "DefaultPaths/HardPath.json"

  /** ***********************************************************************************************************
   * **********************************************  GAME INFOS  ************************************************
   * ************************************************************************************************************ */

  val SIMPLE_LEVEL = 1
  val NORMAL_LEVEL = 2
  val HARD_LEVEL = 3
  val CUSTOM_LEVEL = 0

  /** ***********************************************************************************************************
   * **********************************************  CACHE INFO  ************************************************
   * ************************************************************************************************************ */

  val EMPTY_MAP_PATH = "Empty"

  /** ***********************************************************************************************************
   * **********************************************  TOWER INFO  ************************************************
   * ************************************************************************************************************ */

  //Default towers image
  val BASE_TOWER_IMAGE = "towers/base_tower.png"
  val CANNON_TOWER_IMAGE = "towers/cannon_tower.png"
  val FLAME_TOWER_IMAGE = "towers/flame_tower.png"

  //Default towers name and description
  val BASE_TOWER_NAME = "Base Tower"
  val CANNON_TOWER_NAME = "Cannon Tower"
  val FLAME_TOWER_NAME = "Flame Tower"
  val BASE_TOWER_DESC = "Base Tower"
  val CANNON_TOWER_DESC = "Cannon Tower"
  val FLAME_TOWER_DESC = "Flame Tower"


  //Tower button settings
  val TOWER_BUTTON_WIDTH = 150
  val TOWER_BUTTON_HEIGHT = 150
  val TOWER_BUTTON_LAYOUT_X = 0
  val TOWER_BUTTON_LAYOUT_Y = 50

  //Tower base settings
  val BASE_TOWER_DAMAGE = 5
  val CANNON_TOWER_DAMAGE = 10
  val FLAME_TOWER_DAMAGE = 15
  val BASE_TOWER_RANGE = 200
  val CANNON_TOWER_RANGE = 1000
  val FLAME_TOWER_RANGE = 5
  val BASE_TOWER_FIRING_SPEED = 5
  val CANNON_TOWER_FIRING_SPEED = 3
  val FLAME_TOWER_FIRING_SPEED = 4
  val BASE_TOWER_PRICE = 10
  val CANNON_TOWER_PRICE = 20
  val FLAME_TOWER_PRICE = 30

  //Player base settings
  val MONEY_EARNED = 5
  val INITIAL_HEALTH = 20
  val INITIAL_MONEY = 200

  /** ***********************************************************************************************************
   * **********************************************  PROJECTILE INFO  ************************************************
   * ************************************************************************************************************ */

  val BASE_PROJECTILE_NAME = "Base projectile"
  val BASE_PROJECTILE_DESC = "A Base projectile type"
  val CANNON_PROJECTILE_NAME = "Cannon projectile"
  val CANNON_PROJECTILE_DESC = "A Cannon projectile type"
  val BASE_PROJECTILE_DIAMETER: Int = 10
  val CANNON_PROJECTILE_DIAMETER: Int = 50
  val BASE_PROJECTILE_SPEED: Int = 1200
  val CANNON_PROJECTILE_SPEED: Int = 1300
  val PROJECTILE_ALLOWED_MOVEMENT: Double = 1.0


  /** ***********************************************************************************************************
   * *******************************************  GRID MODEL INFO  **********************************************
   * ************************************************************************************************************ */

  val DIFFICULTY_SELECTION_ERROR = "Something went wrong in difficulty selection, Simple Map will automatically selected"
  val GRID_CREATION_ERROR = "Something went wrong in grid creation"

}
