# City Survival Game

A Java Swing survival game set in a tile-based city environment. The player explores the map, collects useful items, fights enemies, manages health and inventory, and tries to survive long enough to reach the objective.

## Playable Build

Want to run the game without compiling the source code?

**[Download the playable game package](dist/City-Survival-Game.zip)**

The package includes the runnable JAR, game assets, map file, and launch scripts.

### Run on Windows

1. Download and extract `City-Survival-Game.zip`.
2. Open the extracted `package` folder.
3. Double-click `run-windows.bat`.

### Run on macOS

1. Download and extract `City-Survival-Game.zip`.
2. Open the extracted `package` folder.
3. Double-click `run-mac.command`.

If macOS blocks the launcher, open Terminal in that folder and run:

```bash
java -jar CitySurvival.jar
```

### Run on Linux

Extract the package, open a terminal inside the `package` folder, and run:

```bash
java -jar CitySurvival.jar
```

> Java 17 or newer is required. Keep `assets/` and `map.txt` in the same folder as the JAR.

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
├── dist/
│   └── City-Survival-Game.zip
├── map.txt
├── README.md
└── .gitignore
```

## Requirements

- Java 17 or newer
- Java Swing, included with the standard JDK

## Compile From Source

From the project directory:

```bash
mkdir -p out
javac -d out src/citysurvival/*.java
java -cp out citysurvival.Main
```

The same commands can also be used on Windows through Git Bash or a properly configured VS Code terminal.

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
