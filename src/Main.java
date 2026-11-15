package src;
import java.util.Scanner;

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

        // Table headers
        System.out.printf("%-12s", "Process");

        System.out.printf("%-20s", "Allocation");
        System.out.printf("%-20s", "Max");
        System.out.printf("%-20s%n", "Need");

        // Resource labels
        System.out.printf("%-12s", "");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6s", "R" + j);
        }
        System.out.printf("%-2s", "");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6s", "R" + j);
        }
        System.out.printf("%-2s", "");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6s", "R" + j);
        }

        System.out.println();
        System.out.println("------------------------------------------------------------");

        // Process rows
        for (int i = 0; i < processes; i++) {

            System.out.printf("%-12s", "P" + i);

            // Allocation
            for (int j = 0; j < resource; j++) {
                System.out.printf("%-6d", allocation[i][j]);
            }
            System.out.printf("%-2s", "");

            // Max
            for (int j = 0; j < resource; j++) {
                System.out.printf("%-6d", max[i][j]);
            }
            System.out.printf("%-2s", "");

            // Need
            for (int j = 0; j < resource; j++) {
                System.out.printf("%-6d", need[i][j]);
            }

            System.out.println();
        }

        System.out.println("------------------------------------------------------------");

        // Resource summary
        System.out.printf("%-12s", "Total");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6d", total[j]);
        }
        System.out.println();

        System.out.printf("%-12s", "Allocated");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6d", totalAllocated[j]);
        }
        System.out.println();

        System.out.printf("%-12s", "Available");

        for (int j = 0; j < resource; j++) {
            System.out.printf("%-6d", available[j]);
        }
        System.out.println();

        System.out.println("============================================================");
        sc.close();
    }
}
