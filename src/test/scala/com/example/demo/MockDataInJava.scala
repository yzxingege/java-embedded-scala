package com.example.demo
import collection.JavaConverters._

class MockDataInJava {

  def mockDataMap(): Any = {
    //使用Scala方式创建Map容器
    val map = scala.collection.mutable.Map[String, Int]("西门庆" -> 1).+("吕布" -> 2)
    //转成Java
    val jMap = map.asJava
    //转换后，使用Java的api操作
    jMap.put("鲁智深", 41);
    return jMap
  }

}
