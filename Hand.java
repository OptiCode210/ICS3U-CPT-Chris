import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> lstCards;

    public Hand() {
        lstCards = new ArrayList<>();
    }

    public void addCard(Card card) {
        lstCards.add(card);
    }

    public int getValue() {
        int intSum = 0;
        int intAceCount = 0;
        for (Card card : lstCards) {
            int intVal = card.getBlackjackValue();
            if (card.getIntValue() == 1) {
                intAceCount++;
            }
            intSum += intVal;
        }
        for (int i = 0; i < intAceCount; i++) {
            if (intSum + 10 <= 21) {
                intSum += 10;
            }
        }
        return intSum;
    }

    public boolean isBlackjack() {
        return lstCards.size() == 2 && getValue() == 21;
    }

    public boolean isBust() {
        return getValue() > 21;
    }

    public void clear() {
        lstCards.clear();
    }

    public List<Card> getCards() {
        return lstCards;
    }
}
