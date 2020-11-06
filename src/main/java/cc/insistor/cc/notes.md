# 1.jpa的概述
JPA是一个持久层的ORM 框架。  对jdbc的封装。 使用jpa可以实现操作实体对象就能够实现对数据库表的CRUD 。 


Orm关系映射： 
关系型数据库：          java      
数据表                  对应java当中的实体类
记录数：                对应java当中的对象
Field ：                 对应java类当中属性：

Java程序员面向对象的角度操作对象 ，由于我们的表以及表当中的属性已经和关系型数据库当中表和字段进行了一个一一映射。我们操作了对象就能够操作表当中的记录。 


总结持久层技术：  
Jdbc 
queryRunner 
Mybatis  springboot+ mybatis
Hibernate 
Jpa 
