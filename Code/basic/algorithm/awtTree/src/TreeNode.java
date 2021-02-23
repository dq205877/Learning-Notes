import java.util.Vector;
public class TreeNode {
    private Vector<TreeNode> children;
    private TreeNode parent;
    private TreeNode root;
    public TreeNode (TreeNode parent) {
        children = new Vector<TreeNode>();
        if (parent == null)
            root = this;
        else {
            parent.addChild(this);
        }
    }
    public void addChild (TreeNode n) {
        addChild(children.size(), n);
    }
    public void addChild (int index, TreeNode n) {
        children.add(index, n);
        n.parent = this;
    }
    public void removeChild (TreeNode n) {
        children.remove(n);
    }
    public void removeChild (int index) {
        children.remove(index);
    }
    public void setChild (int index, TreeNode n) {
        children.set(index, n);
        n.parent = this;
    }
    public TreeNode getChild (int index) {
        return children.get(index);
    }
    public void changePosition (int index1, int index2) {
        if (0 <= index1 && index1 < children.size() &&
                0 <= index2 && index2 <= children.size()) {
            TreeNode tmp = children.get(index1);
            children.set(index1, children.get(index2));
            children.set(index2, tmp);
        }
    }
    public void setParent (TreeNode n) {
        parent = n;
    }
    public TreeNode getRoot () {
        return root;
    }
    public TreeNode getParent () {
        return parent;
    }
    public Vector<TreeNode> getChildren () {
        return children;
    }
}