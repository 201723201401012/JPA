package cc.insistor;

import cc.insistor.dao.PetDao;
import cc.insistor.domain.Pet;
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
    @Autowired
    private PetDao petDao;//springboot在启动的时候，底层使用了动态代理的方式获得了一个接口实现类，完成了注入

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


}
