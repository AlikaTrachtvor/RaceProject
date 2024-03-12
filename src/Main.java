class RunnerIDInt extends RunnerID{
    private int id;
    public RunnerIDInt(int id){
        super();
        this.id = id;
    }
    @Override
    public boolean isSmaller(RunnerID other) {
        return this.id < ((RunnerIDInt)other).id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }


}
public class Main {
    public static void main(String[] args) {
        Tree2_3<RunnerIDInt, Double> tree = new Tree2_3();
        RunnerIDInt key1 = new RunnerIDInt(100);
        RunnerIDInt key2 = new RunnerIDInt(100);
        TreeNode2_3<RunnerIDInt,Double> A = new TreeNode2_3(null,key1,null,null,null,5.5,false,false);
        TreeNode2_3<Double,Double> B = new TreeNode2_3(null,500.0,null,null,null,null,false,false);

        tree.Insert(A);
        TreeNode2_3<RunnerIDInt,Double> result = tree.Search(key1);
        System.out.println(result.getValue());
        tree.Delete(A);
        result = tree.Search(key1);
        if(result == null)
            System.out.println("deleted successfully");
        else
            System.out.println("something wrong");


    }
}