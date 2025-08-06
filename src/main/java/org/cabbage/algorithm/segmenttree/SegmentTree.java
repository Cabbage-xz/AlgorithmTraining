package org.cabbage.algorithm.segmenttree;

/**
 * @author xzcabbage
 * @since 2025/8/6
 *
 * 线段树 力扣3479
 */
public class SegmentTree {
    /*
    *
    * 线段树是一种二叉搜索树，它将一个区间划分成一些单元区间，每个单元区间对应线段树中的一个叶结点。
    * 对于线段树中的每一个非叶子节点 [a, b]，
    * 它的左儿子表示的区间为 [a, (a+b)/2]，右儿子表示的区间为 [(a+b)/2 + 1, b]。
    * 线段树的主要优势在于，对于区间的修改和查询操作，时间复杂度可以达到 O(log n)。
    *
    * */

    /*
    * 线段树的核心概念
    * 数组表示树：
    * 为了方便计算父子节点的索引，我们通常使用一个数组来存储线段树。
    * 如果一个节点的索引是 i，那么它的左子节点的索引是 2*i + 1，右子节点的索引是 2*i + 2。
    *
    * 建树 (Build)：
    * 根据输入的原始数组，递归地将区间一分为二，直到区间的左右端点相等（叶子节点），
    * 然后自底向上地根据业务需求（例如求和、最大值、最小值）合并子节点的信息。
    *
    * 区间查询 (Query)：
    * 给定一个查询区间 [queryL, queryR]，
    * 从根节点开始，递归地在树中查找。
    * 如果当前节点代表的区间完全被查询区间包含，则直接返回当前节点的值。
    * 如果部分重叠，则递归地在左子树和右子树中查询，然后合并结果。
    *
    * 单点更新 (Update)：
    * 当原始数组中的某个元素被修改时，我们也需要更新线段树。
    * 从根节点开始，找到包含该点的叶子节点并更新其值，然后自底向上地更新所有受影响的父节点。
    * */

    private int[] tree; //使用数组来存储线段树
    private int[] data; //存储原始数据

    /**
     * 构造函数
     * @param array 原始数据数组
     */
    public SegmentTree(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array is null or empty");
        }
        data = new int[array.length];
        System.arraycopy(array, 0, data, 0, array.length);
        // 线段树需要的空间大约是原始数组的4倍
        tree = new int[array.length * 4];

        buildSegmentTree(1, 0, data.length - 1);
    }

    /**
     * 构建线段树的递归函数
     * @param treeIndex 当前节点在tree数组中的索引
     * @param l 区间左边界
     * @param r 区间右边界
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 当区间左右端点相同时，到达叶子节点
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int mid = (l + r) >> 1;
        // 递归构建左子树和右子树
        buildSegmentTree(treeIndex << 1, l, mid);
        buildSegmentTree(treeIndex << 1 | 1, mid + 1, r);

        // 合并左子树和右子树的信息 这里以最大值为例
        tree[treeIndex] = Math.max(tree[treeIndex << 1], tree[treeIndex << 1 | 1]);
    }

    /**
     * 在区间内查询
     * @param ql 区间左边界
     * @param qr 区间右边界
     * @return 查询区间内的最大值
     */
    public int query(int ql, int qr) {
        if (ql < 0 || ql > data.length || qr < 0 || qr > data.length || ql > qr) {
            throw new IllegalArgumentException("Illegal argument");
        }
        return query(1, 0, data.length - 1, ql, qr);
    }

    /**
     * 递归查询
     * @param treeIndex 当前节点在tree数组中的索引
     * @param l 当前节点所表示的左边界
     * @param r 当前节点所表示的右边界
     * @param ql 查询左边界
     * @param qr 查询右边界
     * @return 查询区间最大值
     */
    public int query(int treeIndex, int l, int r, int ql, int qr) {
        // 被查询区间包含当前节点代表的区间
        if (l >= ql && r <= qr) {
            return tree[treeIndex];
        }
        int mid = (l + r) >> 1;
        int maxVal = Integer.MIN_VALUE; // 初始化为一个极小值

        // 如果查询区间与左子树 [l, mid] 有交集
        if (ql <= mid) {
            maxVal = Math.max(maxVal, query(treeIndex << 1, l, mid, ql, qr));
        }

        // 如果查询区间与右子树 [mid + 1, r] 有交集
        if (qr > mid) {
            maxVal = Math.max(maxVal, query(treeIndex << 1 | 1, mid + 1, r, ql, qr));
        }

        return maxVal;

    }

    /**
     * 更新原始数组中 index 位置的值为value
     * @param index 要更新元素的索引
     * @param value 新的值
     */
    private void update(int index, int value) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Illegal argument");
        }
        data[index] = value;
        update(1, 0, data.length - 1, index, value);
    }

    private void update(int treeIndex, int l, int r, int index, int value) {
        // 到达叶子节点
        if (l == r) {
            tree[treeIndex] = value;
            return;
        }

        int mid = (l + r) >> 1;
        if (index <= mid) {
            update(treeIndex << 1, l, mid, index, value);
        } else {
            update(treeIndex << 1 | 1, mid + 1, r, index, value);
        }
        tree[treeIndex] = Math.max(tree[treeIndex << 1], tree[treeIndex << 1 | 1]);
    }

    /**
     * 主函数，用于测试
     */
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segTree = new SegmentTree(arr);

        System.out.println("构建的线段树 (数组表示): ");
        System.out.println(segTree); // 打印内部的tree数组

        System.out.println("\n查询区间 [1, 4] 的最大值:");
        System.out.println(segTree.query(1, 4)); // 9

        System.out.println("\n更新索引 2 的值为 6:");
        segTree.update(2, 12);

        System.out.println("更新后的原始数据 (逻辑上): [1, 3, 6, 7, 9, 11]");

        System.out.println("\n再次查询区间 [1, 4] 的最大值:");
        System.out.println(segTree.query(1, 4)); // 12
    }

}
