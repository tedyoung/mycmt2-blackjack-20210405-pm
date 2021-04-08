package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

  @Test
  public void playerBeatsDealer() throws Exception {
    Deck playerBeatsDealerDeck = new StubDeck(Rank.QUEEN, Rank.EIGHT,
                                              Rank.TEN, Rank.JACK);
    Game game = new Game(playerBeatsDealerDeck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.determineOutcome())
        .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
  }

  @Test
  public void playerHitsAndGoesBustResultsInPlayerLoses() throws Exception {
    Deck playerHitsGoesBustDeck = StubDeck.playerHitsGoesBustDeck();
    Game game = new Game(playerHitsGoesBustDeck);
    game.initialDeal();

    game.playerHits();

    assertThat(game.determineOutcome())
        .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
  }

  @Test
  public void playerIsDealtBlackjackAndWinsBlackjack() throws Exception {
    Deck playerDealtBlackjack = StubDeck.playerDealtBlackjack();
    Game game = new Game(playerDealtBlackjack);
    game.initialDeal();

    assertThat(game.determineOutcome())
        .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
  }

  @Test
  public void playerStandsResultsInDealerDrawAdditionalCard() throws Exception {
    Deck dealerDrawsCardDeck = new StubDeck(Rank.TEN,  Rank.QUEEN,
                                            Rank.NINE, Rank.FIVE,
                                            Rank.SIX);
    Game game = new Game(dealerDrawsCardDeck);
    game.initialDeal();

    game.playerStands();

    assertThat(game.dealerHand().cards())
        .hasSize(3);
  }

}