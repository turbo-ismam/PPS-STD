package Configuration

object DefaultConfig {
  val PROJECT_NAME = Configuration.getString("ProjectName", "Tower Defense the GAME")



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

  //Default projectile image
  val BASE_PROJECTILE_IMAGE = Configuration.getString("BaseProjectileImage", "/projectiles/base_projectile.png")
  val CANNON_PROJECTILE_IMAGE = Configuration.getString("CannonProjectileImage", "/projectiles/cannon_projectile.png")
  val FLAME_PROJECTILE_IMAGE = Configuration.getString("FlameProjectileImage", "/projectiles/flame_projectile.png")

  //Tower button settings
  val TOWER_BUTTON_WIDTH = 150
  val TOWER_BUTTON_HEIGHT = 150
  val TOWER_BUTTON_LAYOUT_X = 0
  val TOWER_BUTTON_LAYOUT_Y = 50

  //Tower base settings
  val TOWER_DAMAGE = 5
  val TOWER_RANGE = 5
  val TOWER_FIRING_SPEED = 4
  val TOWER_PRICE = 50
  val TOWER_SELL_COST = 1000
  val TOWER_CHARGING_TIME = 1.0
  val FLAME_TOWER_CHARGING_TIME = 1.5
  val CANNON_TOWER_CHARGING_TIME = 2.0

  //Player base settings
  val INITIAL_HEALTH = 20
  val INITIAL_MONEY = 200

  /*************************************************************************************************************
   *****************************************  GAME WINDOW DIMENSIONS  ******************************************
   **************************************************************************************************************/

  val GAME_WINDOW_WIDTH = 1580
  val GAME_WINDOW_HEIGHT = 1060

  val GAME_CANVAS_WIDTH: Int = Configuration.getInt("CanvasWidth", 1280)
  val GAME_CANVAS_HEIGHT: Int = Configuration.getInt("CanvasHeight", 960)

  /*************************************************************************************************************
   *************************************  VIEW THINGS (BUTTON, LABEL ..)  **************************************
   **************************************************************************************************************/

  // MainMenuViewModel.scala
  val START_GAME_BTN = "Start Game!"
  val START_GAME_BTN_ID = "StartGameBtn"

  val DIFFICULTY_COMBO_BOX_EASY = "Easy"
  val DIFFICULTY_COMBO_BOX_NORMAL = "Normal"
  val DIFFICULTY_COMBO_BOX_HARD = "Hard"
  val DIFFICULTY_COMBO_BOX_ID = "DifficultyComboBox"

  val EXIT_GAME_BTN = "Exit Game"
  val EXIT_GAME_BTN_ID = "ExitGameBtn"

  val OPTIONS_PADDING = 10
  val OPTIONS_SPACING = 10


  val START_WAVE_BTN = "Start Wave!"
  val START_WAVE_BTN_ID = "StartWaveBtn"

  val GO_MAIN_MENU_BTN = "Back to main menu"
  val GO_MAIN_MENU_BTN_ID = "BackToMainMenuBtn"

  val RESTART_GAME_BTN = "Restart Game!"
  val RESTART_GAME_BTN_ID = "RestartGameBtn"

  // TODO @Hama move here your tower info's (only info used by the GameViewModel)

  val PLAYER_HEALTH_LABEL = "Player Health"
  val PLAYER_HEALTH_LABEL_ID = "PlayerHealthLabel"

  val Player_MONEY_LABEL = "Player Money"
  val PLAYER_MONEY_LABEL_ID = "PlayerMoneyLabel"

  val NOT_IMPLEMENTED_YET = "Not implemented yet"

  /*************************************************************************************************************
   *********************************************  GENERIC ERRORS  **********************************************
   **************************************************************************************************************/

  val ACTION_LISTENER_ERROR = "An action listener doesn't work properly"
  val STAGE_ERROR = "The Primary Stage isn't set properly"
  val GENERIC_GOOD_EXIT_STATUS = 1
  val NOTHING_MESSAGE = "I'm do nothing"
}
