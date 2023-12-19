# Fifteen Puzzle Solver

## Description
The Fifteen Puzzle Solver is a Java application designed to solve the classic 15-puzzle, a sliding puzzle consisting of a frame of numbered square tiles in random order with one tile missing. The goal is to place the tiles in order by making sliding moves that use the empty space.

## Features
- Solves any solvable instance of the Fifteen Puzzle.
- Efficient algorithm implementation for quick solutions.
- Command-line interface for easy interaction.
- Input through text files for puzzle configurations.
- Guaranteed to solve puzzles up to 7x7 in size, and capable of solving easier configurations of 8x8 to 9x9 boards.

## Getting Started

### Prerequisites
- Java JDK 8 or higher installed.

### Installation
1. Clone the repository or download the source code.
2. Navigate to the directory containing the source files.

### Usage
1. Prepare an input file with the puzzle configuration. The first line should indicate the size of the puzzle (e.g., `3` for a 3x3 puzzle), followed by the puzzle rows, with numbers separated by spaces and `0` representing the empty space.

    Example of `input.txt` for a 3x3 puzzle:
    ```
    3
    5 1 2
    8 0 3
    4 6 7
    ```

2. Run the solver using the command line:
    ```
    java fifteenpuzzle.Solver input.txt output.txt
    ```
   Here, `input.txt` is your input file, and `output.txt` is where the solution will be written.

3. The solution will be written to the specified output file.

## Components
- `Solver.java`: The main class that reads the puzzle configuration, solves it, and writes the solution.
- `Vertex.java`: A class representing a state of the puzzle.
- `BoardGen.java`: Utility class for generating board configurations.

## Performance
- This solver is optimized to handle puzzles up to 7x7 size efficiently.
- It can also solve simpler puzzles of sizes 8x8 and 9x9, although the solution time may vary based on the complexity of the puzzle configuration.
