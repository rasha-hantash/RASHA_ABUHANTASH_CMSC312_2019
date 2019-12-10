package OS;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.*;
/**
 * Hello world!
 */
public final class GUI  extends Application {
  
    
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


    static Text programFile0 = new Text();
    static Text programFile1 = new Text();
    static Text programFile2 = new Text();
    static Text programFile3 = new Text();
    static Text programFile4 = new Text();
    static Text cyclesText = new Text();


    //text areas for each program file 
    static TextArea tA0 = new TextArea();
    static TextArea tA1 = new TextArea();
    static TextArea tA2 = new TextArea();
    static TextArea tA3 = new TextArea();
    static TextArea tA4 = new TextArea();
    static TextArea taClockCycle = new TextArea();

    static boolean start = false;
    Button startButton = new Button();
    static Text cycle= new Text();
    static Text memory= new Text();

    static Text timeQuantum = new Text();

    static Text programComplete = new Text();

    


   

    

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
        timeQuantum.setText("Time quantum: ");

        programFile0.setText("Enter the number of processes you want from programfile0.txt: ");
        programFile1.setText("Enter the number of processes you want from programfile1.txt: ");
        programFile2.setText("Enter the number of processes you want from programfile2.txt: ");
        programFile3.setText("Enter the number of processes you want from programfile3.txt: ");
        programFile4.setText("Enter the number of processes you want from programfile4.txt: ");
        cyclesText.setText("How many cycles would you like to run?");
        programComplete.setText("");

        


        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                    start = true;
                    Clock.processRequest[0] = Integer.parseInt(tA0.getText());
                    Clock.processRequest[1] = Integer.parseInt(tA1.getText());
                    Clock.processRequest[2] = Integer.parseInt(tA2.getText());
                    Clock.processRequest[3] = Integer.parseInt(tA3.getText());
                    Clock.processRequest[4] = Integer.parseInt(tA4.getText());
                    Clock.cycle = Integer.parseInt(taClockCycle.getText());

                    Clock.fileReader(Clock.processRequest[0], "src/main/java/OS/programfile0.txt");
                    Clock.fileReader(Clock.processRequest[1], "src/main/java/OS/programfile1.txt");
                    Clock.fileReader(Clock.processRequest[2], "src/main/java/OS/programfile2.txt");
                    Clock.fileReader(Clock.processRequest[3], "src/main/java/OS/programfile3.txt");
                    Clock.fileReader(Clock.processRequest[4], "src/main/java/OS/programfile4.txt");
                    SchedularThread schedularThread = new SchedularThread(Clock.readyQueue, Clock.rQTimeQuantum, 0);
                    schedularThread.start();
                
            }
        });

       
         //setting the position of the text 
        
        //Creating a Group object 
        StackPane layout = new StackPane();
        layout.getChildren().addAll(readyQueueText, runningProcess, readyPCBText , runningText, runningPCBText,
        readyProcess1, readyProcess2, readyProcess3, readyProcess4, readyProcess5, readyProcess6, readyProcess7, readyProcess8,
        readyProcess9, readyProcess10, waitingText, waitingPCBText, waitingProcess1, waitingProcess2, waitingProcess3, waitingProcess4, 
        waitingProcess5, waitingProcess6, waitingProcess7, waitingProcess8, waitingProcess9, waitingProcess10, cycle, memory,
        timeQuantum, startButton, programFile0, programFile1, programFile2, programFile3, programFile4, tA0,tA1,tA2,tA3,tA4, taClockCycle,
        cyclesText, programComplete);
        

        
        



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


        programFile0.setTranslateX(-420);
        programFile0.setTranslateY(0);
        programFile1.setTranslateX(-420);
        programFile1.setTranslateY(50);
        programFile2.setTranslateX(-420);
        programFile2.setTranslateY(100);
        programFile3.setTranslateX(-420);
        programFile3.setTranslateY(150);
        programFile4.setTranslateX(-420);
        programFile4.setTranslateY(200);
        cyclesText.setTranslateX(-485);
        cyclesText.setTranslateY(250);
        

        tA0.setTranslateX(-190);
        tA0.setTranslateY(0);
        tA0.setMaxWidth(100);
        tA0.setMaxHeight(10);
        tA1.setTranslateX(-190);
        tA1.setTranslateY(50);
        tA1.setMaxWidth(100);
        tA1.setMaxHeight(10);
        tA2.setTranslateX(-190);
        tA2.setTranslateY(100);
        tA2.setMaxWidth(100);
        tA2.setMaxHeight(10);
        tA3.setTranslateX(-190);
        tA3.setTranslateY(150);
        tA3.setMaxWidth(100);
        tA3.setMaxHeight(10);
        tA4.setTranslateX(-190);
        tA4.setTranslateY(200);
        tA4.setMaxWidth(100);
        tA4.setMaxHeight(10);
        taClockCycle.setMaxHeight(100);
        taClockCycle.setTranslateX(-190);
        taClockCycle.setTranslateY(250);
        taClockCycle.setMaxWidth(100);
        taClockCycle.setMaxHeight(10);



        startButton.setTranslateX(-550);
        startButton.setTranslateY(300); 

        timeQuantum.setTranslateX(-530);
        timeQuantum.setTranslateY(330);
        cycle.setTranslateX(-540);
        cycle.setTranslateY(350);

        memory.setTranslateX(-535);
        memory.setTranslateY(370);

        programComplete.setTranslateX(500);
        programComplete.setTranslateY(370);

         
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
        
	}
}
