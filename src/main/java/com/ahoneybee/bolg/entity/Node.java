package com.ahoneybee.bolg.entity;

import java.util.List;

/**
 * <p>
 * 节点
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
public class Node {

    /**
     * 放置信息
     */
    private Object object;

    /**
     * 父类id
     */
    private long parentId;

    /**
     * 自己id
     */
    private long id;

    /**
     * 放置子类
     */
    private List<Node> children;

}
