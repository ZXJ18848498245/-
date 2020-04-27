package test;

import crud.bean.Department;
import crud.bean.Employee;
import crud.dao.DepartmentMapper;
import crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/*
* 测试dao层的工作
* 推荐spring的项目就可以使用spring的单位测试，可以自动注入我需要的组件
* 导入springtest单元测试模块
* @ContextConfiguration指定spring配置文件的位置
* 直接autowired要使用的组件即可

 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class MapperTest {
    @Autowired
  private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SqlSession sqlSession;
/*
* 测试DepartmentMapper
* */
    @Test
    public void testCRUD(){
        //1.创建SpringIOC容器
       // ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.从容器中获取mapper
       // DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);

      System.out.println(departmentMapper);

      //插入几个部门
      //departmentMapper.insertSelective(new Department(null,"开发部"));
      //departmentMapper.insertSelective(new Department(null,"测试部"));

        //插入几个员工
       // employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",1));
        //employeeMapper.insertSelective(new Employee(null,"Marry","F","Marry@163.com",1));
        //employeeMapper.insertSelective(new Employee(null,"Tom","M","TOM@163.com",2));

        //批量插入多个员工,使用可以批量操作的sqlsession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i = 0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid, "M", uid+"@atguigu.com", 1));
        }
        System.out.println("批量完成");
    }
}
