# 指定基础镜像 这里springboot项目运行只需要java jdk环境即可
FROM java:8
# 维护者信息
MAINTAINER rafeily
WORKDIR app
# 重命名
ADD *.jar /app/app.jar
# 对外暴漏的端口号
EXPOSE 8110
CMD java -jar /app/app.jar
