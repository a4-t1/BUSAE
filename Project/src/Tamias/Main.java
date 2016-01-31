package Tamias;

public class Main {

	
	public static String nameOfTamia;

	public static void startTamiaFrame(String name) {
		nameOfTamia=name;
		new MainFrame();
	}
	
	public static String getNameOfEmploye(){
		return nameOfTamia;
	}
}
