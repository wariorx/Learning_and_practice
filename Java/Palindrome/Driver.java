import java.util.*;
/**
 * This driver allows the user to input a String, the String is then stored in a Circular queue and
 * a LinearNodeStack. Then, it'll check whether the String is a palindrome and display a message
 * accordingly
 * 
 * @author Raul Calcamo
 * @version 06/02/2016
 */
public class Driver
{
    public static void main(String [] args) throws EmptyCollectionException
    {   
        Scanner keyboard = new Scanner (System.in);//create scanner object to get keyboard input
        String input;
        String exit1 = "q";
        String exit2 = "Q";
        
        do{//will repeat until user enters any of the valid exit strings
            
            System.out.println("Enter text to check; Enter q to exit:");
            input = keyboard.nextLine();
            //System.out.println(input);

            input = input.toLowerCase(); //modify the string so that capitalization doesn't matter
            LinkedStack<Character> stack= new LinkedStack <Character>(); //create stack object that accepts Characters
            CircularArrayQueue<Character> queue= new CircularArrayQueue<Character>(); //create queue object Characters

            if(!input.equals(exit1) && !input.equals(exit2)&& !input.equals("")) //will only execute this if the user doesn't enter the exit command
            {
                //Check each character of the input String. If it is a letter, it will push() and enqueue()
                for(int i=0; i <input.length(); i++) //will iterate as many times as the string's length
                    if(Character.isLetter(input.charAt(i))) //check if the character in current location is a letter
                    {
                        stack.push(input.charAt(i));
                        queue.enqueue(input.charAt(i));
                    }   

                boolean same = true; //Will be set to false if the characters dequeued and poped do not match
                for(int i = 0; i < stack.size(); i++) //both had the same number of elements pushed or enqueued, so any size() is valid to set the number of times the loop will iterate
                {
                    if(stack.peek() == queue.first())
                    {
                        //pop and dequeue so thar the next elements are compared in the next iteration
                        stack.pop();
                        queue.dequeue();
                    }
                    else
                    {
                        same = false;
                        break; //end the loop, since it's won't be a palindrome regardless of the next characters
                    }
                }

                if(same)//All the elements poped and dequeued matched
                    System.out.println("The text entered is a palindrome");
                else //At least one of the elements did not match
                    System.out.println("The text entered is NOT a palindrome");

            }
            
        }while (!input.equals(exit1) && !input.equals(exit2) );
   
            
    }
    
}//End of Class
