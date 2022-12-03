import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class MyAVLTree {
//1、二叉树 2、完全二叉树 3、平衡
        public static void main(String[] args) {
            int[] a= {-2,-1,2,5,6,7,9,10};
            System.out.println(sortedArrayToBST(a));
            prinAvlNode(sortedArrayToBST(a));
            System.out.println("+++++++++++++++++++++++++++++++++");
            printLeve(sortedArrayToBST(a));
        }


        public static AVLTreeNode sortedArrayToBST(int[] nums) {
            if(nums.length == 0) return null;
            int index = nums.length / 2;
            AVLTreeNode node = new AVLTreeNode(nums[index]);
            int[] leftArray = new int[index];
            for(int i = 0;i<leftArray.length;i++) {
                leftArray[i] = nums[i];
            }
            node.lNode = sortedArrayToBST(leftArray);
            int[] rightArray = new int[nums.length - index - 1];
            for(int i = 0;i<rightArray.length;i++) {
                rightArray[i] = nums[index + 1 + i];
            }
            node.rNode = sortedArrayToBST(rightArray);
            return node;
        }

        public static void prinAvlNode(AVLTreeNode node){
            if(node!=null){
                prinAvlNode(node.lNode);
                System.out.println("-----------------------------------------------");
                System.out.println(node);
                System.out.println("-----------------------------------------------");
                prinAvlNode(node.rNode);
            }
        }

        public static void printLeve(AVLTreeNode node){
            if(node!=null){
                Queue<AVLTreeNode> queue = new LinkedList<AVLTreeNode>();
                queue.add(node);
                while (!queue.isEmpty()) {
                    AVLTreeNode nodes= queue.remove();
                    System.out.println(nodes.value);
                    if (nodes.lNode != null) {
                        queue.add(nodes.lNode);
                    }
//                    queue.remove();
                    if (nodes.rNode != null) {
                        queue.add(nodes.rNode);
                    }
                }

            }
        }

}


