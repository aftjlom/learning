import java.util.*;

/**
 * 给你一个整数 n ，表示有 n 节课，课程编号从 1 到 n 。同时给你一个二维整数数组 relations ，
 * 其中 relations[j] = [prevCoursej, nextCoursej] ，表示课程 prevCoursej 必须在课程 nextCoursej 之前 完成（先修课的关系）。
 * 同时给你一个下标从 0 开始的整数数组 time ，其中 time[i] 表示完成第 (i+1) 门课程需要花费的 月份 数。
 * 你根据以下规则算出完成所有课程所需要的 最少 月份数：
 * 如果一门课的所有先修课都已经完成，你可以在 任意 时间开始这门课程。
 * 你可以 同时 上 任意门课程 。
 * 请你返回完成所有课程所需要的 最少 月份数。
 * 注意：测试数据保证一定可以完成所有课程（也就是先修课的关系构成一个有向无环图）。
 * <p>
 * 示例 1:
 * 输入：n = 3, relations = [[1,3],[2,3]], time = [3,2,5]
 * 输出：8
 * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
 * 你可以在月份 0 同时开始课程 1 和 2 。
 * 课程 1 花费 3 个月，课程 2 花费 2 个月。
 * 所以，最早开始课程 3 的时间是月份 3 ，完成所有课程所需时间为 3 + 5 = 8 个月。
 * <p>
 * 示例 2：
 * 输入：n = 5, relations = [[1,5],[2,5],[3,5],[3,4],[4,5]], time = [1,2,3,4,5]
 * 输出：12
 * 解释：上图展示了输入数据所表示的先修关系图，以及完成每门课程需要花费的时间。
 * 你可以在月份 0 同时开始课程 1 ，2 和 3 。
 * 在月份 1，2 和 3 分别完成这三门课程。
 * 课程 4 需在课程 3 之后开始，也就是 3 个月后。课程 4 在 3 + 4 = 7 月完成。
 * 课程 5 需在课程 1，2，3 和 4 之后开始，也就是在 max(1,2,3,7) = 7 月开始。
 * 所以完成所有课程所需的最少时间为 7 + 5 = 12 个月。
 * <p>
 * 提示：
 * 1 <= n <= 5 * 104
 * 0 <= relations.length <= min(n * (n - 1) / 2, 5 * 104)
 * relations[j].length == 2
 * 1 <= prevCoursej, nextCoursej <= n
 * prevCoursej != nextCoursej
 * 所有的先修课程对 [prevCoursej, nextCoursej] 都是 互不相同 的。
 * time.length == n
 * 1 <= time[i] <= 104
 * 先修课程图是一个有向无环图。
 */
public class 并行课程3 {
    /**
     * 方法一：dp深搜
     * 要求出完成所有课程的最少月份数，可以求出每门课程的最少月份数，然后求出最大值。首先根据 relations，
     * 构建先修课邻接表 prev，prev[i] 就表示课程 i 的所有的先修课。定义函数 dp，输入参数为 i，返回完成课程 i 所需的最少月份数。
     * 如果一门课程 i 没有先修课要求，那么完成它的最少月份数就是 time[i−1]。
     * 如果一门课有先修课时，完成它的最少月份数就是在它的所有先修课的最少完成月份的最大值的基础上，
     * 再加上 time[i−1]，即 dp[i]=max(dp[j])+time[i−1],j∈prev[i]。
     * 可以运用记忆化搜索的技巧，求出每门课的最少完成月份数。因为运用了记忆化搜索，每门课的最少完成月份数最多只会被计算一次。
     */
    public int minimumTime(int n, int[][] relations, int[] time) {
        int mx = 0;
        // 构建一个邻接表，每行为当前课程的所有前置课程
        List<Integer>[] prev = new List[n + 1];
        Arrays.setAll(prev, k-> new ArrayList<>());
        for (int[] relation : relations) {
            prev[relation[1]].add(relation[0]);
        }

        // 用于记录所有课程需要的时间
        Map<Integer, Integer> memo = new HashMap<>();
        // 遍历所有的课程，获取当前课程需要的最长时间
        for (int i = 1; i <= n; i++) {
            mx = Math.max(mx, dp(i, time, prev, memo));
        }
        return mx;
    }

    /**
     * 深搜函数
     *
     * @param i    课程编号
     * @param time 所有的课程需要的时间表，下标-1
     * @param prev 所有课程需要学习的前置课程列表
     * @param memo 学习当前课程需要的时间
     * @return 返回当前课程需要的时间
     */
    public int dp(int i, int[] time, List<Integer>[] prev, Map<Integer, Integer> memo) {
        // 若当前课程没有计算所需时间
        if (!memo.containsKey(i)) {
            int cur = 0;
            // 遍历当前课程所有的前置课程，获取需要最长时间的课程
            for (int p : prev[i]) {
                cur = Math.max(cur, dp(p, time, prev, memo));
            }
            // 将当前课程需要的时间加入，并存入map
            cur += time[i - 1];
            memo.put(i, cur);
        }
        // 返回当前课程需要的时间
        return memo.get(i);
    }

    /**
     * 方法二：拓扑排序 + 动态规划
     * 我们首先根据给定的先修课程关系，构建出一个有向无环图，对该图进行拓扑排序，然后根据拓扑排序的结果，
     * 使用动态规划求出完成所有课程所需要的最少时间。
     * 我们定义以下几个数据结构或变量：
     * 邻接表 g 存储有向无环图，同时使用一个数组 indeg 存储每个节点的入度；
     * 队列 q 存储所有入度为 0 的节点；
     * 数组 f 存储每个节点的最早完成时间，初始时 f[i]=0；
     * 变量 ans 记录最终的答案，初始时 ans=0；
     * 当 q 非空时，依次取出队首节点 i，遍历 g[i] 中的每个节点 j，更新 f[j]=max(f[j],f[i]+time[j])，同时更新 ans=max(ans,f[j])，
     * 并将 j 的入度减 1，如果此时 j 的入度为 0，则将 j 加入队列 q 中；
     * 最终返回 ans。
     */
    public int minimumTime2(int n, int[][] relations, int[] time) {
        // 初始化一个二维数组邻接表
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        // 构造一个数组存储每个结点的入度
        int[] indeg = new int[n];
        // 将课程表信息存入邻接表和入度数组
        for (int[] e : relations) {
            int a = e[0] - 1, b = e[1] - 1;
            g[a].add(b);
            ++indeg[b];
        }
        // 拓扑排序的队列
        Deque<Integer> q = new ArrayDeque<>();
        // 记录所有课程需要的时间
        int[] f = new int[n];
        int ans = 0;
        // 获取所有的入度为0的结点，将这些节点入队列，并初始化这些课程需要的时间
        for (int i = 0; i < n; ++i) {
            int v = indeg[i], t = time[i];
            if (v == 0) {
                q.offer(i);
                f[i] = t;
                ans = Math.max(ans, t);
            }
        }
        // 从队列中依次取出结点，直到所有的节点都遍历过
        while (!q.isEmpty()) {
            // 从队列中取出头结点
            int i = q.pollFirst();
            // 遍历当前节点所有的后置课程
            for (int j : g[i]) {
                f[j] = Math.max(f[j], f[i] + time[j]);
                ans = Math.max(ans, f[j]);
                // 若当前节点的出度不为0，入队列
                if (--indeg[j] == 0) {
                    q.offer(j);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        并行课程3 solution = new 并行课程3();
        System.out.println(solution.minimumTime(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
        System.out.println(solution.minimumTime(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}},
                new int[]{1, 2, 3, 4, 5}));

        System.out.println(solution.minimumTime2(3, new int[][]{{1, 3}, {2, 3}}, new int[]{3, 2, 5}));
        System.out.println(solution.minimumTime2(5, new int[][]{{1, 5}, {2, 5}, {3, 5}, {3, 4}, {4, 5}},
                new int[]{1, 2, 3, 4, 5}));
    }
}
