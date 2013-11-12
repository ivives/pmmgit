
public class Carrera {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TortugaThread tortuga = new TortugaThread();
		LiebreThread liebre = new Thread(new LiebreThread()); // LiebreThread implements Runnable
		System.out.println(tortuga.isAlive());
		System.out.println(liebre.isAlive());
		
		tortuga.start();
		liebre.start();
		System.out.println("¿Ha comenzado Tortuga?: " + tortuga.isAlive());
		System.out.println("¿Ha comenzado Liebre?: " + liebre.isAlive());
		

	}

}
