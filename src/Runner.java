public class Runner {
    private RunnerID id;
    private Tree2_3<Double,Double> running_heat;
    private double sum_runtime;
    private int numOfRuns;
    private double minRunTime;

    public Runner(RunnerID id){
        this.id = id;
        this.sum_runtime = 0;
}
    public RunnerID getId() {
        return id;
    }

    public void setId(RunnerID id) {
        this.id = id;
    }
}
