public class Leaf2_3<T,V> extends TreeNode2_3<T>{
    private V value;

    public Leaf2_3(TreeNode2_3 p, T k, V value){
        super(p,k,null,null,null);
        this.value = value;
    }
}
