# java_gamedev

A Java 2D game-dev practice project built with **Swing** (rendering via `JPanel`) featuring:

- a basic game loop (fixed FPS)
- tile/background rendering
- two players with keyboard controls
- simple health bar UI

## Controls

- **Player 1:** Arrow keys (↑ ↓ ← →)
- **Player 2:** WASD
- **Attack:** `J` (sets `basicAttack` flag in `KeyHandler`)

## Project layout

- `src/Main/` — main window, game loop, key handling
  - `Main.java` — creates the `JFrame` and starts the game thread
  - `GamePanel.java` — main loop + draw/update
  - `KeyHandler.java` — keyboard input state
- `src/Entity/` — entities (`Player`, etc.)
- `src/UI/` — UI widgets (health bar)
- `src/tile/` — tile manager / map rendering
- `res/` — images (backgrounds, sprites, etc.)
- `bin/` — compiled `.class` output (generated)

## Requirements

- JDK 8+ (recommended: JDK 17)

## Build + run (CLI)

From the project root:

```bash
# compile
javac -d bin src/Main/*.java src/Entity/*.java src/UI/*.java src/tile/*.java

# run (main class is src.Main.Main)
java -cp bin src.Main.Main
```

## Notes

- Assets are loaded from relative paths under `res/` (e.g. `res/Background/dead_forest.png`). Run from the repo root so those paths resolve.

## TODO

- Add a proper build tool (Gradle/Maven) so builds are one command.
- Package resources into the classpath instead of file paths.
- Add a short gameplay description + screenshots.
