# City Survival Game

A Java Swing survival game set in a tile-based city environment. The player explores the map, collects useful items, fights enemies, manages health and inventory, and tries to survive long enough to reach the objective.

## Run the Game

The source code is fully visible in this repository. To run the game, download the repository and use the included launcher for your operating system.

### Windows

1. Click **Code → Download ZIP** on GitHub.
2. Extract the ZIP.
3. Double-click `run-game-windows.bat`.

### macOS

1. Click **Code → Download ZIP** on GitHub.
2. Extract the ZIP.
3. Double-click `run-game-mac.command`.

If macOS blocks the launcher, right-click it and choose **Open**, or open Terminal in the project folder and run:

```bash
bash run-game-mac.command
```

The launchers compile the Java source automatically and start the game. Java 17 or newer is required.

> This is a Java Swing desktop application, so it does not run directly inside a normal GitHub web page like an HTML/JavaScript game.

## Main Features

- Tile-based city map with a scrolling camera
- Player movement using the keyboard
- Enemies with randomized movement and combat behavior
- Two weapon levels
- Three food and healing item types
- Inventory and health tracking
- Save and load functionality
- Victory, restart, and game-over states

## Controls

| Key | Action |
|---|---|
| Arrow Keys | Move the player |
| 1 / 2 | Equip available weapons |
| 3 / 4 / 5 | Use food or healing items |
| F5 | Save the current game |
| F9 | Load a saved game |

## Project Structure

```text
elona-city-survival-java/
├── assets/
├── src/
│   └── citysurvival/
├── map.txt
├── run-game-mac.command
├── run-game-windows.bat
├── README.md
└── .gitignore
```

## Requirements

- Java 17 or newer
- Java Swing, included with the standard JDK

## Compile From Source Manually

From the project directory:

```bash
mkdir -p out
javac -d out src/citysurvival/*.java
java -cp out citysurvival.Main
```

## About the Project

This project was created to practice object-oriented programming and Java application development.

It includes practical work with:

- classes and objects
- game-state management
- collision detection
- keyboard input
- file-based maps
- inventory systems
- save/load functionality
- GUI development with Java Swing
