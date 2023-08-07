import Collection.ListNode;

public class 合并两个有序链表 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode pro = result;
        while (list1 != null || list2 != null) {
            if (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    pro.next = list1;
                    list1 = list1.next;
                } else {
                    pro.next = list2;
                    list2 = list2.next;
                }
            } else if (list1 != null) {
                pro.next = list1;
                list1 = list1.next;
            } else {
                pro.next = list2;
                list2 = list2.next;
            }
            pro = pro.next;
        }
        return result.next;
    }

    public static void main(String[] args) {
        合并两个有序链表 solution = new 合并两个有序链表();
        System.out.println(ListNode.resetListNodeToList(solution.mergeTwoLists(ListNode.buildListNodeByArray(new int[]{1, 2, 4}), ListNode.buildListNodeByArray(new int[]{1, 3, 4}))));
        System.out.println(ListNode.resetListNodeToList(solution.mergeTwoLists(ListNode.buildListNodeByArray(new int[]{}), ListNode.buildListNodeByArray(new int[]{}))));
        System.out.println(ListNode.resetListNodeToList(solution.mergeTwoLists(ListNode.buildListNodeByArray(new int[]{}), ListNode.buildListNodeByArray(new int[]{0}))));
    }
}
