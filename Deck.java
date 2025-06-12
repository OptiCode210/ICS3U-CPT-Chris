public class Deck {
    private List<Card> lstCards;

    public Deck() {
        lstCards = new ArrayList<>();
        for (int suit = 1; suit <= 4; suit++) {
            for (int value = 1; value <= 13; value++) {
                lstCards.add(new Card(value, suit));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(lstCards);
    }

    public Card dealCard() {
        if (lstCards.isEmpty()) {
            // refill and reshuffle
            for (int suit = 1; suit <= 4; suit++) {
                for (int value = 1; value <= 13; value++) {
                    lstCards.add(new Card(value, suit));
                }
            }
            shuffle();
        }
        return lstCards.remove(0);
    }
}
