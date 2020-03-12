package card;

public class StandardCard extends Card {

    public StandardCard() {
        super();
    }

    public StandardCard(String name) {
        super(name);
    }

    @Override
    public boolean executePayment(double cost) {
        double leftAmount = getAmount() - cost;
        if (leftAmount >= 0) {
            setAmount(leftAmount);
            return true;
        }
        return false;
    }
}