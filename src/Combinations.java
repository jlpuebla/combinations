/**
 * Program that given two non-negative integers n and r with 0 ≤ r ≤ n,
 * will generate all subsets of cardinality r of {1, 2, . . . , n}
 * and print them to the screen.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Combinations {

    public static void main(String[] args) {

        int n = getUserInput("Please enter value of n (where n>0)", 1, Integer.MAX_VALUE);
        int r = getUserInput("Please enter value of r (where 0 ≤ r ≤ n)", 0, n);

        if (n > 0 && r >= 0) {
            ArrayList<Integer> set = generateList(n);
            System.out.println("Set = " + set.toString());


            System.out.println("Generating all combinations with n=" + n + " and r=" + r + "...");
            ArrayList<ArrayList<Integer>> combinations = generateCombinations(set, r);

            System.out.println("Total # of combinations = " + combinations.size());
            printCombinations(combinations);
        }

    }

    /**
     * Generates an ArrayList of integer values 1 to n.
     * @param n size of set to generate
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> generateList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Generates subsets of cardinality r of the input set
     * @param set input set
     * @param r cardinality
     * @return ArrayList of combinations
     */
    static ArrayList<ArrayList<Integer>> generateCombinations(ArrayList<Integer> set, int r) {
        ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
        generateCombinations(combinations, set, r);

        return combinations;
    }

    /**
     * Function that generates the actual combinations and adds them to ArrayList combinations
     * @param combinations
     * @param set
     * @param r
     */
    static void generateCombinations(ArrayList<ArrayList<Integer>> combinations, ArrayList<Integer> set, int r) {
        // Base case: The set is smaller than cardinality
        if (set.size() < r)
            return;

        int n = set.size();

        // Nested loop creates sets {{a}, {a,b}, {a,b,c}, {a,b.c,..n}}
        for (int i = 0; i < set.size(); i++) {
            ArrayList<Integer> combination = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                combination.add(set.get(j));
            }
            n--;

            // Add to combinations list if combination matches cardinality and is not a duplicate
            if (combination.size() == r && !combinations.contains(combination))
                combinations.add(combination);
        }

        // reduce set size by removing an element and recurse again
        for (int i = 0; i < set.size(); i++) {
            ArrayList<Integer> copy1 = new ArrayList<>(set);
            copy1.remove(i);
            generateCombinations(combinations, copy1, r);
        }

    }

    /**
     * Prints the combinations to the screen.
     * @param combinations
     */
    static void printCombinations(ArrayList<ArrayList<Integer>> combinations) {
        System.out.println("Combinations:");

        for (ArrayList<Integer> arrayList : combinations) {
            System.out.println(arrayList.toString());
        }
    }

    /**
     * Function asks the user for valid input
     * @param msg
     * @param max
     * @return
     */
    public static int getUserInput(String msg, int min, int max) {
        Scanner scn = new Scanner(System.in);
        boolean quit = false;                   // Variable used for while loop exit

        do {
            System.out.println(msg + ", enter q to quit:");

            if (scn.hasNextInt()) {
                int num = scn.nextInt();

                if (num >= min && num <= max) {
                    return num;
                } else
                    System.out.println("Invalid number.");
            } else if (scn.hasNext("q") || scn.hasNext("Q")) {
                // User decided to quit and entered "q"/"Q"
                quit = true;
            } else {
                // User did not enter a number or "q"/"Q"
                System.out.println("Not valid input, try again.");
            }

            scn.nextLine();

        } while (quit == false);

        return -1;
    }

}
