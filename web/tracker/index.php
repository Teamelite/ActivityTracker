<?php require_once '../vendor/autoload.php';

/**
 *
 * ActivityTracker
 *
 * This web panel was quickly built in order to
 * interact with the MySQL database that holds
 * the activity data tracked by the ActivityTracker
 * minecraft plugin.
 *
 */

error_reporting(E_ALL);
ini_set("display_errors", 1);

$tracker = new ActivityTracker\Tracker();

?>

<!DOCTYPE html>
<html>
<head>
  <title>ActivityTracker</title>

  <!-- Bootstrap stylesheets -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

</head>
<body>

  <?php echo $tracker->page(); ?>

  <!-- Bootstrap script -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</body>
</html>
