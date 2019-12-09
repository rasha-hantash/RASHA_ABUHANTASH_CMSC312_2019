package cmsc312;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class Clock {

    public static ArrayList<Process> waitingQueue = new ArrayList<>();
    public static ArrayList<Process> readyQueue = new ArrayList<>();
    public static int rQTimeQuantum = 40;
    static int clockCycle = 0;
    static int cycle = 0;
    static boolean goAgain = true;
    static int processID = 0;
    static int[] processRequest = { 0, 0, 0, 0, 0 };
    static boolean clickToStart = false;
    static Process runProcess = new Process(); 
    public static void clock() {
    Scanner scanner = new Scanner(System.in);
        
            

        //may need to change path file 
        System.out.println("Enter the number of processes you want from programfile0.txt: ");
        processRequest[0] = Integer.parseInt(scanner.next());
        fileReader(processRequest[0], "OS/src/main/java/cmsc312/programfile0.txt");
        System.out.println("Enter the number of processes you want from programfile1.txt: ");
        processRequest[1] = Integer.parseInt(scanner.next());
        fileReader(processRequest[1], "OS/src/main/java/cmsc312/programfile1.txt");
        System.out.println("Enter the number of processes you want from programfile2.txt: ");
        processRequest[2] = Integer.parseInt(scanner.next());
        fileReader(processRequest[2], "OS/src/main/java/cmsc312/programfile2.txt");
        System.out.println("Enter the number of processes you want from programfile3.txt: ");
        processRequest[3] = Integer.parseInt(scanner.next());
        fileReader(processRequest[3], "OS/src/main/java/cmsc312/programfile3.txt");
        System.out.println("Enter the number of processes you want from programfile4.txt: ");
        processRequest[4] = Integer.parseInt(scanner.next());
        fileReader(processRequest[4], "OS/src/main/java/cmsc312/programfile4.txt");
        System.out.println("How many cycles would you like to run?");
        cycle = scanner.nextInt();

        scanner.close();
        //Schedular schedular = new Schedular();
        
        //int clock = 0;
        

        
        

        

    }

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

    public static void fileReader(int processRequested, String fileName) {
        //refactor later to scan whole line
        // Memory memory = new Memory();
        
        Dispatcher dispatcher = new Dispatcher();
        try {

            PCB pcb2 = new PCB();
            
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
                if(sNext.equals("Memory:")){
                    sNext = scan.next();
                    int n = Integer.parseInt(sNext);
                    pcb2.memory = n;
                }
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
                
                if (sNext.equals("CALCULATE") || sNext.equals("I/0")) {
                    pcb2.instructions.add(sNext);
                    int n = Integer.parseInt(scan.next());
                    pcb2.clockCycle.add(n);
                    pcb2.ENTER_CRIT.add(false);
                    pcb2.EXIT_CRIT.add(false);
                }
            }

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
                process.pcb.memory = pcb2.memory;
                
                
                
                if(Memory.memorySize > pcb2.memory) {
                    //change this to see if i can load it into memory if not add onto waiting  
                    Memory.memorySize -= pcb2.memory;
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