/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 49.234.182.170:8001
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 13/02/2020 14:52:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '分类的主键',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章的主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章分类关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_category
-- ----------------------------
INSERT INTO `article_category` VALUES (1, 6, 1, '2020-02-13 06:34:08', '2020-02-13 06:34:08');
INSERT INTO `article_category` VALUES (2, 8, 2, '2020-02-13 06:35:18', '2020-02-13 06:51:19');
INSERT INTO `article_category` VALUES (3, 7, 3, '2020-02-13 06:36:40', '2020-02-13 06:51:21');

-- ----------------------------
-- Table structure for article_comment
-- ----------------------------
DROP TABLE IF EXISTS `article_comment`;
CREATE TABLE `article_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章主键',
  `comment_id` bigint(0) NULL DEFAULT NULL COMMENT '评论主键',
  `effective` bit(1) NULL DEFAULT b'0' COMMENT '是否被删除: 0为没有',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章评论关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_content
-- ----------------------------
DROP TABLE IF EXISTS `article_content`;
CREATE TABLE `article_content`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章主键',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章内容',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_content
-- ----------------------------
INSERT INTO `article_content` VALUES (1, 1, '### npm\n\n- `npm init`  生成一个package.json文件\n- 进入包含package.json的目录中，`npm install`下载该项目需要的库\n\n##### install\n\n- `npm install -g xxx`  在全局下载xxx\n- `npm install –save(-s) `  表示本地安装，会被加至dependencies部分，即开发环境\n- `npm install  –save-dev(-D)` 表示本地安装，会被加至devDependencies部分，即生产环境\n\n##### 调用webpack打包器\n\n- 打包：`npm run build`\n- 运行：`npm run dev`\n- 如果有webpack.config.js可以`webpack`打包\n\n### vue\n\n##### vue-cli\n\n###### 3.0以前\n\n- 也可以`init webpack vuedemo` 创建项目，使用`npm run dev启动`\n\n###### 3.0以后\n\n- 先cd到要创建的目录中，`vue create firstdemo` `npm run serve`\n- `vue ui` 在UI界面创建，在浏览器启动`npm run serve`\n- [关于3.0vue的配置](https://segmentfault.com/a/1190000016101954)\n\n### node\n\n- `node xxx(.js)` 运行js\n\n### copy网上代码常用命令\n\n1. 先下载解压\n2. 然后在目录下使用`cnpm install`下载依赖\n3. 然后`npm run dev`运行\n\n\n\n### 项目相关\n\n- 在script可以定义标签\n\n  ```js\n  <template>\n    <div id=\"app\">\n        //add-blog是addBlog中的name\n        <add-blog></add-blog>\n    </div>\n  </template>\n  \n  <script>\n  import AddBlog from \'./views/addBlog\'\n  export default {\n    name: \'App\',\n    components: {\n      AddBlog\n    }\n  }\n  </script>\n  ```\n\n- main.js是总入口，从这引入依赖可以有全局作用\n\n\n\n### 语法相关\n\n- v-bind和v-model的区别\n\n  - v-model用在表单控件上的，用于实现双向数据绑定，所以如果你用在除了表单控件以外的标签是没有任何效果的。\n  - v-bind常用在css样式，数组，连接当中，是单向绑定\n\n- props\n\n  ```js\n  a.vue:\n  {{title}}\n  props:{title}\n  \n  b.js\n  <a :title=\"title\"></a>\n  ```\n - 本文转自 ： http://www.stonee.club', '2020-02-13 06:34:08', '2020-02-13 06:41:49');
INSERT INTO `article_content` VALUES (2, 2, '- 男 / 90后\n- 专注Java / 对前端和设计感兴趣 \n- 跑步 / 篮球 / 旅游 / 下雨 / 电影\n\n- **非常感谢 stalern 的技术分享（原博主）**\n', '2020-02-13 06:35:18', '2020-02-13 06:41:06');
INSERT INTO `article_content` VALUES (3, 3, '- 本站技术栈\n\n  **前端**\n\n  * vue / vueCli 3.0 + \n  * vue Router / vuex / axios\n  * SemanticUI / ElementUI\n  * nginx\n  \n  **后端**\n\n  * SpringBoot\n  * Mybatis - plush\n  * MySQL 8.0 +\n  * RESTful\n  * Swagger\n\n  **IDE & Tools**\n\n  * WebStorm\n  * Intellij IDEA\n  * DataGrip\n  * Postman\n\n  **服务器**\n  * 阿里云\n\n- 本博客用来记录我的日常问题和思考\n- 有问题请补充\n- 互换友链请留言', '2020-02-13 06:36:40', '2020-02-13 06:36:40');

-- ----------------------------
-- Table structure for article_info
-- ----------------------------
DROP TABLE IF EXISTS `article_info`;
CREATE TABLE `article_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `summary` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文章摘要',
  `traffic` bigint(0) NULL DEFAULT 0 COMMENT '文章访问次数',
  `love_num` bigint(0) NULL DEFAULT 0 COMMENT '点赞人数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_info
-- ----------------------------
INSERT INTO `article_info` VALUES (1, 'vue常用命令', '让后端开发的你对vue有一次全新的认识', 3, 0, '2020-02-13 06:34:08', '2020-02-13 06:41:54');
INSERT INTO `article_info` VALUES (2, '关于', '关于', 13, 0, '2020-02-13 06:35:18', '2020-02-13 06:51:48');
INSERT INTO `article_info` VALUES (3, '公告', '公告', 10, 0, '2020-02-13 06:36:40', '2020-02-13 06:51:45');

-- ----------------------------
-- Table structure for article_lover
-- ----------------------------
DROP TABLE IF EXISTS `article_lover`;
CREATE TABLE `article_lover`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章点赞表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_picture
-- ----------------------------
DROP TABLE IF EXISTS `article_picture`;
CREATE TABLE `article_picture`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `article_id` bigint(0) NULL DEFAULT NULL COMMENT '文章的id',
  `picture_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片的url',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category_info
-- ----------------------------
DROP TABLE IF EXISTS `category_info`;
CREATE TABLE `category_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  `number` bigint(0) NULL DEFAULT 0 COMMENT '文章的分类数量',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_info
-- ----------------------------
INSERT INTO `category_info` VALUES (1, 'computer', 1, NULL, '2020-02-13 06:30:29', '2020-02-13 06:34:08');
INSERT INTO `category_info` VALUES (2, '生活', 2, NULL, '2020-02-13 06:30:35', '2020-02-13 06:48:26');
INSERT INTO `category_info` VALUES (3, '后端', 0, 1, '2020-02-13 06:31:55', '2020-02-13 06:31:55');
INSERT INTO `category_info` VALUES (4, 'java', 0, 3, '2020-02-13 06:32:08', '2020-02-13 06:32:08');
INSERT INTO `category_info` VALUES (5, '前端', 1, 1, '2020-02-13 06:32:26', '2020-02-13 06:34:08');
INSERT INTO `category_info` VALUES (6, 'vue', 1, 5, '2020-02-13 06:32:36', '2020-02-13 06:34:08');
INSERT INTO `category_info` VALUES (7, '公告', 1, NULL, '2020-02-13 06:50:08', '2020-02-13 06:50:50');
INSERT INTO `category_info` VALUES (8, '关于', 1, NULL, '2020-02-13 06:50:10', '2020-02-13 06:50:52');

-- ----------------------------
-- Table structure for comment_info
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '评论信息',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问ip',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '评论人id',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '评论详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `operate_url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问的url',
  `operate_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '访问的浏览器',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_me
-- ----------------------------
DROP TABLE IF EXISTS `sys_me`;
CREATE TABLE `sys_me`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `u_id` int(0) NULL DEFAULT NULL COMMENT 'user表主键',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_me
-- ----------------------------
INSERT INTO `sys_me` VALUES (1, '6', '6', 1, '2020-02-11 05:32:59');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名称',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户角色',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浏览器',
  `region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip对应的地区',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户ip',
  `num` bigint(0) NULL DEFAULT 0 COMMENT '用户登陆次数',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '6', 'amdin', 'Google chrome', '上海', '101.80.85.57', 1, '2020-02-11 04:10:31', '2020-02-11 04:11:32');
INSERT INTO `sys_user` VALUES (2, NULL, 'ANY', 'Windows-Chrome-73.0.3683.86', NULL, '127.0.0.1', 24, '2020-02-13 06:07:45', '2020-02-13 06:51:41');

SET FOREIGN_KEY_CHECKS = 1;
