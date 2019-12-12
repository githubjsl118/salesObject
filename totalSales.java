
/*
 * Filename: totalSales.java
 * Author: Justin Lee
 * Date: 11 December 2019
 * Description:  This will hold the price of all the objects
 *               and calculate the sales tax based ont hat
 *               correctly accounting for whether the item
 *               is imported or not
 */


import java.util.*;
import java.io.*;

public class totalSales{
   //because we calculate tax on domestic and import separately
   private double domesticTotal;
   private double importTotal;

   //default constructor here
   public totalSales(){
      domesticTotal = 0.0;
      importTotal = 0.0;
   }  

   public totalSales(double dt, double it){
      this.domesticTotal = dt;
      this.importTotal = it;

   }
   
   public void clear(){
      domesticTotal = 0.0;
      importTotal = 0.0;

   }

   //setters
   public void setdomesticTotal(double dt){
      this.domesticTotal = dt;
   }

   public void setimportTotal(double it){
      this.importTotal = it;
   }
  
   //methods to increment the total amounts
   public void incdomesticTotal(double dt){
      this.domesticTotal += dt;
   }

   public void incimportTotal(double it){
      this.importTotal += it;
   }

   public void incfromsales(salesObject saleso){
      if(saleso.getImport()){
         this.incimportTotal(saleso.getPrice());
      }
      else{
         this.incdomesticTotal(saleso.getPrice());
      }
   }

   //here we are calculating the tax and then we are going to print it
   //we can unit test this function

   public double calculateTax(){
      double taxfromdomestic = domesticTotal * 0.10;
      double taxfromimport = importTotal * 0.15;
      int twodecint = (int)(taxfromdomestic * 100);
      taxfromdomestic = (double)(twodecint / 100.0); //we round down nearest 0.01 ...
      twodecint = (int)(taxfromimport * 100);
      taxfromimport = (double)(twodecint / 100.0);

      //at this point taxfromdomestic and taxfromimport should be two digit ints... 
      double totaltax = taxfromdomestic + taxfromimport;
      
      //totaltax now needs to be rounded to the nearest 0.05 decimal spot.  
      //according to the specifications of the assignment, we are going to round up
    
      twodecint = (int)(totaltax * 100);
      if( (twodecint % 5) != 0) {
         twodecint += (5 - (twodecint %5 )); 
      }
      totaltax = (double)(twodecint / 100.0);
      //should be rounded now... we can unit test here  
      return totaltax;
   }

   public void taxandtotalprinter(){
      double salestax = this.calculateTax();
      double total = domesticTotal + importTotal + salestax;
      System.out.printf("Sales Taxes: %.2f\n", salestax);
      System.out.printf("Total: %.2f\n\n", total);

   }

}
