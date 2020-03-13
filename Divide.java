import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.IOException;

public class Divide {
    public int n;  //number of bits
    public int[] M;
    public int[] A;
    public int[] Q;
    BufferedWriter writer;

    public Divide(int n, BufferedWriter writer){
        this.n=n;
        this.writer = writer;
        M=new int[n+1];
        A=new int[n+1];
        Q=new int[n];
    }

    public int binaryToDecimal(int[] binary){
        int decimalValue = 0;

        for(int i=0; i<binary.length; i++){
            decimalValue+=binary[i]*(Math.pow(2, i));
        }

        return decimalValue;
    }

    public int[] complement(int[] a) {        ///TO FIND NEGATIVE NUMBERS, NUMBER KA COMPLEMENT KARKE + 1 karna h
        boolean check = false;

        for(int i=0; i<a.length; i++){
            if(check==true){
                if(a[i]==0)
                    a[i]=1;
                else
                    a[i]=0;
            }

            if(a[i]==1)
                check=true;
        }

        return a;
    }

    public int signbitofA(){
        return A[A.length-1];
    }

    public void addTWO(int[] X, int[] Y) {
        int rem = 0;
        int n = Math.max(X.length, Y.length);

        for(int i=0; i<n; i++){
            int sum = X[i]+Y[i]+rem;
            if(sum==0){
                X[i]=0;
                rem=0;
            }
            else if(sum==1){
                X[i]=1;
                rem=0;
            }
            else if(sum==2){
                X[i]=0;
                rem = 1;
            }
            else{
                X[i]=1;
                rem=1;
            }
        }
    }

    public int[] leftshiftAQ(int A[],int Q[]){

        for(int i=A.length-1;i>0;i--){
            A[i]=A[i-1];
        }

        A[0]=Q[Q.length-1];

        for(int j=Q.length-1;j>0;j--){
            Q[j]=Q[j-1];
        }
        return A;
    }

    public void divide(int[] Q, int[] X ){
        int[] M = new int[X.length+1];

        for(int i=0; i<M.length; i++){
            A[i]=0;
        }

        M[X.length]=0;
        for(int i=0; i<=X.length-1; i++)
            M[i] = X[i];

        int[] Mc = M.clone();
        complement(Mc);

        while(n>0){
            //check sign bit of A
            if(signbitofA()==0){

                leftshiftAQ(A,Q);

                //A=A-M

                addTWO(A, Mc);

                if(signbitofA()==0){
                    Q[0]=1;
                }
                else if(signbitofA()==1){
                    Q[0]=0;
                }
            }

            else if(signbitofA()==1){

                leftshiftAQ(A,Q);

                //A=A+M
                addTWO(A, M);

                if(signbitofA()==0){
                    Q[0]=1;
                }
                else if(signbitofA()==1){
                    Q[0]=0;
                }
            }
            n--;
        }
        try{
            if(signbitofA()==0)
            {
                //A=A+M
                //addTWO(A, M);
                //Quotient=Q
                //Remainder=A
                writer.write("QUOTIENT : ");
                writer.write(String.valueOf(Q));
                System.out.println("QUOTIENT : ");

                for(int i=Q.length-1; i>=0; i--){
                    System.out.print(Q[i]);
                }

                System.out.println();

                writer.write("REMAINDER : ");
                writer.write(String.valueOf(A));
                System.out.println("REMAINDER : ");

                for(int i=A.length-1; i>=0; i--){
                    System.out.print(A[i]);
                }

                System.out.println();
            }
            else if(signbitofA()==1){
                //A=A-M
                addTWO(A,M);
                //Quotient=Q
                //Remainder=A
                writer.write("QUOTIENT : ");
                writer.write(String.valueOf(Q));
                System.out.println("QUOTIENT : ");
                for(int i=Q.length-1; i>=0; i--){
                    System.out.print(Q[i]);
                    writer.write(Q[i]);
                }

                System.out.println();
                writer.write("/n");
                System.out.println(binaryToDecimal(Q));

                writer.write("REMAINDER : ");
                writer.write(String.valueOf(A));
                System.out.println("REMAINDER : ");

                for(int i=A.length-1; i>=0; i--){
                    System.out.print(A[i]);
                    writer.write(A[i]);
                }

                System.out.println();
                System.out.println(binaryToDecimal(A));

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }


}
