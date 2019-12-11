package OS;
import java.util.Random;

public class CPU {
    Dispatcher dispatcher = new Dispatcher();
    int externalEventIO = 0;

    public int cpu(Process p, int timeQuantum, int queueNumber) {
        Random random = new Random();
        externalEventIO = random.nextInt(10);
        //random external event i/o
        if(externalEventIO == 1){
            if(queueNumber == 0){
                dispatcher.changeState(Clock.readyQueue.get(0), ProcessStates.READY);
                Clock.readyQueue.add(Clock.readyQueue.get(0));
                Clock.readyQueue.remove(0);
                return timeQuantum;
            } else {
                dispatcher.changeState(Clock.readyQueue2.get(0), ProcessStates.READY);
                Clock.readyQueue2.add(Clock.readyQueue2.get(0));
                Clock.readyQueue2.remove(0);
                return timeQuantum;
            }
            
        }

        
        if(timeQuantum <= 0){
             //if timeQuantum is zero
                //if instruction is in critical section
                    
            if(p.pcb.entered_crit && !p.pcb.exited_crit) {
                

                if (p.pcb.EXIT_CRIT.get(0) == true) {
                    //decrement instruction
                    //decrement timeQuantum
                    p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) - 1);
                    //timeQuantum -= 1;
                    
                    //if instruction cycle is zero 
                    if(p.pcb.clockCycle.get(0) <= 0) {
                        //decrement instruction
                            //decrement timeQuantum 
                            //if instruction cycle is zero 
                                //call mutex release 
                                // set exit crit to true 
                                //remove instruction 
                                //decrement timeQuantum 
                        //        System.out.println(Mutex.available); 
                        Mutex.release();
                        GUI.busyWait.setText("");
                        //System.out.println(Mutex.available);
                        Memory.memorySize += p.pcb.memoryInstructions.get(0);
                        p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                        p.pcb.memoryInstructions.remove(0);
                        p.pcb.exited_crit = true;
                        p.pcb.entered_crit = false;
                        p.pcb.instructions.remove(0);
                        p.pcb.clockCycle.remove(0);
                        p.pcb.ENTER_CRIT.remove(0);
                        p.pcb.EXIT_CRIT.remove(0);

                        
                        //timeQuantum -= 1;
                        
                        //timeQuantum = 300;
                    }
                } else {
                    //just decrement critical section   
                    p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) - 1);

                    if (p.pcb.clockCycle.get(0) == 0) {
                        Memory.memorySize += p.pcb.memoryInstructions.get(0);
                        p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                        p.pcb.memoryInstructions.remove(0);
                        
                        p.pcb.instructions.remove(0);
                        p.pcb.clockCycle.remove(0);
                        p.pcb.ENTER_CRIT.remove(0);
                        p.pcb.EXIT_CRIT.remove(0);

                        
                    }
                
                }      
          
            }
                //if process instruction is critical section and hasn't entered
        } else if(p.pcb.ENTER_CRIT.get(0) == true && p.pcb.entered_crit == false) {
            
             
            //change boolean variable to entered for has_Entered 
                    //call mutex acquired
                    //decrement instruction 
                    //decrement timeQuantum
            //creates a busy wait if not available 
            if(Mutex.available){
                p.pcb.entered_crit = true;
                GUI.busyWait.setText("");

            Mutex.acquire();; 
            p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) -1);
            timeQuantum -= 1;
            } else {
                // System.out.println("Busy wait");
                GUI.busyWait.setText("Busy wait");
                // return timeQuantum;

            }
            

            //else if process instruction is critical section exit 
        } else if (p.pcb.EXIT_CRIT.get(0) == true) {
            //decrement instruction
            //decrement timeQuantum
            p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) - 1);
            timeQuantum -= 1;

            //if instruction cycle is zero 
            if(p.pcb.clockCycle.get(0) <= 0) {
                //decrement instruction
                    //decrement timeQuantum 
                    //if instruction cycle is zero 
                        //call mutex release 
                        // set exit crit to true 
                        //remove instruction 
                        //decrement timeQuantum 
                
                Mutex.release();
                GUI.busyWait.setText("");
                
                p.pcb.exited_crit = true;
                p.pcb.entered_crit = false;
                p.pcb.instructions.remove(0);
                p.pcb.clockCycle.remove(0);
                p.pcb.ENTER_CRIT.remove(0);
                p.pcb.EXIT_CRIT.remove(0);
                timeQuantum -= 1;

                Memory.memorySize += p.pcb.memoryInstructions.get(0);
                p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                p.pcb.memoryInstructions.remove(0);
            }       
        } else {
            p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) - 1);
            timeQuantum -= 1;
            if(p.pcb.clockCycle.get(0) <= 0){
                p.pcb.instructions.remove(0);
                p.pcb.clockCycle.remove(0);
                p.pcb.ENTER_CRIT.remove(0);
                p.pcb.EXIT_CRIT.remove(0);

                Memory.memorySize += p.pcb.memoryInstructions.get(0);
                p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                p.pcb.memoryInstructions.remove(0);
            }
        }
        //checks to see which queue to remove a process from 
        if(queueNumber == 0){
            dispatcher.changeState(Clock.readyQueue.get(0), ProcessStates.READY);
            Clock.readyQueue.add(Clock.readyQueue.get(0));
            Clock.readyQueue.remove(0);
            return timeQuantum;
        } else {
            dispatcher.changeState(Clock.readyQueue2.get(0), ProcessStates.READY);
            Clock.readyQueue2.add(Clock.readyQueue2.get(0));
            Clock.readyQueue2.remove(0);
            return timeQuantum;
        }         
    } 
}