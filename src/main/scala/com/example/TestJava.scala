package com.example
import scala.collection.JavaConverters._

/**
 * Java测试类
 */
class TestJava {

  def returnMap(map: Map[String, Int]): Any = {
    //转成Java
    val jMap = map.asJava
    //转换后，可以使用Java的api操作
//    jMap.put("鲁智深", 41);
    return jMap
  }

}
