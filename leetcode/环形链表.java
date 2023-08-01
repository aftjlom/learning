import Collection.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 给你一个链表的头节点 head ，判断链表中是否有环。
 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 如果链表中存在环 ，则返回 true 。 否则，返回 false 。

 示例 1：
 输入：head = [3,2,0,-4], pos = 1
 输出：true
 解释：链表中有一个环，其尾部连接到第二个节点。

 示例 2：
 输入：head = [1,2], pos = 0
 输出：true
 解释：链表中有一个环，其尾部连接到第一个节点。

 示例 3：
 输入：head = [1], pos = -1
 输出：false
 解释：链表中没有环。

 提示：
 链表中节点的数目范围是 [0, 104]
 -10^5 <= Node.val <= 10^5
 pos 为 -1 或者链表中的一个 有效索引 。
 */
public class 环形链表 {
    /**
     方法一：哈希表
     最容易想到的方法是遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
     具体地，我们可以使用哈希表来存储所有已经访问过的节点。每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，
     否则就将该节点加入哈希表中。重复这一过程，直到我们遍历完整个链表即可。
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while(head != null) {
            if (nodeSet.contains(head)) {
                return true;
            }
            nodeSet.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     方法二：快慢指针
     本方法需要读者对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
     假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表上的同一个节点开始移动时，
     如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
     等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。

     我们可以根据上述思路来解决本题。具体地，我们定义两个指针，一快一慢。慢指针每次只移动一步，而快指针每次移动两步。
     初始时，慢指针在位置 head，而快指针在位置 head.next。这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。
     否则快指针将到达链表尾部，该链表不为环形链表。

     细节
     为什么我们要规定初始时慢指针在位置 head，快指针在位置 head.next，而不是两个指针都在位置 head（即与「乌龟」和「兔子」中的叙述相同）？
     观察下面的代码，我们使用的是 while 循环，循环条件先于循环体。由于循环条件一定是判断快慢指针是否重合，如果我们将两个指针初始都置于 head，
     那么 while 循环就不会执行。因此，我们可以假想一个在 head 之前的虚拟节点，慢指针从虚拟节点移动一步到达 head，
     快指针从虚拟节点移动两步到达 head.next，这样我们就可以使用 while 循环了。
     当然，我们也可以使用 do-while 循环。此时，我们就可以把快慢指针的初始值都置为 head。
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode solw = head;
        ListNode fast = head.next;
        while (solw != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            solw = solw.next;
            fast = fast.next.next;
        }
        return true;
    }
}
