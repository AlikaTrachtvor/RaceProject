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
        Tree2_3<Float,RunnerIDInt, Float> tree = new Tree2_3();
        System.out.println("the root's size: " + tree.getRoot().getSize());
        RunnerIDInt key1 = new RunnerIDInt(100);
        RunnerIDInt key2 = new RunnerIDInt(101);
        RunnerIDInt key3 = new RunnerIDInt(102);
        RunnerIDInt key4 = new RunnerIDInt(103);
        Float k1 = new Float(5.4);
        Float k2 = new Float(6);
        Float k3 = new Float(7);
        TreeNode2_3<Float,RunnerIDInt,Float> A = new TreeNode2_3(null,k1, key1,null,null,null,1,false,false);
        TreeNode2_3<Float,RunnerIDInt,Float> B = new TreeNode2_3(null,k1,key2,null,null,null,2,false,false);
        TreeNode2_3<Float,RunnerIDInt,Float> C = new TreeNode2_3(null,k2,key3,null,null,null,3,false,false);
        TreeNode2_3<Float,RunnerIDInt,Float> D = new TreeNode2_3(null,k3,key4,null,null,null,3,false,false);
        tree.Insert(A);
        tree.Insert(B);
        tree.Insert(C);
        tree.Insert(D);
        System.out.println("The new root's size: " + tree.getRoot().getSize());
       System.out.println(tree.findRank(A));
        //TreeNode2_3<Float,RunnerIDInt,Float> result = tree.Search(k1,key2);
        //if(result == null)
           //System.out.println("A deleted successfully");
        //else
            //System.out.println("something wrong");
        //TreeNode2_3<Float,RunnerIDInt,Float> min = tree.findMinimum();
        //System.out.println(min.getValue());
        //System.out.println(B.getParent().getParent().getPlusInf());
        //TreeNode2_3<Float,RunnerIDInt,Float> result = tree.Search(k, key2);
        //System.out.println(result.getValue());

        //System.out.println(A.getParent().getNumOfChildren());
        //TreeNode2_3<Float,RunnerIDInt,Float> result = tree.Search(k,key2);
        //if(result != null)
            //System.out.println(result.getValue());
        //else
            //System.out.println("something is wrong");



    }
}