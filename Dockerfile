# Sử dụng image OpenJDK 17 với nền tảng slim để giảm kích thước image
FROM openjdk:17-jdk-slim


# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR từ thư mục target của máy chủ vào thư mục làm việc trong container
COPY target/APIChatRealtime-0.0.1-SNAPSHOT.jar APIChatRealtime.jar

# Chạy ứng dụng Java khi container khởi động
ENTRYPOINT ["java", "-jar", "APIChatRealtime.jar"]
