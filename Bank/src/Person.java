import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;
    private String username;
    private String password;
    private boolean hasAccount;
    private Map<String, BankCard> cards;

    public Person(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.hasAccount = false;
        this.cards = new HashMap<>();
    }


    public void login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Успішний вхід.");
        } else {
            System.out.println("Неправильний логін або пароль.");
        }
    }

    public void createAccount() {
        this.hasAccount = true;
        System.out.println("Створено обліковий запис для " + this.name);
    }

    public void receiveCard(BankCard card) {
        this.cards.put(card.getCardNumber(), card);
    }

    public String getName() {
        return name;
    }


    public void viewAccountInformation(Account account) {
        if (this.hasAccount) {
            System.out.println("Інформація про обліковий запис:");
            System.out.println("Ім'я користувача: " + account.getUsername());
            System.out.println("Баланс: " + account.getBalance());
            System.out.println("Валюта: " + account.getCurrency());
            System.out.println("Статус власника: " + (account.getOwner() == this ? "Власник" : "Довірена особа"));
        } else {
            System.out.println("У вас немає облікового запису.");
        }
    }

    public void viewBankCardInformation(BankCard card) {
        if (this.cards.containsValue(card)) {
            System.out.println("Інформація про карточку:");
            System.out.println("Номер карти: " + card.getCardNumber());
            System.out.println("Призначена для облікового запису з ім'ям користувача: " + card.getLinkedAccount().getUsername());
            System.out.println("Статус власника: " + (card.getOwner() == this ? "Власник" : "Довірена особа"));
        } else {
            System.out.println("У вас немає доступу до цієї карточки.");
        }
    }

}

