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
    $data = '
      var ctx = document.getElementById("myChart").getContext("2d");
    ';

    $data .= '
      var data = {
        labels: [
    ';

    // Add the labels:
    foreach ($records as $rec) {
      $data .= '\"' . $rec['date'] . '\", ';
    }

    $data .= '],
      datasets: [{
        label: "My First dataset",
        fillColor: "rgba(220,220,220,0.2)",
        strokeColor: "rgba(220,220,220,1)",
        pointColor: "rgba(220,220,220,1)",
        pointStrokeColor: "#fff",
        pointHighlightFill: "#fff",
        pointHighlightStroke: "rgba(220,220,220,1)",
        data: [
    ';

    // Add the data:
    foreach ($records as $rec) {
      $data .= $rec['time'] . ',';
    }

    $data .= ']
       }]
     };
    ';

    $data .= '
      var chart = new Chart(ctx).Line(data, Chart.defaults.Line);
    ';

    return '<script>' . $data . '</script>';
  }

}
