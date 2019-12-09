package cmsc312;
import java.util.ArrayList;
public class Schedular {
    
    //Mutex mutex = new Mutex();
    CPU cpu = new CPU();
    Dispatcher dispatcher = new Dispatcher();
    public int schedular(ArrayList<Process> readyQueue, int timeQuantum, int queueNumber) {
        //decrement timeQuantum only in cpu
        //let cpu handle time quantum  
        //let cpu handle deleting instructions 
        //let schedular handle deleting process and shifting process from queue to queue
        //if ready queue is not empty 
        if(readyQueue.size() > 0){
            // System.out.println("readyQueue Process ID is " + readyQueue.get(0).pcb.processID);
        }
       
        if(readyQueue.size() !=0){
            // System.out.println("WE ARE IN READY QUEUE QUEUE");
            //check to see if process still has instructions left
            if(readyQueue.get(0).pcb.instructions.size() == 0)
            {
                //delete process
                //change state here 
                dispatcher.changeState(readyQueue.get(0), ProcessStates.TERMINATE);
                Memory.memorySize += readyQueue.get(0).pcb.memory;
                readyQueue.remove(0);
            //check to see if time quantum is zero or less 
            } else if(timeQuantum == 0) {
                //check to see if process is in critical section 
                // by looking at mutex variable
                if(readyQueue.get(0).pcb.entered_crit == true){
                    //change state here
                    Clock.runProcess = readyQueue.get(0);
                    dispatcher.changeState(Clock.runProcess, ProcessStates.RUN);
                    timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum);
                } else {
                    //timeQuantum for that process is done and so put it at the end
                    //of the readyQueue
                    dispatcher.changeState(readyQueue.get(0), ProcessStates.READY);
                    readyQueue.get(0).pcb.entered_crit = false;
                    readyQueue.get(0).pcb.exited_crit = false;
                    readyQueue.add(readyQueue.get(0));
                    //change the state here 
                    readyQueue.remove(0);
                    if(queueNumber == 0){
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
                            timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum);
                        }
                } else {
                    //change state 
                    //send to cpu
                    // ?decrement time quantum
                    Clock.runProcess = readyQueue.get(0);
                    dispatcher.changeState(Clock.runProcess, ProcessStates.RUN);
                    
                    timeQuantum = cpu.cpu(readyQueue.get(0), timeQuantum);
                    
                }

            } else {
                //add onto waiting queue
                if (readyQueue.get(0).pcb.instructions.size() == 0) {
                    dispatcher.changeState(Clock.waitingQueue.get(0), ProcessStates.TERMINATE);
                    Memory.memorySize += readyQueue.get(0).pcb.memory;
                    readyQueue.remove(0);
                    // System.out.println("HERE");
                } else {
                    
                   
                        // System.out.println("PRINTING HERE");
                        if(readyQueue.size() !=0){
                            dispatcher.changeState(readyQueue.get(0), ProcessStates.WAIT);
                            Clock.waitingQueue.add(readyQueue.get(0));
                            Memory.memorySize += readyQueue.get(0).pcb.memory;
                            readyQueue.remove(0);
                        }
                        
                    

                   
                }
                
            }
        }
        if(readyQueue.size() > 0){
            if(readyQueue.get(0).pcb.instructions.size() == 0){
                dispatcher.changeState(readyQueue.get(0), ProcessStates.TERMINATE);
                Memory.memorySize += readyQueue.get(0).pcb.memory;
                readyQueue.remove(0);

            }
        }
        
       
        return timeQuantum;
    } 
}



    //check to see if process still has instructions left
        //if no
               //delete process
        //else 
             //check to see if time quantum is zero or less 
             //check to see if process is in critical section 
            // by looking at mutex variable
            //if yes 
                //send to cpu
                //cpu wont decrement time quantum 
            //else 
                //remove process from ready queue
                //call cpu to set timeQuantum to 300 

        //else 
        //check to see if process in that queue has a calc instruction
        // if yes
            //check to see if it is critical section
                //if yes 
                    //check to see entered_crit already equals true
                    //if yes 
                        //put that process on  onto the end of the readyQueue 
                        // dont change time quantum
                    //else 
                        ////set entered_crit equal to true
                        //call mutex
                        //send to cpu 
            //else    
                //send to cpu
            // ?decrement time quantum
        // if no, 
            //add onto waiting queue

        

