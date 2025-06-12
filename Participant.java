public abstract class Participant {
    protected Hand hndHand;

    public Participant() {
        hndHand = new Hand();
    }

    public void receiveCard(Card card) {
        hndHand.addCard(card);
    }

    public int getHandValue() {
        return hndHand.getValue();
    }

    public boolean hasBlackjack() {
        return hndHand.isBlackjack();
    }

    public boolean isBust() {
        return hndHand.isBust();
    }

    public void resetHand() {
        hndHand.clear();
    }
}
