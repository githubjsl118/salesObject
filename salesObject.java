import java.util.*;
import java.io.*;



/* Filename: salesObject.java
 * Author: Justin Lee
 * Date: 10 December 2019
 * Description:  This will be the main driver for the program
 */


public class salesObject{
  
   //we instantiate local variables here, this is because we are assuming the inputs
   //are robust therefore we are going to assume that these variables are correctly set
   //at each iteration of the while loop within the for loop
   private int quantity;
   private boolean imported;
   private String name;
   private double price; 





   public salesObject() { //default constructor
      //we can leave this empty


   }
  
   //partial constructor is unnecessary as well 
   

   //this would be the full constructor 
   public salesObject(int q, boolean i, String n, double p){
      this.quantity = q;
      this.imported = i;
      this.name = n;
      this.price = p;
   }

   //getters (we do not need setters) (potentially we do not need this
   public int getQuantity(){
      return this.quantity;
   }

   public boolean getImport(){
      return this.imported;
   }
   
   public String getName(){
      return this.name;
   }
   
   public double getPrice(){
      return this.price;
   } 


   //this method will be used to print info from the salesObject
   //this method can be unit tested
   public void printInfo(){
      System.out.printf("%d ", this.quantity);
      //determine if we print imported
      if(this.imported){
         System.out.printf("imported ");
      }
      System.out.printf("%s: %.2f\n", this.name, this.price);
         
   }

   public static void main(String[] args){
      File input1;
      StringTokenizer st;
      String line;
      String curr;
      
      int quantity;
      boolean imported;
      String name;
      double price;      
      totalSales salesTotal = new totalSales();
      String inputtxt = "input"; 
      Scanner scan;

 
      //we set up this for loop so that we can iterate through input1, input2, etc
     
      //we now have to separate the output based on which input file it is from

      for(int i=1; i<= 3; i++){
         System.out.printf("Output %d:\n", i);
         input1 = new File(inputtxt + i + ".txt");
         try{
            scan = new Scanner(input1);
         }
         catch(Exception e){return; } //we are assuming that the input is robust. 
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
            imported = false;  //by default we assume that item is not imported
            curr = st.nextToken(); //this is either part of the name or import toggle
            if(curr.equals("imported")){ //if it is the import toggle, set the toggle
               imported = true;
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
 
            price = Double.parseDouble(curr); //now the price variable set

            //we can now instantiate the salesObject
            salesObject item = new salesObject(quantity, imported, name, price);
           
            //we can print the item line here;
            item.printInfo();
            
            //we now need to add the current item price to the total sales price
            salesTotal.incfromsales(item); 
         } //end while
         salesTotal.taxandtotalprinter();
         salesTotal.clear();
      } //end for 

      //we can now print the sales tax and the total price
      return;
   } //end main 



}
