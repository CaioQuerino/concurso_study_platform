# DOCUMENTAÇÃO TÉCNICA DO PROJETO

## 1. INTRODUÇÃO

Este documento apresenta a documentação técnica do projeto **Plataforma de Estudos para Concursos com IA (EstudaIA)**, desenvolvido com arquitetura *full-stack* utilizando **Spring Boot** para o *backend* e **Angular/Ionic** para o *frontend*. O objetivo principal do projeto é fornecer uma plataforma robusta para auxiliar estudantes na preparação para concursos públicos, com foco em funcionalidades de gerenciamento de questões e simulados.

## 2. ARQUITETURA DO PROJETO

O projeto segue uma arquitetura de microsserviços monolítica, dividida em três componentes principais:

| Componente | Tecnologia | Função |
| :--- | :--- | :--- |
| **Backend** | Spring Boot (Java 21) | Responsável pela lógica de negócios, API RESTful e persistência de dados. |
| **Frontend** | Angular/Ionic (TypeScript) | Interface do usuário, responsável pela apresentação e interação com o usuário. |
| **Banco de Dados** | PostgreSQL | Armazenamento persistente dos dados da aplicação. |

## 3. CONFIGURAÇÃO E AMBIENTE DE DESENVOLVIMENTO

### 3.1. Pré-requisitos

Para executar o projeto, são necessários os seguintes softwares:

*   **Java Development Kit (JDK) 21**
*   **Apache Maven**
*   **Node.js e npm** (versão LTS recomendada)
*   **Angular CLI**
*   **PostgreSQL** (versão 14 ou superior)

### 3.2. Configuração do Banco de Dados

O banco de dados utilizado é o PostgreSQL. As seguintes configurações foram aplicadas:

1.  **Criação do Usuário e Banco de Dados:**
    ```sql
    CREATE USER estudaia WITH PASSWORD 'estudaia';
    CREATE DATABASE estudaia OWNER estudaia;
    ```
2.  **Inicialização do Esquema:** O esquema do banco de dados foi criado e populado com o script `database/init.sql`.

### 3.3. Configuração do Backend (Spring Boot)

O backend está configurado para se conectar ao PostgreSQL. O arquivo de configuração é `backend/estudaia/src/main/resources/application.properties`:

```properties
spring.application.name=estudaia
spring.datasource.url=jdbc:postgresql://localhost:5432/estudaia
spring.datasource.username=estudaia
spring.datasource.password=estudaia
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
server.port=8080
```

**Para iniciar o Backend:**

```bash
cd concurso_study_platform/backend/estudaia
mvn clean install -DskipTests
java -jar target/estudaia-0.0.1-SNAPSHOT.jar
```

### 3.4. Configuração do Frontend (Angular/Ionic)

O frontend se comunica com o backend através da URL configurada em `frontend/estudaia/src/environments/environment.ts` e `environment.prod.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

**Para iniciar o Frontend:**

```bash
cd concurso_study_platform/frontend/estudaia
npm install
ng build
# Para servir o projeto (após a compilação)
http-server www -p 4200
```

## 4. ESTRUTURA DE DADOS (MODELAGEM)

A modelagem de dados foi realizada para suportar as funcionalidades de gerenciamento de questões, concursos e usuários.

| Tabela | Descrição | Chave Primária | Chaves Estrangeiras |
| :--- | :--- | :--- | :--- |
| `usuarios` | Armazena informações dos usuários. | `id` | - |
| `concursos` | Armazena informações sobre concursos públicos. | `id` | - |
| `disciplinas` | Armazena as disciplinas de estudo. | `id` | - |
| `questoes` | Armazena o enunciado e metadados das questões. | `id` | `disciplina_id`, `concurso_id` |
| `alternativas` | Armazena as opções de resposta para cada questão. | `id` | `questao_id` |
| `simulados` | Armazena os simulados criados pelos usuários. | `id` | `usuario_id` |

## 5. FUNCIONALIDADES PRINCIPAIS

O projeto oferece as seguintes funcionalidades principais:

*   **Gerenciamento de Questões:** Criação, edição, listagem e visualização de questões.
*   **Gerenciamento de Concursos e Disciplinas:** Cadastro e consulta de concursos e disciplinas.
*   **Simulados:** Criação e realização de simulados (funcionalidade a ser expandida).

## 6. REFERÊNCIAS

Não há referências externas citadas neste documento.

---
**Autor:** Caio Querino
**Data:** 23 de Novembro de 2025
**Norma:** ABNT NBR 14724:2011 (Adaptada para Documentação Técnica)