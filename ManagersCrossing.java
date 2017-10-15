// Terrence Urich
import java.util.Scanner;

public class ManagersCrossing {
	// current position of boat = true means boat on the starting side of the river
	public static boolean currentBoat = true;
	// current position of manager for company A, B, C
	public static boolean currentManagerA = true, currentManagerB = true, currentManagerC = true;
	// current position of engineer for company A, B, C
	public static boolean currentEngineerA = true, currentEngineerB = true, currentEngineerC = true; 
	// next position of manager for company A, B, C
	public static boolean nextManagerA = true, nextManagerB = true, nextManagerC = true;
	// next position of engineer for company A, B, C
	public static boolean nextEngineerA = true, nextEngineerB = true, nextEngineerC = true; 
	
	public static void main(String[] args) {
	
	Scanner s = new Scanner(System.in);
    System.out.print("Manager A: ");
    boolean currentManagerA = s.nextBoolean();
    System.out.print("Manager B: ");
    boolean currentManagerB = s.nextBoolean();
	System.out.print("Manager C: ");
    boolean currentManagerC = s.nextBoolean();
    System.out.print("Engineer A: ");
	boolean currentEngineerA = s.nextBoolean();
	System.out.print("Engineer B: ");
    boolean currentEngineerB = s.nextBoolean();
    System.out.print("Engineer C: ");
	boolean currentEngineerC = s.nextBoolean();

	if ((currentManagerA = true) & (currentManagerB = false) & (currentManagerC = false) & (currentEngineerA= true) & (currentEngineerB= false) & (currentEngineerC=false)) {
		System.out.println("Good move!");
		System.out.println("Boat: "+currentBoat);
		System.out.println("Manager A: " +currentManagerA);
		System.out.println("Manager B: "+currentManagerB);
		System.out.println("Manager C: "+currentManagerC);
		System.out.println("Engineer A: "+currentEngineerA);
		System.out.println("Engineer B: "+currentEngineerB);
		System.out.println("Engineer C: "+currentEngineerC);
	} else {
		System.out.println("Bad move!");
	}


	
	}

}
