#!/bin/bash
#执行protoc编译器命令, 本人编译器版本为3.17
#生成java语言的类到这个指定的路径(../就是当前路径的上一级),  *.proto是指是当前目录下的所有*.proto文件去进行生成
protoc -I=./\
 --java_out=../\
 *.proto

echo "200 OK finished"
