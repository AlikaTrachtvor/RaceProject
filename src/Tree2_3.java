import javax.swing.*;

public class Tree2_3<K1,K2,V>{
    private TreeNode2_3<K1,K2,V> root;

    /**
     * This is the constructor of the 2_3 Tree and it creates the root and the 2 sentinels
     */
    public Tree2_3(){
        TreeNode2_3<K1,K2,V> sentinelL = new TreeNode2_3<>(null, null,null, null, null,null,null,true,false);
        TreeNode2_3<K1,K2,V> sentinelM = new TreeNode2_3<>(null, null,null, null, null,null,null,false,true);
        this.root = new TreeNode2_3<>(null,null,null,sentinelL,sentinelM,null,null,false,true);
        sentinelL.setParent(this.root);
        sentinelM.setParent(this.root);
    }
    public TreeNode2_3 getRoot(){return this.root;}
    public void setRoot(TreeNode2_3 r){this.root = r;}

    /**
     * This method deals with every needed comparisons between the keys (and subkeys if necessary) of 2 tree nodes
     * @param A the first tree node
     * @param B the second tree node
     * @param state 0 - equal, 1 - small or equal, -1 - smaller
     * @return true if A (state) B is true and false otherwise
     */
    public boolean isSmallerOrEqual(TreeNode2_3 A, TreeNode2_3 B, int state){
        if(state == 0 && (A.getMinusInf() || A.getPlusInf() || B.getMinusInf()||B.getPlusInf()))
            return false;
        if(state != 0 && (A.getMinusInf() || B.getPlusInf()))
            return true;
        if(state != 0 && (A.getPlusInf() || B.getMinusInf()))
            return false;
        if(A.getSubKey() == null)
            return isSmallerOrEqualAux(A,B,state, false);
        else{
            boolean mainKeyEqual = isSmallerOrEqualAux(A,B,0,false);
            if(state == 0)
                return mainKeyEqual && isSmallerOrEqualAux(A,B,state,true);
            else{
                if (!mainKeyEqual)
                    return isSmallerOrEqualAux(A, B, state, false);
                else
                    return isSmallerOrEqualAux(A, B, state, true);
            }
        }
    }
    /**
     * This method performs the actual comparison based on the type of the keys
     * @param A the first tree node
     * @param B the second tree node
     * @param state 0 - equal, 1 - small or equal, -1 - smaller
     * @param subKey true if there is a subkey and false otherwise
     * @return true if A (state) B is true and false otherwise
     */
    public boolean isSmallerOrEqualAux(TreeNode2_3 A, TreeNode2_3 B, int state, boolean subKey)
    {
        if (!subKey) {
            if (A.getKey() instanceof RunnerID) {
                boolean equal = !(((RunnerID) A.getKey()).isSmaller((RunnerID) B.getKey())) && !(((RunnerID) B.getKey()).isSmaller((RunnerID) A.getKey()));
                boolean smaller = ((RunnerID) A.getKey()).isSmaller((RunnerID) B.getKey());
                if (state == 0)
                    return equal;
                else if (state == 1)
                    return smaller || equal;
                else
                    return smaller;
            }
            else {
                if (state == 0)
                    return (float) A.getKey() == (float) B.getKey();
                else if (state == 1)
                    return (float) A.getKey() <= (float) B.getKey();
                else
                    return (float) A.getKey() < (float) B.getKey();
            }
        }
        else {
            if (A.getSubKey() instanceof RunnerID) {
                boolean equal = !(((RunnerID) A.getSubKey()).isSmaller((RunnerID) B.getSubKey())) && !(((RunnerID) B.getSubKey()).isSmaller((RunnerID) A.getSubKey()));
                boolean smaller = ((RunnerID) A.getSubKey()).isSmaller((RunnerID) B.getSubKey());
                if (state == 0)
                    return equal;
                else if (state == 1)
                    return smaller || equal;
                else
                    return smaller;
            } else {
                if (state == 0)
                    return (float) A.getSubKey() == (float) B.getSubKey();
                else if (state == 1)
                    return (float) A.getSubKey() <= (float) B.getSubKey();
                else
                    return (float) A.getSubKey() < (float) B.getSubKey();
            }
        }
    }

    /**
     * This method is a shell function for the actual search function of a 2_3 tree and it's meant
     * to edit the data in a more convenient way
     * @param key1 the key being looked for in the tree
     * @param key2 the subkey being looked for (if it exists)
     * @return true if key exists in the tree and false otherwise
     */
    public TreeNode2_3 Search(K1 key1, K2 key2){
        TreeNode2_3<K1,K2,V> temp = new TreeNode2_3<>(null, key1 ,key2, null, null,null,null,false,false);
        return SearchAux(this.root,temp);
    }

    /**
     * This method checks whether a node with the same key as y exists in the tree rooted by x
     * @param x the root of the tree
     * @param y the node sharing the key of the node being searched
     * @return the node that has the same key as y if it exists and null otherwise
     */
    private TreeNode2_3 SearchAux(TreeNode2_3 x, TreeNode2_3 y){
        if (x.getNumOfChildren() == 0){
            if(isSmallerOrEqual(x,y,0))
                return x;
            else
                return null;
        }
        if(isSmallerOrEqual(y, x.getLeft(), 1))
            return SearchAux(x.getLeft(),y);
        else if (isSmallerOrEqual(y, x.getMiddle(), 1))
            return SearchAux(x.getMiddle(), y);
        else
            return SearchAux(x.getRight(),y);
    }

    /**
     * This method update the key/s of the node according to the keys of the node's children
     * @param x the node we wish to update
     */
    public void Update_Key(TreeNode2_3 x){
        if(x.getLeft().getMinusInf()){
            x.setKeys(null,null);
            x.setMinusInf(true);
            x.setPlusInf(false);
        }
        else{
            x.setKeys(x.getLeft().getKey(), x.getLeft().getSubKey());
            x.setMinusInf(false);
            x.setPlusInf(false);
        }
        if(x.getMiddle() != null){
            if(x.getMiddle().getPlusInf()){
                x.setKeys(null,null);
                x.setMinusInf(false);
                x.setPlusInf(true);
            }
            else{
                x.setKeys(x.getMiddle().getKey(), x.getMiddle().getSubKey());
                x.setMinusInf(false);
                x.setPlusInf(false);
            }
        }
        if(x.getRight() != null) {
            if(x.getRight().getPlusInf()){
                x.setKey(null);
                x.setMinusInf(false);
                x.setPlusInf(true);
            }
            else{
                x.setKeys(x.getRight().getKey(), x.getRight().getSubKey());
                x.setMinusInf(false);
                x.setPlusInf(false);
            }


        }
    }

    /**
     * This method sets the children of x
     * @param x the node we wish to update its children
     * @param l the left child
     * @param m the middle child
     * @param r the right child
     */
    public void Set_Children(TreeNode2_3 x, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        l.setParent(x);
        //x.setSize(l.getSize());
        int count = 1;
        if(m != null) {
            m.setParent(x);
            //x.setSize(x.getSize() + m.getSize());
            count++;
        }
        if(r != null) {
            r.setParent(x);
            //x.setSize(x.getSize() + r.getSize());
            count++;
        }
        x.setNumOfChildren(count);
        Update_Key(x);
    }

    /**
     * This method update the size field of x and all of its ancestors
     * @param x the node we wish to update the tree from
     */
    private void UpdateSize(TreeNode2_3 x){
        TreeNode2_3<K1,K2,V> node = x;
        while(node != null){
            node.setSize(node.getLeft().getSize());
            if(node.getMiddle() != null)
                node.setSize(node.getSize() + node.getMiddle().getSize());
            if(node.getRight() != null)
                node.setSize(node.getSize() + node.getRight().getSize());
            node = node.getParent();
        }
    }

    /**
     * This method inserts z to be the child of x and splits x if necessary
     * @param x the parent node
     * @param z the node being inserted
     * @return a new node y if x was split and null if not
     */
    public TreeNode2_3 Insert_And_Split(TreeNode2_3 x, TreeNode2_3 z){
        TreeNode2_3 l = x.getLeft();
        TreeNode2_3 m = x.getMiddle();
        TreeNode2_3 r = x.getRight();
        if(r == null){
            if(isSmallerOrEqual(z,l,-1))
                Set_Children(x,z,l,m);
            else if (isSmallerOrEqual(z,m,-1))
                Set_Children(x,l,z,m);
            else
                Set_Children(x,l,m,z);
            UpdateSize(x);
            return null;
        }
        TreeNode2_3<K1,K2,V> y = new TreeNode2_3<>();
        if(isSmallerOrEqual(z,l,-1)){
            Set_Children(x,z,l,null);
            Set_Children(y,m,r,null);
        }
        else if(isSmallerOrEqual(z,m,-1)){
            Set_Children(x,l,z,null);
            Set_Children(y,m,r,null);
        }
        else if(isSmallerOrEqual(z,r,-1)){
            Set_Children(x,l,m,null);
            Set_Children(y,z,r,null);
        }
        else {
            Set_Children(x,l,m,null);
            Set_Children(y,r,z,null);
        }
        UpdateSize(x);
        UpdateSize(y);
        return y;
    }

    /**
     * This method inserts node z to the tree according to the rules of 2_3 tree
     * @param z the node being inserted
     */
    public void Insert(TreeNode2_3 z){
        z.setSize(1);
        TreeNode2_3<K1,K2,V> y = this.root;
        while(y.getNumOfChildren() > 0) {
            if (isSmallerOrEqual(z, y.getLeft(), -1))
                y = y.getLeft();
            else if (isSmallerOrEqual(z, y.getMiddle(), -1))
                y = y.getMiddle();
            else
                y = y.getRight();
        }
        TreeNode2_3<K1,K2,V> x = y.getParent();
        z = Insert_And_Split(x,z);
        while(!x.equals(root)){
            x = x.getParent();
            if(z != null)
                z = Insert_And_Split(x,z);
            else
                Update_Key(x);
        }
        if(z!=null){
            setRoot(new TreeNode2_3<>(null,null,null,null,null,null,null,false,true));
            Set_Children(this.root, x,z,null);
            UpdateSize(this.root);
        }
    }

    /**
     * This method borrows a sibling x of node y or merges the 2 nodes to one node
     * @param y the node which we borrow from
     * @return the parent of y
     */
    public TreeNode2_3<K1,K2,V> Borrow_And_Merge(TreeNode2_3<K1,K2,V> y) {
        TreeNode2_3<K1,K2,V> z = y.getParent();
        if (y.equals(z.getLeft())) {
            TreeNode2_3<K1,K2,V> x = z.getMiddle();
            if (x.getRight() != null) {
                Set_Children(y, y.getLeft(), x.getLeft(), null);
                Set_Children(x, x.getMiddle(), x.getRight(), null);
                UpdateSize(y);
                UpdateSize(x);
            }
            else{
                Set_Children(x, y.getLeft(), x.getLeft(), x.getMiddle());
                Set_Children(z, x, z.getRight(), null);
                UpdateSize(x);
                UpdateSize(z);
            }
            return z;
        }
        else if (y.equals(z.getMiddle())) {
            TreeNode2_3<K1,K2,V> x = z.getLeft();
            if (x.getRight() != null) {
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
                UpdateSize(y);
                UpdateSize(x);
            }
            else {
                Set_Children(x, x.getLeft(), x.getMiddle(), y.getLeft());
                Set_Children(z, x, z.getRight(), null);
                UpdateSize(x);
                UpdateSize(z);
            }
            return z;
        }
        else {
            TreeNode2_3<K1,K2,V> x = z.getMiddle();
            if (x.getRight() != null) {
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
                UpdateSize(y);
                UpdateSize(x);
            }
            else {
                Set_Children(x, x.getLeft(), y.getMiddle(), y.getLeft());
                Set_Children(z, z.getLeft(), x, null);
                UpdateSize(y);
                UpdateSize(x);
            }
            return z;
        }
    }

    /**
     * This method deletes the node x from the tree
     * @param x the node being deleted
     */
    public void Delete(TreeNode2_3 x){
        TreeNode2_3<K1,K2,V> y = x.getParent();
        if(x.equals(y.getLeft()))
            Set_Children(y, y.getMiddle(), y.getRight(),null);
        else if (x.equals(y.getMiddle()))
            Set_Children(y,y.getLeft(),y.getRight(),null);
        else
            Set_Children(y,y.getLeft(),y.getMiddle(),null);
        UpdateSize(y);
        boolean flag = true;
        while (y != null && flag){
            if(y.getMiddle() != null){
                Update_Key(y);
                y = y.getParent();
            }
            else{
                if(!y.equals(this.root))
                    y = Borrow_And_Merge(y);
                else{
                    setRoot(y.getLeft());
                    y.getLeft().setParent(null);
                    flag = false;
                }
            }
        }
    }

    /**
     * This method finds the node with the minimal key (not including the -inf sentinel)
     * @return the node with the minimal key and null if the tree is empty
     */
    public TreeNode2_3<K1,K2,V> findMinimum(){
        //maybe we need deep clone
        TreeNode2_3<K1,K2,V> x = this.root;
        while(x.getNumOfChildren() > 0)
            x = x.getLeft();
        x = x.getParent().getMiddle();
        if(!x.getPlusInf())
            return x;
        else
            return null;
    }

    public int findRank(TreeNode2_3 x){
        int rank = 1;
        TreeNode2_3<K1,K2,V> y = x.getParent();
        while(y != null){
            if(x.equals(y.getMiddle()))
                rank = rank + y.getLeft().getSize();
            else if (x.equals(y.getRight()))
                rank = rank + y.getLeft().getSize() + y.getMiddle().getSize();
            x = y;
            y = y.getParent();
        }
        return rank;
    }
}

