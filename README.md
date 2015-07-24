# tictactoe-glazebrook

tictactoe-glazebrook is a web api based implementation of the game of tictactoe created with dropwizard.

### Author
Josh Glazebrook

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
```sh
INFO  [2015-07-24 02:26:57,045] liquibase: Successfully acquired change log lock
INFO  [2015-07-24 02:26:57,686] liquibase: Creating database history table with name: PUBLIC.DATABASECHANGELOG
INFO  [2015-07-24 02:26:57,691] liquibase: Reading from PUBLIC.DATABASECHANGELOG
INFO  [2015-07-24 02:26:57,707] liquibase: migrations.yml: 1::joshglazebrook: Table games created
INFO  [2015-07-24 02:26:57,708] liquibase: migrations.yml: 1::joshglazebrook: ChangeSet migrations.yml::1::joshglazebrook ran successfully in 4ms
INFO  [2015-07-24 02:26:57,727] liquibase: migrations.yml: 2::joshglazebrook: Table moves created
INFO  [2015-07-24 02:26:57,727] liquibase: migrations.yml: 2::joshglazebrook: ChangeSet migrations.yml::2::joshglazebrook ran successfully in 3ms
INFO  [2015-07-24 02:26:57,738] liquibase: migrations.yml: 3::joshglazebrook: Table players created
INFO  [2015-07-24 02:26:57,738] liquibase: migrations.yml: 3::joshglazebrook: ChangeSet migrations.yml::3::joshglazebrook ran successfully in 2ms
INFO  [2015-07-24 02:26:57,747] liquibase: Successfully released change log lock
```

Start application
```sh
$ java -jar application/target/application-1.0.jar server config.yml
```
If everything worked properly the app should now be running. By default it listens on port 3000 for the web application, and port 3001 for the administrative diagnostic tools.

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

### Playing the game

Below is a short overview of a typical game of tictactoe with this app. The examples use cURL to demonstrate how to interact with the api.

#### Creating a game
To create a game, you simply need to POST to the /game endpoint.

```sh
$ curl -X "POST" "http://localhost:3000/game"
```

If successful, the server will respond with the id of the game and your player token. Make sure to keep this a secret!

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
    "token": "8d7a92e7-fc46-41a3-a860-8192c7c9df84"
  }
}
```

Notice the data we want is wrapped in a generic JSON response format. When an error occurs, the error property of the object will contain the error message and data will be null and the status response code will be either 4xxx or 5xx. In the case of a succesful transaction, error will be null and data will contain the data we need, and a 2xx status code will be used.


#### Joining a game

To join a game you are going to need to know the id of the game! You can get that from the person who created the game.

Joining a game works almost exactly like creating a game, just you're POSTing to a different endpoint /game/:id.

```sh
$ curl -X "POST" "http://localhost:3000/game/e4d48cae-038b-4135-8aad-b7d31e610385"
```

If successful, the server will return a response containing the id of the game, and the token for you (player 2).

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
    "token": "07e85526-de09-4a00-bad5-3adbd5eaed70"
  }
}
```

At this point, the game is ready to be played. No player is chosen to go first, but rather the first player to play will be designated player 1.

#### Playing your turn

To play your turn, you must know the id of the game you're playing and your secret token you got from either creating or joining a game. Let's say that the player thar created the game will be going first and they want to play in space (0, 0) (row 0, column 0).

```sh
$ curl -X "POST" "http://localhost:3000/game/e4d48cae-038b-4135-8aad-b7d31e610385/move"
  	-H "Content-Type: application/json"
  	-d '{"token":"8d7a92e7-fc46-41a3-a860-8192c7c9df84","row":0,"col":0}'
```

If successful, the server will respond with the following:

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "gameEnded": false,
    "gameWinner": null,
    "moves": [
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 0
      }
    ]
  }
}
```
Above is more detailed information about the game you're playing. The moves property contains a list of a moves that have been played in the game (including the one you just made). The gameEnded and gameWinner properties are used to tell the requester that the game has ended and who the winner is. If gameEnded is true, gameWinner will be equal to the game winner's id or if the game resulted in a tie it will remain null.

What would happen if player one tries to play again in another spot?

```sh
$ curl -X "POST" "http://localhost:3000/game/e4d48cae-038b-4135-8aad-b7d31e610385/move"
    	-H "Content-Type: application/json"
    	-d '{"token":"8d7a92e7-fc46-41a3-a860-8192c7c9df84","row":0,"col":1}'
```

```json
{
  "error": "It is not your turn",
  "data": null
}
```

Uh oh! Guess there won't be any cheating!

Now let's have player 2 player their turn (using their token of course). They wish to play at (2, 2).

```sh
$ curl -X "POST" "http://localhost:3000/game/e4d48cae-038b-4135-8aad-b7d31e610385/move"
  	-H "Content-Type: application/json"
  	-d '{"token":"07e85526-de09-4a00-bad5-3adbd5eaed70","row":2,"col":2}'
```

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "gameEnded": false,
    "gameWinner": null,
    "moves": [
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 0
      },
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
        "row": 2,
        "col": 2
      }
    ]
  }
}
```

#### Winning the game

To win a game of tictactoe you must get three plays in a row either horizontally, vertically, or diagonally. 

Let's assume the following game play has gone on while you were reading the last sentence.

> Player 1: (0, 1)

> Player 2: (2, 1)

It's now player 1's turn again and they can win the game by playing at (0, 2). This would make a winning row at the very top of the tictactoe game board.

```sh
$ curl -X "POST" "http://localhost:3000/game/e4d48cae-038b-4135-8aad-b7d31e610385/move" 
	-H "Content-Type: application/json" 
	-d '{"token":"8d7a92e7-fc46-41a3-a860-8192c7c9df84","row":0,"col":2}'
```
```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "gameEnded": true,
    "gameWinner": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
    "moves": [
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 0
      },
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 1
      },
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
        "row": 2,
        "col": 1
      },
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
        "row": 2,
        "col": 2
      },
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 2
      }
    ]
  }
}
```

In the response above, gameEnded property is now set to true, and the gameWinner property is set to the winning player's id. Remember that if a tie had occurred, gameWinner would be null.

There are a couple more endpoints to investigate but that information is in the next section!

###API Reference

####Create a game

Creates new game and returns gameId, playerId, token for player 1.

`POST` /game

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
    "token": "8d7a92e7-fc46-41a3-a860-8192c7c9df84"
  }
}
```

####Join a game
Joins the provided game based on its id, and returns the gameId, playerId, token for player 2. From now on we will use the gameId from above in the url.

`POST` /game/e4d48cae-038b-4135-8aad-b7d31e610385/join

```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
    "token": "07e85526-de09-4a00-bad5-3adbd5eaed70"
  }
}
```

###Play your turn

To play your turn in a game, you must provide the gameId and your secret token as well as the row and column positions showing where you want to play.

`POST` /game/e4d48cae-038b-4135-8aad-b7d31e610385/move
```json
{
	"token": "8d7a92e7-fc46-41a3-a860-8192c7c9df84",
	"row": 0,
	"col": 2
}
```
```json
{
  "error": null,
  "data": {
    "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "gameEnded": false,
    "gameWinner": null,
    "moves": [
      {
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
        "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "row": 0,
        "col": 2
      }
    ]
  }
}
```

If the game ends, gameEnded will be set to true, and if there is a winner (tie games can happen), gameWinner will be set to the playerId of the player that won.

####Get game information
Retrieves detailed information about a game's state. 

`GET` /game/e4d48cae-038b-4135-8aad-b7d31e610385

```json
{
  "error": null,
  "data": {
    "id": "e4d48cae-038b-4135-8aad-b7d31e610385",
    "gameEnded": true,
    "winningPlayerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
    "lastPlayerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
    "players": [
      {
        "id": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385"
      },
      {
        "id": "626a58f3-2a85-402b-9702-3a1367fab586",
        "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385"
      }
    ]
  }
}
```

####Get moves

Gets a list of moves that have been played in the given game based on its id.

`GET` /game/e4d48cae-038b-4135-8aad-b7d31e610385/moves

```json
{
  "error": null,
  "data": [
    {
      "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
      "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
      "row": 0,
      "col": 0
    },
    {
      "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
      "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
      "row": 0,
      "col": 1
    },
    {
      "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
      "playerId": "a4965b9a-5b93-4e98-8515-371d8e8764e2",
      "row": 0,
      "col": 2
    },
    {
      "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
      "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
      "row": 2,
      "col": 1
    },
    {
      "gameId": "e4d48cae-038b-4135-8aad-b7d31e610385",
      "playerId": "626a58f3-2a85-402b-9702-3a1367fab586",
      "row": 2,
      "col": 2
    }
  ]
}
```

