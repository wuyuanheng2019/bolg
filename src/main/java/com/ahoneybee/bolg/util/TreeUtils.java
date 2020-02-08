package com.ahoneybee.bolg.util;

import cn.hutool.core.collection.CollectionUtil;
import com.ahoneybee.bolg.entity.CategoryInfo;
import com.ahoneybee.bolg.entity.CommentInfo;
import com.ahoneybee.bolg.entity.Node;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

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
@Slf4j
public class TreeUtils {

    /**
     * 封装node节点
     *
     * @param commentInfos  评论信息
     * @param categoryInfos 分类信息
     * @param id            父节点id
     * @return 节点
     */
    public static Node buildTree(List<CommentInfo> commentInfos, List<CategoryInfo> categoryInfos, long id) {

        /*
         * 1 创建父节点 father ，所有节点归属于父节点
         * 2 遍历子节点，并判别子节点中是否存在父id
         * 3 如存在，找到该子节点的父节点，把该子节点放入
         * 4 在 father 中移除该子节点
         */
        Node tree = new Node();
        List<Node> childrens = Collections.synchronizedList(new ArrayList<>());
        List<Node> operlist = Collections.synchronizedList(new ArrayList<>());

        creadNode(commentInfos, categoryInfos, id, tree, childrens);

        childrens.forEach(node -> {
            Node no = findFatherNode(childrens, node.getParentId());
            judge(operlist, node, no);
        });

        childrens.removeAll(operlist);
        return tree;
    }


    /**
     * 判断是否存在父节点，并作出相应的处理
     *
     * @param operlist 操作节点(记录要移除的node)
     * @param node     当前节点
     * @param no       父节点
     */
    private static void judge(List<Node> operlist, Node node, Node no) {

        //判断是否存在
        if (ObjectUtils.isNotEmpty(no)) {

            //判断当前父节点是否存在子节点
            if (CollectionUtil.isNotEmpty(no.getChildren())) {

                no.getChildren().add(node);
            } else {

                //创建子节点，并设置
                List<Node> childrenList = Collections.synchronizedList(new ArrayList<>());
                childrenList.add(node);
                no.setChildren(childrenList);

            }

            //记录需要移除的节点
            operlist.add(node);
        }
    }

    /**
     * 当前子节点集合是否存在父节点
     *
     * @param childrens 集合
     * @param parentId  父id
     * @return 父节点
     */
    private static Node findFatherNode(List<Node> childrens, Long parentId) {

        for (Node n : childrens) {
            if (n.getId().equals(parentId)) {
                return n;
            }
        }
        return null;
    }


    /**
     * @param commentInfos  评论信息
     * @param categoryInfos 分类信息
     * @param id            id
     * @param tree          父节点
     * @param children      子节点
     */
    private static void creadNode(List<CommentInfo> commentInfos, List<CategoryInfo> categoryInfos,
                                  long id, Node tree, List<Node> children) {

        //创建子节点
        if (CollectionUtil.isNotEmpty(commentInfos)) {
            commentInfos.forEach(commentInfo -> {
                children.add(
                        new Node(commentInfo, commentInfo.getId(),
                                commentInfo.getParentId(), null));
            });
        } else {
            categoryInfos.forEach(categoryInfo -> {
                children.add(
                        new Node(categoryInfo, categoryInfo.getId(),
                                categoryInfo.getParentId(), null));
            });
        }

        tree.setId(id);
        tree.setChildren(children);
    }

}
