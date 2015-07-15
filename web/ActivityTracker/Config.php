<?php namespace ActivityTracker;

class Config
{
  /**
   * Get a configuration setting.
   *
   * @param $setting the setting to retrieve.
   * @param $key the key of the value to retrieve.
   */
  public static function get($setting, $key)
  {
    $config = array(

      /**
       * Application information.
       */
      'app' => array(
        'name'        =>  'ActivityTracker',
        'version'     =>  '1.0.0',

        // The URL to the application's root directory, this should not contain a trailling slash. ('/')
        'url'         =>  'http://example.com/tracker'
      ),

      /**
       * Database connection information.
       */
      'mysql' => array(
        'host'        =>  'localhost',
        'port'        =>  '3306',
        'database'    =>  'minecraft',
        'username'    =>  'root',
        'password'    =>  'secret'
      ),

    );

    return $config[$setting][$key];
  }


}
