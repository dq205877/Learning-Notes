public class AVLTreeNode {
    //1、二叉树,所有节点都是平衡的
    AVLTreeNode  node;
    AVLTreeNode  lNode;
    AVLTreeNode  rNode;
    boolean  balance;
    int value;

    public AVLTreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "value=" + value +
                '}';
    }
}
