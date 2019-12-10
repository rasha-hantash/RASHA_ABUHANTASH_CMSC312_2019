package OS;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Clock {

    public static ArrayList<Process> waitingQueue = new ArrayList<>();
    public static ArrayList<Process> readyQueue = new ArrayList<>();
    public static ArrayList<Process> terminatedQueue = new ArrayList<>();
    public static int rQTimeQuantum = 40;
    static int clockCycle = 0;
    static int cycle = 0;
    static boolean goAgain = true;
    static int processID = 0;
    static int[] processRequest = { 0, 0, 0, 0, 0 };
    static boolean clickToStart = false;
    static Process runProcess = new Process(); 
    
    

    public static PCB randomizer(int random, PCB pcb) {
        
        int instructionIndex = 0;
        while (instructionIndex < pcb.clockCycle.size()) {

            int clockCycle = pcb.clockCycle.get(instructionIndex);
            int max = clockCycle + random;
            int min = clockCycle - random;
            Random r = new Random();
            int randomCycle = (int) r.nextInt((max - min) + 1) + min;
            randomCycle = (int) Math.abs(randomCycle);
            if (randomCycle == 0) {
                randomCycle++;
            }
            pcb.clockCycle.set(instructionIndex, randomCycle);
            instructionIndex++;
        }
        return pcb;
    }

    @SuppressWarnings("unchecked")
    public static void fileReader(int processRequested, String fileName) {
        //refactor later to scan whole line
        // Memory memory = new Memory();
        
        Dispatcher dispatcher = new Dispatcher();
        try {

            PCB pcb2 = new PCB();
            int memorySum = 0;
            
            System.out.println("Process ID " + processID);
            File file = new File(fileName); // change path name
            Scanner scan = new Scanner(file);
            boolean enter_crit;
            boolean exit_crit;
            while (scan.hasNext()) {
                String sNext = scan.next();
                //getting all the critical section instruction
                // and flagging the first and last critical section
                exit_crit = false;
                enter_crit = false;
                // if(sNext.equals("Memory:")){
                //     sNext = scan.next();
                //     int n = Integer.parseInt(sNext);
                //     pcb2.memory = n;
                // }
                if (sNext.equals("CRIT")) {
                    sNext = scan.next();
                    if (sNext.equals("+")) {
                        while (!sNext.equals("CRIT")) {
                            if(!sNext.equals("CALCULATE")){
                                sNext = scan.next();
                            }
                            pcb2.ENTER_CRIT.add(false);
                            pcb2.EXIT_CRIT.add(false);
                            
                            pcb2.instructions.add(sNext);
                            sNext = scan.next();
                            int n = Integer.parseInt(sNext);
                            pcb2.clockCycle.add(n);

                            //grabbing memory
                            sNext = scan.next();
                            n = Integer.parseInt(sNext);
                            memorySum+= n;
                            pcb2.memoryInstructions.add(n);

                            if(!enter_crit){
                                pcb2.ENTER_CRIT.set(pcb2.EXIT_CRIT.size()-1, true);
                                enter_crit = true;
                            }
                            sNext = scan.next();
                        }
                        sNext = scan.next();
                        if (sNext.equals("-")) {
                            if(!exit_crit){
                                pcb2.EXIT_CRIT.set(pcb2.EXIT_CRIT.size()-1, true);
                                exit_crit = true;
                            }
                            // else {
                            //     pcb2.EXIT_CRIT.add(false);
                            // }
                        }
                    }
                    
                }
                
                if (sNext.equals("CALCULATE") || sNext.equals("I/0") || sNext.equals("YIELD") || sNext.equals("OUT")) {
                    //use out to indicate that a process has terminated 
                    pcb2.instructions.add(sNext);
                    int n = Integer.parseInt(scan.next());
                    pcb2.clockCycle.add(n);
                    pcb2.ENTER_CRIT.add(false);
                    pcb2.EXIT_CRIT.add(false);

                    //grabbing memory
                    sNext = scan.next();
                    n = Integer.parseInt(sNext);
                    memorySum+= n;
                    pcb2.memoryInstructions.add(n);
                }
            }

            //create the number of processes requested for that program file
            //and randomize the instructions 
            for (int i = 0; i < processRequested; i++) {
                Process process = new Process();
                Random r = new Random();
                int randomInt = (int) r.nextInt(11);
                randomizer(randomInt, pcb2);
                processID++;
                process.pcb.processID = processID;
                
                
                process.pcb.clockCycle = (ArrayList<Integer>) pcb2.clockCycle.clone();
                process.pcb.instructions = (ArrayList<String>) pcb2.instructions.clone();
                process.pcb.ENTER_CRIT = (ArrayList<Boolean>) pcb2.ENTER_CRIT.clone();
                process.pcb.EXIT_CRIT = (ArrayList<Boolean>) pcb2.EXIT_CRIT.clone();
                process.pcb.memoryInstructions= (ArrayList<Integer>) pcb2.memoryInstructions.clone();
                process.pcb.totalMemory = memorySum;
                
                
                
                
                if(Memory.memorySize > memorySum) {
                    //change this to see if i can load it into memory if not add onto waiting  
                    Memory.memorySize -= memorySum;
                    dispatcher.changeState(process, ProcessStates.READY);
                    readyQueue.add(process);
                } else {
                    dispatcher.changeState(process, ProcessStates.WAIT);
                    waitingQueue.add(process);
                }
                

            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

}