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
    $config = [

      /**
       * Application information.
       */
      'app' => [
        'name'        =>  'ActivityTracker',
        'version'     =>  '1.0.0',

        // The URL should not contain a trailling slash. ('/')
        'url'         =>  'http://localhost/_applications'
      ],

      /**
       * Database connection information.
       */
      'mysql' => [
        'host'        =>  'localhost',
        'port'        =>  '3306',
        'database'    =>  'activitytracker',
        'username'    =>  'root',
        'password'    =>  ''
      ],

    ];

    return $config[$setting][$key];
  }


}
