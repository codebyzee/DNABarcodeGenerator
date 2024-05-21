import java.util.*;

public class DNABarcodeGenerator { 
    
    private static int barcodeLength; // variables for barcode length and noOfsequences
    private static int noOfSequences;

    //setter for barcode length
    public static void setBarcodeLength(int length) { 
        barcodeLength = length; 
    }

    //setter for number of Sequences
    public static void setNoOfBarcodeSequence(int number) { 
        noOfSequences = number;
    }

    //generates a barcode of a specific length 
    public static String generateBarcode() {
        Random random = new Random();
        StringBuilder barcode = new StringBuilder();  //Using the stringbuilder for more flexible string manipulation

        for (int i = 0; i < barcodeLength; i++) { 
            int result = random.nextInt(4); //randomly generate a number from 0-3 
            switch (result) { //switch statement to handle the logic of appending the nucleotides
                case 0: barcode.append("A"); break;
                case 1: barcode.append("T"); break;
                case 2: barcode.append("G"); break;
                case 3: barcode.append("C"); break;
            }
        }
        return barcode.toString(); //return to String type 

       

     }

     public static boolean isRestrictedList(String barcode) { 
        String[] restrictedList = {"ACCGGT", "GGCGCGCC", "GGATCC", "CCTGCAGG"}; //store restricted list in array 
        for(int i = 0; i < restrictedList.length; i++)  { 
            if (barcode.equals(restrictedList[i])) {//iterate through array and find matches 
                return true;
             } 
        }

        return false; 

     }

     public static boolean validateGCCount(String barcode){ 
        int count = 0;//initalize a count variable for g&c nucleotides 
        for (int i = 0; i < barcode.length(); i++)
            if(barcode.charAt(i) == 'C' || barcode.charAt(i) == 'G')
                count++; // increments if character is g or c 
        if (count >= barcode.length() * 0.4 && count <= barcode.length() * 0.6)
            return true;  //returns true if g&c count is between 40 and 60 % 
        return false; 
     }

    public static boolean isRedundantBarcode(String barcode) { 
        for(int i = 0; i < barcode.length()-2; i++) {
            if(barcode.charAt(i) == barcode.charAt(i + 1) && barcode.charAt(i) == barcode.charAt(i+2)) //checks if three characters are redundant
                return false;  

        }
        return true;
    }
     
    
    
    

    public static void main(String[] args){
        //Zuriel Nwankwo
        Scanner input = new Scanner(System.in); //Scanner 
        System.out.println("How many sequences of DNA barcodes you would like to generate?");
        int n = input.nextInt(); // input of noOfbarcodes
        setNoOfBarcodeSequence(n);

        System.out.println("What is the length of the DNA barcode?");
        int l = input.nextInt(); //input of length 
     

        setBarcodeLength(l);

        String[] barcodeArray = new String[noOfSequences]; //barcode Array of length n 
        int count = 0; 
        while(count < noOfSequences) { 
            String barcode = generateBarcode();
            if (isRestrictedList(barcode) == false && isRedundantBarcode(barcode) == true && validateGCCount(barcode) == true) { //validates barcode 
                barcodeArray[count] = barcode; // adds it to array 
                count++;//
            }
        }
        for(int i = 0; i < barcodeArray.length; i++)
            System.out.println("barcode" + (i+1) + ": "+ barcodeArray[i]);//prints barcodes


        input.close();
    }

}
