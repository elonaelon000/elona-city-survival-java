# City Survival Game

A Java Swing survival game set in a tile-based city environment. The player explores the map, collects useful items, fights enemies, manages health and inventory, and tries to survive long enough to reach the objective.

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
├── README.md
└── .gitignore
```

## Requirements

- Java 17 or newer
- Java Swing, included with the standard JDK

## Compile and Run

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
