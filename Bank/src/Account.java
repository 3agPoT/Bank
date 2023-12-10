import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Account {
    private double balanceInUSD;
    private double balanceInEUR;
    private double balanceInUAH;
    private String activeCurrency;
    private boolean hasAccount;
    private String username;
    private String password;
    private static Map<String, Account> accounts = new HashMap<>();
    private Map<String, BankCard> cards = new HashMap<>();
    private Person owner;
    private double balance;


    public Account(String username, String password, double initialBalance, String initialCurrency, String cardNumber) {
        this.username = username;
        this.password = password;
        this.hasAccount = true;

        this.balanceInUSD = 0;
        this.balanceInEUR = 0;
        this.balanceInUAH = 0;

        switch (initialCurrency) {
            case "USD":
                this.balanceInUSD = initialBalance;
                break;
            case "EUR":
                this.balanceInEUR = initialBalance;
                break;
            case "UAH":
                this.balanceInUAH = initialBalance;
                break;
            default:
                throw new IllegalArgumentException("Непідтримувана валюта");
        }
        this.activeCurrency = initialCurrency;
        this.cards.put(cardNumber, new BankCard(cardNumber, this, this.owner));
    }


    public double getBalance() {
        switch (activeCurrency) {
            case "USD":
                return balanceInUSD;
            case "EUR":
                return balanceInEUR;
            case "UAH":
                return balanceInUAH;
            default:
                throw new IllegalStateException("Невідома валюта");
        }
    }

    public void deposit(double amount, String currency) {
        switch (currency) {
            case "USD":
                balanceInUSD += amount;
                break;
            case "EUR":
                balanceInEUR += amount;
                break;
            case "UAH":
                balanceInUAH += amount;
                break;
            default:
                throw new IllegalArgumentException("Непідтримувана валюта");
        }
    }

    public void withdraw(double amount, String currency) {
        switch (currency) {
            case "USD":
                if (balanceInUSD >= amount) {
                    balanceInUSD -= amount;
                } else {
                    throw new IllegalArgumentException("Недостатньо коштів на рахунку");
                }
                break;
            case "EUR":
                if (balanceInEUR >= amount) {
                    balanceInEUR -= amount;
                } else {
                    throw new IllegalArgumentException("Недостатньо коштів на рахунку");
                }
                break;
            case "UAH":
                if (balanceInUAH >= amount) {
                    balanceInUAH -= amount;
                } else {
                    throw new IllegalArgumentException("Недостатньо коштів на рахунку");
                }
                break;
            default:
                throw new IllegalArgumentException("Непідтримувана валюта");
        }
    }



    public void setActiveCurrency(String currency) {
        if (currency.equals("USD") || currency.equals("EUR") || currency.equals("UAH")) {
            this.activeCurrency = currency;
        } else {
            throw new IllegalArgumentException("Непідтримувана валюта");
        }
    }

    public static Account login(String username, String password) {
        if (accounts.containsKey(username)) {
            Account acc = accounts.get(username);
            if (acc.password.equals(password)) {
                System.out.println("Успішно ввійшли в обліковий запис.");
                return acc;
            }
        }
        System.out.println("Неправильний логін або пароль.");
        return null;
    }

    public static BankCard register(String username, String password, double initialBalance, String initialCurrency) {
        if (!accounts.containsKey(username)) {
            String cardNumber = generateCardNumber();
            Account newAccount = new Account(username, password, initialBalance, initialCurrency, cardNumber);
            accounts.put(username, newAccount);
            System.out.println("Обліковий запис зареєстровано.");

            Person owner = new Person("Ім'я", "Прізвище", "Адреса");
            BankCard card = newAccount.issueCard(owner);
            return card;
        } else {
            System.out.println("Користувач з таким ім'ям вже існує.");
            return null;
        }
    }

    public BankCard issueCard(Person person) {
        if (this.hasAccount) {
            BankCard card = new BankCard(generateCardNumber(), this, person);
            person.receiveCard(card);
            System.out.println("Видано карточку " + person.getName());
            return card;
        } else {
            System.out.println("Ця особа не має облікового запису, тому не може видавати карточки.");
            return null;
        }
    }

    public void createCard(Person person) {
        if (this.hasAccount) {
            BankCard card = new BankCard(generateCardNumber(), this, person);
            person.receiveCard(card);
            System.out.println("Видано карточку " + person.getName());
            System.out.println("Номер карти: " + card.getCardNumber());
        } else {
            System.out.println("Ця особа не має облікового запису, тому не може видавати карточки.");
        }
    }

    public String getUsername() {
        return this.username;
    }

    public Person getOwner() {
        return this.owner;
    }

    public void viewCardInformation(String cardNumber) {
        if (cards.containsKey(cardNumber)) {
            BankCard card = cards.get(cardNumber);
            System.out.println("Інформація про карточку:");
            System.out.println("Номер карти: " + card.getCardNumber());
            System.out.println("Баланс на карті: " + card.getLinkedAccount().getBalance());
            System.out.println("Валюта на карті: " + card.getLinkedAccount().getCurrency());
            System.out.println("Статус власника: " + (card.getOwner().equals(this) ? "Власник" : "Довірена особа"));
        } else {
            System.out.println("У вас немає доступу до цієї карточки або введений номер карти невірний.");
        }
    }

    public Account(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            throw new IllegalArgumentException("Початковий баланс не може бути від'ємним.");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Сума поповнення має бути більше за 0.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && balance - amount >= 0) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Неприпустима сума для зняття коштів.");
        }
    }

    public Account(double initialBalance, String initialCurrency) {
        this.balance = initialBalance;
        this.activeCurrency = initialCurrency;
    }

    public String getPassword() {
        return this.password;
    }

    private static String generateCardNumber() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public void addCard(String cardNumber, BankCard card) {
        cards.put(cardNumber, card);
    }

    public void removeCard(String cardNumber) {
        cards.remove(cardNumber);
    }

    public String getCurrency() {
        return activeCurrency;
    }
}
