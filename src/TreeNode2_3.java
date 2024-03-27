public class TreeNode2_3<K1,K2, V> {
    private TreeNode2_3 parent;
    private K1 key;
    private K2 subkey;
    private TreeNode2_3 left;
    private TreeNode2_3 middle;
    private TreeNode2_3 right;
    private int numOfChildren = 0;
    private int size = 0; //for computing rank -  based on what was shown in the tutorial
    private V value; //satellite attributes if needed
    private boolean isMinusInf; //true if it's the left sentinel
    private boolean isPlusInf; //true if it's the right sentinel

    public TreeNode2_3(){
        this.parent = null;
        this.key = null;
        this.subkey = null;
        this.left = null;
        this.middle = null;
        this.right = null;
        this.value = null;
        this.isMinusInf = false;
        this.isPlusInf = false;
    }
    public TreeNode2_3(TreeNode2_3 p, K1 k, K2 sk, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r, V value, boolean negInf, boolean posInf){
        this.parent = p;
        this.key = k;
        this.subkey = sk;
        if(l != null){
            this.numOfChildren++;
            this.size += l.getSize();
        }
        this.left = l;
        if(m != null){
            this.numOfChildren++;
            this.size += m.getSize();
        }
        this.middle = m;
        if(r != null){
            this.numOfChildren++;
            this.size += r.getSize();
        }
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

    public K1 getKey() {return this.key;}

    public void setKey(K1 key) {
        this.key = key;
    }

    public K2 getSubKey(){return this.subkey;}
    public void setSubKey(K2 sk){this.subkey = sk;}

    public void setKeys(K1 key1, K2 key2){
        this.key = key1;
        this.subkey = key2;
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

    public int getSize(){return this.size;}
    public void setSize(int s){this.size = s;}
    public V getValue(){return this.value;}
    public void setValue(V val){this.value = val;}
    public boolean getMinusInf() {return this.isMinusInf;}
    public void setMinusInf(boolean negInf){this.isMinusInf = negInf;}
    public void setPlusInf(boolean posInf){this.isPlusInf = posInf;}
    public boolean getPlusInf(){return this.isPlusInf;}
}
