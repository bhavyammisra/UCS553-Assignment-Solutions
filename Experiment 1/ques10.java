import java.util.Scanner;

public class ques10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        int num = n, reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        if (n == reversed) {
            System.out.println(n + " is a Palindrome.");
        } else {
            System.out.println(n + " is not a Palindrome.");
        }
        sc.close();
    }
}