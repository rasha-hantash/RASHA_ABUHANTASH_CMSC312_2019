package cmsc312;
public class WaitingQueue {

    Dispatcher dispatcher = new Dispatcher();
    public  void waitingQueue() {
        if (Clock.waitingQueue.size() != 0 && Clock.waitingQueue.get(0).pcb.instructions.size() != 0) {
            
            if (Clock.waitingQueue.get(0).pcb.instructions.get(0).equals("I/0")) {
                
                //decrement i/o cycle
                Clock.waitingQueue.get(0).pcb.clockCycle.set(0, Clock.waitingQueue.get(0).pcb.clockCycle.get(0) - 1);

                // check to see if clockCycle is zero, if so then remove
                if (Clock.waitingQueue.get(0).pcb.clockCycle.get(0) == 0) {
                    Clock.waitingQueue.get(0).pcb.clockCycle.remove(0);
                    Clock.waitingQueue.get(0).pcb.instructions.remove(0);
                    Clock.waitingQueue.get(0).pcb.ENTER_CRIT.remove(0);
                    Clock.waitingQueue.get(0).pcb.EXIT_CRIT.remove(0);

                    // if there are no more instructions remove the process from waiting queue
                    // dont forget dispatcher
                    if (Clock.waitingQueue.get(0).pcb.instructions.size() == 0) {
                        dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                        Clock.waitingQueue.remove(0);
                    }
                    
                }
            } else {
                // if the first instruction on waiting queue is CALC then put it on ready queue
                // dont forget to change the state here as well
                //if there is enough memory then put it onto ready queue, otherwise put it onto
                // the end of the waiting queue
                if(Memory.memorySize > Clock.waitingQueue.get(0).pcb.memory) {
                    dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.READY);
                    Clock.readyQueue.add(Clock.waitingQueue.get(0));
                Memory.memorySize -= Clock.waitingQueue.get(0).pcb.memory;
                Clock.waitingQueue.remove(0);
                } else {
                    Clock.waitingQueue.add(Clock.waitingQueue.get(0));
                    Clock.waitingQueue.remove(0);

                }
                
            }
        }

        if(Clock.waitingQueue.size() !=0){
            if(Clock.waitingQueue.get(0).pcb.instructions.size() == 0){
                System.out.println("HERE");
                dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                Clock.waitingQueue.remove(0);
            }
        }
        
    }
}