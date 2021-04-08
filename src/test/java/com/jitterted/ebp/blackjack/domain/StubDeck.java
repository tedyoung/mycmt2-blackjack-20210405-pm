package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class StubDeck extends Deck {
  private static final Suit DUMMY_SUIT = Suit.HEARTS;
  private final ListIterator<Card> iterator;

  public StubDeck(Rank... ranks) {
    List<Card> cards = new ArrayList<>();
    for (Rank rank : ranks) {
      cards.add(new Card(DUMMY_SUIT, rank));
    }
    this.iterator = cards.listIterator();
  }

  public StubDeck(List<Card> cards) {
    iterator = cards.listIterator();
  }

  public static StubDeck playerHitsGoesBustDeck() {
    return new StubDeck(Rank.QUEEN, Rank.EIGHT,
                        Rank.TEN, Rank.FOUR,
                        Rank.THREE);
  }

  public static StubDeck playerHitsDoesNotBust() {
    return new StubDeck(Rank.TEN, Rank.TWO,
                        Rank.EIGHT, Rank.FIVE,
                        Rank.TWO);
  }

  public static StubDeck playerDealtBlackjack() {
    return new StubDeck(Rank.QUEEN, Rank.EIGHT,
                        Rank.ACE, Rank.JACK);
  }

  @Override
  public Card draw() {
    return iterator.next();
  }
}
