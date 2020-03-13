import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static BufferedWriter writer;

    public static int binaryToDecimal(int[] binary){
        int decimalValue = 0;

        for(int i=0; i<binary.length; i++){
            decimalValue+=binary[i]*(Math.pow(2, i));
        }

        return decimalValue;
    }

    public static int[] decimalToBinary(int decimal){
        int ctr = 0;
        int x = decimal;
        while(x>0){
            x/=2;
            ctr++;
        }

        int[] binaryValue = new int[ctr];

        int i=0;
        while(decimal>0){
            binaryValue[i] = decimal%2;
            decimal/=2;
            i++;
        }

        return binaryValue;
    }

    public static int[] scaleTo(int[] array, int n){
        int[] arrayB = new int[n];

        for(int i=0; i<n; i++){
            if(i<array.length)
                arrayB[i]=array[i];
            else
                arrayB[i]=0;
        }

        return arrayB;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            writer = new BufferedWriter(new FileWriter("Results"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter two numbers");
        int x = scan.nextInt();
        int y = scan.nextInt();

        boolean check;
         if((x>0 && y>0)||(x<0 && y<0)){
            check = false;
         }
         else
             check = true;

        int[] number1 = decimalToBinary(Math.abs(x));
        int[] number2 = decimalToBinary(Math.abs(y));

        int len = Math.max(number1.length, number2.length);

        int[] n1 = scaleTo(number1, len);
//        for(int i=n1.length-1; i>=0; i--)
//            System.out.print(n1[i]+" ");
//        System.out.println();

        int[] n2 = scaleTo(number2, len);
//        for(int i=n2.length-1; i>=0; i--)
//            System.out.print(n2[i]+" ");
//        System.out.println();

        Multiply op1 = new Multiply(len);
        int[] product;
        if(x>=y)
            product = op1.multiply(n1, n2);
        else
            product = op1.multiply(n2, n1);

        try {

            System.out.println("PRODUCT :");
            writer.write("PRODUCT :");

            if(check) {
                System.out.println(-binaryToDecimal(product));
                writer.write(-binaryToDecimal(product));
                product = op1.complement(product);
            }
            else{
                System.out.println(binaryToDecimal(product));

                    writer.write(binaryToDecimal(product));
            }

            //writer.write(String.valueOf(product));

            for(int i=product.length-1; i>=0; i--){
                System.out.print(product[i]);
                writer.write(product[i]);
            }


            System.out.println();

            Divide op2 = new Divide(len, writer);
            op2.divide(n1, n2);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
