package card;

public abstract class Card {
    private String name;
    private double amount;

    public Card() {
        this.name = "Unknown";
        this.amount = 0;
    }

    public Card(String name) {
        this.name = name;
        this.amount = 0;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Card other = (Card) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Card [name=" + name + ", amount=" + amount + "]";
    }

    public abstract boolean executePayment(double cost);

}
