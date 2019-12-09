package cmsc312;
enum ProcessStates{

       NEW, READY, RUN, WAIT, TERMINATE

   }

public class Process {
    PCB pcb;
    ProcessStates processStates;
    public Process() {
        processStates = ProcessStates.NEW;
        pcb = new PCB(); 
    }
}