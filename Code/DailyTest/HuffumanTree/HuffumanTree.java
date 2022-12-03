import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffumanTree {
    public static void main(String[] args) {
            Integer[] arr =  {2,3,4,5,6,7,3,8,2};
            Node node = arrayToHuffuman(arr);
            System.out.println(node);
            System.out.println(node.leftNode+""+node.rightNode);
    }

    public static Node arrayToHuffuman(Integer [] array){
        List<Node> nodes = new ArrayList<>();
        if (array == null)
            return null;
        for(Integer i:array){
            nodes.add(new Node(i));
        }

        while (nodes.size()>1){
            //排序
            Collections.sort(nodes);
            System.out.println(nodes);
            //取出来权值最小的两个二叉树
            Node left = nodes.get(nodes.size()-1);
            Node right = nodes.get(nodes.size()-2);
            //创建一个新的二叉树
            Node parent = new Node(left.data+right.data);
            parent.leftNode=left;parent.rightNode=right;
            //移除两个最小的二叉树
            nodes.remove(left);
            nodes.remove(right);
            //加入原来的二叉树
            nodes.add(parent);
        }
        return nodes.size()>0?nodes.get(0):null;
    }


}


class Node implements Comparable<Node>{
    Integer weight;
    Integer data;
    Node leftNode;
    Node rightNode;

    public Node(Integer i){
        this.data=i;
    }

    @Override
    public int compareTo(Node o) {
        return -(this.data-o.data);
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    // 二分搜索树的层序遍历
//    public void levelOrder(){
//
//        if(root == null)
//            return;
//
//        Queue<Node> q = new LinkedList<>();
//        q.add(root);
//        while(!q.isEmpty()){
//            Node cur = q.remove();
//            System.out.println(cur.e);
//
//            if(cur.left != null)
//                q.add(cur.left);
//            if(cur.right != null)
//                q.add(cur.right);
//        }
//    }
}