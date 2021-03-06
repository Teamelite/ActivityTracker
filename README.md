# ActivityTracker

> ActivityTracker is a Minecraft server modification built on the SpigotAPI platform. It is used to track a player's level of activity and determine if they're inactive.

#### TODO:

- Seperate dates into a seperate table.
- Allow the comparing of multiple players in the web view.
- Refactor the 'web' package to create a more modular codebase.
- Implement a whitelist and a blacklist.
- Allow the attaching of other data to the track such as in-game chat and bans.
- To consider implementing in-game commands for easier tracking.
- Implement an algorithm for determining whether a player is active.

### Spigot setup

ActivityTracker make's use of Bukkit's Database API which includes Ebean's Object Relational Mapping library.
This means that a database must be configured within your [ bukkit.yml ] file.
An example configuration is provided below:
```php
database:
  username: root
  isolation: SERIALIZABLE
  driver: com.mysql.jdbc.Driver
  password: secret
  url: jdbc:mysql://{{HOST}}:{{PORT}}/{{DATABASE}}
```
  `{{HOST}}`, `{{PORT}}` and `{{DATABASE}}` aren't templates. 
  You must manually enter the details yourself, like so:
```php
  url: jdbc:mysql://localhost:3306/minecraft
 
```

### Web setup

The setup for the web-application is fairly straight forward. You should create a folder on your web-server which can be accessed, and then drop the contents of the ```web``` package into the newely created folder.
Once this has been done, you must then edit the configuration options which can be found within:
```php
  <folder_name>/ActivityTracker/Config.php
```

### Example:

A live demonstration of it can be viewed here:
http://teamelite.io/tracker/index.php

Example names:
```php
  - life855
  - BeMyZelf
  - KraZ__
  - Welshy__
```

