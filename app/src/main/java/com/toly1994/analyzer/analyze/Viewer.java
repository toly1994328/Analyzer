package com.toly1994.analyzer.analyze;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/2/17/017:8:31<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：View遍历类
 */
public class Viewer {
    private static final String TAG = "Viewer";
    private ViewNode mNode;
    private final StringBuilder mSbLayoutNode;
    private int layoutNodeDeep;


    public Viewer(View view) {
        mNode = new ViewNode(null);
        mNode.view = view.getRootView();
        mSbLayoutNode = new StringBuilder("布局层次:" + mNode.view.getClass().getName() + "\n");
        parserView(mNode);
        traversal(mNode);
    }

    private void parserView(ViewNode node) {
        layoutNodeDeep++;//每次进入层级+1
        View target = node.view;//目标View
        if (target instanceof ViewGroup) {//继承自ViewGroup
            ViewGroup group = (ViewGroup) target;
            //遍历ViewGroup的孩子
            for (int i = 0; i < group.getChildCount(); i++) {
                //每个孩子进行处理
                View child = group.getChildAt(i);
                ViewNode nodeChild = new ViewNode(child);//创建节点对象
                node.children.add(nodeChild);//添加孩子
                if (child instanceof ViewGroup) {//如果孩子也是ViewGroup
                    nodeChild.deep = layoutNodeDeep;//维护节点深度
                    parserView(nodeChild);//递归调用
                }
            }
        }
        layoutNodeDeep--;//每次跳出层级-1
    }


    private static StringBuilder blankBuilder(String symbol, int num) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuffer.append(symbol);
        }
        return stringBuffer;
    }

    /**
     * 文件节点
     */
    private class ViewNode {
        public View view; //文件路径
        public List<ViewNode> children;
        public int deep;//深度

        public ViewNode(View view) {
            this.view = view;
            children = new ArrayList<>();
        }
    }


    /**
     * 遍历布局节点
     *
     * @param node
     */
    private void traversal(ViewNode node) {
        if (node.children == null) {
            return;
        }
        for (ViewNode child : node.children) {
            mSbLayoutNode
                    .append(blankBuilder("·", node.deep))
                    .append(child.view.getClass().getName()).append("\n");
            traversal(child);
        }
    }


    public String showInfo(){
        return mSbLayoutNode.toString();
    }

    public interface OnTraversal {
        void traversal(View view, int deep);
    }

    public void onTraversal(OnTraversal onTraversal) {
        if (onTraversal == null) {
            return;
        }
        onTraversal(mNode, onTraversal);
    }

    private void onTraversal(ViewNode node, OnTraversal onTraversal) {
        if (node.children == null) {
            return;
        }
        for (ViewNode child : node.children) {
            onTraversal.traversal(child.view, node.deep);
            onTraversal(child, onTraversal);
        }
    }

}
