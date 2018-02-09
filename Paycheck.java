// February 8th 2018
// Heidi Clinger

import java.util.Scanner;

/**
   This program accept input from the user and
   prints the infomation in a paycheck format
*/

public class Paycheck {
   private String date;
   private String name;
   private String amount;
   
   /**
      The getUserInfo method promts the user
      for data and stores the information in
      the instance variables
   */
   public void getUserInfo() {
      boolean isValidDate = false;  //flag to validate date input
      boolean isValidAmount = false;  //flag to validate amount input
      Scanner scnr = new Scanner(System.in);
      
      System.out.print("Enter payee name: ");
      name = scnr.nextLine();
      
      //continue prompting for date until input validates true
      //based on the validateDate method
      do {
         System.out.print("Enter paycheck date in dd/mm/yy format: ");
         date = scnr.nextLine();
         if(validateDate(date))
            isValidDate = true;
         else
            System.out.println("\tInvalid date format.");
      } while(!isValidDate);
      
      //continue prompting for amount until input validates true
      //based on the validateAmount method
      do {
         System.out.print("Enter paycheck amount in ####.## format: ");
         amount = scnr.nextLine();
         if(validateAmount(amount))
            isValidAmount = true;
         else
            System.out.println("\tInvalid amount format.");
         } while(!isValidAmount);    
   }
   
   /**
      The writtenAmount method checks each character in the amount
      String and appends the correct number word to the StringBuilder,
      based on the matching case
      @param the amount entered by the user
      @return a StringBuilder object containing the written format
      of the numeric amount given by the user
   */
   public StringBuilder writtenAmount(String amount) {
      String cents = amount.substring(amount.length() - 2, amount.length());
      StringBuilder writtenAmount = new StringBuilder();
      
      //loop through each character until the decimal point
      for(int i = 0; i < amount.length() - 2; i++) {
         char nextDigit = amount.charAt(i);  //holds the current char
         int position = amount.length() - i;  //position from right to left
         
         switch (nextDigit) {
            //one should print as one unless in the 10's position        
            case '1': if(position != 5)
                        writtenAmount.append("one ");
                      else if(amount.charAt(i+1) == '1')
                        { writtenAmount.append("eleven ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '2')
                        { writtenAmount.append("twelve ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '3')
                        { writtenAmount.append("thirteen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '4')
                        { writtenAmount.append("fourteen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '5')
                        { writtenAmount.append("fifteen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '6')
                        { writtenAmount.append("sixteen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '7')
                        { writtenAmount.append("seventeen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '8')
                        { writtenAmount.append("eighteen ");
                          i++;
                        }
                      else if(amount.charAt(i+1) == '9')
                        { writtenAmount.append("nineteen ");
                          i++;
                        }                
                    break;
            //each case has a different option when the digit is
            //in the 10's or 10 thousand's position
            case '2': if(position == 5 || position == 8)
                        writtenAmount.append("twenty ");
                      else
                        writtenAmount.append("two ");
                    break;
            case '3': if(position == 5 || position == 8)
                        writtenAmount.append("thirty ");
                      else
                        writtenAmount.append("three ");
                      break;
            case '4': if(position == 5 || position == 8)
                        writtenAmount.append("fourty ");
                      else
                        writtenAmount.append("four ");
                    break;
            case '5': if(position == 5 || position == 8)
                        writtenAmount.append("fifty ");
                      else
                        writtenAmount.append("five ");
                    break;
            case '6': if(position == 5 || position == 8)
                        writtenAmount.append("sixty ");
                      else
                        writtenAmount.append("six ");
                    break;
            case '7': if(position == 5 || position == 8)
                        writtenAmount.append("seventy ");
                      else
                        writtenAmount.append("seven ");
                    break;
            case '8': if(position == 5 || position == 8)
                        writtenAmount.append("eighty ");
                      else
                        writtenAmount.append("eight ");
                    break;
            case '9': if(position == 5 || position == 8)
                        writtenAmount.append("ninety ");
                      else
                        writtenAmount.append("nine ");
                    break;   
            case '.': writtenAmount.append("and ");
                    break;                            
         }
         
         //check if there is a digit in the hundred's position
         if(position == 6 && nextDigit != '0')
               writtenAmount.append("hundred ");
         //check if there is a digit in the thousand's position
         if(position == 7)
               writtenAmount.append("thousand ");
         
      }
      
      //add the word cents to the end of the StringBuilder
      writtenAmount.append(cents + " cents");
      //assign the first letter of the StringBuilder to a variable and
      //capitalize it
      char firstLetter = Character.toUpperCase(writtenAmount.charAt(0));
      //replace the old lowercase letter with the upper case letter
      writtenAmount.setCharAt(0, firstLetter);
      
      return writtenAmount;
   }
   
   /**
      The validateDate method test the date String
      entered by the user to determine if it is formatted
      properly.
      @param The date String entered by the user
      @return a boolean value
   */
   public boolean validateDate(String date) {
      boolean validity = true;
      
      //check that the length is no more or less than 8 characters
      if(date.length() != 8)
                 validity = false;
      //check that the 3rd and 6th character are forward slashes
      else if(date.charAt(2) != '/' || date.charAt(5) != '/')
                 validity = false;
      //check that every other character is a digit
      else if(!Character.isDigit(date.charAt(0)) || !Character.isDigit(date.charAt(1)) ||
              !Character.isDigit(date.charAt(3)) || !Character.isDigit(date.charAt(4)) ||
              !Character.isDigit(date.charAt(6)) || !Character.isDigit(date.charAt(7)))
                 validity = false;
      //check that the day, month, and year are within reasonable parameters
      //this isn't perfect, but there is a time limit to the assignment :)
      else if(Integer.parseInt(date.substring(0, 2)) < 1 || 
              Integer.parseInt(date.substring(0, 2)) > 12 ||
              Integer.parseInt(date.substring(3, 5)) < 1 ||
              Integer.parseInt(date.substring(3, 5)) > 31 ||
              Integer.parseInt(date.substring(6, 8)) < 18)
                 validity = false;
       
      return validity;
   }
   
   /**
      The validateAmount method test the amount String
      entered by the user to determine if it is formatted
      properly.
      I originally had the amount variable as a double but
      had difficulty when entering 0's in the cents section
      @param The amount String entered by the user
      @return a boolean value
   */
   public boolean validateAmount(String moneyAmount) {
      boolean validity = true;
      
      //check that a decimal point is in the correct position
      if(moneyAmount.charAt(moneyAmount.length() - 3) != '.')
         validity = false;
      
      //check that the characters before the decimal are digits
      for(int i = 0; i < moneyAmount.length() - 3; i++) {
         if(!Character.isDigit(moneyAmount.charAt(i)))
            validity = false;
      }
      
      //check that the characters after the decimal are digits
      for(int i = moneyAmount.length() - 2; i < moneyAmount.length(); i++) {
         if(!Character.isDigit(moneyAmount.charAt(i)))
            validity = false;
      }
       
      return validity;
   }
   
   /**
      The creatCeck method puts all the validated input
      together and formats it properly for output
   */
   public void createCheck() {
      getUserInfo();
      System.out.println("\n");
      System.out.printf("%75s%n%n", "Date:  " + date);
      System.out.printf("%-55s%10s%10s%n", "Pay to the Order of:  " + name,"$", amount);
      System.out.print(writtenAmount(amount)); 
      
   }
   
   public static void main(String[] args) {
      Paycheck employeeCheck = new Paycheck();
      employeeCheck.createCheck();
   }
   

}
