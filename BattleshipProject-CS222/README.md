# Battleship Game Guide

---
# Objective
The goal of Battleship is to sink all enemy ships before they sink yours.

## Player vs Computer:
- You choose a preset ship layout
- The computer randomly places its ships
- You take turns attacking coordinates
- First to sink all enemy ships wins

## Player vs Player:
- Both players use the same board setup
- Players take turns entering coordinates
- First player to sink all ships wins

## How to enter moves:
You attack using grid coordinates:
A1, B5, J10

## Format:
- Letter (A–J) = Column
- Number (1–10) = Row
Example:
B3 → Column B, Row 3

## Game Feedback
When you attack, you will see:
- **Hit!** → You hit part of a ship
- **Miss!** → No ship at that location
- **Hit and sunk!** → You destroyed an entire ship (This feature is not working yet)
- **Already attacked!** → You picked that spot before
- **Out of bounds!** → Invalid coordinate

## Winning the Game
You win when: All enemy ships are destroyed

The game will display: You win!

# Class Overview

## AIGameDriver

- Contains the main method and runs the actual game.

- Manages the "Master Game Loop" (taking turns, checking for wins).

- Handles the pauses (Thread.sleep) and prints the UI to the console.

## PvpGameDriver

- Functions as a "pass and play" game.

- Very similar to AIGameDriver, but two human players are used.

- Utilizes pause() and clearScreen() methods to ensure players don't see each other's screens.

## Board

- Owns the 10x10 character grid array.

- Validates all math regarding the physical space (e.g., making sure ships don't overlap or go out of bounds).

- Acts as the "referee" by taking an attack coordinate, checking it against the ships, and returning the exact result (Hit, Miss, already attacked, or NUKE).

## PresetBoards
- A helper class completely dedicated to storing the hardcoded coordinates for your 5 board layouts.

- Keeps your AIGameDriver clean by hiding all the messy addShip setup lines in one place.

## ShipLogic
- An abstract base class that establishes the absolute bare minimum a piece needs to exist: a starting row and column.

- Forces any subclasses to figure out their own occupies(row, col) logic.

## Ship
- Extends ShipLogic.

- Calculates its own physical footprint using its size and horizontal/vertical orientation.

- Tracks its own "health" (how many times it has been hit) and knows when it is completely sunk.

## Nuke

- Extends Ship.

- A specialized 1x1 piece that acts identically to a ship for placement purposes, but triggers a sudden-death end to the game when hit.

## Player

- An abstract class that requires all players to have a target Board to look at.

- Forces subclasses to provide a chooseAttack() method, returning an array of [col, row].

## HumanPlayer

- Extends Player.

- Takes human-readable input from the scanner (like "B4"), translates it into computer-readable array indices (col 1, row 3), and makes sure it's actually on the map before passing it back to the game.

## AIPlayer

- Extends Player.

- Uses a Random generator to pick coordinates.

- Keeps a memory array to ensure it never wastes a turn guessing the exact same spot twice.


