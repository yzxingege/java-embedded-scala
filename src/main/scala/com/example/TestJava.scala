package com.example
import org.springframework.beans.factory.annotation.Autowired
import scala.collection.JavaConverters._

/**
 * Java测试类
 */
class TestJava {

  //错误的使用，相当于直接new使用，要解决ioc注入的问题
  @Autowired
  private val au: AutoTest = new AutoTest

  def returnMap(map: Map[String, Int]): Any = {
    au.aaa()
    print(au.toString)
    //转成Java
    val jMap = map.asJava
    //转换后，可以使用Java的api操作
//    jMap.put("鲁智深", 41);
    return jMap
  }

}
