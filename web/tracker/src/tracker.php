<?php namespace ActivityTracker;

use ActivityTracker\Config;
use ActivityTracker\Page;

class Tracker
{

  protected $page;
  protected $connection;

  /**
   * Constructs a new Tracker instance.
   */
  public function __construct()
  {
    try
    {
      $this->connection = new \PDO('mysql:host='.Config::get('mysql', 'host').';port='.Config::get('mysql', 'port').';dbname='.Config::get('mysql', 'database'), Config::get('mysql', 'username'), Config::get('mysql', 'password'));
    }
    catch (\PDOException $e)
    {
      header('HTTP/1.1 500 Internal Server Error');
    }

    // If the connection exists...
    if ($this->getConnection() != null)
    {
      $this->page.=Page::Alert('alert alert-success', Config::get('app', 'name').' - '.Config::get('app', 'version'));
      if ($_GET['name'] != null) {
          // Get tracker information and display it...
          $query = "SELECT * FROM activitytracker_users WHERE name=:value;";
          $statement = $this->connection->prepare($query);

          // Bind the parameters.
          $statement->bindParam(':value', $_GET['name']);
          $statement->execute();

          if ($statement->rowCount() == 0) {
              $query = "SELECT * FROM activitytracker_users WHERE unique_id=:value;";
              $statement = $this->connection->prepare($query);

              // Bind the parameters.
              $statement->bindParam(':value', $_GET['name']);
              $statement->execute();
          }

          // Execute the query.
          $record = $statement->fetchAll()[0];

          if ($record != null)
          {
            $query = "SELECT * FROM activitytracker_logs WHERE user_id=:value;";
            $statement = $this->connection->prepare($query);

            // Bind the parameters.
            $statement->bindParam(':value', $record['id']);
            $statement->execute();

            if ($statement->rowCount() > 0)
            {
              $this->page.=Page::Graph($statement->fetchAll());
            }
            else
            {
              $this->page.=Page::Alert("alert alert-info", "The user specified hasn't played at all.");
              $this->page.=Page::Form();
            }
          }
          else
          {
            $this->page.=Page::Alert("alert alert-danger", "A user by the name/uuid of: `" . $_GET['name'] . "` was not found.");
            $this->page.=Page::Form();
          }
      }
      else
      {
        $this->page.=Page::Form();
      }
    }
    else
    {
      $this->page.=Page::Alert("alert alert-danger", "Unable to connect to the database.");
    }
  }

  /**
   * @return the PDO connection instance.
   */
  public function getConnection()
  {
    return $this->connection;
  }

  /**
   * @return the page content.
   */
  public function page() {
    return $this->page;
  }

}
