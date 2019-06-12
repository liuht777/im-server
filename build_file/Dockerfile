FROM 172.31.205.29/library/jdk:8u171-jdk
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ADD liuht-netty-server.jar /app/app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app/app.jar"]
