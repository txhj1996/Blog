package com.lrm;

import com.lrm.dao.BlogRepository;
import com.lrm.dao.TypeRepository;
import com.lrm.dao.UserRepository;
import com.lrm.po.Blog;
import com.lrm.po.Type;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void contextLoads() {
        List<Blog> blogs = blogRepository.getAll();
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    @Test
    public void contextLoads1() {
        String str = "id,title,content,first_picture,flag,views,appreciation,share_statement,commentabled,published,recommend,create_time,update_time,type_id,user_id,description";
        String[] split = str.split(",");

        for (int i = 0; i < split.length; i++) {
            split[i] = split[i] + "=#{" + split[i] + "}";
        }
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            b.append(split[i]);
            if (i == split.length - 1)
                break;
            b.append(", ");
        }
        System.out.println(b.toString());
    }

    @Test
    public void contextLoads2() {
		List<Type> list = typeRepository.findTop(3);
		for (Type type : list) {
			System.out.println(type);
		}
	}

    @Test
    public void contextLoads3() {
        System.out.println(new Date());
    }

    @Test
    public void contextLoads4() {
        List<Blog> blogs = blogRepository.getAll();
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    @Test
    public void contextLoads5() {
        List<String> blogCreateYears = blogRepository.getBlogCreateYears();
        for (String blogCreateYear : blogCreateYears) {
            System.out.println(blogCreateYear);
        }

    }

}
