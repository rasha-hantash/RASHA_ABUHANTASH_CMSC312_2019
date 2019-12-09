package cmsc312;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.*;
/**
 * Hello world!
 */
public final class GUI  extends Application {
    // private App() {
    // }

    static Text readyQueueText = new Text();
    static Text readyPCBText = new Text(); 
    static Text readyProcess1= new Text();
    static Text readyProcess2= new Text();
    static Text readyProcess3= new Text();
    static Text readyProcess4= new Text();
    static Text readyProcess5= new Text();
    static Text readyProcess6= new Text();
    static Text readyProcess7= new Text();
    static Text readyProcess8= new Text();
    static Text readyProcess9= new Text();
    static Text readyProcess10= new Text();
    
    // static Text rQueueText = new Text();
    static Text runningText = new Text();
    static Text runningPCBText = new Text();
    static Text runningProcess = new Text();


    static Text waitingText = new Text();
    static Text waitingPCBText = new Text();
    static Text waitingProcess1= new Text();
    static Text waitingProcess2= new Text();
    static Text waitingProcess3= new Text();
    static Text waitingProcess4= new Text();
    static Text waitingProcess5= new Text();
    static Text waitingProcess6= new Text();
    static Text waitingProcess7= new Text();
    static Text waitingProcess8= new Text();
    static Text waitingProcess9= new Text();
    static Text waitingProcess10= new Text();

    static Text cycle= new Text();
    static Text memory= new Text();



   

    

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        launch(args);
        
    }

	@Override
	public void start(Stage stage) throws Exception {
		
            //Creating a Text object 
       
           
      
        //Setting the text to be added. 

        readyQueueText.setText("Ready Queue");
        readyPCBText.setText("PID \t Memory \t Process State \t Critical Section");


        runningText.setText("Running");
        runningPCBText.setText("PID \t Memory \t Process State \t Critical Section");


        waitingText.setText("WaitingQueue");
        waitingPCBText.setText("PID \t Memory \t Process State \t Critical Section");

        cycle.setText("Cycles left: " );
        memory.setText("Memory left:" );
       
         //setting the position of the text 
        
        //Creating a Group object 
        StackPane layout = new StackPane();
        layout.getChildren().addAll(readyQueueText, runningProcess, readyPCBText , runningText, runningPCBText,
        readyProcess1, readyProcess2, readyProcess3, readyProcess4, readyProcess5, readyProcess6, readyProcess7, readyProcess8,
        readyProcess9, readyProcess10, waitingText, waitingPCBText, waitingProcess1, waitingProcess2, waitingProcess3, waitingProcess4, 
        waitingProcess5, waitingProcess6, waitingProcess7, waitingProcess8, waitingProcess9, waitingProcess10, cycle, memory);
        

        
        



        readyQueueText.setTranslateX(-550); 
        readyQueueText.setTranslateY(-300); 
        readyPCBText.setTranslateX(-450);
        readyPCBText.setTranslateY(-275);
        readyProcess1.setTranslateX(-460);
        readyProcess1.setTranslateY(-250);
        readyProcess2.setTranslateX(-460);
        readyProcess2.setTranslateY(-240);
        readyProcess3.setTranslateX(-460);
        readyProcess3.setTranslateY(-230);
        readyProcess4.setTranslateX(-460);
        readyProcess4.setTranslateY(-220);
        readyProcess5.setTranslateX(-460);
        readyProcess5.setTranslateY(-210);
        readyProcess6.setTranslateX(-460);
        readyProcess6.setTranslateY(-200);
        readyProcess7.setTranslateX(-460);
        readyProcess7.setTranslateY(-190);
        readyProcess8.setTranslateX(-460);
        readyProcess8.setTranslateY(-180);
        readyProcess9.setTranslateX(-460);
        readyProcess9.setTranslateY(-170);
        readyProcess10.setTranslateX(-460);
        readyProcess10.setTranslateY(-160);


        runningText.setTranslateX(-215);
        runningText.setTranslateY(-300);
        runningPCBText.setTranslateX(-100);
        runningPCBText.setTranslateY(-275);

        runningProcess.setTranslateX(-120);
        runningProcess.setTranslateY(-250);


        waitingText.setTranslateX(200);
        waitingText.setTranslateY(-300);
        
        waitingPCBText.setTranslateX(300);
        waitingPCBText.setTranslateY(-275);


        waitingProcess1.setTranslateX(275);
        waitingProcess1.setTranslateY(-250);
        waitingProcess2.setTranslateX(275);
        waitingProcess2.setTranslateY(-240);
        waitingProcess3.setTranslateX(275);
        waitingProcess3.setTranslateY(-230);
        waitingProcess4.setTranslateX(275);
        waitingProcess4.setTranslateY(-220);
        waitingProcess5.setTranslateX(275);
        waitingProcess5.setTranslateY(-210);
        waitingProcess6.setTranslateX(275);
        waitingProcess6.setTranslateY(-200);
        waitingProcess7.setTranslateX(275);
        waitingProcess7.setTranslateY(-190);
        waitingProcess8.setTranslateX(275);
        waitingProcess8.setTranslateY(-180);
        waitingProcess9.setTranslateX(275);
        waitingProcess9.setTranslateY(-170);
        waitingProcess10.setTranslateX(275);
        waitingProcess10.setTranslateY(-160);

        cycle.setTranslateX(-550);
        cycle.setTranslateY(350);

        memory.setTranslateX(-540);
        memory.setTranslateY(370);
        





         
        Scene scene = new Scene(layout, 1200, 800);
        //Call the clock class here 
        //Ask for user input here
        //Then call the class 
        stage.setTitle("My JavaFX Application");
        // for (int i = 0; i < 1000000000; i++) {
        //     ta.appendText("lahsdvl lefwq gwlqwkjgl kqwldfwkhevf.");
        // }
        stage.setScene(scene);
        stage.show();
        Clock.clock();
            
           
            
        SchedularThread schedularThread = new SchedularThread(Clock.readyQueue, Clock.rQTimeQuantum, 0);
        schedularThread.start();
           
        
	}
}
