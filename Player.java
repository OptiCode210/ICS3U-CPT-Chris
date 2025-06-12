public class Player extends Participant {
    private int intGameBalance;

    public Player(int intStartBalance) {
        super();
        intGameBalance = intStartBalance;
    }

    public int getBalance() {
        return intGameBalance;
    }

    public void adjustBalance(int amount) {
        intGameBalance += amount;
    }
}
