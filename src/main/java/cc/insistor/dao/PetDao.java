package cc.insistor.dao;

import cc.insistor.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * 实现完成t_pet表的crud操作
 * 继承JpaRepository<T, ID> 获取数据库表进行curd操作的方法
 * T:具体的实现类：Pet实现类
 * ID：指定主键字段类型：Integer
 */


public interface PetDao extends JpaRepository<Pet,Integer> {
    /**
     * 自定义查询：
     * （1）什么时候进行自定义查询、
     * JpaRepository 接口当中提供的方法不能正常满足实际业务需求，此时我们需要进行一个自定义查询:
     * PetDao接口当中自定义方法：
     *
     *  （2）方法定义注意事项：
     *  方法的返回值是根据实际的业务需求定义：  List Pet
     *  方法的名称必须满足规范： findByXxx  findBy固定开始 Xxx属性名称： findByPname
     *  参数列表： 根据实际的业务需求定义：
     *
     *  注意： 方法的名称必须满足相应的规范。
     *
     */


    List<Pet> findByPname(String pname);
    List<Pet> findByColor(String color);

    /*
      根据pname查询并且根据color查
      select * from t_pet pet0_ where pet0_.color=? and pet0_panme =?
    */
    List<Pet> findByPnameAndColor(String pname,String color);


    /**
     * 根据id 查询 ： 查询id在 1-5之间的pet对象：
     */
    List<Pet> findByIdBetweenOrderById(Integer minId, Integer maxId);


    /**
     * jpql 查询：
     *  语法： select attName ... from entityName;
     */

    /**
     *  需求：查询pet 列表：
     * sql: select * from t_pet
     * jpql: from cc.insistor.domain.Pet pet
         select 别名 from xxx;


     @Query(value ="from cc.insistor.domain.Pet")
     或者

    */

    @Query(value ="select pet from cc.insistor.domain.Pet pet")
    List<Pet> loadPetslist();





}
