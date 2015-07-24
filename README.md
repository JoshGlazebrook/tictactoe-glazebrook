# tictactoe-glazebrook

tictactoe-glazebrook is an api based implementation of the game of tictactoe created with dropwizard.

### Version
1.0

### Requirements
- Java 1.7+
- Maven

### Installation

Clone this repository
```sh
$ git clone https://github.com/JoshGlazebrook/tictactoe-glazebrook
```
Download dependencies and build project jar
```sh
$ mvn install
```

Run migrations
```sh
$ java -jar application/target/application-1.0.jar db migrate config.yml --migrations migrations.yml
```

Start application
```sh
$ java -jar application/target/application-1.0.jar server config.yml
```
If everything worked properly the app should now be running.

```sh
INFO  [2015-07-24 01:20:21,749] org.eclipse.jetty.util.log: Logging initialized @1660ms
created
INFO  [2015-07-24 01:20:22,092] io.dropwizard.server.ServerFactory: Starting TicTacToeApplication
INFO  [2015-07-24 01:20:22,175] org.eclipse.jetty.setuid.SetUIDListener: Opened application@4d8b9e26{HTTP/1.1}{0.0.0.0:3000}
INFO  [2015-07-24 01:20:22,176] org.eclipse.jetty.setuid.SetUIDListener: Opened admin@93bf890{HTTP/1.1}{0.0.0.0:3001}
INFO  [2015-07-24 01:20:22,180] org.eclipse.jetty.server.Server: jetty-9.2.z-SNAPSHOT
INFO  [2015-07-24 01:20:23,070] io.dropwizard.jersey.DropwizardResourceConfig: The following paths were found for the configured resources:

    GET     /game/all (com.glazebrook.tictactoe.resources.GameResource)
    GET     /game/{id} (com.glazebrook.tictactoe.resources.GameResource)
    POST    /game (com.glazebrook.tictactoe.resources.GameResource)
    GET     /test (com.glazebrook.tictactoe.resources.TestResource)

INFO  [2015-07-24 01:20:23,072] org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@6c5bb8f7{/,null,AVAILABLE}
INFO  [2015-07-24 01:20:23,077] io.dropwizard.setup.AdminEnvironment: tasks =

    POST    /tasks/log-level (io.dropwizard.servlets.tasks.LogConfigurationTask)
    POST    /tasks/gc (io.dropwizard.servlets.tasks.GarbageCollectionTask)

INFO  [2015-07-24 01:20:23,082] org.eclipse.jetty.server.handler.ContextHandler: Started i.d.j.MutableServletContextHandler@7998f5c7{/,null,AVAILABLE}
INFO  [2015-07-24 01:20:23,109] org.eclipse.jetty.server.ServerConnector: Started application@4d8b9e26{HTTP/1.1}{0.0.0.0:3000}
INFO  [2015-07-24 01:20:23,110] org.eclipse.jetty.server.ServerConnector: Started admin@93bf890{HTTP/1.1}{0.0.0.0:3001}
INFO  [2015-07-24 01:20:23,110] org.eclipse.jetty.server.Server: Started @3022ms
```

