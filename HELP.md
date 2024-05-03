# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

# Exemplo de POST para cadastrar Pizza, Ingrediente e Cardápio

```json
[
  {
    "id": 11,
    "ingredientes": [
      {
        "id": 22,
        "ingrediente": "Queijo",
        "quantidade": "200g"
      },
      {
        "id": 21,
        "ingrediente": "Pepperoni",
        "quantidade": "250g"
      }
    ],
    "cardapio": [
      {
        "id": 7,
        "valor": 40.0,
        "tamanho": "G"
      },
      {
        "id": 8,
        "valor": 30.0,
        "tamanho": "M"
      }
    ],
    "sabor": "Pepperoni"
  }
]
