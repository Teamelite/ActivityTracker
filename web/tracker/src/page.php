<?php namespace ActivityTracker;

use ActivityTracker\Config;

class Page
{

  /**
   * Create an alert.
   *
   * @param $class the bootstrap's alert class you wish to use.
   * @param $message the message you wish to display.
   * @return HTML content.
   */
  public static function Alert($class, $message) {
    return '
      <div class="row">
        <div class="'.$class.'" role="alert" align="center" style="margin: 0;">'.$message.'</div>
      </div>
    ';
  }

  /**
   * Create a search bar.
   *
   * @return HTML content.
   */
  public static function Form() {
    return '
      <div align="center" style="min-width: 500px; margin-top: 50px;">
        <p>Please enter the name/uuid: </p>
        <form class="form-inline" action="'.Config::get('app', 'url').'/ActivityTracker/index.php" method="GET">
          <div class="form-group">
            <input type="text" class="form-control" name="name">
          </div>
          <div class="form-group">
            <input type="submit" class="btn btn-default" value="Search">
          </div>
        </form>
      </div>
    ';
  }

  /**
   * Display an Activity graph for a player.
   *
   * @param $records the user record.
   * @return HTML content.
   */
  public static function Graph($records) {
    /**
     * TODO
     *
     * This method needs to display the information from the
     * records supplied in readable form.
     *
     * A nice chart using [ chart.js ] would be nice.
     *
     */
  }

}
