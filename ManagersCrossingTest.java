import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Test of CS 142 Assignment 2 by Martin Hock (Version of 8:00 PM 10/11/2017)
 * 
 * You may only use this code as a student of Martin Hock, CS 142 Fall 2017.
 */

public class ManagersCrossingTest {

	public static boolean[] n(boolean[] a) {
		boolean[] b = Arrays.copyOf(a, a.length);
		for (int i = 0; i < b.length; i++) {
			b[i] = !b[i];
		}
		return b;
	}
	
	public static void printError(boolean[] current, boolean[] next, String error, String received) {
		System.out.println("I saw a problem when I set the following variables: ");
		System.out.println("public static boolean currentBoat = " + current[6]);
		System.out.println("public static boolean currentManagerA = " + current[0] + ", currentManagerB = " + current[1]
				+ ", currentManagerC = " + current[2] + ";");
		System.out.println("public static boolean currentEngineerA = " + current[3] + ", currentEngineerB = "
				+ current[4] + ", currentEngineerC = " + current[5] + ";");
		System.out.println("public static boolean nextManagerA = " + next[0] + ", nextManagerB = " + next[1]
				+ ", nextManagerC = " + next[2] + ";");
		System.out.println("public static boolean nextEngineerA = " + next[3] + ", nextEngineerB = " + next[4]
				+ ", nextEngineerC = " + next[5] + ";");
		if (error == null) {
			System.out.println("I expected no error message and the next variables set to the current variables.");
			if (received == null)
				System.out.println("I did see no error, but the variables weren't set correctly.");
			else
				System.out.println("I saw the following message: " + received);
		} else {
			System.out.println("I expected an error message and the next variables not changed.");
			System.out.println("Expected error message: " + error);
			System.out.println("I saw: " + received);
		}
	}

	public static boolean checkMove(boolean[] current, boolean[] next, String error) {
		ManagersCrossing.currentManagerA = current[0];
		ManagersCrossing.currentManagerB = current[1];
		ManagersCrossing.currentManagerC = current[2];
		ManagersCrossing.currentEngineerA = current[3];
		ManagersCrossing.currentEngineerB = current[4];
		ManagersCrossing.currentEngineerC = current[5];
		ManagersCrossing.currentBoat = current[6];

		ManagersCrossing.nextManagerA = next[0];
		ManagersCrossing.nextManagerB = next[1];
		ManagersCrossing.nextManagerC = next[2];
		ManagersCrossing.nextEngineerA = next[3];
		ManagersCrossing.nextEngineerB = next[4];
		ManagersCrossing.nextEngineerC = next[5];

		PrintStream oldOut = System.out;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(stream));
		ManagersCrossing.main(new String[0]);
		System.setOut(oldOut);
		Scanner scanner = new Scanner(new String(stream.toByteArray()));
		String firstLine = null;
		if (scanner.hasNextLine())
			firstLine = scanner.nextLine().trim();
		if (error != null) {
			if (error.equals(firstLine)) {
				if (ManagersCrossing.currentManagerA != current[0] || ManagersCrossing.currentManagerB != current[1]
						|| ManagersCrossing.currentManagerC != current[2]
						|| ManagersCrossing.currentEngineerA != current[3]
						|| ManagersCrossing.currentEngineerB != current[4]
						|| ManagersCrossing.currentEngineerC != current[5]
						|| ManagersCrossing.currentBoat != current[6]) {
					printError(current, next, error, firstLine);
					System.out.println("The 'current' variables were not left as they were.");
					return false;
				} else {
					return true;
				}
			} else {
				printError(current, next, error, firstLine);
				System.out.println("The error message differed from what was expected.");
				return false;
			}
		} else {
			// Verify that current is what next was
			if (ManagersCrossing.currentManagerA != next[0] || ManagersCrossing.currentManagerB != next[1]
					|| ManagersCrossing.currentManagerC != next[2] || ManagersCrossing.currentEngineerA != next[3]
					|| ManagersCrossing.currentEngineerB != next[4] || ManagersCrossing.currentEngineerC != next[5]
					|| ManagersCrossing.currentBoat == current[6]) {
				printError(current, next, error, firstLine);
				System.out.println("The 'current' variables were not correctly set to their next positions.");
				if (ManagersCrossing.currentBoat == current[6]) {
					System.out.println("The boat's position should change after a successful move!");
				}
				return false;
			} else {
				return true;
			}
		}
	}

	public static void main(String[] args) {
		int score = 0;
		System.out.println("This program indicates the errors it sees.");
		System.out.println("Please check announcements to see if there is a new version. The version is at the top in a comment.");
		System.out.println(
				"This program only looks at the first line of output from your program. Make sure it is the appropriate error.");
		System.out.println("Errors should be checked in the order they are described in the assignment (the manager recruitment in alphabetical order)");
		try {
			System.out.println("Testing a solution and its opposite to make sure your program accepts it.");
			boolean[][] moves = { { true, false, false, true, false, false },
					{ false, false, false, true, false, false }, { false, false, false, true, true, true },
					{ false, false, false, false, true, true }, { false, true, true, false, true, true },
					{ false, false, true, false, false, true }, { true, true, true, false, false, true },
					{ true, true, true, false, false, false }, { true, true, true, true, false, true },
					{ true, false, true, true, false, true }, { true, true, true, true, true, true }, };
			boolean[] start = { false, false, false, false, false, false, false };
			for (int i = 0; i < moves.length; i++) {
				if (i > 0) {
					System.arraycopy(moves[i - 1], 0, start, 0, moves[i - 1].length);
					start[6] = !start[6];
					if (checkMove(n(start), n(moves[i]), null))
						score += 2;
				}
				if (checkMove(start, moves[i], null))
					score += 3;
			}
			System.out.println("Testing some errors.");
			boolean[] c1 = { false, false, false, false, false, false, false },
					n1 = { true, true, true, true, true, true };
			if (checkMove(c1, n1, "You may only move up to two people at a time!"))
				score += 2;
			if (checkMove(n(c1), n(n1), "You may only move up to two people at a time!"))
				score += 2;
			boolean[] c2 = { false, false, false, true, true, true, false },
					n2 = { true, true, true, true, true, true };
			if (checkMove(c2, n2, "You may only move up to two people at a time!"))
				score += 2;
			if (checkMove(n(c2), n(n2), "You may only move up to two people at a time!"))
				score += 2;
			boolean[] c3 = { false, false, false, true, true, true, false },
					n3 = { true, true, true, true, true, true };
			if (checkMove(c3, n3, "You may only move up to two people at a time!"))
				score += 2;
			if (checkMove(n(c3), n(n3), "You may only move up to two people at a time!"))
				score += 2;

			boolean[] nw = {false, false, false, true, true, true, true};
			boolean[] nw1 = {true, false, false, true, true, true};
			boolean[] nw2 = {false, true, false, true, true, true};
			boolean[] nw3 = {false, false, true, true, true, true};
			if (checkMove(nw, nw1, "You may not move a person who is not with the boat!"))
				score += 2;
			if (checkMove(n(nw), n(nw1), "You may not move a person who is not with the boat!"))
				score += 2;
			if (checkMove(nw, nw2, "You may not move a person who is not with the boat!"))
				score += 2;
			if (checkMove(n(nw), n(nw2), "You may not move a person who is not with the boat!"))
				score += 2;
			if (checkMove(nw, nw3, "You may not move a person who is not with the boat!"))
				score += 2;
			if (checkMove(n(nw), n(nw3), "You may not move a person who is not with the boat!"))
				score += 2;
			
			boolean[] ma = {true, false, false, false, true, false};
			if (checkMove(c1, ma, "Manager A is trying to recruit someone!"))
				score += 3;
			if (checkMove(n(c1), n(ma), "Manager A is trying to recruit someone!"))
				score += 3;
			boolean[] ma2 = {false, true, false, true, false, false};
			if (checkMove(c1, ma2, "Manager A is trying to recruit someone!"))
				score += 3;
			if (checkMove(n(c1), n(ma2), "Manager A is trying to recruit someone!"))
				score += 2;
			boolean[] pb = {false, true, false, false, true, false, false};
			boolean[] mb = {false, true, false, true, true, false};
			if (checkMove(pb, mb, "Manager B is trying to recruit someone!"))
				score += 3;
			if (checkMove(n(pb), n(mb), "Manager B is trying to recruit someone!"))
				score += 3;
			boolean[] pc = {false, false, false, true, true, true, false};
			boolean[] mc = {false, false, true, true, true, true};
			if (checkMove(pc, mc, "Manager C is trying to recruit someone!"))
				score += 3;
			if (checkMove(n(pc), n(mc), "Manager C is trying to recruit someone!"))
				score += 3;

		} finally {
			System.out.println("Calculated score: " + score + " / 100");
			System.out.println("Tentative score! Academic dishonesty would affect your score.");
			System.out.println("If any problems are found with the tester, it will be announced and you should retest.");
		}

	}

}
