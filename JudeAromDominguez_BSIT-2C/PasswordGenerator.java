import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    
    // Character sets for password generation
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String finalPassword = "";
        
        // Display header
        System.out.println("\nName: Jude Arom F. Dominguez");
        System.out.println("ID Number: 2024304258");
        System.out.println("------------------------------");
        System.out.println("=== Random Password Generator ===\n");
        
        // Get password length with failsafe
        int length = 12;
        System.out.print("Enter password length (8-32): ");
        
        try {
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
                
                if (length < 8 || length > 32) {
                    System.out.println("\nInvalid length. Using default length of 12.");
                    length = 12;
                }
            } else {
                scanner.next(); // Clear the invalid input
                System.out.println("\nInvalid input. Using default length of 12.");
                length = 12;
            }
        } catch (Exception e) {
            System.out.println("\nInvalid input. Using default length of 12.");
            length = 12;
        }
        
        // Get character options
        System.out.print("\nInclude uppercase letters? (y/n): ");
        boolean useUpper = scanner.next().toLowerCase().charAt(0) == 'y';
        
        System.out.print("\nInclude lowercase letters? (y/n): ");
        boolean useLower = scanner.next().toLowerCase().charAt(0) == 'y';
        
        System.out.print("\nInclude digits? (y/n): ");
        boolean useDigits = scanner.next().toLowerCase().charAt(0) == 'y';
        
        System.out.print("\nInclude special characters? (y/n): ");
        boolean useSpecial = scanner.next().toLowerCase().charAt(0) == 'y';
        
        // Build character pool
        StringBuilder charPool = new StringBuilder();
        if (useUpper) charPool.append(UPPERCASE);
        if (useLower) charPool.append(LOWERCASE);
        if (useDigits) charPool.append(DIGITS);
        if (useSpecial) charPool.append(SPECIAL);
        
        // Check if at least one option is selected
        if (charPool.length() == 0) {
            System.out.println("\nNo character types selected. Using all character types.");
            charPool.append(UPPERCASE).append(LOWERCASE).append(DIGITS).append(SPECIAL);
        }
        
        // Generate password with approval loop
        String password;
        boolean approved = false;
        
        while (!approved) {
            password = generatePassword(length, charPool.toString());
            
            // Display result
            System.out.println("\nResult: Your generated password is: " + password);
            
            // Ask for approval
            System.out.print("\nDo you approve this password? (y/n): ");
            char response = scanner.next().toLowerCase().charAt(0);
            
            if (response == 'y') {
                approved = true;
                finalPassword = password;
            } else {
                System.out.println("\nGenerating a new password...");
            }
        }
        
        // Clear screen and show final result
        clearScreen();
        System.out.println("\nName: Jude Arom F. Dominguez");
        System.out.println("ID Number: 2024304258");
        System.out.println("------------------------------");
        System.out.println("Result: Your generated password is: " + finalPassword + "\n");
        
        scanner.close();
    }
    
    private static void clearScreen() {
        try {
            final String os = System.getProperty("os.name");
            
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clearing fails, just print newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    private static String generatePassword(int length, String charPool) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charPool.length());
            password.append(charPool.charAt(index));
        }
        
        return password.toString();
    }
}
