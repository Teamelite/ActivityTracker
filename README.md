# ActivityTracker

ActivityTracker is a Minecraft server modification built on the SpigotAPI platform. It is used to track a player's level of activity and determine if they're inactive.

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

The database currently stores a user's play time in milliseconds. This should be converted to minutes and then be used to determine a player's activity through the use of some commands or maybe a fancy web-interface.
