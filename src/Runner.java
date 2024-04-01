public class Runner {
    private RunnerID id;
    private Tree2_3<Float,Float,Float> running_heat;
    private float avgRunTime;
    private int numOfRuns;
    private float minRunTime;

    public Runner(RunnerID id){
        this.id = id;
        this.running_heat = new Tree2_3<Float, Float, Float>();
        this.avgRunTime = Float.MAX_VALUE;
        this.numOfRuns = 0;
        this.minRunTime = Float.MAX_VALUE;
}
    public RunnerID getId() {
        return id;
    }
    public void setId(RunnerID id) {
        this.id = id;
    }
    public float getMinRunTime(){return this.minRunTime;}
    public float getAvgRunTime(){return this.avgRunTime;}
    public int getNumOfRuns(){return this.numOfRuns;}

    /**
     * This method adds a run with  give time to the record of the runner's runs
     * @param time the time of the new run
     * @throws IllegalArgumentException if time has illegal value (0 or negative) or this run already exists
     */
    public void addRun(float time){
        if(time <= 0)
            throw new IllegalArgumentException();
        TreeNode2_3<Float, Float,Float> temp = this.running_heat.Search(time,null);
        if(temp != null)
            throw new IllegalArgumentException();
        TreeNode2_3<Float,Float,Float> new_heat = new TreeNode2_3<Float,Float,Float>(null,time,null,null,null,null,null,false,false);
        this.running_heat.Insert(new_heat);
        if(numOfRuns == 0){
            this.numOfRuns++;
            this.avgRunTime = time;
            this.minRunTime = time;
        }
        else{
            this.avgRunTime = ((this.numOfRuns*avgRunTime)+time)/(this.numOfRuns + 1);
            this.numOfRuns++;
            if(time < this.minRunTime)
                this.minRunTime = time;
        }
    }

    /**
     * This method deletes a specific run from the runner and updates the minRun and avgRum fields accordingly
     * @param time the time of the specific run we wish to delete
     * @throws IllegalArgumentException if the run doesn't exist
     */
    public void deleteRun(float time){
        TreeNode2_3<Float, Float,Float> temp = this.running_heat.Search(time,null);
        if(temp == null)
            throw new IllegalArgumentException();
        this.running_heat.Delete(temp);
        if(this.numOfRuns == 1){
            this.minRunTime = Float.MAX_VALUE;
            this.avgRunTime = Float.MAX_VALUE;
            this.numOfRuns--;
        }
        else{
            this.avgRunTime = ((this.numOfRuns*avgRunTime)-time)/(this.numOfRuns - 1);
            this.numOfRuns--;
            TreeNode2_3<Float, Float,Float> min = this.running_heat.findMinimum();
            this.minRunTime = (float) min.getKey();
        }
    }
}
