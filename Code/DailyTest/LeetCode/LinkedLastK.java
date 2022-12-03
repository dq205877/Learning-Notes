import java.util.LinkedList;

public class LinkedLastK {
    public static void main(String[] args) {
//        LinkedList linkedList = new LinkedList();
//        linkedList.add(1);
//        linkedList.add(2);
//        linkedList.add(3);
//        linkedList.add(4);
//        linkedList.add(5);
//        System.out.println(linkedList.get(2));//3
//        linkedList = FindKthToTail(linkedList,5);
//        System.out.println(linkedList);

        ListNode node= new ListNode(1);
        ListNode node1= new ListNode(2);
        ListNode node2= new ListNode(3);
        ListNode node3= new ListNode(4);
        ListNode node4= new ListNode(5);
        node.nextNode(node1);
        node1.nextNode(node2);
        node2.nextNode(node3);
        node3.nextNode(node4);
        ListNode nodePro=FindKthToTail(node,3);
        System.out.println(nodePro);

    }

//    public static LinkedList FindKthToTail (LinkedList pHead, int k) {
//        // write code here
//        LinkedList last = new LinkedList();
//        if(k>pHead.size()){
//            return last;
//        }
//        while(k>0){
//            last.add(pHead.get(pHead.size()-k));
//            k--;
//        }
//        return last;
//    }

    static  class ListNode{
        ListNode curr;
        ListNode next;
        int  val;
        public ListNode(int val){
            this.val=val;
        }

        public ListNode nextNode(ListNode node){
            this.next=node;
            return  node;
        }
    }


    public static ListNode FindKthToTail (ListNode pHead, int k) {
        // write code here
        ListNode first = pHead;
        for(int  i= 0;i<k;i++){
            if(first == null) return first;
            first = first.next;

        }
        ListNode last = pHead;
        while(first!=null){//
            first = first.next;
            last = last.next;
        }
        return last;
    }
}
