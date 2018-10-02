package com.duan.blogos.service.dao.blog;

import com.duan.blogos.service.dao.BaseDao;
import com.duan.blogos.service.entity.blog.BlogCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created on 2017/12/20.
 *
 * @author DuanJiaNing
 */
@Repository
public interface BlogCategoryDao extends BaseDao<BlogCategory> {

    /**
     * 根据id查询类别
     *
     * @param ids 类别id
     * @return 查询结果
     */
    List<BlogCategory> listCategoryById(@Param("ids") List<Long> ids);

    /**
     * 查询博主创建的所有博文类别
     *
     * @param bloggerId 博主id
     * @return 查询结果
     */
    List<BlogCategory> listCategoryByBloggerId(@Param("bloggerId") Long bloggerId);

    /**
     * 在限定博主的情况下获取指定id的博文类别
     *
     * @param categoryId 类别id
     * @return 查询结果
     */
    BlogCategory getCategory(Long categoryId);

    /**
     * 统计指定博主创建的类别数量
     *
     * @param bloggerId 博主id
     * @return 数量
     */
    Integer countByBloggerId(Long bloggerId);

    /**
     * 根据类别名精确查询类别 id
     *
     * @param bloggerId 博主 id
     * @param title     类别名
     * @return 存在返回 id
     */
    Long getCategoryIdByTitle(@Param("bloggerId") Long bloggerId, @Param("title") String title);
}