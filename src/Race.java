public class Race {
    private TreeNode2_3<RunnerID> runnersRoot;

    public void init() {
        Sentinel l = new Sentinel(null, null, false);
        Sentinel m = new Sentinel(null, null, true);
        this.runnersRoot = new TreeNode2_3(null, null, l, m, null);
        l.setParent(this.runnersRoot);
        m.setParent(this.runnersRoot);
    }

    private TreeNode2_3<RunnerID> SearchRunner(TreeNode2_3 root, RunnerID id){
        if(root instanceof Leaf2_3 && !(root instanceof Sentinel)){
            if(root.getKey().equals(id))
                return root;
            else
                return null;
        }
        if (id.isSmaller((RunnerID) root.getLeft().getKey()))
            return SearchRunner(root.getLeft(), id);
        else if (id.isSmaller((RunnerID) root.getMiddle().getKey()))
            return SearchRunner(root.getMiddle(), id);
        else
            return SearchRunner(root.getRight(), id);
    }

    public void addRunner(RunnerID id){
        Runner runner = new Runner(id);
        while()
    }
}
