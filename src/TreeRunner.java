public class TreeRunner extends Tree2_3<RunnerID, Float>{

    @Override
    public Leaf2_3<RunnerID, Float> Search(TreeNode2_3 x, RunnerID key) {
         if(x instanceof Leaf2_3 && ((Leaf2_3) x).getInf())
            return null;
         else if (x instanceof Leaf2_3 && !((Leaf2_3) x).getInf()) {
             if (!((RunnerID) x.getKey()).isSmaller(key) && !key.isSmaller(((RunnerID) x.getKey())))
                 return (Leaf2_3<RunnerID, Float>) x;
             else
                 return null;
         }
         RunnerID leftKey = ((RunnerID) x.getLeft().getKey());
        RunnerID midKey = ((RunnerID) x.getMiddle().getKey());
        RunnerID rightKey = ((RunnerID) x.getRight().getKey());
         if(leftKey != null && (key.isSmaller(leftKey)) || (!leftKey.isSmaller(key) && !key.isSmaller(leftKey)))
             return Search(x.getLeft(), key);
        else if(midKey != null && (key.isSmaller(midKey)) || (!midKey.isSmaller(key) && !key.isSmaller(midKey)))
             return Search(x.getMiddle(), key);
        else
            return Search(x.getRight(), key);
    }

    @Override
    public TreeNode2_3 Insert_And_Split(TreeNode2_3 x, TreeNode2_3 z) {
        TreeNode2_3 l = x.getLeft();
        TreeNode2_3 m = x.getMiddle();
        TreeNode2_3 r = x.getRight();
        if(r == null){
            if(((RunnerID)z.getKey()).isSmaller((RunnerID) l.getKey()))
                Set_Children(x,z,l,m);
            else if (((RunnerID)z.getKey()).isSmaller((RunnerID) m.getKey()))
                Set_Children(x,l,z,m);
            else
                Set_Children(x,l,m,z);
            return null;
        }
        TreeNode2_3 y = new TreeNode2_3(null, null, null,null,null);
        if(((RunnerID)z.getKey()).isSmaller((RunnerID) l.getKey())){
            Set_Children(x,z,l,null);
            Set_Children(y,m,r,null);
        }
        else if (((RunnerID)z.getKey()).isSmaller((RunnerID) m.getKey())){
            Set_Children(x,l,z,null);
            Set_Children(y,m,r,null);
        }
        else if (((RunnerID)z.getKey()).isSmaller((RunnerID) r.getKey())){
            Set_Children(x, l, m, null);
            Set_Children(y, z,r,null);
        }
        else{
            Set_Children(x, l, m, null);
            Set_Children(y, r,z,null);
        }
        return y;
    }

    @Override
    public void Insert(TreeNode2_3 root, TreeNode2_3 z) {
        if(! (z instanceof Leaf2_3))
            System.out.println("error");
        TreeNode2_3 y = root;
        while (! (y instanceof Leaf2_3)){
            if(((RunnerID)z.getKey()).isSmaller((RunnerID) y.getLeft().getKey()))
                y = y.getLeft();
            else if(((RunnerID)z.getKey()).isSmaller((RunnerID) y.getMiddle().getKey()))
                y = y.getMiddle();
            else y = y.getRight();
        }
        TreeNode2_3 x = y.getParent();
        z = Insert_And_Split(x,z);
        while(!x.equals(root)){
            x = x.getParent();
            if(z != null)
                z = Insert_And_Split(x,z);
            else Update_Key(x);
        }
        if(z != null){
            TreeNode2_3 w = new TreeNode2_3(null, null, null, null, null);
            Set_Children(w,x,z,null);
            setRoot(w);
        }
    }
}
