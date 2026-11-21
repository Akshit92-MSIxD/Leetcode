// Note : I have written two approaches for this problem below. Please read both of them !!!

// Approach 2 is the most optimal one !!!



// Approach 1 : Using two stacks
// TC for push() : O(n) , pop() : O(1) , top() : O(1) , empty() : O(1)
// SC : O(n)

class MyQueue {
     
     Stack<Integer> mainS;
     Stack<Integer> helpS; 
      
    public MyQueue() {
        mainS = new Stack<>();
        helpS = new Stack<>();
    }
    
    public void push(int x) {

        while(!mainS.isEmpty())
        helpS.push(mainS.pop());

        mainS.push(x);

        while(!helpS.isEmpty())
        mainS.push(helpS.pop());
        
    }
    
    public int pop() {
        return mainS.pop();
    }
    
    public int peek() {
        return mainS.peek();
    }
    
    public boolean empty() {
        return mainS.size() == 0;
    }
}



/*------------------------------------------------------------------------------------------------------*/




// Approach 2 : Using two stacks
// TC for push() : O(1) , pop() : O(1) amortized , top() : O(1) amortized , empty() : O(1)
// SC : O(n)

// class MyQueue {
     
//      Stack<Integer> s1;
//      Stack<Integer> s2; 
      
//     public MyQueue() {
//        s1 = new Stack<>();
//        s2 = new Stack<>();
//     }
    
//     public void push(int x) {

//       s1.push(x);
        
//     }
    
//     public int pop() {
         
//         if(!s2.isEmpty())
//         {
//             return s2.pop();
//         }
//         else
//         {
//             while(!s1.isEmpty())
//             s2.push(s1.pop());

//             return s2.pop();
//         }
//     }
    
//     public int peek() {
            
//         if(!s2.isEmpty())
//         {
//             return s2.peek();
//         }
//         else
//         {
//             while(!s1.isEmpty())
//             s2.push(s1.pop());

//             return s2.peek();
//         }
//     }
    
//     public boolean empty() {
//         return (s1.size() + s2.size()) == 0;
//     }
// }

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */