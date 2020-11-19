package cc.insistor;

import cc.insistor.dao.*;
import cc.insistor.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaApplicationTests {
    @Autowired
    private PetDao petDao;//springboot在启动的时候，底层使用了动态代理的方式获得了一个接口实现类，完成了注入
    @Autowired
    private ClazzDao clazzDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private EmpDao empDao;
    @Autowired
    private BoyDao boyDao;
    @Autowired
    private GirlDao girlDao;

    @Test
    void contextLoads() {
        System.out.println("------创建成功了！！！------");
    }


    //对jpa提供的方法进行测试
    /***
     * 插入
     * 注入PetDao接口，只要是JpaRepository接口的子接口，自动被纳入到spring容器
     * ，纳入spring容器的是实现类的对象(代理对象)，产生的代理对象的id是接口首字母小写
     * <bean id="petDao" class="com.sun.Proxy2">
     *
     */

    /***
     * 更新宠物信息
     * save(obj)
     * 	  如果id是null，执行insert操作
     *   如果id不是null,查询数据库
     *   		id在数据库存在   执行update
     *   		id在数据不存在   执行insert
     */
    //所以save 有插入和更新的作用，就是看主键在不在表中

    @Test
    void addPet() {
        //创建Pet对象 注意一点： 当数据库当中设置了主键自增。就不要在设置id。否则设置的id不会生效。


        System.out.println("添加一个宠物！！！");
        Pet pet = new Pet();
        pet.setPname("2大黄");
        pet.setColor("白色");
        petDao.save(pet);
    }


    /***
     * 查询某个宠物信息
     */

    @Test
    void findPet() {
        //查询某个宠物信息，单条查询
        Optional<Pet> byId = petDao.findById(1);
        Pet pet = byId.get();
        System.out.println(pet.getId() + " " + pet.getPname() + " " + pet.getColor());


    }


    /***
     * 查询所有宠物信息
     * 查询接口的实现类： ctrl+alt+B
     */
    @Test
    public void findAllPets() {
//        List<Pet> all = petDao.findAll();
//        for(Pet pet:all){
//            System.out.println(pet.getId()+" "+pet.getPname()+" "+pet.getColor());
//
//        }


        //根据pname 查询 查询的结构降序排序， 默认是升序
        Sort sort = Sort.by(Sort.Direction.DESC, "pname");
        List<Pet> pname = petDao.findAll(sort);
        for (Pet pet : pname) {
            System.out.println(pet.getPname());
        }

    }


    /***
     * 查询宠物信息，完成分页
     注意：分页查询： 第一页用0表示。 page

     */
    @Test
    public void findAllPetsWithPage() {

        //指定分页查询的参数
        /*
        Pageable:接口类型
        PageRequest：接口实现类对象：不能直接new 构造器被protect

         */
        Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");

        Page<Pet> all = petDao.findAll(pageable);

        for (Pet pet : all) {
            System.out.println(pet.getId() + " " + pet.getPname() + " " + pet.getColor());

        }


        /*
        获得元素个数
        int totalPages = pe.getTotalPages();
        获得页码数
		long totalElements = pe.getTotalElements();

         */

    }


    /***
     * 删除宠物信息
     */
    @Test
    public void deletePet() {
        petDao.deleteById(4);
    }


    @Test
    public void test3() {
        //根据pname查询
        List<Pet> list = petDao.findByPname("小黄狗");
        System.out.println(list);

        //根据color查询
        List<Pet> list1 = petDao.findByColor("白色");
        System.out.println(list1);

        //联合查询： 根据pname和color联合查询：
        List<Pet> list2 = petDao.findByPnameAndColor("小黄狗", "黄色");
        System.out.println(list2);

        //根据id区间查询，并且排序：
        List<Pet> list3 = petDao.findByIdBetweenOrderById(1, 2);
        System.out.println(list3);
    }


    @Test
    public void test4() {
        List<Pet> pets = petDao.loadPetslist();
        System.out.println(pets);
    }



    /*
    添加班级信息

     */

    @Test
    public void testAddClazz() {
        Clazz clazz = new Clazz();
        clazz.setCname("二班");
        clazzDao.save(clazz);


    }


    /**
     * 添加学生信息
     */

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setName("yhf");
        Clazz clazz = new Clazz();
        clazz.setCid(1);
        student.setClz(clazz);
        studentDao.save(student);
    }
    //更新班级信息：
    @Test
    public void testUpdateClz(){
        //查询出来修改的班级信息；
        Clazz clz = new Clazz();
        //cid在数据库表当中存在：2已经存在了
        clz.setCid(2);

        clz.setCname("元宵班");
         /*    1:根据id 查表当中进行查询操作：
               2： id在表当中存在，更新操作；   id 不存在， 执行save操作：
          */
        clazzDao.save(clz);}



    //更新学生信息：
     @Test
     public void testupdateStu(){
        //更新学生信息：
         Student student = new Student();
         student.setSid(3);
         //重新分配名称：
         student.setName("李四");
         //准备班级信息；
         Clazz clazz = new Clazz();
         clazz.setCid(2);
         //给student重新分配班级信息：
         student.setClz(clazz);
         studentDao.save(student);
    }


    //查询班级信息 关联查询了学生信息： onetomany 是懒加载查询。
    @Test
    public void testFindClz(){
        //根据所有的班级信息：
        List<Clazz> clazzes = clazzDao.findAll();
        for(Clazz it:clazzes){
            /*
            select * from t_clazz clazz0_
            查询班级信息，并没有查询班级当中的学生信息：
            懒加载： 查询班级信息并没有发送select语句查询班级当中的学生信息，
             这样的查询就是懒加载查询。
             只有当我们使用到学生信息的时候，才会发送select语句查询学生信息。
              立即加载： 查询班级信息立即发送select语句查询学生信息：
             总结： 一般情况查询一的一方，不需要立即加载。性能降低。  建议使用默认值。
            */
            System.out.println(it.getCid()+" "+it.getCname()+" "+it.getList());
            //查询班级当中的学生信息；
            // /*          查询班级当中的学生信息： no Session 异常信息：
            // 原因：
            // （1）查询班级信息，当班级信息查询完毕，dao层方法结束之后，session就会关闭。          （
            // 2）再次调用dao层的方法方法查询student信息，此时就会没有session对象。
            // 所以出现一个no session 异常。
            // 解决 no session问题：          （
            // 1）设置立即加载：  @OneToMany(mappedBy = "clz",fetch = FetchType.EAGER)
            // （2）延长session的生命周期：       */
            List<Student> students = it.getList();
            for (Student student : students) {
                System.out.println(student.getSid()+ " "+student.getName());
            }

        }

    }

    @Test
    public  void deleteClz(){
        //删除班级信息，班级当中没有学生， （主键表当中主键的值没有被引用。）
        // /*Clazz clazz = new Clazz();
        // clazz.setCid(1);   clazzDao.delete(clazz);*/
        clazzDao.deleteById(1);


        //删除班级信息，班级当中有学生信息（主键表当中的值被引用）
        // /*     直接删除班级表删除失败：
        // 原因： 主键表当中的数据已经被引用了。
        // 如何删除？     （
        // (1）级联删除： 删除班级信息的时候，级联操作学生信息：
        //              设置： cascade = CascadeType.ALL
        // 2） 先断开主外键连接: 再去删除主键表当中的数据：
        // 步骤一：断开主外键连接： update
        // 步骤二：执行一个删除操作：      遇到的问题：no session
        // 解决： 添加一个拦截器，延长session的生命周期：    */

    }

    /*
    添加员工信息以及员工参与的项目信息:
    结果： 只添加了员工信息，并没有添加员工关联的项目信息
    原因：  没有设置员工和第三张表的关联关系，员工放弃了外键的维护权利：
    */

    @Test
    public void testAddEmp(){
        //创建员工信息：
        Emp emp = new Emp();
        emp.setName("旺达");
        //项目信息：
        Project project = new Project();
        project.setPname("CRM");
        Project project1 = new Project();
        project1.setPname("ERP");
        //员工信息和项目信息建立关系；
        emp.getProjects().add(project);
        emp.getProjects().add(project1);
        //保存员工信息；
        empDao.save(emp);
    }


    @Test
    public void testAddPro(){
        //创建员工信息：
        Emp emp = new Emp();
        emp.setName("mrzhang");
        Emp emp1 = new Emp();
        emp1.setName("lisi");

        //项目信息：
        Project project = new Project();
        project.setPname("CRM");

        //项目信息和员工信息建立关系；
        project.getEmps().add(emp);
        project.getEmps().add(emp1);


        //将emp对象交给session管理：（连接数据库的绘画）
        empDao.save(emp);
        empDao.save(emp1);

        //保存员工信息；
        projectDao.save(project);

    }


    /** * 更新操作：
     *   更新项目信息：  更新项目信息，关联更新项目的人员信息。     成功
     *   更新员工信息：  更新员工信息，关联更新人员参与的项目信息， 不能完成。
     *
     *   */


    @Test
    public void testUpdatePro(){
        //更新项目信息：
        Project project = new Project();
        project.setId(2);
        project.setPname("cc");
        //查询到员工信息：
        Emp emp = empDao.getOne(2);
        Emp emp1 = empDao.getOne(3);
        //给当前的项目重新分配人员：
        project.getEmps().add(emp);
        project.getEmps().add(emp1);
        //更新操作:
        projectDao.save(project);
    }


    @Test
    public void testFindPro(){
        //查询所有：
        List<Project> projects = projectDao.findAll();
        for (Project project : projects) {
            System.out.println(project.getId()+" "+ project.getPname());
            // no session的问题：
            List<Emp> emps = project.getEmps();
            System.out.println(emps);    }
    }



    @Test
    public  void testAddBoyAndGirl(){
        Boy boy= new Boy();
        boy.setName("1黄晓明");
        //数据库存在的girl信息：
        Girl girl = new Girl();
        girl.setName("1杨颖");
        //创建关联关系：
        girlDao.save(girl);
        boy.setGirl(girl);
        //保存：
        boyDao.save(boy);
    }
}
