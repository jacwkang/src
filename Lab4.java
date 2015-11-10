// # lab4
// # 
// # CPE 315
// # Name:  Jeffrey Tang
// # Section:  1
// # Description:  This program is a MIPS Branch Predictor

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

public class Lab4{
  
   public static int encode(String reg){
   
      int i = 0;
      switch(reg){
         case "$pc": i = 0;
                    break;
         case "$0": i = 1;
                    break;
         case "$v0": i = 2;
                    break;
         case "$v1": i = 3;
                    break;
         case "$a0": i = 4;
                    break;
         case "$a1": i = 5;
                    break;
         case "$a2": i = 6;
                    break;
         case "$a3": i = 7;
                    break;
         case "$t0": i = 8;
                    break;
         case "$t1": i = 9;
                    break;
         case "$t2": i = 10;
                    break;
         case "$t3": i = 11;
                    break;
         case "$t4": i = 12;
                    break;
         case "$t5": i = 13;
                    break;
         case "$t6": i = 14;
                    break;
         case "$t7": i = 15;
                    break;
         case "$s0": i = 16;
                    break;
         case "$s1": i = 17;
                    break;
         case "$s2": i = 18;
                    break;
         case "$s3": i = 19;
                    break;
         case "$s4": i = 20;
                    break;
         case "$s5": i = 21;
                    break;
         case "$s6": i = 22;
                    break;
         case "$s7": i = 23;
                    break;
         case "$t8": i = 24;
                    break;
         case "$t9": i = 25;
                    break;
         case "$sp": i = 26;
                    break;
         case "$ra": i = 27;
                    break;
      }
      return i;
   }

   //for DUMP
   public static void decode(int code){
   
      switch(code){
         case 0: System.out.print("pc = ");
                    break;
         case 1: System.out.print("$0 = ");
                    break;
         case 2: System.out.print("$v0 = ");
                    break;
         case 3: System.out.print("$v1 = ");
                    break;
         case 4: System.out.print("$a0 = ");
                    break;
         case 5: System.out.print("$a1 = ");
                    break;
         case 6: System.out.print("$a2 = ");
                    break;
         case 7: System.out.print("$a3 = ");
                    break;
         case 8: System.out.print("$t0 = ");
                    break;
         case 9: System.out.print("$t1 = ");
                    break;
         case 10: System.out.print("$t2 = ");
                    break;
         case 11: System.out.print("$t3 = ");
                    break;
         case 12: System.out.print("$t4 = ");
                    break;
         case 13: System.out.print("$t5 = ");
                    break;
         case 14: System.out.print("$t6 = ");
                    break;
         case 15: System.out.print("$t7 = ");
                    break;
         case 16: System.out.print("$s0 = ");
                    break;
         case 17: System.out.print("$s1 = ");
                    break;
         case 18: System.out.print("$s2 = ");
                    break;
         case 19: System.out.print("$s3 = ");
                    break;
         case 20: System.out.print("$s4 = ");
                    break;
         case 21: System.out.print("$s5 = ");
                    break;
         case 22: System.out.print("$s6 = ");
                    break;
         case 23: System.out.print("$s7 = ");
                    break;
         case 24: System.out.print("$t8 = ");
                    break;
         case 25: System.out.print("$t9 = ");
                    break;
         case 26: System.out.print("$sp = ");
                    break;
         case 27: System.out.print("$ra = ");
                    break;
      }
   }

   
   
   
   public static void run(String pass2, int i, int[] rVal, ArrayList strArr, 
                  int stack[], int line, int line2){
   
      //abc = reg 
      String reg,reg2,reg3;
      //mnl = 
      int m,n,o;
      int start, back;
      

      pass2 = pass2.replaceAll(" ", "");          
      pass2 = pass2.replaceAll("\t", "");       
      pass2 = pass2.replaceAll("\n", "");       
      pass2 = pass2.replaceAll("\r\n", "");      
      pass2 = pass2.replaceAll("\r", "");
      if((i = pass2.indexOf(35)) != -1){     //35 = unicode '#'
         pass2 = pass2.substring(0, i);
      }
      if((i = pass2.indexOf(58)) != -1){     //58 = unicode ':'
         pass2 = pass2.substring(++i);
      }
      if(pass2.length()>1) {
        System.out.println(pass2.substring(0,4));
      }
      
      
      if(pass2.contains("addi")){
         
         start = 4;
         reg = pass2.substring(start, start + 3);              //reg 1
         start = start + 4;
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
            start = start - 1;
         }
         else if(reg.substring(0, 2).equals("$z")){
            reg = "$0";
            start = start + 2;
         }
         m = encode(reg);
         
         reg2 = pass2.substring(start, start + 3);             //reg 2
         start = start + 4;
         if(reg2.substring(0, 2).equals("$0")){
            reg2 = "$0";
            start = start - 1;
         }
         else if(reg2.substring(0, 2).equals("$z")){
            reg2 = "$0";
            start = start + 2;
         }
         n = encode(reg2);
         
         reg3 = pass2.substring(start);
         o = Integer.parseInt(reg3);
         rVal[m] = rVal[n] + o;                             //equation
      }
   
      else if(pass2.contains("add") || pass2.contains("and") ||
         pass2.contains("or")||pass2.contains("sub")||pass2.contains("slt")){
         
         start = 3;
         if(pass2.contains("or")){
            start = 2;
         }
         
         reg = pass2.substring(start, start + 3);
         start = start + 4;
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
            start = start - 1;
         }
         else if(reg.substring(0, 2).equals("$z")){
            reg = "$0";
            start = start + 2;
         }
         
         reg2 = pass2.substring(start, start + 3);
         start = start + 4;
         if(reg2.substring(0, 2).equals("$0")){
            reg2 = "$0";
            start = start - 1;
         }
         else if(reg2.substring(0, 2).equals("$z")){
            reg2 = "$0";
            start = start + 2;
         }
         
         reg3 = pass2.substring(start);
         start = start + 4;
         if(reg3.substring(0, 2).equals("$0")){
            reg3 = "$0";
            start = start - 1;
         }
         else if(reg3.substring(0, 2).equals("$z")){
            reg3 = "$0";
            start = start + 2;
         }
         
         n = encode(reg2);
         o = encode(reg3);
         m = encode(reg);
         
         if(pass2.contains("add")){
            rVal[m] = rVal[n] + rVal[o];  
         }
         if(pass2.contains("and")){
            rVal[m] = rVal[n] & rVal[o];  
         }
         if(pass2.contains("or")){
            rVal[m] = rVal[n] | rVal[o];  
         }
         if(pass2.contains("sub")){
            rVal[m] = rVal[n] - rVal[o];  
         }
         if(pass2.contains("slt")){
         
            if(rVal[n] < rVal[o])
               rVal[m] = 1;
            else
               rVal[m] = 0;
         }
      }
      
      else if(pass2.contains("sll")){                          //sll
         
         start = 3;
         
         reg = pass2.substring(start, start + 3);           //reg 1
         start = start + 4;
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
            start = start - 1;
         }
         else if(reg.substring(0, 2).equals("$z")){
            reg = "$0";
            start = start + 2;
         }

         
         reg2 = pass2.substring(start, start + 3);          //reg 2
         start = start + 4;
         if(reg2.substring(0, 2).equals("$0")){
            reg2 = "$0";
            start = start - 1;
         }
         else if(reg2.substring(0, 2).equals("$z")){
            reg2 = "$0";
            start = start + 2;
         }

         n = encode(reg2);
         m = encode(reg);
         
         reg3 = pass2.substring(start);                     //reg 3
         o = Integer.parseInt(reg3);
         
         rVal[m] = n << rVal[o];
      }
      
            
            
      else if(pass2.contains("lw") || pass2.contains("sw")){
      
         start = 2;
         
         reg = pass2.substring(start, start + 3);              //reg 1
         start = start + 4;
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
            start = start - 1;
         }
         else if(reg.substring(0, 2).equals("$z")){
            reg = "$0";
            start = start + 2;
         }
         
         reg2 = pass2.substring(start, start + 1);             //reg 2
         start = start + 2;

         
         reg3 = pass2.substring(start, start + 3);             //reg 3
         if(reg.substring(0, 2).equals("$0")){
            reg3 = "$0";
         }
         else if(reg3.substring(0, 2).equals("$z")){
            reg3 = "$0";
         }
         n = encode(reg3);
         m = encode(reg);
         o = Integer.parseInt(reg2);
            
         if (pass2.contains("sw")){
            stack[rVal[n]+o] = rVal[m];
         }            
         if (pass2.contains("lw")){
            rVal[m] = stack[rVal[n]+o];
         }
      }

      else if(pass2.contains("beq") || pass2.contains("bne")){
   
         start = 3;

         reg = pass2.substring(start, start + 3);              //reg 1
         start = start + 4;
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
            start = start - 1;
         }
         else if(reg.substring(0, 2).equals("$z")){
            reg = "$0";
            start = start + 2;
         }
         m = encode(reg);
         
         reg2 = pass2.substring(start, start + 3);             //reg 2
         start = start + 4;
         if(reg2.substring(0, 2).equals("$0")){
            reg2 = "$0";
            start = start - 1;
         }
         else if(reg2.substring(0, 2).equals("$z")){
            reg2 = "$0";
            start = start + 2;
         }
         n = encode(reg2);
         
         reg3 = pass2.substring(start);
         
         line = strArr.indexOf(reg3);
         line--;
         back = line - line2;
         if(back < 0)
            reg = reg.substring(16);   //reg = loop name
         
         //find reg in loop1 then use the same index to move pc to loop2

         if (pass2.contains("beq")){
            if(rVal[m] == rVal[n]){
               rVal[0] = strArr.indexOf(reg3);
               rVal[0]--;
            }
         }
         if (pass2.contains("bne")){
            if(rVal[m] != rVal[n]){
               rVal[0] = strArr.indexOf(reg3);
               rVal[0]--;
            }
         }
      }
      

      
      
      
      
      
      else if(pass2.contains("jr")){ 
      
         start = 2;
         
         reg = pass2.substring(start, start + 3);
         if(reg.substring(0, 2).equals("$0")){
            reg = "$0";
         }
         //System.out.println(rVal[encode(reg)]);
         rVal[0] = rVal[encode(reg)];
         rVal[0]--;
      }      
      
      
      
      else if(pass2.contains("j") || pass2.contains("jal")){
      
         start = 0;
         if(pass2.contains("jal")){       //wrong j
            start = 3;
            rVal[0] = rVal[0] + 1;
         }
         else if(pass2.contains("j")){     // j
            start = 1;
         }
         reg = pass2.substring(start);
         m = strArr.indexOf(reg);
         
         rVal[0] = m;
         rVal[0]--;
         
      }
      
   
      rVal[0]++;  //next pc line
 
      
   }
   
   
   
   

   
   
   
   public static void main(String args[]) throws IOException{

      File file = new File(args[0]);
      String absF = file.getAbsolutePath();
      String absF2 = file.getAbsolutePath();
      int count = 0;
      int count2 = 0;
      int line = 0;                    //line count of 1st pass
      int line2 = 0;                   //line count of 2st pass
      int start = 0;
      int val = 0;
      int back = 0;                    //how many lines to go back
   
      ArrayList reg = new ArrayList();
      ArrayList strArr = new ArrayList();
      int stack[] = new int[8192];
      ArrayList lnArr = new ArrayList();
      String pass = null;
      String pass2 = null;
      int i = 0;
      int[] rVal = new int[28];
   
   
      Scanner scan;
      boolean script = false;
      
      
      try {
         FileReader fr = new FileReader(absF);
         FileReader fr2 = new FileReader(absF2);
         BufferedReader br = new BufferedReader(fr);
         BufferedReader br2 = new BufferedReader(fr2);
         pass = br.readLine();
         pass2 = br2.readLine();
      
      
         //1st pass
         while((pass = br.readLine()) != null){
         
            //System.out.println(pass);
            pass = pass.replace("\\r\\n|\\r|\\n", "");
            pass = pass.trim();
            pass = pass.replace(" ", "");          
            pass = pass.replace("\t", "");       
            pass = pass.replace("\n", "");       
            pass = pass.replace("\r\n", "");      
            pass = pass.replace("\r", "");
            
            
            if((i = pass.indexOf(35)) != -1){      //35 = unicode '#'
               pass = pass.substring(0, i);
            }
            
            if((i = pass.indexOf(58)) != -1){      //58 = unicode ':'
               pass2 = pass.substring(i+1);
               pass = pass.substring(0, i);
               
               strArr.add(pass);
               lnArr.add(pass2);
               // System.out.println("    pass (strArr) = " + pass);
               // System.out.println("pass2 (lnArr) = " + pass2);
               line++;
            }
            
            
            else if( pass.length() != 0){
               pass2 = pass;
               pass = pass.substring(0,0);         //empty line
               strArr.add(pass);
               lnArr.add(pass2);
               lnArr.trimToSize();
               // System.out.println("pass (strArr) = " + pass);
               // System.out.println("pass2 (strArr) = " + pass2);
               line++;
            }
         }  
      
      
      
      
      
         //input file or SCRIPT
         if (args.length == 2){
            System.out.print("mips> ");
            scan = new Scanner(new FileInputStream(args[1]));
            script = true;
         }         
         //INPUT mode
         else{
            System.out.print("mips> ");
            scan = new Scanner(System.in);      
         }  
         
       
         
         
         String rLine = null;
         int num1, num2;
         Scanner lineScanner;


         //read inputs from scan (script or user input)
         while(scan.hasNextLine()){
         

            
            num1 = num2 = -1;
            
            //save all inputs
            rLine = scan.nextLine();
            if(script == true){
               System.out.print(rLine);
            }
            lineScanner = new Scanner(rLine);
            
            if(lineScanner.hasNext()){
               rLine = lineScanner.next();
            }
            if(lineScanner.hasNextInt()){
               num1 = lineScanner.nextInt();
            }         
            if(lineScanner.hasNextInt()){
               num2 = lineScanner.nextInt();
            }
            System.out.println();
            
            
            
            
            if(rLine.equals("h")){
               System.out.println();
               System.out.print("h = show help\n" +
                  "d = dump register state\n" +
                  "s = single step through the program (i.e. execute 1 instruction and stop)\n" +
                  "s num = step through num instructions of the program\n" +
                  "r = run until the program ends\n" +
                  "m num1 num2 = display data memory from location num1 to num2\n" +
                  "c = clear all registers, memory, and the program counter to 0\n" +
                  "q = exit the program\n");
            }
            
            
            
            //print out all the values of registers
            else if(rLine.equals("d")){
               count2 = 3;
               System.out.println();
               for(count = 0; count < 28; count++){
                  decode(count);
                  System.out.print(rVal[count] + "\t\t");
                  
                  //new line counter
                  count2++;
                  if(count2 == 4){
                     System.out.println();
                     count2 = 0;
                  }
               }
                  System.out.println();
            }
            
            
            
            
            
            else if(rLine.equals("s") && num1 != -1){
               
               System.out.print("\t" + num1 + " instruction(s) executed");
               while(num1-- != 0){
                  try{
                     //System.out.println((String)lnArr.get(rVal[0])+ " " + (rVal[0]));
                     run((String)lnArr.get(rVal[0]), i, rVal, strArr, stack, line, line2);
                  }catch(IndexOutOfBoundsException e){
                  }
               }
            }
            
            
            
            
            else if(rLine.equals("s")){

               //System.out.println((String)lnArr.get(rVal[0]) + " " + (rVal[0]));
               try{
                  run((String)lnArr.get(rVal[0]), i, rVal, strArr, stack, line, line2);
               }catch(IndexOutOfBoundsException e){
               }
               System.out.print("\t1 instruction(s) executed");
               
            }
            
            
            
            
            else if(rLine.equals("r")){
            
               while(rVal[0] != line){      
               
                  //System.out.println(line);
                  //System.out.println((String)lnArr.get(rVal[0])+ " " + (rVal[0]));
                  run((String)lnArr.get(rVal[0]), i, rVal, strArr, stack, line, line2);
               }
            }
            
            
            
            
            else if(rLine.equals("m")){
            
               for(i = num1; i <= num2; i++){
                  System.out.println("[" + i + "] = " + stack[i]);
               }
            }
            
            
            
            else if(rLine.equals("c")){
            
               rVal = new int[28];
               System.out.println("\tSimulator reset");
            }
            
            
            else if(rLine.equals("q")){
               System.exit(1);
            }
            
            System.out.println();
            
            System.out.print("mips> ");

   
         }
         
         br.close();  
      }
      catch(IOException e) {
         System.err.println("Caught IOException: " + e.getMessage());
      }
      
      
      
   }
}





