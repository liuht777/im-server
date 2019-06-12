#!/bin/bash
mvn clean package -DskipTests
mv ./target/liuht-netty-server.jar $1
