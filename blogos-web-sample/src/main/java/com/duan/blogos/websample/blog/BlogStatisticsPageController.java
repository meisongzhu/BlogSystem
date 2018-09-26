package com.duan.blogos.websample.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2018/3/30.
 *
 * @author DuanJiaNing
 */
@Controller
@RequestMapping("/{bloggerName}/blog-statistics")
public class BlogStatisticsPageController {

    @RequestMapping
    public ModelAndView page(@PathVariable String bloggerName,
                             @ModelAttribute("blogId") Integer blogId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/blogger/blog_statistics");
        mv.addObject("bloggerName", bloggerName);

        return mv;
    }
}
