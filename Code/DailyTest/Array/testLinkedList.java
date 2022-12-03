public class testLinkedList {
    public static void main(String[] args) {
        LinkedLastK.ListNode head = new LinkedLastK.ListNode(12);
        if(head == null || head.next == null)
            return;

        // 快满指针找到中间节点
        LinkedLastK.ListNode fast = head;
        LinkedLastK.ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 拆分链表，并反转中间节点之后的链表
        LinkedLastK.ListNode after = slow.next;
        slow.next = null;
        LinkedLastK.ListNode pre = null;
        while(after != null){
            LinkedLastK.ListNode temp = after.next;
            after.next = pre;
            pre = after;
            after = temp;
        }
        // 合并两个链表
        LinkedLastK.ListNode first = head;
        after = pre;
        while(first != null && after != null){
            LinkedLastK.ListNode ftemp = first.next;
            LinkedLastK.ListNode aftemp = after.next;
            first.next = after;
            first = ftemp;
            after.next = first;
            after = aftemp;
        }
    }
}
