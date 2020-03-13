public class Multiply {
    int[] binary;
    int SC;

    public Multiply(int n){
        this.SC = n;
        binary = new int[2*n+2];
    }

    public int[] ashr(int[] array){

        for(int i = 0; i<array.length-1; i++){
            array[i] = array[i+1];
        }

        return array;
    }

    public int[] multiply(int[] a, int[] b){
        int[] BR = new int[a.length+1];
        int[] QR = new int[b.length+1];

        BR[a.length]=0;
        for(int i=0; i<BR.length-1; i++)
            BR[i]=a[i];

        QR[b.length]=0;
        for(int i=0; i<QR.length-1; i++)
            QR[i]=b[i];

        int[] tempA = BR.clone();
        int[] BRc = complement(tempA);

        for(int i=0; i<(2*SC+2); i++){
            if(i<QR.length)
                binary[i] = QR[i];
            else
                binary[i] = 0;
        }

        int Q = 0;
        int term = QR[0]*10+Q;

        SC++;

        while(SC>0){
            if(term==10){
                addThis(BRc);
            }
            else if(term==1) {
                addThis(BR);
            }

            Q = binary[0];
            ashr(binary);

            term = (binary[0]*10)+Q;

            SC--;
        }

        return binary;
    }

    public void addThis(int[] bRc) {
        int rem = 0;
        int n = binary.length/2;

        for(int i=n, j=0; i<binary.length; i++, j++){
            if(binary[i]==1 && bRc[j]==1){
                binary[i]=rem;
                rem = 1;
            }
            else if(binary[i]==1 || bRc[j]==1){
                if(rem==1)
                    binary[i]=0;
                else
                    binary[i]=1;
            }
            else if(binary[i]==0 && bRc[j]==0){
                binary[i]=rem;
                rem = 0;
            }

        }
    }

    public int[] complement(int[] a) {
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
}
