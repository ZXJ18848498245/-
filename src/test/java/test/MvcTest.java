package test;

import com.github.pagehelper.PageInfo;
import crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/*
* 使用spring测试模块提示的测试请求功能
* */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:WEB-INF/dispatcher-servlet.xml"})
public class MvcTest {
    //传入springmvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟的mvc请求，获取到处理结果
    MockMvc mockMvc;

    @Before
    public void initMokcMvc(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn","1")).andReturn();
        //请求成功以后，请求域中会有pageinfo，我们可以取出pageinfo进行验证
         MockHttpServletRequest request=result.getRequest();
         PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
         System.out.println("当前页码："+ pageInfo.getPageNum());
         System.out.println("总页码"+pageInfo.getPages());
         System.out.println("总记录数："+pageInfo.getTotal());
         System.out.println("在页面需要连续显示的页码");
         int [] nums = pageInfo.getNavigatepageNums();
         for(int i :nums){
             System.out.println(" "+i);
         }

         //获取员工数据
        List<Employee> list =  pageInfo.getList();
         for(Employee employee:list){
             System.out.println(employee);
         }
    }
}
