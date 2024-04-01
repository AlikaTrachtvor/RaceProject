public class Race {
    private Tree2_3<RunnerID, RunnerID, Runner> runners;
    private Tree2_3<Float,RunnerID,Float> avgTimes;
    private Tree2_3<Float,RunnerID, Float> minTimes;
    private float minTime;
    private RunnerID minTimeID;
    private float minAvg;
    private RunnerID minAvgID;

    public Race(){
        init();
    }
    public void init(){
        this.runners = new Tree2_3<RunnerID,RunnerID,Runner>();
        this.minTimes = new Tree2_3<Float,RunnerID,Float>();
        this.avgTimes = new Tree2_3<Float,RunnerID,Float>();
        this.minTime = Float.MAX_VALUE;
        this.minAvg = Float.MAX_VALUE;
        this.minTimeID = null;
        this.minAvgID = null;
    }
    public Tree2_3<RunnerID, RunnerID, Runner> getRunners(){return this.runners;}

    /**
     * This method gets an id of a runner and adds it to the tree that holds the information of the runners
     * @param id The id of the new runner
     * @throws IllegalArgumentException if the runner already exists in the data set
    */
    public void addRunner(RunnerID id){
        if(runners.Search(id,null) != null)
            throw  new IllegalArgumentException();
        Runner runner = new Runner(id);
        TreeNode2_3<RunnerID, RunnerID,Runner> x = new TreeNode2_3<>(null, id,null, null,null,null,runner,false,false);
        this.runners.Insert(x);
        this.minTimes.Insert(new TreeNode2_3<>(null,Float.MAX_VALUE,id,null,null,null,null,false,false));
        this.avgTimes.Insert(new TreeNode2_3<>(null,Float.MAX_VALUE,id,null,null,null,null,false,false));
        //updateMinimum();
        //updateAverage();
    }

    private void updateMinimum(){
        TreeNode2_3<Float,RunnerID,Float> minRun = this.minTimes.findMinimum();
        if(minRun == null){
            this.minTime = Float.MAX_VALUE;
            this.minTimeID = null;
        }
        else {
            this.minTime = (float) minRun.getKey();
            if (this.minTime == Float.MAX_VALUE)
                this.minTimeID = null;
            else
                this.minTimeID = (RunnerID) minRun.getSubKey();
        }
    }
    private void updateAverage(){
        TreeNode2_3<Float,RunnerID,Float> avgRun = this.avgTimes.findMinimum();
        if(avgRun == null){
            this.minAvg = Float.MAX_VALUE;
            this.minAvgID = null;
        }
        else{
            this.minAvg = (float) avgRun.getKey();
            if(this.minAvg == Float.MAX_VALUE)
                this.minAvgID = null;
            else
                this.minAvgID = (RunnerID) avgRun.getSubKey();
        }
    }

    /**
     * This method adds a new record of a run to a specific runner
     * @param id the id of the runner we wish to add a run to
     * @param time the time of the specific run
     * @throws IllegalArgumentException if the runner doesn't exist or such run does exist
     */
    public void addRunToRunner(RunnerID id, float time){
        TreeNode2_3<RunnerID,RunnerID,Runner> temp = this.runners.Search(id, null);
        if(temp == null)
            throw new IllegalArgumentException();
        Runner runner = (Runner) temp.getValue();
        float oldMinTime = runner.getMinRunTime();
        float oldAvgTime = runner.getAvgRunTime();
        runner.addRun(time);
        float newMinTime = runner.getMinRunTime();
        float newAvgTime = runner.getAvgRunTime();
        this.minTimes.Delete(this.minTimes.Search(oldMinTime,id));
        this.avgTimes.Delete(this.avgTimes.Search(oldAvgTime,id));
        this.minTimes.Insert(new TreeNode2_3<>(null,newMinTime,id,null,null,null,null,false,false));
        this.avgTimes.Insert(new TreeNode2_3<>(null,newAvgTime,id,null,null,null,null,false,false));
        updateMinimum();
        updateAverage();
    }

    /**
     * This method removes a record of a specific run from a specific runner and updates the min and avg trees accordingly
     * @param id The id of the runner we wish to delete a run from
     * @param time the time that indicate the specific run we wish to delete
     * @throws IllegalArgumentException if the runner or the run don't exist
     */
    public void removeRunFromRunner(RunnerID id, float time){
        TreeNode2_3<RunnerID,RunnerID,Runner> temp = runners.Search(id,null);
        if(temp == null)
            throw new IllegalArgumentException();
        Runner runner = (Runner) temp.getValue();
        float oldMinTime = runner.getMinRunTime();
        float oldAvgTime = runner.getAvgRunTime();
        runner.deleteRun(time);
        this.minTimes.Delete(this.minTimes.Search(oldMinTime,id));
        this.avgTimes.Delete(this.avgTimes.Search(oldAvgTime,id));
        float newMinTime = runner.getMinRunTime();
        float newAvgTime = runner.getAvgRunTime();
        this.minTimes.Insert(new TreeNode2_3<>(null,newMinTime,id,null,null,null,null,false,false));
        this.avgTimes.Insert(new TreeNode2_3<>(null,newAvgTime,id,null,null,null,null,false,false));
        updateMinimum();
        updateAverage();
    }

    /**
     *This method deletes a runner and all the records of his runs from Race
     * @param id the id of the runner we wish to delete
     * @throws IllegalArgumentException if the runner doesn't exist
     */
    public void removeRunner(RunnerID id){
        TreeNode2_3<RunnerID,RunnerID,Runner> temp = this.runners.Search(id,null);
        if(temp == null)
            throw new IllegalArgumentException();
        Runner runner = (Runner) temp.getValue();
        float minRunTime = runner.getMinRunTime();
        float avgRunTime = runner.getAvgRunTime();
        this.runners.Delete(temp);
        this.minTimes.Delete(this.minTimes.Search(minRunTime,id));
        this.avgTimes.Delete(this.avgTimes.Search(avgRunTime,id));
        updateMinimum();
        updateAverage();
    }
    /**
     * This method finds the min runtime of a runner
     * @param id the id of the runner
     * @throws IllegalArgumentException if the runner doesn't exist or has no runs
     */
    public float getMinRun(RunnerID id){
        TreeNode2_3 temp = this.runners.Search(id,null);
        if(temp == null)
            throw new IllegalArgumentException();
        float min = ((Runner) temp.getValue()).getMinRunTime();
        if (min == Float.MAX_VALUE)
            throw new IllegalArgumentException();
        return min;
    }
    /**
     * This method finds the average runtime of a runner
     * @param id the id of the runner
     * @throws IllegalArgumentException if the runner doesn't exist or has no runs
     */
    public float getAvgRun(RunnerID id){
        TreeNode2_3 temp = this.runners.Search(id,null);
        if(temp == null)
            throw new IllegalArgumentException();
        float avg = ((Runner) temp.getValue()).getAvgRunTime();
        if(avg == Float.MAX_VALUE)
            throw new IllegalArgumentException();
        return avg;
    }

    /**
     * This method finds the id of the runner with the minimal average runtime
     * @throws IllegalArgumentException if there aren't any runs
     * @return the id of the runner with the minimal average runtime
     */
    public RunnerID getFastestRunnerAvg(){
        if(this.minAvgID == null)
            throw new IllegalArgumentException();
        return this.minAvgID;
    }

    /**
     * This method finds the id of the runner with the smallest runtime
     * @throws IllegalArgumentException if there aren't any runs
     * @return the id of the runner with the smallest runtime
     */
    public RunnerID getFastestRunnerMin(){
        if(this.minTimeID == null)
            throw new IllegalArgumentException();
        return this.minTimeID;
    }

    /**
     * This method finds the rank of a runner based on his average runtime
     * @param id the id of the runner whose rank we wish to compute
     * @throws IllegalArgumentException if the runner doesn't exist or has no runs
     * @return the rank of the runner
     */
    public int getRankAvg(RunnerID id){
        TreeNode2_3 temp1 = this.runners.Search(id, null);
        if(temp1 == null)
            throw new IllegalArgumentException();
        float avgRun = ((Runner) temp1.getValue()).getAvgRunTime();
        if(avgRun == Float.MAX_VALUE)
            throw new IllegalArgumentException();
        TreeNode2_3 temp2 = this.avgTimes.Search(avgRun, id);
        return this.avgTimes.findRank(temp2);
    }

    public int getRankMin(RunnerID id){
        TreeNode2_3 temp1 = this.runners.Search(id, null);
        if(temp1 == null)
            throw new IllegalArgumentException();
        float minRun = ((Runner) temp1.getValue()).getMinRunTime();
        if(minRun == Float.MAX_VALUE)
            throw new IllegalArgumentException();
        TreeNode2_3 temp2 = this.minTimes.Search(minRun, id);
        return this.minTimes.findRank(temp2);
    }

}
