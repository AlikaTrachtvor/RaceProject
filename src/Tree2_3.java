abstract public class Tree2_3<T>{
    private TreeNode2_3<T> root;

    //init of 2_3 Tree (need to deal with the null key)
    public Tree2_3(){
        Sentinel l = new Sentinel(null, null, false);
        Sentinel m = new Sentinel(null, null, true);
        this.root = new TreeNode2_3(null, null, l ,m, null);
        l.setParent(this.root);
        m.setParent(this.root);
    }
    public TreeNode2_3 getRoot(){return this.root;}
    public void setRoot(TreeNode2_3 r){this.root = r;}

    public abstract TreeNode2_3 Search(TreeNode2_3 x, T key);

    public void Update_Key(TreeNode2_3 x){
        x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null)
            x.setKey(x.getMiddle().getKey());
        if(x.getRight() != null)
            x.setKey(x.getRight().getKey());
    }
    public void Set_Children(TreeNode2_3 x, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        if(m != null)
            m.setParent(x);
        if(r != null)
            r.setParent(x);
        Update_Key(x);
    }

    public abstract TreeNode2_3 Insert_And_Split(TreeNode2_3 x, TreeNode2_3 z);
    public abstract void Insert(TreeNode2_3 root, TreeNode2_3 z);
    public abstract TreeNode2_3 Borrow_And_Merge(TreeNode2_3 y);
    public abstract TreeNode2_3 Delete(TreeNode2_3 root, TreeNode2_3 x);
}

