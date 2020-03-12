import card.Card;
import payment.PaymentInfo;

public class VirtualWallet implements VirtualWalletAPI {
    private Card[] cards;
    private int totalNumberOfCards;
    private static final int MAX_COUNT_CARDS = 5;

    public VirtualWallet() {
        this.cards = new Card[MAX_COUNT_CARDS];
        this.totalNumberOfCards = 0;
    }

    @Override
    public boolean registerCard(Card card) {
        if (card == null || card.getName() == null) {
            return false;
        }
        if (getIndexOfCard(card) != -1) {
            return false;
        }
        if (this.totalNumberOfCards < MAX_COUNT_CARDS) {
            this.cards[this.totalNumberOfCards++] = card;
            return true;
        }
        return false;
    }

    @Override
    public boolean executePayment(Card card, PaymentInfo paymentInfo) {
        if (card == null || card.getName() == null || paymentInfo == null || paymentInfo.getCost() < 0) {
            return false;
        }
        if (getIndexOfCard(card) != -1) {
            return this.cards[getIndexOfCard(card)].executePayment(paymentInfo.getCost());
        }
        return false;
    }

    @Override
    public boolean feed(Card card, double amount) {
        if (amount < 0 || card == null || card.getName() == null) {
            return false;
        }
        if (getIndexOfCard(card) != -1) {
            Card currCard = this.cards[getIndexOfCard(card)];
            currCard.setAmount(currCard.getAmount() + amount);
            return true;
        }
        return false;
    }

    @Override
    public Card getCardByName(String name) {
        if (name == null || this.totalNumberOfCards == 0) {
            return null;
        }
        for (Card el : this.cards) {
            if (el != null && el.getName() != null && name.equals(el.getName())) {
                return el;
            }
        }
        return null;
    }


    @Override
    public int getTotalNumberOfCards() {
        return this.totalNumberOfCards;
    }

    private int getIndexOfCard(Card card) {
        if (card == null) {
            return -1;
        }
        for (int i = 0; i < this.totalNumberOfCards; i++) {
            if (this.cards[i].getName().equals(card.getName())) {
                return i;
            }
        }
        return -1;
    }
}