package OS;
public final class Mutex {

	public static boolean available = true;
	public Mutex(){
		available = true;
	}

	public static void acquire(){
		
		available = false;
	}

	public static void release() {
		available = true;
	}

	   
}