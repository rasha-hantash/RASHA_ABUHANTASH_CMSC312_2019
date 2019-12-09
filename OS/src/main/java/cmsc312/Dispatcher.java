package cmsc312;
public class Dispatcher{

    public void changeState(Process process, ProcessStates pState){
        process.processStates = pState; 
    }
}