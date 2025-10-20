# Products API

## Descrição do Projeto
Este projeto é uma API REST construída com **Spring Boot** que permite gerenciar produtos em um banco de dados relacional (**PostgreSQL**). A API fornece funcionalidades de **CRUD (Create, Read, Update, Delete)** para produtos, incluindo informações como nome, descrição, preço e categoria.

A API foi desenvolvida como parte de um processo seletivo e inclui **testes unitários e de integração** utilizando **JUnit** e **Mockito**.

---

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- JUnit 5
- Mockito
- Maven

---

## Funcionalidades
- Criar um produto (`POST /api/produtos`)
- Listar todos os produtos (`GET /api/produtos`)
- Consultar produto por ID (`GET /api/produtos/{id}`)
- Atualizar um produto (`PUT /api/produtos/{id}`)
- Deletar um produto (`DELETE /api/produtos/{id}`)

---

## Passo a Passo para Rodar Localmente
- mvn clean install
- mvn spring-boot:run

### Pré-requisitos
- Java 21 instalado
- PostgreSQL instalado e rodando
- Maven instalado

### Configuração do Banco de Dados
1. Crie um banco no PostgreSQL, por exemplo `products_db`.
2. Configure o arquivo `application.properties` com as credenciais corretas:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/products_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
### Testes
Testes unitários foram implementados para Controller e Service usando JUnit e Mockito.

Para rodar os testes:mvn test


### Swagger UI
A API inclui documentação interativa utilizando Swagger. Após iniciar a aplicação, você pode acessar a documentação em:
```
http://localhost:8080/swagger-ui.html ou http://localhost:8080/swagger-ui/index.html
```
### Licença
Este projeto está licenciado sob a Licença MIT.