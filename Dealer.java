public class Dealer extends Participant {
    public Dealer() {
        super();
    }

    public boolean shouldHit() {
        return getHandValue() < 17;
    }
}
