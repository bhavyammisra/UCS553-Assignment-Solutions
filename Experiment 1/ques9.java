import java.util.Scanner;

public class ques9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter base: ");
        double base = sc.nextDouble();
        System.out.print("Enter exponent (non-negative integer): ");
        int exp = sc.nextInt();

        double result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }

        System.out.println(base + " ^ " + exp + " = " + result);
        sc.close();
    }
}