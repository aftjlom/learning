import Collection.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class 重排链表 {
    /**
     * 方法一：线性表
     * 因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
     * 因此比较容易想到的一个方法是，我们利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> nodeList = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            nodeList.add(node);
            node = node.next;
        }
        int begin = 0, end = nodeList.size() - 1;
        while (begin < end) {
            nodeList.get(begin).next = nodeList.get(end);
            begin++;
            if (begin == end) {
                break;
            }
            nodeList.get(end).next = nodeList.get(begin);
            end--;
        }
        nodeList.get(begin).next = null;
    }

    /**
     方法二：寻找链表中点 + 链表逆序 + 合并链表
     注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
     这样我们的任务即可划分为三步：
     找到原链表的中点（参考「876. 链表的中间结点」）。
     我们可以使用快慢指针来 O(N) 地找到链表的中间节点。
     将原链表的右半端反转（参考「206. 反转链表」）。
     我们可以使用迭代法实现链表的反转。
     将原链表的两端合并。
     因为两链表长度相差不超过 1，因此直接合并即可.
     */
    public void reorderList2(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode list1 = head;
        ListNode list2 = mid.next;
        mid.next = null;
        list2 = reverseList(list2);
        mergeList(list1, list2);
    }

    /**
     * 快慢指针查询中点指针
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 反转链表
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 合并链表
     */
    public void mergeList(ListNode list1, ListNode list2) {
        ListNode list1Tmp;
        ListNode list2Tmp;
        while (list1 != null && list2 != null) {
            list1Tmp = list1.next;
            list2Tmp = list2.next;
            list1.next = list2;
            list1 = list1Tmp;
            list2.next = list1;
            list2 = list2Tmp;
        }
    }
}
