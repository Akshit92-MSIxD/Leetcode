
// // Approach 1 : Using Two Queues
// // TC for push() : O(1) , pop() : O(n) , top() : O(n) , empty() : O(1)

// class MyStack {
  
//     Queue<Integer> mainQ;
//     Queue<Integer> helpQ;
    
//     public MyStack() {
//         mainQ = new ArrayDeque();
//         helpQ = new ArrayDeque();
//     }
    
//     public void push(int x) {
         
//           mainQ.add(x);
//     }
    
//     public int pop() {
        
//          while(mainQ.size() > 1)
//             helpQ.add(mainQ.poll());

//          int poppedValue = mainQ.poll();

//          mainQ = helpQ;
//          helpQ = new ArrayDeque();

//          return poppedValue;
//     }
    
//     public int top() {
         
//           while(mainQ.size() > 1)
//           helpQ.add(mainQ.poll());

//           int topValue = mainQ.peek();
//           helpQ.add(mainQ.poll());

//           mainQ = helpQ;
//           helpQ = new ArrayDeque();

//           return topValue; 
//     }
    
//     public boolean empty() {
         
//          return mainQ.size() == 0;
//     }
// }




/*----------------------------------------------------------------------------------------------------*/




// // Approach 2 : Using Two Queues
// // TC for push() : O(2*n) , pop() : O(1) , top() : O(1) , empty() : O(1)

// class MyStack {
  
//     Queue<Integer> mainQ;
//     Queue<Integer> helpQ;
    
//     public MyStack() {
//         mainQ = new ArrayDeque();
//         helpQ = new ArrayDeque();
//     }
    
//     public void push(int x) {
         
//          if(mainQ.size() == 0)
//          {
//             mainQ.add(x);
//             return;
//          }

//          while(!mainQ.isEmpty())
//          helpQ.add(mainQ.poll());

//          mainQ.add(x);

//          while(!helpQ.isEmpty())
//          mainQ.add(helpQ.poll());
//     }
    
//     public int pop() {
          
//            return mainQ.poll();
//     }
    
//     public int top() {
         
//          return mainQ.peek();
//     }
    
//     public boolean empty() {
         
//          return mainQ.size() == 0;
//     }
// }



/*---------------------------------------------------------------------------------------------------*/


// Approach 3 : Using Single Queue
// TC for push() : O(n) , pop() : O(1) , top() : O(1) , empty() : O(1)

class MyStack {
  
    Queue<Integer> mainQ;
    
    public MyStack() {
        mainQ = new ArrayDeque();
    }
    
    public void push(int x) {
         
         mainQ.add(x);

         int currSize = mainQ.size();

        for(int i=1;i<=currSize-1;i++)
           mainQ.add(mainQ.poll());
    }
    
    public int pop() {
          
           return mainQ.poll();
    }
    
    public int top() {
         
         return mainQ.peek();
    }
    
    public boolean empty() {
         
         return mainQ.size() == 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */