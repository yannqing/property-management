# 使用 OpenJDK 17 slim 版本作为基础镜像
FROM openjdk:17-slim

# 设置维护者标签
LABEL maintainer="yannqing <yannqing.com>"
LABEL version="1.0"
LABEL description="Property Management"

# 设置工作目录
WORKDIR /yannqing/property-management/java

# 创建一个挂载点
VOLUME /yannqing/property-management/logs

# 复制应用程序
COPY ./target/property-0.0.1-SNAPSHOT.jar /tmp/app.jar

# 暴露端口
EXPOSE 8091

# 启动命令
CMD ["java", "-jar", "/tmp/app.jar", "--spring.profiles.active=dev"]
