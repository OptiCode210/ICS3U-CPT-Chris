public class Card {
    private int intValue;    // 1–13
    private int intSuit;     // 1–4

    public Card(int intValue, int intSuit) {
        this.intValue = intValue;
        this.intSuit = intSuit;
    }

    public int getIntValue() {
        return intValue;
    }

    public int getIntSuit() {
        return intSuit;
    }

    public int getBlackjackValue() {
        if (intValue >= 10) return 10;
        return intValue;
    }
}
