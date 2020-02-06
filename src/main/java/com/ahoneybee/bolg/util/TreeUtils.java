package com.ahoneybee.bolg.util;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.CommentInfo;
import com.ahoneybee.bolg.entity.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 树形结构转换工具
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
public class TreeUtils {

    /**
     * 封装node节点
     *
     * @param commentInfo 评论信息
     * @param articleId   文章id
     * @return 节点
     */
    public static Node buildTree(List<CommentInfo> commentInfo, long articleId) {

        /*
         * 1 创建父节点 father ，所有节点归属于父节点
         * 2 遍历子节点，并判别子节点中是否存在父id
         * 3 如存在，找到该子节点的父节点，把该子节点放入
         * 4 在 father 中移除该子节点
         */
        Node tree = new Node();
        List<Node> children = Collections.synchronizedList(new ArrayList<>());

        creadNode(commentInfo, articleId, tree, children);

        children.parallelStream().forEach(node -> {
            Node no = findFatherNode(children, node.getParentId());
            judge(children, node, no);
        });
        return tree;
    }

    /**
     * 判断是否存在父节点，并作出相应的处理
     *
     * @param children 节点
     * @param node     当前节点
     * @param no       父节点
     */
    private static void judge(List<Node> children, Node node, Node no) {
        if (no != null) {
            if (CollectionUtil.isNotEmpty(no.getChildren())) {
                no.getChildren().add(node);
            } else {
                List<Node> childrenList = Collections.synchronizedList(new ArrayList<>());
                childrenList.add(node);
                no.setChildren(childrenList);
            }
            children.remove(node);
        }
    }

    /**
     * 当前子节点集合是否存在父节点
     *
     * @param childrens 集合
     * @param parentId  父id
     * @return 父节点
     */
    private static Node findFatherNode(List<Node> childrens, long parentId) {

        for (Node node : childrens) {
            if (node.getId() == parentId) {
                return node;
            }
        }
        return null;
    }


    /**
     * @param commentInfos 评论信息
     * @param articleId    文章id
     * @param tree         父节点
     * @param children     子节点
     */
    private static void creadNode(List<CommentInfo> commentInfos, long articleId, Node tree, List<Node> children) {

        //创建子节点
        commentInfos.parallelStream().forEach(commentInfo -> {
            children.add(
                    new Node(commentInfo, commentInfo.getParentId(), commentInfo.getId(), null)
            );
        });
        tree.setId(articleId);
        tree.setChildren(children);
    }


}
