public class Runner {
    private RunnerID id;
    private B_Heap<Float> running_heat;
    private float sum_runtime;

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
