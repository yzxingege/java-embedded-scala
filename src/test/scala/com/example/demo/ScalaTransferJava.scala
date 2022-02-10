package com.example.demo
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Scala调用Java代码示例
 */
@SpringBootTest
class ScalaTransferJava {

  @Test
  def ScalaTransferJavaTest(): Unit = {
    //直接拿到Java对象进行调用
    val javaObject = new MockDataInScala();
    print(javaObject.mockDataMap());
  }

}

