
public class Printer extends Thread{

	String nom = "";
	public Printer(String nom){
		super(nom);
		this.nom = nom;
	}
	
	
	public void run(){
		for (int b = -128; b < 128; b++){
			System.out.println(nom + " ---> " +b);
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
