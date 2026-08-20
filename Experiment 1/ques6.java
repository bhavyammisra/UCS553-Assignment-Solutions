import java.util.Scanner;

public class ques6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a = sc.nextInt();
        System.out.print("Enter second number: ");
        int b = sc.nextInt();

        int gcd = findGCD(a, b);
        int lcm = (a * b) / gcd;

        System.out.println("LCM of " + a + " and " + b + " = " + lcm);
        sc.close();
    }

    static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}