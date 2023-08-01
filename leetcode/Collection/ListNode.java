package Collection;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildListNodeByArray(int[] nums) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return pre.next;
    }

    public static List<Integer> resetListNodeToList(ListNode node) {
        List<Integer> resList = new ArrayList<>();
        while (node != null) {
            resList.add(node.val);
            node = node.next;
        }
        return resList;
    }
}
