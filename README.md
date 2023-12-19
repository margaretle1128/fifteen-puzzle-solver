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

### Compilation and Running the Solver
To compile and run the solver, follow these steps:

1. Open a terminal or command prompt.
2. Change directory to the `src` folder where the source files are located:
   ```
   cd src
   ```
3. Compile the `Solver` class (and any other required classes):
   ```
   javac fifteenpuzzle/Solver.java
   ```
4. Run the solver using the command line, specifying the input and output file paths:
   ```
   java fifteenpuzzle.Solver input.txt output.txt
   ```
   Replace `input.txt` with the path to your input file and `output.txt` with the path where you want the solution to be saved.

### Usage
1. Prepare an input file with the puzzle configuration. The first line should indicate the size of the puzzle (e.g., `3` for a 3x3 puzzle), followed by the puzzle rows, with numbers separated by spaces and `0` representing the empty space.

    Example of `input.txt` for a 3x3 puzzle:
    ```
    3
    5 1 2
    8 0 3
    4 6 7
    ```

2. Follow the compilation and running instructions above to get the solution.

## Components
- `Solver.java`: The main class that reads the puzzle configuration, solves it, and writes the solution.
- `Vertex.java`: A class representing a state of the puzzle.
- `BoardGen.java`: Utility class for generating board configurations.

## Performance
- This solver is optimized to handle puzzles up to 7x7 size efficiently.
- It can also solve simpler puzzles of sizes 8x8 and 9x9, although the solution time may vary based on the complexity of the puzzle configuration.

## Implementation Details
The Fifteen Puzzle Solver uses an A* search algorithm for efficient puzzle solving. Key components include:

- **A* Algorithm**: Employs a priority queue for pathfinding, with f-values guiding the search.
- **Heuristic Function**: Varies based on puzzle size. Uses Manhattan distance for smaller boards and combines it with row/column conflicts and Euclidean distance for larger ones.
- **Classes**:
   - `Vertex`: Represents puzzle states, including the board configuration, movement, parent vertex, and heuristic value.
   - `Solver`: Manages input processing, goal board generation, and implements the A* algorithm.

This design allows the solver to effectively handle puzzles up to 7x7 and simpler configurations of larger puzzles.
