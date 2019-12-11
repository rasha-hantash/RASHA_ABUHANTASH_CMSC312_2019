package OS;

import java.util.Random;

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

                    Clock.waitingQueue.get(0).pcb.totalMemory -= Clock.waitingQueue.get(0).pcb.memoryInstructions.get(0);
                    Clock.waitingQueue.get(0).pcb.memoryInstructions.remove(0);

                    // if there are no more instructions remove the process from waiting queue
                    // dont forget dispatcher
                    if (Clock.waitingQueue.get(0).pcb.instructions.size() == 0) {
                        dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                        //this should be zero 
                        Clock.terminatedQueue.add(Clock.waitingQueue.get(0));
                        Memory.memorySize += Clock.waitingQueue.get(0).pcb.totalMemory;
                        Clock.waitingQueue.remove(0);
                    }
                    
                }
            } else {
                // if the first instruction on waiting queue is CALC then put it on ready queue
                // dont forget to change the state here as well
                //if there is enough memory then put it onto ready queue, otherwise put it onto
                // the end of the waiting queue
                if(Memory.memorySize > Clock.waitingQueue.get(0).pcb.totalMemory) {
                    Random rand = new Random();
                    int randQueue = rand.nextInt(2);

                    if(randQueue == 0){
                        dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.READY);
                        Clock.readyQueue.add(Clock.waitingQueue.get(0));
                        Memory.memorySize -= Clock.waitingQueue.get(0).pcb.totalMemory;
                        Clock.waitingQueue.remove(0);
                    } else {
                        dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.READY);
                        Clock.readyQueue2.add(Clock.waitingQueue.get(0));
                        Memory.memorySize -= Clock.waitingQueue.get(0).pcb.totalMemory;
                        Clock.waitingQueue.remove(0);
                    }
                    
                } else {
                    //if not enough memory for that process put that process at the end 
                    //of the waiting queue
                    Clock.waitingQueue.add(Clock.waitingQueue.get(0));
                    Clock.waitingQueue.remove(0);

                }
                
            }
        }
        //Logic to ensure that a process that is in waiting queue but has not 
        //more insturctions to execute is send to the terminated queue
        if(Clock.waitingQueue.size() !=0){
            if(Clock.waitingQueue.get(0).pcb.instructions.size() == 0){
                dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                Clock.terminatedQueue.add(Clock.waitingQueue.get(0));
                Memory.memorySize += Clock.waitingQueue.get(0).pcb.totalMemory;
                Clock.waitingQueue.remove(0);
            }
        }
        
    }
}