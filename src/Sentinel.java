public class Sentinel<T,V> extends Leaf2_3<T,V>{
    private boolean infinity;
    public Sentinel(TreeNode2_3 p, T k, boolean inf){
        super(p,k,null);
        this.infinity = inf;
    }
}
