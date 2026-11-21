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

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */