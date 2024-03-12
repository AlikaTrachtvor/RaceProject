public class Leaf2_3<T,V> extends TreeNode2_3<T>{
    private V value;
    private boolean isMinusInf;
    private boolean isPlussInf;

    public Leaf2_3(TreeNode2_3 p, T k, V value, boolean negInf, boolean posInf){
        super(p,k,null,null,null);
        this.value = value;
        this.isMinusInf = negInf;
        this.isPlussInf = posInf;
    }


    public boolean getMinusInf() {
        return this.isMinusInf;
    }
    public boolean getPlusInf(){
        return this.isPlussInf;
    }
}
