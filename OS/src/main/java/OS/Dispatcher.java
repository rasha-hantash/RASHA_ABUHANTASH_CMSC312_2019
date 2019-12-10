package OS;
public class Dispatcher{

    public void changeState(Process process, ProcessStates pState){
        process.processStates = pState; 
    }
}