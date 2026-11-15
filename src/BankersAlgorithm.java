package src;

public class BankersAlgorithm {
    private int processes, resource;
    private int[] total, totalAllocated, available;
    private int[][] max, allocation;
    private int[][] need;

    public BankersAlgorithm(int[] total, int[][] max, int[][] allocation){
        this.processes = allocation.length;
        this.resource = total.length;

        this.total = total;
        this.allocation = allocation;
        this.max = max;
        
        need = new int[processes][resource];
        totalAllocated = new int[resource];
        available = new int[resource];

        calculateNeed();
        calculateTotalAllocated();
        calculateAvailable();
    }
    
    public void calculateNeed(){
        for(int i = 0; i < processes; i++){
                for(int j = 0; j < resource; j++) {
                    need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public void calculateTotalAllocated(){
        totalAllocated = new int[resource];
        for(int j = 0; j < resource; j++) {
            for(int i = 0; i < processes; i++) {
                totalAllocated[j] += allocation[i][j];
            }
        }
    }

    public void calculateAvailable(){
        for(int i = 0; i < resource; i++){
            available[i] = total[i] - totalAllocated[i];
        }
    }

    public int getProcesses(){
        return processes;
    }
    public int getResource(){
        return resource;
    }
    public int[] getTotal(){
        return total;
    }
    public int[] getTotalAllocated(){
        return totalAllocated;
    }
    public int[] getAvailable(){
        return available;
    }
    public int[][] getMax (){
        return max;
    }
    public int[][] getAllocation(){
        return allocation;
    }
    public int[][] getNeed(){
        return need;
    }

}