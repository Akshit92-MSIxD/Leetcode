class MyStack {
  
    Queue<Integer> mainQ;
    Queue<Integer> helpQ;
    
    public MyStack() {
        mainQ = new ArrayDeque();
        helpQ = new ArrayDeque();
    }
    
    public void push(int x) {
         
          mainQ.add(x);
    }
    
    public int pop() {
        
         while(mainQ.size() > 1)
            helpQ.add(mainQ.poll());

         int poppedValue = mainQ.poll();

         mainQ = helpQ;
         helpQ = new ArrayDeque();

         return poppedValue;
    }
    
    public int top() {
         
          while(mainQ.size() > 1)
          helpQ.add(mainQ.poll());

          int topValue = mainQ.peek();
          helpQ.add(mainQ.poll());

          mainQ = helpQ;
          helpQ = new ArrayDeque();

          return topValue; 
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