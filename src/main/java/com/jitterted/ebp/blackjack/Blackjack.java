package com.jitterted.ebp.blackjack;

import com.jitterted.ebp.blackjack.adapter.in.console.ConsoleGame;
import com.jitterted.ebp.blackjack.domain.Game;

public class Blackjack {

  // Startup Assembler/Bootstrap for the Application
  public static void main(String[] args) {
    Game game = new Game(); // initialize DOMAIN
    ConsoleGame consoleGame = new ConsoleGame(game); // in general: Entities aren't directly passed in to Adapters
    consoleGame.start();
  }
}
