/**
 * 基于rank优化树结构实现的并查集
 */

public class UnionFind4 implements UF {

    private int[] parent;
    private int[] rank;  //rank[i]表示以i为根的集合所表示的树的深度

    public UnionFind4(int size) {

        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //查找过程,查找元素p所对应的集合编号
    //O(h)复杂度，h为树的高度
    private int find(int p) {

        if(p < 0 && p >= parent.length)
            throw new IllegalArgumentException("p is out of bound !");

        //循环到根节点
        while(p != parent[p])
            p = parent[p];
        return p;
    }

    //查看p和q是否所属一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }


    //合并元素p和元素q所属的集合
    //O(h)复杂度,h为树的高度
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        //节点深度(rank)小的合并到节点深度大的集合上
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else {
            parent[qRoot] = pRoot;
            rank[pRoot]++;
        }
    }
}
