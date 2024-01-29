# My Personal Project

My project takes the simple game of connect 4 and adds a spin to it where there is a UFO at the top of the
screen which will drop the coloured alien heads into to connect 4 frame based on their input of what column they want
to drop it into. This will add a slight spin on the original game graphically.

This game will be used as a simple break for some competitive fun, the same way other pre-downloaded games like
solitaire and chess provide a simple break for users.

Connect 4 has always been a fun game to play with friends and family, however it can get extremely repetitive, so
to combat its repetitive nature, I thought making a spin-off version will help preserve the fun of the original game
while providing a sense freshness everytime the game is being played.

## User Stories

A *bulleted* list:
- as a user I can see the current **win-loss** column of the two players playing after each game
- as a user I can add a name for each player playing
- as a user I can restart the current game without having to quit the application
- as a user I can choose how many wins a player needs for the game to be over
- as a user I can save the current state of the game
- as a user I can load in the previously saved state of game and continue the game


# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking on insert (will add different coloured aliens alternatively to the board at given column).
- You can generate the second required action related to adding Xs to a Y by clicking reset on the panel (will reset the whole board).
- You can locate my visual component by finishing a round/ match, and it will appear on the JOptionPane.
- You can save the state of my application by clicking the save button at the top of the panel.
- You can reload the state of my application by using the dropdown menu when the game first loads.
- You can tell whose turn it is if their name is highlighted
- Game is started through the PrimaryGUI class in ui

Task2:
Representative sample for Event Log:
- When dropping an alien into the board (X's into Y): Thu Apr 13 15:12:08 PDT 2023
  Alien has been dropped successfully, column: 0
- When board is reset (removes X's from Y): Thu Apr 13 15:12:12 PDT 2023
  Board has been reset
- When a game is won by a player: Thu Apr 13 15:12:23 PDT 2023
  Horizontal match found for color: red
  Thu Apr 13 15:12:23 PDT 2023
  Vedant wins the game
  Thu Apr 13 15:12:23 PDT 2023
  VedantWin:1, Loss:0
  Thu Apr 13 15:12:23 PDT 2023 
  BenWin:0, Loss:1
  Thu Apr 13 15:12:23 PDT 2023
- When a game is loaded:
  Thu Apr 13 15:18:51 PDT 2023
  File has been read
- When a game is saved: Thu Apr 13 15:18:56 PDT 2023
  File has been saved Successfully

Task3:

By implementing the Iterable interface, the Board class can be looped through by other classes without direct access to
its elements. Furthermore, the implementation details of the Board class are abstracted away from other classes,
allowing them to iterate without knowledge of the underlying collection type used, such as ArrayList or Set.