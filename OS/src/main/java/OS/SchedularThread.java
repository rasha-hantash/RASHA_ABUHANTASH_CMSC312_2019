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
                while (readyQueue.size() != 0 || Clock.waitingQueue.size() != 0) {
                    Clock.clockCycle++;
                    try {
                        Thread.sleep(20);
                     } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   lock.lock();
                    String criticalSection = "" + Clock.runProcess.pcb.entered_crit;
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
                    GUI.timeQuantum.setText("Time quantum: " + timeQuantum);
    
                    lock.unlock();
                    timeQuantum = schedular.schedular(readyQueue, timeQuantum, 0);
                    timeQuantum2 = schedular.schedular(readyQueue2, timeQuantum, 1);
                    waitQueue.waitingQueue();
                }
                System.out.println(Clock.cycle + " cycles left");
                
                Clock.cycle--;
                if(Clock.cycle > 0){
                    Clock.fileReader(Clock.processRequest[0], "src/main/java/OS/programfile0.txt");
                    Clock.fileReader(Clock.processRequest[1], "src/main/java/OS/programfile1.txt");
                    Clock.fileReader(Clock.processRequest[2], "src/main/java/OS/programfile2.txt");
                    Clock.fileReader(Clock.processRequest[3], "src/main/java/OS/programfile3.txt");
                    Clock.fileReader(Clock.processRequest[4], "src/main/java/OS/programfile4.txt");
                    
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