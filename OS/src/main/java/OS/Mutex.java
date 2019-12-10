package OS;
public final class Mutex {

	public static boolean available = true;
	//what happens when process exceeds time quantum gets put back 
	//and the next process wants to also hit critical section? 
	public Mutex(){
		available = true;
	}

	public static void acquire(){
		//while(!available) //busy wait? 
		available = false;
	}

	public static void release() {
		available = true;
	}

	   
}