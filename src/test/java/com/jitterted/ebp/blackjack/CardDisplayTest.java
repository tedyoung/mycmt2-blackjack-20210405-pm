package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.fusesource.jansi.Ansi.ansi;

public class CardDisplayTest {
  private static final Rank DUMMY_RANK = Rank.TEN;

  @Test
  public void displayTenAsString() throws Exception {
    Card card = new Card(Suit.CLUBS, Rank.TEN);

    assertThat(card.display())
        .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
  }

  @Test
  public void displayNonTenAsString() throws Exception {
    Card card = new Card(Suit.SPADES, Rank.EIGHT);

    assertThat(card.display())
        .isEqualTo("[30m┌─────────┐[1B[11D│8        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        8│[1B[11D└─────────┘");
  }

  @Test
  public void suitOfHeartsOrDiamondsIsDisplayedInRed() throws Exception {
    // given a card with Hearts or Diamonds
    Card heartsCard = new Card(Suit.HEARTS, DUMMY_RANK);
    Card diamondsCard = new Card(Suit.DIAMONDS, DUMMY_RANK);

    // when we ask for its display representation
    String ansiRedString = ansi().fgRed().toString();

    // then we expect a red color ansi sequence
    assertThat(heartsCard.display())
        .contains(ansiRedString);
    assertThat(diamondsCard.display())
        .contains(ansiRedString);
  }

}
