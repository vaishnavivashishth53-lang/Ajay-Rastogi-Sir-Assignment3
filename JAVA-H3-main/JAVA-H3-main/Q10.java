class BankTransaction extends Thread {
    String transactionType;
    int amount;

    BankTransaction(String transactionType, int amount) {
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public void run() {
        System.out.println(transactionType
                + " transaction processed: ₹" + amount);
    }
}

public class Q10 {
    public static void main(String[] args)
            throws InterruptedException {

        BankTransaction lowValue =
                new BankTransaction("Low-value", 5000);

        BankTransaction highValue =
                new BankTransaction("High-value", 50000);

        lowValue.setPriority(Thread.MIN_PRIORITY);
        highValue.setPriority(Thread.MAX_PRIORITY);

        highValue.start();
        highValue.join();

        lowValue.start();
        lowValue.join();
    }
}