import java.util.*;
import java.io.*;



/* Filename: salesObject.java
 * Author: Justin Lee
 * Date: 10 December 2019
 * Description:  This will be the main driver for the program
 */


public class salesObject{
   private int quantity;
   private boolean import;
   private String name;
   private double price; 





   public salesObject() { //default constructor
      //we can leave this empty


   }
  
   //partial constructor is unnecessary as well 
   

   //this would be the full constructor 
   public salesObject(int q, boolean i, String n, double p){
      this.quantity = q;
      this.import = i;
      this.name = n;
      this.price = p;
   }

   public static void main(String args[]){
      File input1;
      StringTokenizer st;
      String line;
      String curr;
      
      int quantity;
      boolean import;
      String name;
      double price;      

      String inputtxt = "input"; 
       
      //we set up this for loop so that we can iterate through input1, input2, etc
      for(int i=1; i<= 3; i++){
         input1 = new File(inputtxt + i + ".txt");
         scan = new Scanner(input1);
 
         //this while loop will scan the file.  within the while loop, the first word
         //will be the quantity of the object while the second word will determine
         //if the item is imported or not and that is determined by checking if the
         //second word is "imported" or not.  therefore
         


         while(scan.hasNextLine()){
          
         //when we tokenize the string:
         //The first token will be the quantity
         //If the next token is "imported" then we can set the boolean to true
         //the next tokens until the token "at" will be the name of the product
         //the last token will be parsed into the a double and will be the price
         //of the item

            line = scan.nextLine(); //we need to tokenize this string
            st = new StringTokenizer(line);
            curr = st.nextToken(); //we know that the first token is the quantity
            quantity = Integer.parseInt(curr);
            import = false;  //by default we assume that item is not imported
            curr = st.nextToken(); //this is either part of the name or import toggle
            if(curr.equals("imported")){ //if it is the import toggle, set the toggle
               import = true;
               curr = st.nextToken(); 
            }
            //current token is now the first part of the name
            name = curr;
            curr = st.nextToken();
            while(!curr.equals("at")){
               name = name + " " + curr;
               curr = st.nextToken();
            }
            //curr aka current token should be "at" so we proceed to the next token
            curr = st.nextToken();
            //right now the curr string should be at the end, i.e we convert
            //the string to a double and that should give us the price
         }



      } //end  





   } //end main 



}
