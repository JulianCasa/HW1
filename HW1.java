
/*
 * *** PLACE YOUR NAME / SECTION  HERE ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)  {        // Constructor
                data = d;
                next = null;
            }
        }
        Node head;                // head of Linked-list


        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data ) {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data ) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }

            return;
        }


        /*
         * Method removeElementsLT() - this method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than thr parameter value passed.
         */
        public void removeElementsLT ( int ltValue ) {

            // YOUR CODE GOES HERE

            //Creates Nodes needed to traverse the linked list
            Node cursor = this.head;
            Node previousNode = null;

            //Loops through the Linked List until it reaches the end, which is when the cursor becomes null
            while (cursor != null){

                /*The program then checks to see if the current cursor meets the removal requirement
                 * If it doesn't it continues through the Linked List
                */
                if (cursor.data < ltValue){

                    /*On the chance that the first item in the Linked List meets the removal requirement
                     * the program sets the next node as the new head. Otherwise it sets the previous node's
                     * next field as the current cursors next field. It then continues through the Linked List
                    */
                   if (previousNode == null){
                    this.head = cursor.next;
                   }
                   else{
                    previousNode = cursor.next;
                   }

                   cursor = cursor.next;
                }
                else{
                previousNode = cursor;
                cursor = cursor.next;
                }
            }
            return;
        }


        /*
         * Method removeElement() - this method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         */

        public void removeElement ( int value ) {

            // YOUR CODE GOES HERE

            //Creates Nodes needed to traverse the linked list
            Node cursor = this.head;
            Node previousNode = null;

            //Loops through the Linked List until it reaches the end, which is when the cursor becomes null
            while (cursor != null){

                /*If the current cursor meets the removal requirements it skips over the current cursor and reassigns the previous
                 *next field. Otherwise it continues the previousNode. Regardless of what was removed or not the cursor will continue through
                */ 
                if (cursor.data == value){
                    previousNode.next = cursor.next;
                }
                else{
                    previousNode = cursor;
                }

                cursor = cursor.next;
            }
            

            return;
        }


        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    } // End class LinkedList




    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignore, so both 'race car' and 'racecar' are plaindromes.
         *
         * The method should utilize the provided Stack class.
         */
        public static boolean isPalindrome(String input) {

            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("\\s+", "");

            // Your CODE GOES HERE

            //creates the needed return Variable
            Boolean isPalindrome = false;

            //The for loop adds each character of the inputted string as its own element in the stack
            for (int i = 0; i < input.length(); i++) {
                stack.push(input.charAt(i));
            }

            //The program checks the size of the stack and determines whether it is odd or even 
            int stackSize = stack.size();
            if (stackSize % 2 == 0){

                /*If the stack size is even, it creates an extra stack. It then stores half of that stack in the new
                 * one and checks if the two stacks are equal. If they are, it sets the boolean variable to true
                 * and returns said variable
                 */
                Stack<Character> halfStack2 = new Stack<>();

                for (int i = 0; i < stackSize/2; i++){
                    halfStack2.push(stack.pop());
                }

                if (halfStack2.equals(stack)){
                    isPalindrome = true;
                }

            }
            else{
                /*When the stack size is odd, it similarly splits the character stack into 2. However,
                 * the extra middle character is set to the side in its own stack and then compares
                 * the "left" and "right" stacks to see if they are equal. If they are, it sets the boolean 
                 * variable to true and returns said variable
                 */
                Stack<Character> halfStack3 = new Stack<>();
                Stack<Character> midStack = new Stack<>();

                for (int i = 0; i < stackSize/2; i++){
                    halfStack3.push(stack.pop());
                }

                midStack.push(stack.pop());

                if (halfStack3.equals(stack)){
                    isPalindrome = true;
                }

            }

            return isPalindrome;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So it you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         * The method should utilize the provided Stack class. This method should NOT
         * destroy the passed in stack, meaning when the method returns, the passed in
         * stack should be identical to when this method is passed. One trick as you
         * pop elements off the passed in stack, place them in a temp stack. Then when
         * completed, place them all back in teh original stack.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {

            // YOUR CODE GOES HERE
            int largestK = -1;


            /*Before doing anything the program checks to see if the stack has elements
             * and if the element k exists within that stack. If the conditions are met
             * the program continues, otherwise it returns the value -1 to indicate failure
            */
            if (!stack.empty() && stack.contains(k)){

                //Creates a temporary stack to store whatever's been popped
                Stack<Integer> tempStack = new Stack<>();

                /*The proogram loops through the stack looking for the trget value
                 * First it creates an Integer variable, topInt, to use during the checks
                 * It then checks to see if the value if it matches. On a match the program
                 * sets the return variable as the size - 1 as that would be what the index
                 * of k would be. Otherwise the top element is popped and stored in the temp stack
                */
                while (largestK < 0){
                    Integer topInt = stack.peek();

                    if (topInt.equals(k)){
                        largestK = stack.size()-1;
                    }
                    else{
                        tempStack.push(stack.pop());
                    }
                }

                /*After finding the element k, the program restores the original stack by looping
                 * through the temp stack and reversing the process
                 */
                while(!tempStack.isEmpty()){
                    stack.push(tempStack.pop());
                }
            }

            //Program returns the index of the value at the furthest index from the bottom
            return largestK;
        }

    }  // End class Stacks



    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
        */

        // RETURN THE CORRECT OPTION NUMBER LISTED ABOVE
        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        // RETURN THE CORRECT OPTION LISTED ABOVE
        return 2;
    }

}

