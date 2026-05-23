# Projeto E-commerce UNIBRA

API REST desenvolvida em Java com Spring Boot para gerenciamento básico de um e-commerce.

---

# Objetivo

Atender à entrega de:

* Camada Service para cada entidade principal
* Controllers utilizando Service ao invés de Repository
* Uso de `@Transactional`
* Validação com `@Valid`
* `ResourceNotFoundException`
* `GlobalExceptionHandler`
* CRUD completo com GET, POST, PUT e DELETE

---

# Tecnologias Utilizadas

* Java 11
* Spring Boot 2.7.18
* Spring Web
* Spring Data JPA
* H2 Database
* Bean Validation
* Lombok
* Spring Security
* Swagger / SpringDoc OpenAPI
* Maven

---

# Entidades

* Categoria
* Produto
* Promocao
* Avaliacao
* Usuario

---

# Relacionamentos

* Produto possui `@ManyToOne` com Categoria
* Categoria possui `@OneToMany` com Produto
* Produto possui `@OneToMany` com Avaliacao
* Produto possui `@ManyToMany` com Promocao

---

# Como Executar

## Linux / Mac

```bash
./mvnw spring-boot:run
```

## Windows

```bash
mvnw.cmd spring-boot:run
```

---

# URLs

| Serviço      | URL                                         |
| ------------ | ------------------------------------------- |
| API          | http://localhost:8080                       |
| Swagger      | http://localhost:8080/swagger-ui/index.html |
| H2 Console   | http://localhost:8080/h2-console            |
| OpenAPI JSON | http://localhost:8080/v3/api-docs           |

---

# Estrutura do Projeto

```text
src/main/java/com/example/ecomerce/demo
│
├── controller
├── service
├── repository
├── entities
├── exception
└── config
```

---

# Endpoints Principais

## Produtos

| Método | Rota                  | Descrição            |
| ------ | --------------------- | -------------------- |
| GET    | /api/v1/produtos      | Lista produtos       |
| GET    | /api/v1/produtos/{id} | Busca produto por ID |
| POST   | /api/v1/produtos      | Cria produto         |
| PUT    | /api/v1/produtos/{id} | Atualiza produto     |
| DELETE | /api/v1/produtos/{id} | Remove produto       |

---

## Categorias

| Método | Rota                    | Descrição              |
| ------ | ----------------------- | ---------------------- |
| GET    | /api/v1/categorias      | Lista categorias       |
| GET    | /api/v1/categorias/{id} | Busca categoria por ID |
| POST   | /api/v1/categorias      | Cria categoria         |
| PUT    | /api/v1/categorias/{id} | Atualiza categoria     |
| DELETE | /api/v1/categorias/{id} | Remove categoria       |

---

## Avaliações

| Método | Rota                                   | Descrição                   |
| ------ | -------------------------------------- | --------------------------- |
| GET    | /api/v1/avaliacoes                     | Lista avaliações            |
| GET    | /api/v1/avaliacoes/{id}                | Busca avaliação por ID      |
| GET    | /api/v1/avaliacoes/produto/{produtoId} | Lista avaliações do produto |
| POST   | /api/v1/avaliacoes                     | Cria avaliação              |
| PUT    | /api/v1/avaliacoes/{id}                | Atualiza avaliação          |
| DELETE | /api/v1/avaliacoes/{id}                | Remove avaliação            |

---

## Promoções

| Método | Rota                       | Descrição                |
| ------ | -------------------------- | ------------------------ |
| GET    | /api/v1/promocoes          | Lista promoções          |
| GET    | /api/v1/promocoes/{id}     | Busca promoção por ID    |
| GET    | /api/v1/promocoes/vigentes | Lista promoções vigentes |
| POST   | /api/v1/promocoes          | Cria promoção            |
| PUT    | /api/v1/promocoes/{id}     | Atualiza promoção        |
| DELETE | /api/v1/promocoes/{id}     | Remove promoção          |

---

## Usuários

| Método | Rota                            | Descrição              |
| ------ | ------------------------------- | ---------------------- |
| GET    | /api/v1/usuarios                | Lista usuários         |
| GET    | /api/v1/usuarios/{id}           | Busca usuário por ID   |
| GET    | /api/v1/usuarios/busca?nome=gui | Busca usuário por nome |
| POST   | /api/v1/usuarios                | Cria usuário           |
| PUT    | /api/v1/usuarios/{id}           | Atualiza usuário       |
| DELETE | /api/v1/usuarios/{id}           | Remove usuário         |

---

# Exemplos de Teste no Swagger

## Criar Categoria

```json
{
  "nome": "Eletrônicos",
  "descricao": "Produtos eletrônicos e acessórios"
}
```

---

## Criar Produto

```json
{
  "nome": "Mouse Gamer",
  "preco": 89.90,
  "descricao": "Mouse com iluminação RGB",
  "estoque": 10,
  "categoria": {
    "id": 1
  }
}
```

---

## Criar Avaliação

```json
{
  "nota": 5,
  "comentario": "Produto muito bom",
  "autor": "Cliente Teste",
  "produto": {
    "id": 1
  }
}
```

---

# Validações e Tratamento de Erros

A API retorna erro `400 Bad Request` quando algum campo obrigatório está inválido.

## Exemplo de erro 400

```json
{
  "status": 400,
  "message": "nome: O nome do produto é obrigatório",
  "timestamp": "2026-05-23T10:00:00"
}
```

A API retorna erro `404 Not Found` quando o recurso não é encontrado.

## Exemplo de erro 404

```json
{
  "status": 404,
  "message": "Produto não encontrado com ID: 99",
  "timestamp": "2026-05-23T10:00:00"
}
```

---

# Funcionalidades Implementadas

* CRUD completo
* Documentação Swagger/OpenAPI
* Persistência com JPA/Hibernate
* Banco H2 em memória
* Relacionamentos entre entidades
* Camada Service
* Tratamento global de erros
* Validações com Bean Validation
* Arquitetura REST

---

# Autor

Projeto acadêmico desenvolvido para a disciplina de Desenvolvimento Web / API REST - UNIBRA.
