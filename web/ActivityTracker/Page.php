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
  public static function Alert($class, $message)
  {
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
  public static function Form()
  {
    return '
      <div align="center" style="min-width: 500px; margin-top: 50px;">
        <p>Please enter the name/uuid: </p>
        <form class="form-inline" action="'.Config::get('app', 'url').'/index.php" method="GET">
          <div class="form-group">
            <input type="text" class="form-control" name="name" placeholder="name/uuid">
          </div>
          <div class="form-group">
            <label class="control-label">:</label>
          </div>
          <div class="form-group">
            <fieldset>
              <div class="control-group">
                  <div class="controls">
                      <select class="form-control" name="time">
                          <option value="milliseconds">milliseconds</option>
                          <option value="seconds">seconds</option>
                          <option value="minutes">minutes</option>
                          <option value="hours">hours</option>
                      </select>
                  </div>
              </div>
            </fieldset>
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
  public static function Graph($records, $measurement)
  {
    $data = '
      <!-- The chart -->
      <canvas id="chart" width="500" height="500" style="max-width: 100%; margin: 25px auto; display: block;"></canvas>
    ';
    $data .= '<script>';
    $data .= '
      var ctx = document.getElementById("chart").getContext("2d");
    ';
    $data .= '
      var data = {
        labels: [
    ';
    foreach ($records as $rec) {
      $data .= '"' . $rec['date'] . '", ';
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
    foreach ($records as $rec) {
      if ($measurement == "seconds")
      {
        $data .= $rec['time'] / (1000) . ',';
      }
      else if ($measurement == "minutes")
      {
        $data .= $rec['time'] / (1000 * 60) . ',';
      }
      else if ($measurement == "hours")
      {
        $data .= $rec['time'] / (1000 * 60 * 60) . ',';
      }
      else
      {
        $data .= $rec['time'] . ',';
      }
    }
    $data .= ']
       }]
     };
    ';
    $data .= '
      var chart = new Chart(ctx).Line(data, Chart.defaults.Line);
    ';
    $data .= '</script>';
    $data .= '
      <a class="btn btn-default" href="'.Config::get('app', 'url').'/index.php">Back</a>
    ';
    return $data;
  }

}
