# java-embedded-scala
 新建github仓库，目前已有的代码部分是在SpringBoot中整合并使用webFlux+r2dbc的常规使用;
 此次创建代码仓库是为Java整合嵌入Scala为目标,后续会涉及到的可能会有如何java和scala共享数据(或需要将scala注入到spring容器中),
 整合kafka, spark, inflush db等
 
# Scala环境设置的说明
   下载完该工程后,在idea的plugin中安装插件"scala", 下载重启后，右键新建File文件结尾以.scala结尾
   打开文件会提示没有设置sdk,非scala开发者本机是没有安装scala的sdk的(相当于jdk),需先安装scala的sdk
   通过点击提示设置sdk时点击create选项，在选项列表中点击其中和pom中一致的版本进行下载即可
   下载完毕后，删除掉刚才创建的.scala结尾的文件，右键新建Scala class文件即可
   使用哪一个版本比较合适，这个不能一概而论，得需要看实际的情况。
   首先，scala是一个基础组件，是后续spark、kafka、flink等软件的开发语言。
   其次，那么此时问题就简单起来了，得看公司中所选择的spark、kafka、flink软件版本是什么了，
   比如，kafka_2.11-1.1.1.tgz,这就意味这个这个kafka1.1.1的版本，对应的编译是所采用的的scala版本为2.11，
   所以我们此时在选择版本的时候最好选择同一个主要版本的scala了，spark和flink同样。
   最后，那可能会问，kafka、spark、flink版本不统一怎么办？这着实是一个比较难办的问题，
   因为在软件开发过程中由于版本不统一造成的问题千奇百怪，可能会消耗掉大量的时间去解决版本冲突的问题，
   所以最好建议在做开发之前一定要做好版本调研，避免在开发中处理这些没有必要的问题

# Protocol Buffers 
  下版本将会更新数据传输协议Protocol Buffers(protobuf) 官网：https://developers.google.cn/protocol-buffers
  <br/> 为提高可读性可安装idea插件: Protocol Buffer Editor
  <br/> 使用场景：
  思考一个问题，如服务器部署在美国，数据库部署在亚洲，那么使用http请求响应是秒级，甚至二三十秒甚至timeout，这种情况如何解决?
  如果连基本的请求响应都保证不了，后续的并发问题就不用考虑了,这时候就可以考虑脱库方案了。
  调用流程如下：
  1.可通过定时任务系统，定时拉取我们的数据，通过pb文件进行传输
  2.脱库服务接收pb文件，并缓存到内存中，接收到的请求都使用缓存的数据，如发生宕机等可重启后继续使用pb文件重新缓存
  3.这种情况必然存在数据不一致的情况，不能达到秒级更新。场景为允许有延迟。
  首先需先安装好protobuf编译器:
    在工程的顶级file目录里已上传请自取， 解压打开项目后执行 ./configure 
    安装完成后执行 make 再执行 make install
    最后执行命令 protoc --version 验证是否安装成功
    
  