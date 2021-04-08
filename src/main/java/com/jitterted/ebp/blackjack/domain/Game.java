package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;

public class Game {

  private final Deck deck;
  private final GameMonitor gameMonitor;

  private final Hand dealerHand = new Hand();
  private final Hand playerHand = new Hand();
  private boolean playerDone;

  public Game() {
    this(new Deck());
  }

  public Game(Deck deck) {
    this.deck = deck;
    this.gameMonitor = game -> {};
  }

  public Game(Deck deck, GameMonitor gameMonitor) {
    this.deck = deck;
    this.gameMonitor = gameMonitor;
  }

  public void initialDeal() {
    dealRoundOfCards();
    dealRoundOfCards();
    updatePlayerDoneTo(playerHand.isBlackjack());
  }

  public void playerHits() {
    // Guard: throw exception if playerDone == true
    playerHand.drawFrom(deck);
    updatePlayerDoneTo(playerHand.isBusted());
  }

  public void playerStands() {
    // Guard: throw exception if playerDone == true
    dealerTurn();
    updatePlayerDoneTo(true);
  }

  private void updatePlayerDoneTo(boolean playerDone) {
    this.playerDone = playerDone;
    if (playerDone) {
      gameMonitor.roundCompleted(this);
    }
  }

  public boolean isPlayerDone() {
    return playerDone;
  }

  private void dealRoundOfCards() {
    // why: players first because this is the rule
    playerHand.drawFrom(deck);
    dealerHand.drawFrom(deck);
  }

  public GameOutcome determineOutcome() {
    // Guard: throw exception if isPlayerDone == false
    if (playerHand.isBlackjack()) {
      return GameOutcome.PLAYER_WINS_BLACKJACK;
    }
    if (playerHand.isBusted()) {
      return GameOutcome.PLAYER_BUSTED;
    }
    if (dealerHand.isBusted()) {
      return GameOutcome.DEALER_BUSTED;
    }
    if (playerHand.beats(dealerHand)) {
      return GameOutcome.PLAYER_BEATS_DEALER;
    }
    if (playerHand.pushes(dealerHand)) {
      return GameOutcome.PLAYER_PUSHES;
    }
    return GameOutcome.PLAYER_LOSES;
  }

  private void dealerTurn() {
    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    if (!playerHand.isBusted()) {
      while (dealerHand.dealerMustDrawCard()) {
        dealerHand.drawFrom(deck);
      }
    }
  }

  // Query: snapshot cards() & value() and return Value Object
  public Hand playerHand() {
    return playerHand;
  }

  public Hand dealerHand() {
    return dealerHand;
  }
}
