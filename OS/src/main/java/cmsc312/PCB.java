package cmsc312;
import java.util.ArrayList;

public class PCB {

    ArrayList<String> instructions;
    ArrayList<Integer> clockCycle;
    ArrayList<Boolean> ENTER_CRIT;
    ArrayList<Boolean> EXIT_CRIT;
    boolean entered_crit;
    boolean exited_crit;
    int processID;
    int memory;
    public PCB() {
        this.processID = 0;
        this.instructions = new ArrayList<>();
        this.clockCycle = new ArrayList<>();
        this.ENTER_CRIT = new ArrayList<>();
        this.EXIT_CRIT = new ArrayList<>();
        entered_crit = false;
        exited_crit = false;
        memory = 0;
    }
}