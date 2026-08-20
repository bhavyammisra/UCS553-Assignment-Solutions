import java.util.Scanner;

public class ques8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        int num = Math.abs(n);
        int reversed = 0;

        while (num != 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }

        if (n < 0) {
            reversed = -reversed;
        }

        System.out.println("Reversed number = " + reversed);
        sc.close();
    }
}
