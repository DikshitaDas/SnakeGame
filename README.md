# Snake Game in Java

This project implements the classic Snake game using Java Swing for the graphical user interface and basic game logic.

## Overview

The Snake Game consists of a snake that moves around the board, eating food to grow longer. The game ends if the snake collides with itself or the board boundaries.

## Features

- **Snake Movement:** Use arrow keys to control the snake's direction (up, down, left, right).
- **Food Generation:** Randomly generates food on the board for the snake to eat.
- **Score Tracking:** Displays the current score (length of the snake) during gameplay.
- **Game Over Condition:** Ends the game if the snake collides with itself or the board boundaries.

## Technologies Used

- **Java:** Core programming language.
- **Java Swing:** For creating the graphical user interface.
- **Random:** To generate food at random positions on the board.

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/DikshitaDas/SnakeGame.git
   cd SnakeGame
   ```

2. **Compile and run:**

   Compile the project using a Java compiler. You can use your preferred IDE or command-line tools.

   ```bash
   javac *.java
   ```

   Run the game:

   ```bash
   java App
   ```

3. **Game Controls:**

   - Use the **arrow keys** to navigate the snake:
     - Up arrow: Move up
     - Down arrow: Move down
     - Left arrow: Move left
     - Right arrow: Move right

4. **Gameplay:**

   - The snake starts with a default length and moves at a constant speed.
   - Eat the red food blocks to grow longer.
   - Avoid colliding with the snake's own body or the edges of the board.
   - The game ends when the snake collides, and your final score is displayed.

5. **Customization:**

   - Adjust the board size and game speed by modifying parameters in the `SnakeGame` class constructor (`boardWidth`, `boardHeight`, `Timer` delay).

## Future Enhancements

- Implement levels with increasing difficulty.
- Add sound effects and background music.
- Enable two-player mode using networking.

## Contributing

Contributions are welcome! Fork the repository, make improvements, and submit pull requests.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
