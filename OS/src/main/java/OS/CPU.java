package OS;
import java.util.Random;

public class CPU {
    Dispatcher dispatcher = new Dispatcher();
    int externalEventIO = 0;
    //public int timeQuantum = 40;
    //scheduler should check to see if process has already exited a a critical section
    //before entering another one 
    //check to see if the start of the new instruction is a critical section and if it had already 
    //exited one 
    //if yes remove that process from running 
    //let cpu handle mutex stuff

    public int cpu(Process p, int timeQuantum) {
        
        Random random = new Random();
        externalEventIO = random.nextInt(10);

        if(externalEventIO == 1){
            // System.out.println("External event IO " + externalEventIO);
            dispatcher.changeState(Clock.readyQueue.get(0), ProcessStates.READY);
            Clock.readyQueue.add(Clock.readyQueue.get(0));
            Clock.readyQueue.remove(0);
            return timeQuantum;
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
                        //System.out.println(Mutex.available);
                        p.pcb.exited_crit = true;
                        p.pcb.entered_crit = false;
                        p.pcb.instructions.remove(0);
                        p.pcb.clockCycle.remove(0);
                        p.pcb.ENTER_CRIT.remove(0);
                        p.pcb.EXIT_CRIT.remove(0);

                        Memory.memorySize += p.pcb.memoryInstructions.get(0);
                        p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                        p.pcb.memoryInstructions.remove(0);
                        //timeQuantum -= 1;
                        
                        //timeQuantum = 300;
                    }
                } else {
                    //just decrement critical section   
                    p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) - 1);

                    if (p.pcb.clockCycle.get(0) == 0) {
                        p.pcb.instructions.remove(0);
                        p.pcb.clockCycle.remove(0);
                        p.pcb.ENTER_CRIT.remove(0);
                        p.pcb.EXIT_CRIT.remove(0);

                        Memory.memorySize += p.pcb.memoryInstructions.get(0);
                        p.pcb.totalMemory -= p.pcb.memoryInstructions.get(0);
                        p.pcb.memoryInstructions.remove(0);
                    }
                
                }      
            // } else {
                //else 
                    //set timeQuantum to 300 
            //     timeQuantum = 300; 
            // }
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
            // System.out.println(Mutex.available); 

            Mutex.acquire();
            // System.out.println(Mutex.available); 
            p.pcb.clockCycle.set(0, p.pcb.clockCycle.get(0) -1);
            timeQuantum -= 1;
            } else {
                System.out.println("Busy wait");
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
        dispatcher.changeState(Clock.readyQueue.get(0), ProcessStates.READY);
        return timeQuantum;                  
    } 
}