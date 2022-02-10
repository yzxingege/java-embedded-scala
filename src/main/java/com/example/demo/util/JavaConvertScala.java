package com.example.demo.util;

import scala.Tuple2;
import scala.collection.Seq;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author hanxiang
 * @email hanxiang@huhumart.com
 * @Date 2022/2/10 19:39
 * @Description Java转Scala相关
 **/
public class JavaConvertScala {

    /**
     * Java的Map转scala.collection.immutable.Map
     * @param jmap
     * @param <K>
     * @param <V>
     * @return
     */
    public static  <K, V> scala.collection.immutable.Map<K, V> toScalaImmutableMap(java.util.Map<K, V> jmap) {
        List<Tuple2<K, V>> tuples = jmap.entrySet()
                .stream()
                .map(e -> Tuple2.apply(e.getKey(), e.getValue()))
                .collect(Collectors.toList());

        Seq<Tuple2<K, V>> scalaSeq = scala.collection.JavaConverters.asScalaBufferConverter(tuples).asScala().toSeq();

        return (scala.collection.immutable.Map<K, V>) scala.collection.immutable.Map$.MODULE$.apply(scalaSeq);
    }

}
