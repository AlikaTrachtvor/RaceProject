public class TreeNode2_3<T> {
    private TreeNode2_3 parent;
    private T key;
    private TreeNode2_3 left;
    private TreeNode2_3 middle;
    private TreeNode2_3 right;
    private int numOfChildren;

    public TreeNode2_3(){super();}
    public TreeNode2_3(TreeNode2_3 p, T k, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r){
        this.parent = p;
        this.key = k;
        int count = 0;
        if(left != null)
            count++;
        this.left = l;
        if(middle != null)
            count++;
        this.middle = m;
        if(right != null)
            count++;
        this.right = r;
        this.numOfChildren = count;
    }

    public TreeNode2_3 getParent() {
        return parent;
    }

    public void setParent(TreeNode2_3 parent) {
        this.parent = parent;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
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
}
