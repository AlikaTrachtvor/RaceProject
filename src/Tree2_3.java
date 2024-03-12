import javax.swing.*;

public class Tree2_3<K,V>{
    private TreeNode2_3<K,V> root;

    //init of 2_3 Tree (need to deal with the null key)
    public Tree2_3(){
        TreeNode2_3<K,V> sentinelL = new TreeNode2_3<>(null, null, null, null,null,null,true,false);
        TreeNode2_3<K,V> sentinelM = new TreeNode2_3<>(null, null, null, null,null,null,false,true);
        this.root = new TreeNode2_3<>(null,null,sentinelL,sentinelM,null,null,false,true);
        sentinelL.setParent(this.root);
        sentinelM.setParent(this.root);
    }
    public TreeNode2_3 getRoot(){return this.root;}
    public void setRoot(TreeNode2_3 r){this.root = r;}

    // 0 - equal, 1 - small or equal, -1 - smaller
    public boolean isSmallerOrEqual(TreeNode2_3 A, TreeNode2_3 B, int state){
        if(state == 0 && (A.getMinusInf() || A.getPlusInf() || B.getMinusInf()||B.getPlusInf()))
            return false;
        if(state != 0 && (A.getMinusInf() || B.getPlusInf()))
            return true;
        if(state != 0 && (A.getPlusInf() || B.getMinusInf()))
            return false;
        if(A.getKey() instanceof RunnerID){
            boolean equal = !(((RunnerID) A.getKey()).isSmaller((RunnerID) B.getKey())) && !(((RunnerID) B.getKey()).isSmaller((RunnerID) A.getKey()));
            boolean smaller = ((RunnerID) A.getKey()).isSmaller((RunnerID) B.getKey());
            if(state == 0)
                return equal;
            else if(state == 1)
                return smaller || equal;
            else
                return smaller;
        }
        else{
            if(state == 0)
                return (float) A.getKey() == (float) B.getKey();
            else if(state == 1)
                return (float) A.getKey() <= (float) B.getKey();
            else
                return (float) A.getKey() < (float) B.getKey();
        }
    }
    public TreeNode2_3 Search(TreeNode2_3 x, K key){
        TreeNode2_3<K,V> temp = new TreeNode2_3<>(null, key, null, null,null,null,false,false);
        return SearchAux(x,temp);
    }
    public TreeNode2_3 SearchAux(TreeNode2_3 x, TreeNode2_3 y){
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

    public void Update_Key(TreeNode2_3 x){
        if(x.getLeft().getMinusInf() || x.getLeft().getPlusInf()){
            x.setKey(null);
            x.setMinusInf(x.getLeft().getMinusInf());
            x.setPlusInf(x.getLeft().getPlusInf());
        }
        else
            x.setKey(x.getLeft().getKey());
        if(x.getMiddle() != null){
            if(x.getMiddle().getMinusInf() || x.getMiddle().getPlusInf()){
                x.setKey(null);
                x.setMinusInf(x.getMiddle().getMinusInf());
                x.setPlusInf(x.getMiddle().getPlusInf());
            }
            else
                x.setKey(x.getMiddle().getKey());
        }
        if(x.getRight() != null) {
            if(x.getRight().getMinusInf()|| x.getRight().getPlusInf()){
                x.setKey(null);
                x.setMinusInf(x.getRight().getMinusInf());
                x.setPlusInf(x.getRight().getPlusInf());
            }
            else
                x.setKey(x.getRight().getKey());

        }
    }
    public void Set_Children(TreeNode2_3 x, TreeNode2_3 l, TreeNode2_3 m, TreeNode2_3 r){
        x.setLeft(l);
        x.setMiddle(m);
        x.setRight(r);
        int count = 1;
        if(m != null) {
            m.setParent(x);
            count++;
        }
        if(r != null) {
            r.setParent(x);
            count++;
        }
        x.setNumOfChildren(count);
        Update_Key(x);
    }

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
            return null;
        }
        TreeNode2_3<K,V> y = new TreeNode2_3<>();
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
        return y;
    }
    public void Insert(TreeNode2_3 z){
        TreeNode2_3<K,V> y = this.root;
        while(y.getNumOfChildren() > 0){
            if(isSmallerOrEqual(z, y.getLeft(),-1))
                y = y.getLeft();
            else if (isSmallerOrEqual(z, y.getMiddle(),-1))
                y = y .getMiddle();
            else
                y = y.getRight();
            TreeNode2_3<K,V> x = y.getParent();
            z = Insert_And_Split(x,z);
            while(!x.equals(root)){
                x = x.getParent();
                if(z != null)
                    z = Insert_And_Split(x,z);
                else
                    Update_Key(x);
            }
            if(z!=null){
                TreeNode2_3<K,V> w = new TreeNode2_3<>();
                Set_Children(w,x,z,null);
                root = w;
            }
        }
    }
    public TreeNode2_3<K,V> Borrow_And_Merge(TreeNode2_3<K,V> y) {
        TreeNode2_3<K, V> z = y.getParent();
        if (y.equals(z.getLeft())) {
            TreeNode2_3<K, V> x = z.getMiddle();
            if (x.getRight() != null) {
                Set_Children(y, y.getLeft(), x.getLeft(), null);
                Set_Children(x, x.getMiddle(), x.getRight(), null);
            } else Set_Children(x, y.getLeft(), x.getLeft(), x.getMiddle());
            Set_Children(z, x, z.getRight(), null);
            return z;
        }
        else if (y.equals(z.getMiddle())) {
            TreeNode2_3<K, V> x = z.getLeft();
            if (x.getRight() != null) {
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
            } else Set_Children(x, x.getLeft(), y.getMiddle(), y.getLeft());
            Set_Children(z, x, z.getRight(), null);
            return z;
        }
        else {
            TreeNode2_3<K, V> x = z.getMiddle();
            if (x.getRight() != null) {
                Set_Children(y, x.getRight(), y.getLeft(), null);
                Set_Children(x, x.getLeft(), x.getMiddle(), null);
            } else Set_Children(x, x.getLeft(), y.getMiddle(), y.getLeft());
            Set_Children(z, z.getLeft(), x, null);
            return z;
        }
    }
    public void Delete(TreeNode2_3 x){
        TreeNode2_3<K,V> y = x.getParent();
        if(x.equals(y.getLeft()))
            Set_Children(y, y.getMiddle(), y.getRight(),null);
        else if (x.equals(y.getMiddle()))
            Set_Children(y,y.getLeft(),y.getMiddle(),null);
        else
            Set_Children(y,y.getLeft(),y.getMiddle(),null);
        while (y != null){
            if(y.getMiddle() != null){
                Update_Key(y);
                y = y.getParent();
            }
            else{
                if(!y.equals(this.root))
                    y = Borrow_And_Merge(y);
                else{
                    this.root = y.getLeft();
                    y.getLeft().setParent(null);
                }
            }
        }
    }
}

