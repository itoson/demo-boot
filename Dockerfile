# 基础镜像使用 Maven 官方镜像，用于构建项目
FROM maven:3.6.3-jdk-11-slim AS build

# 将工作目录设置为 /app
WORKDIR /app

# 复制 pom.xml 和源代码到工作目录
COPY pom.xml .
COPY src ./src

# 执行 Maven 命令构建应用
RUN mvn clean package -DskipTests

# 使用 OpenJDK 镜像作为运行环境
FROM openjdk:8-jre-slim

# 设置工作目录
WORKDIR /app

# 从构建阶段复制打包好的 JAR 文件到工作目录
COPY --from=build /app/target/*.jar app.jar

# 暴露端口
EXPOSE 8080

# 设置容器启动命令
ENTRYPOINT ["java","-jar","/app/app.jar"]