import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть дію: 1 - Реєстрація, 2 - Вхід");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Введіть ім'я користувача:");
            String username = scanner.next();
            System.out.println("Введіть пароль:");
            String password = scanner.next();
            System.out.println("Введіть початковий баланс:");
            double initialBalance = scanner.nextDouble();
            System.out.println("Введіть валюту (USD, EUR, UAH):");
            String initialCurrency = scanner.next();

            BankCard registeredCard = Account.register(username, password, initialBalance, initialCurrency);
            Account loggedInAccount = Account.login(username, password);

            if (loggedInAccount != null) {
                System.out.println("Баланс: " + loggedInAccount.getBalance());
                if (registeredCard != null) {
                    System.out.println("Видано карточку " + username);
                    System.out.println("Обліковий запис зареєстровано.");
                    System.out.println("Номер карти: " + registeredCard.getCardNumber());
                    System.out.println("Баланс на карті: " + registeredCard.getLinkedAccount().getBalance());
                    System.out.println("Валюта на карті: " + registeredCard.getLinkedAccount().getCurrency());
                    System.out.println("Статус власника: " + (registeredCard.getOwner().equals(loggedInAccount) ? "Власник" : "Довірена особа"));
                }
            }
        } else if (choice == 2) {
            System.out.println("Введіть ім'я користувача:");
            String username = scanner.next();
            System.out.println("Введіть пароль:");
            String password = scanner.next();
            Account loggedInAccount = Account.login(username, password);

            if (loggedInAccount != null) {
                System.out.println("Баланс: " + loggedInAccount.getBalance());

                System.out.println("Введіть номер картки:");
                String cardNumber = scanner.next();
                loggedInAccount.viewCardInformation(cardNumber); // Показати інформацію про картку
            }
        }

        scanner.close();
    }

    // Метод для генерації номера картки
    private static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = (int) (Math.random() * 10);
            cardNumber.append(digit);
            if ((i + 1) % 4 == 0 && i < 15) {
                cardNumber.append("-");
            }
        }
        return cardNumber.toString();
    }
}
