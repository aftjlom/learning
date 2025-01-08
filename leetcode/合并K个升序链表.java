import Collection.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 合并K个升序链表 {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.val)));
    public ListNode mergeKLists(ListNode[] lists) {
        for(ListNode node:lists){
            if(node!=null){
                pq.offer(node);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while(!pq.isEmpty()){
            ListNode s = pq.poll();
            cur.next = s;
            cur=cur.next;
            s = s.next;
            if(s!=null){
                pq.offer(s);
            }
        }
        return dummy.next;
    }
}
