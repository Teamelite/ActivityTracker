# ActivityTracker

> ActivityTracker is a Minecraft server modification built on the SpigotAPI platform. It is used to track a player's level of activity and determine if they're inactive.

### Spigot setup

ActivityTracker make's use of Bukkit's Database API which includes Ebean's Object Relational Mapping library.
This means that a database must be configured within your [ bukkit.yml ] file.
An example configuration is provided below:
```yaml
database:
  username: root
  isolation: SERIALIZABLE
  driver: com.mysql.jdbc.Driver
  password: secret
  url: jdbc:mysql://{{HOST}}:{{PORT}}/{{DATABASE}}
```
  `{{HOST}}`, `{{PORT}}` and `{{DATABASE}}` aren't templates. 
  You must manually enter the details yourself, like so:
```yaml
  url: jdbc:mysql://localhost:3306/minecraft
 
```

### Web setup

The setup for the web-application is fairly straight forward. You should create a folder on your web-server which can be accessed, and then drop the contents of the ```web``` package into the newely created folder.
Once this has been done, you must then edit the configuration options which can be found within:
```yaml
  /[folder]/ActivityTracker/Config.php
```

