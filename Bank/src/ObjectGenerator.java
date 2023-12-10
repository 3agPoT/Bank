import java.util.ArrayList;
import java.util.List;

public class ObjectGenerator {
    public static void main(String[] args) {
        List<Person> people = generatePeople(5); // Генерація списку з 5 людей
        List<Account> accounts = generateAccounts(5); // Генерація списку з 5 облікових записів
        List<BankCard> cards = generateBankCards(5); // Генерація списку з 5 банківських карток
        List<Product> products = generateProducts(10); // Генерація списку з 10 товарів
    }

    // Метод для генерації списку людей
    private static List<Person> generatePeople(int numberOfPeople) {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            Person person = new Person("Ім'я_" + i, "Користувач_" + i, "Пароль_" + i);
            people.add(person);
        }
        return people;
    }

    // Метод для генерації списку облікових записів
    private static List<Account> generateAccounts(int numberOfAccounts) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < numberOfAccounts; i++) {
            String username = "Ім'я_користувача_" + i;
            String password = "Пароль_" + i;
            double initialBalance = 1000.0; // Приклад початкового балансу
            String initialCurrency = "USD"; // Приклад початкової валюти
            String cardNumber = "Номер_карти_" + i; // Приклад номеру картки

            Account account = new Account(username, password, initialBalance, initialCurrency, cardNumber);
            accounts.add(account);
        }
        return accounts;
    }

    // Метод для генерації списку банківських карток
    private static List<BankCard> generateBankCards(int numberOfCards) {
        List<BankCard> cards = new ArrayList<>();
        List<Account> allAccounts = generateAccounts(numberOfCards); // Приклад отримання облікових записів

        for (int i = 0; i < numberOfCards; i++) {
            String cardNumber = "Номер_карти_" + i;
            Account linkedAccount = allAccounts.get(i);
            Person owner = generatePeople(1).get(0); // Приклад отримання власника

            BankCard card = new BankCard(cardNumber, linkedAccount, owner);
            cards.add(card);
        }
        return cards;
    }

    // Метод для генерації списку товарів
    private static List<Product> generateProducts(int numberOfProducts) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numberOfProducts; i++) {
            String name = "Товар_" + i;
            String description = "Опис товару " + i;
            double price = 50.0 * (i + 1); // Приклад ціни, залежно від індексу

            Product product = new Product(name, description, price);
            products.add(product);
        }
        return products;
    }
}
