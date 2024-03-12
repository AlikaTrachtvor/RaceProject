public class TreeNode2_3<K, V> {
    private TreeNode2_3 parent;
    private K key;
    private TreeNode2_3 left;
    private TreeNode2_3 middle;
    private TreeNode2_3 right;
    private int numOfChildren = 0;
    private V value;
    private boolean isMinusInf;
    private boolean isPlusInf;

    public TreeNode2_3(){
        this.parent = null;
        this.key = null;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.value = null;
        this.isMinusInf = false;
        this.isPlusInf = false;
    }
    public TreeNode2_3(TreeNode2_3 p, K k, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r, V value, boolean negInf, boolean posInf){
        this.parent = p;
        this.key = k;
        if(left != null)
            this.numOfChildren++;
        this.left = l;
        if(middle != null)
            this.numOfChildren++;
        this.middle = m;
        if(right != null)
            this.numOfChildren++;
        this.right = r;
        this.value = value;
        this.isMinusInf = negInf;
        this.isPlusInf = posInf;
    }

    public TreeNode2_3 getParent() {
        return parent;
    }

    public void setParent(TreeNode2_3 parent) {
        this.parent = parent;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public TreeNode2_3 getLeft() {
        return left;
    }
    public void setLeft(TreeNode2_3 left) {
        this.left = left;
    }
    public TreeNode2_3 getMiddle() {
        return middle;
    }
    public void setMiddle(TreeNode2_3 middle) {
        this.middle = middle;
    }
    public TreeNode2_3 getRight() {
        return right;
    }
    public void setRight(TreeNode2_3 right) {
        this.right = right;
    }
    public int getNumOfChildren() {
        return numOfChildren;
    }
    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }
    public boolean getMinusInf() {return this.isMinusInf;}
    public void setMinusInf(boolean negInf){this.isMinusInf = negInf;}
    public void setPlusInf(boolean posInf){this.isPlusInf = posInf;}
    public boolean getPlusInf(){return this.isPlusInf;}
}
