package OS;
import java.util.ArrayList;
public class Schedular {
    
    //Mutex mutex = new Mutex();
    CPU cpu = new CPU();
    Dispatcher dispatcher = new Dispatcher();
    
    public int schedular(ArrayList<Process> readyQueue, int timeQuantum, int queueNumber) {
        
       
        if(readyQueue.size() !=0){
            //check to see if process still has instructions left
            if(readyQueue.get(0).pcb.instructions.size() == 0)
            {
                //delete process
                //change state here 
                dispatcher.changeState(readyQueue.get(0), ProcessStates.TERMINATE);
                //should be zero
                Clock.terminatedQueue.add(readyQueue.get(0));
                Memory.memorySize += readyQueue.get(0).pcb.totalMemory;
                readyQueue.remove(0);
            //check to see if time quantum is zero or less 
            } else if(timeQuantum <= 0) {
                //check to see if process is in critical section 
                // by looking at mutex variable
                if(readyQueue.get(0).pcb.entered_crit == true){
                    //change state here
                    Clock.runProcess = readyQueue.get(0);
                    dispatcher.changeState(Clock.runProcess, ProcessStates.RUN);
                    timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum, queueNumber);
                } else {
                    //timeQuantum for that process is done and so put it at the end
                    //of the readyQueue
                    dispatcher.changeState(readyQueue.get(0), ProcessStates.READY);
                    readyQueue.get(0).pcb.entered_crit = false;
                    readyQueue.get(0).pcb.exited_crit = false;
                    readyQueue.add(readyQueue.get(0));
                    //change the state here 
                    readyQueue.remove(0);
                    if(queueNumber <= 0){
                        timeQuantum = 40;
                    } else if (queueNumber == 1){
                        timeQuantum = 100;
                    }
                    
                    //talk about what should set the time quantum
                    // i think schedular should do it 
                }
            //check to see if process in that queue has a calc instruction
            } else if (readyQueue.get(0).pcb.instructions.get(0).equals("CALCULATE")){

                //check to see if it is critical section
                if(readyQueue.get(0).pcb.ENTER_CRIT.get(0) == true) {
                    //check to see if process has already exited a critical section during its timeQuantum
                        if(readyQueue.get(0).pcb.exited_crit == true){
                            //put that process on  onto the end of the readyQueue 
                            // dont change time quantum
                            dispatcher.changeState(readyQueue.get(0), ProcessStates.READY);
                            readyQueue.get(0).pcb.exited_crit = false;
                            readyQueue.get(0).pcb.entered_crit = false;
                            readyQueue.add(readyQueue.get(0));
                            readyQueue.remove(0);
                            
                        } else {
                            ////set entered_crit equal to true
                            //call mutex
                            //send to cpu 
                            //readyQueue.get(0).pcb.entered_crit = true;
                            //mutex.acquire();
                            //change state
                            Clock.runProcess = readyQueue.get(0);
                            dispatcher.changeState(Clock.runProcess, ProcessStates.RUN);
                            timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum, queueNumber);
                        }
                } else {
                    //change state 
                    //send to cpu
                    // ?decrement time quantum
                    Clock.runProcess = readyQueue.get(0);
                    dispatcher.changeState(Clock.runProcess, ProcessStates.RUN);
                    
                    timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum, queueNumber);
                    
                }

            } else if (readyQueue.get(0).pcb.instructions.get(0).equals("YIELD")){
                //subtract yield
                //check to see if yield if zero
                //if it it remove instruction  remove totalmemory add to memory aviable 
                readyQueue.get(0).pcb.clockCycle.set(0, readyQueue.get(0).pcb.clockCycle.get(0) - 1);

                if(readyQueue.get(0).pcb.clockCycle.get(0) <= 0){
                    Memory.memorySize += readyQueue.get(0).pcb.memoryInstructions.get(0);
                    readyQueue.get(0).pcb.totalMemory -= readyQueue.get(0).pcb.memoryInstructions.get(0);
                    readyQueue.get(0).pcb.memoryInstructions.remove(0);
                    readyQueue.get(0).pcb.instructions.remove(0);
                    readyQueue.get(0).pcb.clockCycle.remove(0);
                    readyQueue.get(0).pcb.ENTER_CRIT.remove(0);
                    readyQueue.get(0).pcb.EXIT_CRIT.remove(0);
                }
                //let that process yield to another process 
                readyQueue.add(readyQueue.get(0));
                readyQueue.remove(0);


            } else if (readyQueue.get(0).pcb.instructions.get(0).equals("OUT")){ 
                readyQueue.get(0).pcb.clockCycle.set(0, readyQueue.get(0).pcb.clockCycle.get(0) - 1);

                if(readyQueue.get(0).pcb.clockCycle.get(0) <= 0){
                    Memory.memorySize += readyQueue.get(0).pcb.memoryInstructions.get(0);
                    readyQueue.get(0).pcb.totalMemory -= readyQueue.get(0).pcb.memoryInstructions.get(0);
                    readyQueue.get(0).pcb.memoryInstructions.remove(0);
                    readyQueue.get(0).pcb.instructions.remove(0);
                    readyQueue.get(0).pcb.clockCycle.remove(0);
                    readyQueue.get(0).pcb.ENTER_CRIT.remove(0);
                    readyQueue.get(0).pcb.EXIT_CRIT.remove(0);
                }

                
                    
                    
                //put on the terminated queuej
                if(readyQueue.get(0).pcb.instructions.size() <= 0){
                    
                    Clock.terminatedQueue.add(readyQueue.get(0));
                    dispatcher.changeState(readyQueue.get(0), ProcessStates.TERMINATE);
                    System.out.println("TERMINATED HERE " + Clock.terminatedQueue.size());
                    readyQueue.remove(0);
                }

                

            } else {
                //add onto waiting queue
                if (readyQueue.get(0).pcb.instructions.size() == 0) {
                    dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                    //should be zero
                    Clock.terminatedQueue.add(readyQueue.get(0));
                    Memory.memorySize += readyQueue.get(0).pcb.totalMemory;
                    readyQueue.remove(0);
                } else {
                    
                        if(readyQueue.size() !=0){
                            dispatcher.changeState(readyQueue.get(0), ProcessStates.WAIT);
                            Clock.waitingQueue.add(readyQueue.get(0));
                            Memory.memorySize += readyQueue.get(0).pcb.totalMemory;
                            readyQueue.remove(0);
                        }
                        
                    

                   
                }
                
            }
        }
        if(readyQueue.size() > 0){
            if(readyQueue.get(0).pcb.instructions.size() == 0){
                dispatcher.changeState(readyQueue.get(0), ProcessStates.TERMINATE);
                //should be zero 
                Clock.terminatedQueue.add(readyQueue.get(0));
                Memory.memorySize += readyQueue.get(0).pcb.totalMemory;
                readyQueue.remove(0);

            }
        }
        
       
        return timeQuantum;
    } 
}