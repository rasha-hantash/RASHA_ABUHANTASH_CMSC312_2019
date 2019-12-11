package OS;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class SchedularThread extends Thread {
    ArrayList<Process> readyQueue = new ArrayList<Process>();
    ArrayList<Process> readyQueue2 = new ArrayList<Process>();
    int timeQuantum;
    int timeQuantum2;

    WaitingQueue waitQueue;
    Schedular schedular = new Schedular();
    ReentrantLock lock = new ReentrantLock();

    public SchedularThread(ArrayList<Process> readyQueue, ArrayList<Process> readyQueue2,int timeQuantum, int timeQuantum2) {
        this.readyQueue = readyQueue;
        this.readyQueue2 = readyQueue2;
        this.timeQuantum = timeQuantum;
        this.timeQuantum2 = timeQuantum2;
        
        waitQueue = new WaitingQueue();

    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        while(true){
            if(GUI.start) {
            while (Clock.cycle > 0) {
                while (readyQueue.size() != 0 || Clock.waitingQueue.size() != 0 || readyQueue2.size()!=0) {
                    Clock.clockCycle++;
                    try {
                        Thread.sleep(100);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeQuantum = schedular.schedular(readyQueue, timeQuantum, 0);
                    timeQuantum2 = schedular.schedular(readyQueue2, timeQuantum2, 1);
                    waitQueue.waitingQueue();
                    lock.lock();
                    String criticalSection = "" + Clock.runProcess.pcb.entered_crit;

                    //READY QUEUE GUI 
                    if(Clock.readyQueue.size() >=2){
                        GUI.readyProcess1.setText( Clock.readyQueue.get(1).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(1).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(1).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess1.setText("");
                    }
                    
                    if(Clock.readyQueue.size()>=3){
                        GUI.readyProcess2.setText( Clock.readyQueue.get(2).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(2).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(2).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess2.setText("");
                    }
                    
                    if(Clock.readyQueue.size()>=4){
                        GUI.readyProcess3.setText( Clock.readyQueue.get(3).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(3).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(3).processStates.toString()
                        + " \t\t\t " + "FALSE"); 
                    } else {
                        GUI.readyProcess3.setText("");
                    }
    
                    if(Clock.readyQueue.size()>=5){
                        GUI.readyProcess4.setText( Clock.readyQueue.get(4).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(4).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(4).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess4.setText("");
                    }
                                                        
                    
                    if(Clock.readyQueue.size()>=6){
                        GUI.readyProcess5.setText( Clock.readyQueue.get(5).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(5).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(5).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess5.setText("");
                    }
    
                    if(Clock.readyQueue.size()>=7){
                        GUI.readyProcess6.setText( Clock.readyQueue.get(6).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(6).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(6).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess6.setText("");
                    }
    
    
                    if(Clock.readyQueue.size()>=8){
                        GUI.readyProcess7.setText( Clock.readyQueue.get(7).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(7).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue.get(7).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.readyProcess7.setText("");
                    }
    
    
                    if(Clock.readyQueue.size()>=9){
                        GUI.readyProcess8.setText( Clock.readyQueue.get(8).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(8).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue.get(8).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.readyProcess8.setText("");
                    }
    
    
                    if(Clock.readyQueue.size()>=10){
                        GUI.readyProcess9.setText( Clock.readyQueue.get(9).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(9).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue.get(9).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess9.setText("");
                    }
    
                    if(Clock.readyQueue.size()>=11){
                        GUI.readyProcess10.setText( Clock.readyQueue.get(10).pcb.processID 
                        + "\t\t" + Clock.readyQueue.get(10).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue.get(10).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.readyProcess10.setText("");
                    }
                    //READY QUEUE 2 GUI
                    if(Clock.readyQueue2.size() >=2){
                        GUI.ready2Process1.setText( Clock.readyQueue2.get(1).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(1).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(1).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process1.setText("");
                    }

                    if(Clock.readyQueue2.size()>=3){
                        GUI.ready2Process2.setText( Clock.readyQueue2.get(2).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(2).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(2).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process2.setText("");
                    }

                    if(Clock.readyQueue2.size()>=4){
                        GUI.ready2Process3.setText( Clock.readyQueue2.get(3).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(3).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(3).processStates.toString()
                        + " \t\t\t " + "FALSE"); 
                    } else {
                        GUI.ready2Process3.setText("");
                    }

                    if(Clock.readyQueue2.size()>=5){
                        GUI.ready2Process4.setText( Clock.readyQueue2.get(4).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(4).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(4).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process4.setText("");
                    }
                                                        
                    
                    if(Clock.readyQueue2.size()>=6){
                        GUI.ready2Process5.setText( Clock.readyQueue2.get(5).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(5).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(5).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process5.setText("");
                    }
    
                    if(Clock.readyQueue2.size()>=7){
                        GUI.ready2Process6.setText( Clock.readyQueue2.get(6).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(6).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(6).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process6.setText("");
                    }
    
    
                    if(Clock.readyQueue2.size()>=8){
                        GUI.ready2Process7.setText( Clock.readyQueue2.get(7).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(7).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue2.get(7).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.ready2Process7.setText("");
                    }
    
    
                    if(Clock.readyQueue2.size()>=9){
                        GUI.ready2Process8.setText( Clock.readyQueue2.get(8).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(8).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue2.get(8).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.ready2Process8.setText("");
                    }
    
    
                    if(Clock.readyQueue2.size()>=10){
                        GUI.ready2Process9.setText( Clock.readyQueue2.get(9).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(9).pcb.totalMemory
                        + " \t\t\t " + Clock.readyQueue2.get(9).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process9.setText("");
                    }
    
                    if(Clock.readyQueue2.size()>=11){
                        GUI.ready2Process10.setText( Clock.readyQueue2.get(10).pcb.processID 
                        + "\t\t" + Clock.readyQueue2.get(10).pcb.totalMemory 
                        + " \t\t\t " + Clock.readyQueue2.get(10).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.ready2Process10.setText("");
                    }

                    //TERMINATED QUEUE GUI
                    if(Clock.terminatedQueue.size() >=1){
                        GUI.terminatedProcess1.setText( Clock.terminatedQueue.get(0).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(0).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(0).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess1.setText("");
                    }


                    if(Clock.terminatedQueue.size() >=2){
                        GUI.terminatedProcess2.setText( Clock.terminatedQueue.get(1).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(1).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(1).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess2.setText("");
                    }
                    
                    if(Clock.terminatedQueue.size()>=3){
                        GUI.terminatedProcess3.setText( Clock.terminatedQueue.get(2).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(2).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(2).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess3.setText("");
                    }
                    
                    if(Clock.terminatedQueue.size()>=4){
                        GUI.terminatedProcess4.setText( Clock.terminatedQueue.get(3).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(3).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(3).processStates.toString()
                        + " \t\t\t " + "FALSE"); 
                    } else {
                        GUI.terminatedProcess4.setText("");
                    }
    
                    if(Clock.terminatedQueue.size()>=5){
                        GUI.terminatedProcess5.setText( Clock.terminatedQueue.get(4).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(4).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(4).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess5.setText("");
                    }
                                                        
                    
                    if(Clock.terminatedQueue.size()>=6){
                        GUI.terminatedProcess6.setText( Clock.terminatedQueue.get(5).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(5).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(5).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess6.setText("");
                    }
    
                    if(Clock.terminatedQueue.size()>=7){
                        GUI.terminatedProcess7.setText( Clock.terminatedQueue.get(6).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(6).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(6).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess7.setText("");
                    }
    
    
                    if(Clock.terminatedQueue.size()>=8){
                        GUI.terminatedProcess8.setText( Clock.terminatedQueue.get(7).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(7).pcb.totalMemory 
                        + " \t\t\t " + Clock.terminatedQueue.get(7).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.terminatedProcess8.setText("");
                    }
    
    
                    if(Clock.terminatedQueue.size()>=9){
                        GUI.terminatedProcess9.setText( Clock.terminatedQueue.get(8).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(8).pcb.totalMemory 
                        + " \t\t\t " + Clock.terminatedQueue.get(8).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.terminatedProcess9.setText("");
                    }
    
    
                    if(Clock.terminatedQueue.size()>=10){
                        GUI.terminatedProcess10.setText( Clock.terminatedQueue.get(9).pcb.processID 
                        + "\t\t" + Clock.terminatedQueue.get(9).pcb.totalMemory
                        + " \t\t\t " + Clock.terminatedQueue.get(9).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.terminatedProcess10.setText("");
                    }
    
                    
                    //WAITING QUEUE GUI 
                    if(Clock.waitingQueue.size() >=1){
                        GUI.waitingProcess1.setText( Clock.waitingQueue.get(0).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(0).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(0).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess1.setText("");
                    }
                    
                    if(Clock.waitingQueue.size()>=2){
                        GUI.waitingProcess2.setText( Clock.waitingQueue.get(1).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(1).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(1).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess2.setText("");
                    }
                    
                    if(Clock.waitingQueue.size()>=3){
                        GUI.waitingProcess3.setText( Clock.waitingQueue.get(2).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(2).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(2).processStates.toString()
                        + " \t\t\t " + "FALSE"); 
                    } else {
                        GUI.waitingProcess3.setText("");
                    }
    
                    if(Clock.waitingQueue.size()>=4){
                        GUI.waitingProcess4.setText( Clock.waitingQueue.get(3).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(3).pcb.totalMemory 
                        + " \t\t\t " + Clock.waitingQueue.get(3).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess4.setText("");
                    }
                                                        
                    
                    if(Clock.waitingQueue.size()>=5){
                        GUI.waitingProcess5.setText( Clock.waitingQueue.get(4).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(4).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(4).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess5.setText("");
                    }
    
                    if(Clock.waitingQueue.size()>=6){
                        GUI.waitingProcess6.setText( Clock.waitingQueue.get(5).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(5).pcb.totalMemory 
                        + " \t\t\t " + Clock.waitingQueue.get(5).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess6.setText("");
                    }
    
    
                    if(Clock.waitingQueue.size()>=7){
                        GUI.waitingProcess7.setText( Clock.waitingQueue.get(6).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(6).pcb.totalMemory 
                        + " \t\t\t " + Clock.waitingQueue.get(6).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.waitingProcess7.setText("");
                    }
    
    
                    if(Clock.waitingQueue.size()>=8){
                        GUI.waitingProcess8.setText( Clock.waitingQueue.get(7).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(7).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(7).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    }else {
                        GUI.waitingProcess8.setText("");
                    }
    
    
                    if(Clock.waitingQueue.size()>=9){
                        GUI.waitingProcess9.setText( Clock.waitingQueue.get(8).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(8).pcb.totalMemory 
                        + " \t\t\t " + Clock.waitingQueue.get(8).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess9.setText("");
                    }
    
                    if(Clock.waitingQueue.size()>=10){
                        GUI.waitingProcess10.setText( Clock.waitingQueue.get(9).pcb.processID 
                        + "\t\t" + Clock.waitingQueue.get(9).pcb.totalMemory
                        + " \t\t\t " + Clock.waitingQueue.get(9).processStates.toString()
                        + " \t\t\t " + "FALSE");
                    } else {
                        GUI.waitingProcess10.setText("");
                    }
    
    
    
                    
                    GUI.runningProcess.setText( Clock.runProcess.pcb.processID 
                    + "\t\t" + Clock.runProcess.pcb.totalMemory 
                    + " \t\t\t " + "RUN"
                    + " \t\t\t " + criticalSection.toUpperCase());  
    
                    
                    GUI.cycle.setText("Cycles left: " + Clock.cycle);
                    GUI.memory.setText("Memory left:" + Memory.memorySize);
                    GUI.timeQuantum1.setText("Time quantum 1: " + timeQuantum);
                    GUI.timeQuantum2.setText("Time quantum 2: " + timeQuantum2);
    
                    lock.unlock();
                    
                }
                System.out.println(Clock.cycle + " cycles left");
                
                Clock.cycle--;
                if(Clock.cycle > 0){

                    Clock.fileReader(Clock.processRequest[0], "OS/src/main/java/OS/programfile0.txt");
                    Clock.fileReader(Clock.processRequest[1], "OS/src/main/java/OS/programfile1.txt");
                    Clock.fileReader(Clock.processRequest[2], "OS/src/main/java/OS/programfile2.txt");
                    Clock.fileReader(Clock.processRequest[3], "OS/src/main/java/OS/programfile3.txt");
                    Clock.fileReader(Clock.processRequest[4], "OS/src/main/java/OS/programfile4.txt");

                    // Clock.fileReader(Clock.processRequest[0], "src/main/java/OS/programfile0.txt");
                    // Clock.fileReader(Clock.processRequest[1], "src/main/java/OS/programfile1.txt");
                    // Clock.fileReader(Clock.processRequest[2], "src/main/java/OS/programfile2.txt");
                    // Clock.fileReader(Clock.processRequest[3], "src/main/java/OS/programfile3.txt");
                    // Clock.fileReader(Clock.processRequest[4], "src/main/java/OS/programfile4.txt");
                    
                } else {
                    GUI.programComplete.setText("PROGRAM COMPLETE");
                }
                
                
            }
                GUI.readyProcess1.setText("");
                GUI.readyProcess2.setText("");
                GUI.readyProcess3.setText("");
                GUI.readyProcess4.setText("");
                GUI.readyProcess5.setText("");
                GUI.readyProcess6.setText("");
                GUI.readyProcess7.setText("");
                GUI.readyProcess8.setText("");
                GUI.readyProcess9.setText("");
                GUI.readyProcess10.setText("");

                GUI.ready2Process1.setText("");
                GUI.ready2Process2.setText("");
                GUI.ready2Process3.setText("");
                GUI.ready2Process4.setText("");
                GUI.ready2Process5.setText("");
                GUI.ready2Process6.setText("");
                GUI.ready2Process7.setText("");
                GUI.ready2Process8.setText("");
                GUI.ready2Process9.setText("");
                GUI.ready2Process10.setText("");


                GUI.terminatedProcess1.setText("");
                GUI.terminatedProcess2.setText("");
                GUI.terminatedProcess3.setText("");
                GUI.terminatedProcess4.setText("");
                GUI.terminatedProcess5.setText("");
                GUI.terminatedProcess6.setText("");
                GUI.terminatedProcess7.setText("");
                GUI.terminatedProcess8.setText("");
                GUI.terminatedProcess9.setText("");
                GUI.terminatedProcess10.setText("");
    
                GUI.runningProcess.setText("");
                
                GUI.waitingProcess1.setText("");
                GUI.waitingProcess2.setText("");
                GUI.waitingProcess3.setText("");
                GUI.waitingProcess4.setText("");
                GUI.waitingProcess5.setText("");
                GUI.waitingProcess6.setText("");
                GUI.waitingProcess7.setText("");
                GUI.waitingProcess8.setText("");
                GUI.waitingProcess9.setText("");
                GUI.waitingProcess10.setText("");
    
                GUI.cycle.setText("Cycles left: " + Clock.cycle);
                GUI.memory.setText("Memory left:" + Memory.memorySize);
 
            }
        }
        
        
    }
   

}