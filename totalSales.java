
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
   private double edomesticTotal;
   private double eimportTotal;

   //default constructor here
   public totalSales(){
      domesticTotal = 0.0;
      importTotal = 0.0;
      edomesticTotal = 0.0;
      eimportTotal = 0.0;
   }  

   public totalSales(double dt, double it){
      this.domesticTotal = dt;
      this.importTotal = it;
      edomesticTotal = 0.0;
      eimportTotal = 0.0;

   }
   
   public void clear(){
      domesticTotal = 0.0;
      importTotal = 0.0;
      edomesticTotal = 0.0;
      eimportTotal = 0.0;
   }

   //setters
   public void setdomesticTotal(double dt){
      this.domesticTotal = dt;
   }

   public void setimportTotal(double it){
      this.importTotal = it;
   }
  
   public void seteimportTotal(double it){
      this.eimportTotal = it;
   }

   public void setedomesticTotal(double it){
      this.edomesticTotal = it;
   }
  
   //methods to increment the total amounts
   public void incdomesticTotal(double dt){
      this.domesticTotal += dt;
   }

   public void incimportTotal(double it){
      this.importTotal += it;
   }

   public void incedomesticTotal(double dt){
      this.edomesticTotal += dt;
   }

   public void inceimportTotal(double it){
      this.eimportTotal += it;
   }

   //in this method we have to check to see if the exempt variable
   //has been set in the parameter salesobject
   

   //potentially we can go ahead and have 4 categories for sales
   //imported / not imported and
   //exempt / not exempt and the two categories will combine 2x2 = 4
   public void incfromsales(salesObject saleso){
      if(saleso.getImport()){
         if(saleso.getExempt()){
            this.inceimportTotal(saleso.getPrice());
         }
         else{ //i.e not exempt
            this.incimportTotal(saleso.getPrice());
         }
      }
      else{
         if(saleso.getExempt()){
            this.incedomesticTotal(saleso.getPrice());
         }
         else{
            this.incdomesticTotal(saleso.getPrice());
         }
      }
   }

   //here we are calculating the tax and then we are going to print it
   //we can unit test this function

   public double calculateTax(){
      double taxfromdomestic = domesticTotal * 0.10;
      double taxfromimport = importTotal * 0.15;
      double taxfromeimport = eimportTotal * 0.05;
      int twodecint = (int)(taxfromdomestic * 100);
      taxfromdomestic = (double)(twodecint / 100.0); //we round down nearest 0.01 ...
      twodecint = (int)(taxfromimport * 100);
      taxfromimport = (double)(twodecint / 100.0);
      twodecint = (int)(taxfromeimport * 100);
      taxfromeimport = (double)(twodecint / 100.0);

      //at this point taxfromdomestic and taxfromimport should be two digit ints... 
      double totaltax = taxfromdomestic + taxfromimport + taxfromeimport;
      
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
      double total = domesticTotal + importTotal + edomesticTotal + eimportTotal + salestax;
      System.out.printf("Sales Taxes: %.2f\n", salestax);
      System.out.printf("Total: %.2f\n\n", total);

   }

}
