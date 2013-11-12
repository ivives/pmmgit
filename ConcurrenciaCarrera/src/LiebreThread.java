
public class LiebreThread  extends Thread{
	public void run(){
		int i = 0;
		System.out.println("Comienza la liebre.");
		while(i<5){
			try{
				Thread.sleep(1000);
				System.out.println("Liebre.");
			}
			catch(InterruptedException ex){
			
			}
			i++;
		}
		System.out.println("Termina la liebre.");
	}

}
