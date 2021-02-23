FROM openjdk:11
ADD build/libs/shopping-product-0.0.1-SNAPSHOT.jar products.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","products.jar"]