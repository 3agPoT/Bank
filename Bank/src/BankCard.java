public class BankCard {
    private String cardNumber;
    private Account linkedAccount;
    private Person owner;

    public BankCard(String cardNumber, Account linkedAccount, Person owner) {
        this.cardNumber = cardNumber;
        this.linkedAccount = linkedAccount;
        this.owner = owner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Account getLinkedAccount() {
        return linkedAccount;
    }

    public void deposit(double amount) {
        linkedAccount.deposit(amount, linkedAccount.getCurrency());
    }

    public void withdraw(double amount) {
        linkedAccount.withdraw(amount, linkedAccount.getCurrency());
    }


    public Person getOwner() {
        return owner;
    }

    public void viewCardInformation() {
        System.out.println("Інформація про карточку:");
        System.out.println("Номер карти: " + this.getCardNumber());
        System.out.println("Баланс на карті: " + this.getLinkedAccount().getBalance());
        System.out.println("Валюта на карті: " + this.getLinkedAccount().getCurrency());
        System.out.println("Статус власника: " + (this.getOwner().equals(this.getLinkedAccount().getOwner()) ? "Власник" : "Довірена особа"));
    }
}
