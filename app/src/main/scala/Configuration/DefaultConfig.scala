package Configuration

object DefaultConfig {
  val PROJECT_NAME: String = Configuration.getString("ProjectName", "Tower Defense the GAME")
  val CELL_SIZE = 64
  //Wave music
  val WAVE_MUSIC: String = Configuration.getString("WAVE_MUSIC", "/music/wave_music.mp3")

  //Default enemy image
  val BASE_ENEMY_IMAGE: String = Configuration.getString("BaseEnemyImage", "/enemies/baseEnemy.png")
  val MEDIUM_ENEMY_IMAGE: String = Configuration.getString("MediumEnemyImage", "/enemies/mediumEnemy.png")
  val HARD_ENEMY_IMAGE: String = Configuration.getString("HardEnemyImage", "/enemies/superEnemy.png")

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

  val SIMPLE_PATH_FILE_NAME = "/DefaultPaths/SimplePath.json"
  val NORMAL_PATH_FILE_NAME = "/DefaultPaths/NormalPath.json"
  val HARD_PATH_FILE_NAME = "/DefaultPaths/HardPath.json"

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
  val BASE_TOWER_IMAGE = Configuration.getString("BaseTowerImage", "/towers/base_tower.png")
  val CANNON_TOWER_IMAGE = Configuration.getString("CannonTowerImage", "/towers/cannon_tower.png")
  val FLAME_TOWER_IMAGE = Configuration.getString("FlameTowerImage", "/towers/flame_tower.png")

  //Default towers name and description
  val BASE_TOWER_NAME = Configuration.getString("BaseTowerName", "Base Tower")
  val CANNON_TOWER_NAME = Configuration.getString("CannonTowerName", "Cannon Tower")
  val FLAME_TOWER_NAME = Configuration.getString("FlameTowerName", "Flame Tower")
  val BASE_TOWER_DESC = Configuration.getString("BaseTowerDesc", "Base Tower")
  val CANNON_TOWER_DESC = Configuration.getString("CannonTowerDesc", "Cannon Tower")
  val FLAME_TOWER_DESC = Configuration.getString("FlameTowerDesc", "Flame Tower")


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
  val BASE_TOWER_FIRING_SPEED = 3
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

  val BASE_PROJECTILE_NAME = Configuration.getString("BaseProjectileName", "Base projectile")
  val BASE_PROJECTILE_DESC = Configuration.getString("BaseProjectileDesc","A Base projectile type")
  val CANNON_PROJECTILE_NAME = Configuration.getString("CannonProjectileName", "Cannon projectile")
  val CANNON_PROJECTILE_DESC = Configuration.getString("CannonProjectileDesc","A Cannon projectile type")
  val BASE_PROJECTILE_DIAMETER: Int = 10
  val CANNON_PROJECTILE_DIAMETER: Int = 50
  val BASE_PROJECTILE_SPEED: Int = 1000
  val CANNON_PROJECTILE_SPEED: Int = 1300
  val PROJECTILE_ALLOWED_MOVEMENT: Double = 1.0


  /** ***********************************************************************************************************
   * *******************************************  GRID MODEL INFO  **********************************************
   * ************************************************************************************************************ */

  val DIFFICULTY_SELECTION_ERROR = "Something went wrong in difficulty selection, Simple Map will automatically selected"
  val GRID_CREATION_ERROR = "Something went wrong in grid creation"


}
