package src;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        System.out.print("Number of processes: ");
        int processes = sc.nextInt();
        System.out.print("Number of resource types: ");
        int resource = sc.nextInt();
        System.out.println();

        int[] total = new int[resource];

        System.out.println("Enter the total instances:");
        for(int i = 0; i < resource; i++) {
            System.out.print("[" + (i + 1) + "]: ");
            total[i] = sc.nextInt();
        }
        System.out.println();
        System.out.print("Total Instance: [");

        for(int i = 0; i < resource; i++) {
            System.out.print(total[i]);
            if(i != resource - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();

        int[][] max = new int[processes][resource];
        System.out.println("Enter the element of the max matrix of each processes:");
        
        for(int i = 0; i < processes; i++){
            System.out.println("Process " + (i + 1) + ":");
            for(int j = 0; j < resource; j++) {
                System.out.print("M" + (j + 1) + ": ");
                max[i][j] = sc.nextInt();
            }
        }

        int[][] allocation = new int[processes][resource];
        System.out.println("Enter the element of the allocation matrix of each processes:");
        
        for(int i = 0; i < processes; i++){
            System.out.println("Process " + (i + 1) + ":");
            for(int j = 0; j < resource; j++) {
                System.out.print("A" + (j + 1) + ": ");
                allocation[i][j] = sc.nextInt();
            }
        }

        BankersAlgorithm banker = new BankersAlgorithm(total, max, allocation);

        int [][] need = banker.getNeed();
        int[] totalAllocated = banker.getTotalAllocated();
        int[] available = banker.getAvailable();

        System.out.println("\n================ BANKER'S ALGORITHM ================\n");
        System.out.println("                    BASELINE TABLE\n");

        System.out.printf("%-12s %-18s %-18s %-18s%n",
                "Process", "Allocation", "Max", "Need");

        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < processes; i++) {
            System.out.printf("%-12s %-18s %-18s %-18s%n",
                    "P" + i,
                    Arrays.toString(allocation[i]),
                    Arrays.toString(max[i]),
                    Arrays.toString(need[i]));
        }

        System.out.println("------------------------------------------------------------");

        System.out.println("Total Resources:  " + Arrays.toString(total));
        System.out.println("Total Allocated:  " + Arrays.toString(totalAllocated));
        System.out.println("Available:        " + Arrays.toString(available));

        System.out.println("============================================================");

        boolean[] result = banker.checkSafeState();

        boolean safe = true;

        for(boolean finished : result) {
            if(!finished) {
                safe = false;
                break;
            }
        }

        if(safe) {
            System.out.println("The system is in SAFE state!");
        } else {
            System.out.println("The system is in UNSAFE.");
        }

        sc.close();
    }
}
