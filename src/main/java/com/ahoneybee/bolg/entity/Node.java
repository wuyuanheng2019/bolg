package com.ahoneybee.bolg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 节点
 * </p>
 *
 * @author wuyuanheng
 * @since 2020-02-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    /**
     * 放置信息
     */
    private Object object;

    /**
     * 自己id
     */
    private Long id;

    /**
     * 父类id
     */
    private Long parentId;


    /**
     * 放置子类
     */
    private List<Node> children;

}
