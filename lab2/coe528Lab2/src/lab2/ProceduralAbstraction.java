package lab2;

/**
 *
 * @author Romit Sagu
 */
public class ProceduralAbstraction {
    
    //Requires: None
    //Modifies: None
    //Effects: Returns the smallest positive integer n for which n!
    //         (i.e. 1*2*3*…*n) is greater than or equal to x, for positive
    //         integer x. Otherwise returns 1.
    public static int reverseFactorial(int x) {
        int y = 1; // Variables needed for the method
        int i;
        if (x > 0){ // Checks wheather the input is postive or not
            for (i = 2; y < x; i++){ // Checks the factorials until n! is greater than or equal to x
                y *= i;
            }
            return i - 1; // Returns the value of i where the loop ended
        }
        else{
            return 1; // if x is zero or negative it returns a 1
        }
    }
    
    //Requires: None
    //Modifies: None
    //Effects: If the matrix arr satisfies Nice property, prints the sum and
    //         returns true. Otherwise returns false.
    public static boolean isMatrixNice(int[][] arr) {
        int c = 0, d = 0, e = 0, f = 0; // Variables needed for the method
        
        if (!(arr.length == arr[0].length)){ // Checks wheather the matrix is a square or not
            return false;
        }
        
        //System.out.println("a = " + a);
        
        for (int i = 0; i < arr.length; i++) { // Getts the sum of a single row of values so the other values can be compare to it 
            c += arr[0][i];
        }
        
        //System.out.println("c = " + c);
        
        for (int j = 0; j < arr.length; j++) { // Checks the rows to make sure that all of the sums are equal to the refrence value c
            for (int k = 0; k < arr.length; k++) {
                d += arr[j][k];
            }
            //System.out.println("d" + j + " = " + d);
            if (d!=c){
                return false;
            }
            d = 0;
        }
        
        
        d = 0;
        for (int n = 0; n < arr.length; n++) { // Checks the columns to make sure that all of the sums are equal to the refrence value c
            for (int o = 0; o < arr.length; o++) {
                d += arr[o][n];
            }
            //System.out.println("d" + j + " = " + d);
            if (d!=c){
                return false;
            }
            d = 0;
        }
        
        
        for (int l = 0; l < arr.length; l++) { // Checks the diagonal starting at (0,0) to make sure that all of the sums are equal to the refrence value c
                e += arr[l][l];
        }
        //System.out.println("e = " + e);
        
        for (int m = 0; m < arr.length; m++) { // Checks the diagonal starting at the bottom left of the matrix to make sure that all of the sums are equal to the refrence value c
                f += arr[arr.length - m - 1][m];
        }
        
        //System.out.println("f = " + f);
        
        if (c==e && c==f){ // Checks the output of the diagonals and returns wheather they are equal to the refrence or not, the other cases all return false and end the method if they fail
            System.out.println("The sum of the matrix is: " + c); // Prints the sum that each diagonal/row/column are equal to
            return true;
        }
        else{
          return false;
        }
        
        
    }
    
    
    public static void main (String[] args){
        
        // Intitilizes test arrays for the isMatrixNice() mehod
        int [][] test = new int[][]{{2,7,6},{9,5,1},{4,3,8}};
        int [][] antiTest = new int[][]{{-3,1,0},{4,-3,4},{7,-9,0}};
        
        // Test cases for the reverseFactorial() method
        System.out.println("Reverse Factorial of 24 is:" + reverseFactorial(24));
        System.out.println("Reverse Factorial of 119 is:" + reverseFactorial(119));
        System.out.println("Reverse Factorial of 120 is:" + reverseFactorial(120));
        System.out.println("Reverse Factorial of 121 is:" + reverseFactorial(121));
        
        System.out.println("Ans to test: " + isMatrixNice(test)); // Tests the isMatrixNice() mehod
        System.out.println("Ans to antiTest: " + isMatrixNice(antiTest));
    }
}
