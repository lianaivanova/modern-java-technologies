package card;

public class GoldenCard extends Card {
    private static final double DISCOUNT = 0.15;

    public GoldenCard() {
        super();
    }

    public GoldenCard(String name) {
        super(name);
    }

    @Override
    public boolean executePayment(double cost) {
        if (cost < 0) {
            return false;
        }
        double leftAmount = getAmount() - (cost - cost * DISCOUNT);
        if (leftAmount >= 0) {
            setAmount(leftAmount);
            return true;
        }
        return false;
    }
}
