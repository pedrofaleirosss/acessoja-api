# ♿ AcessoJá — REST API

### Acessibilidade começa com informação.

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.2-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle-Database-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![Maven](https://img.shields.io/badge/Apache_Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

**AcessoJá** é uma plataforma colaborativa voltada à promoção da acessibilidade urbana por meio do compartilhamento de informações sobre estabelecimentos acessíveis, espaços públicos e barreiras de mobilidade.

Este repositório contém o **backend da aplicação**, desenvolvido com Java 21, Spring Boot e Oracle Database, utilizando uma API RESTful estruturada em camadas.

O projeto foi desenvolvido para a **Global Solution 2026.2 da FIAP**, no curso de Engenharia de Software, dentro da proposta CitizenTech.

---

## 📑 Sumário

1. [Sobre o projeto](#-sobre-o-projeto)
2. [Objetivos](#-objetivos)
3. [Funcionalidades](#-funcionalidades)
4. [Tecnologias utilizadas](#-tecnologias-utilizadas)
5. [Arquitetura da aplicação](#-arquitetura-da-aplicação)
6. [Estrutura do projeto](#-estrutura-do-projeto)
7. [Modelo de dados](#-modelo-de-dados)
8. [Endpoints da API](#-endpoints-da-api)
9. [Como executar o projeto](#-como-executar-o-projeto)
10. [Exemplos de requisições](#-exemplos-de-requisições)
11. [Regras de negócio](#-regras-de-negócio)
12. [Tratamento de erros](#-tratamento-de-erros)
13. [Testes](#-testes)
14. [Limitações da versão atual](#-limitações-da-versão-atual)
15. [Melhorias futuras](#-melhorias-futuras)
16. [Contexto acadêmico](#-contexto-acadêmico)

---

## 🌍 Sobre o projeto

A acessibilidade urbana é fundamental para garantir a autonomia e a participação de todas as pessoas na sociedade. Entretanto, a ausência de informações detalhadas sobre as condições de acesso a estabelecimentos e espaços públicos ainda representa um desafio para pessoas com deficiência e mobilidade reduzida.

O problema não está apenas na existência de barreiras físicas, mas também na dificuldade de encontrar informações confiáveis e atualizadas sobre elas.

O **AcessoJá** foi idealizado para enfrentar esse desafio por meio de uma plataforma colaborativa, na qual cidadãos podem registrar e consultar informações sobre a acessibilidade de diferentes locais.

A proposta permite reunir informações sobre:

- Estabelecimentos comerciais e serviços acessíveis;
- Praças, parques e áreas públicas adaptadas;
- Barreiras urbanas que dificultam a mobilidade;
- Recursos de acessibilidade disponíveis nos estabelecimentos;
- Avaliações e experiências compartilhadas pelos cidadãos;
- Confirmações e contestações de informações;
- Relatos sobre a resolução de obstáculos urbanos.

A primeira versão do projeto foi planejada como um MVP, com estrutura preparada para armazenar informações de diferentes cidades e bairros.

### Público-alvo

A plataforma foi pensada especialmente para pessoas com deficiência, pessoas idosas, cidadãos com mobilidade reduzida, familiares, cuidadores e demais pessoas interessadas em contribuir para uma cidade mais acessível.

### Proposta de valor

**Transformar informações compartilhadas pela comunidade em uma ferramenta de inclusão, autonomia e conscientização sobre acessibilidade urbana.**

---

## 🎯 Objetivos

### Objetivo geral

Desenvolver uma API RESTful para gerenciar informações sobre acessibilidade urbana, permitindo o cadastro, a consulta, a avaliação e a atualização colaborativa de registros por meio de uma estrutura organizada e integrada ao banco de dados Oracle.

### Objetivos específicos

- Gerenciar usuários, cidades e bairros;
- Permitir o cadastro de estabelecimentos e espaços acessíveis;
- Registrar barreiras urbanas identificadas pela comunidade;
- Associar recursos de acessibilidade aos estabelecimentos;
- Possibilitar avaliações com notas e comentários;
- Permitir confirmações e contestações de registros;
- Gerenciar relatos sobre a resolução de barreiras;
- Aplicar regras de negócio e validações no backend;
- Disponibilizar endpoints REST para integração com interfaces web e mobile;
- Aplicar conceitos de orientação a objetos e arquitetura em camadas.

---

## ⚙️ Funcionalidades

A versão atual do backend contempla operações de cadastro, consulta, atualização e exclusão para as 12 entidades do modelo relacional.

| Módulo | Funcionalidades |
|---|---|
| Usuários | Cadastro, consulta, atualização e exclusão |
| Cidades | Gerenciamento das cidades atendidas |
| Bairros | Gerenciamento e consulta de bairros por cidade |
| Registros | Cadastro e gerenciamento de informações gerais de acessibilidade |
| Estabelecimentos | Gerenciamento de estabelecimentos acessíveis |
| Áreas acessíveis | Gerenciamento de praças, parques e outros espaços |
| Barreiras | Cadastro e gerenciamento de obstáculos urbanos |
| Recursos de acessibilidade | Cadastro de rampas, elevadores e outros recursos |
| Estabelecimento-Recurso | Associação de recursos aos estabelecimentos |
| Avaliações | Notas e comentários sobre estabelecimentos |
| Validações | Confirmação e contestação de informações |
| Relatos de resolução | Cadastro e análise de relatos sobre barreiras |

### Funcionalidades adicionais

Além dos CRUDs tradicionais, a API possui consultas específicas por cidade, tipo de registro, estabelecimento e barreira.

A aplicação também conta com validações de dados, tratamento centralizado de exceções e operações transacionais para cadastros que envolvem mais de uma tabela.

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Utilização |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot 4.1.2 | Framework da API REST |
| Spring Web MVC | Controllers e endpoints HTTP |
| Oracle Database | Armazenamento dos dados |
| Oracle JDBC Driver | Conexão com Oracle |
| JDBC | Operações SQL e persistência |
| Maven | Gerenciamento de dependências e build |
| IntelliJ IDEA | Ambiente de desenvolvimento |
| Postman | Testes manuais dos endpoints |
| Oracle SQL Developer | Consultas e administração do banco |
| Oracle SQL Developer Data Modeler | Modelagem do banco relacional |
| Git e GitHub | Versionamento e hospedagem do código |

A persistência é realizada com JDBC, utilizando `Connection`, `PreparedStatement` e `ResultSet`.

O projeto não utiliza Spring Data JPA nem Hibernate, pois segue a arquitetura de acesso a dados trabalhada na disciplina.

---

## 🏗️ Arquitetura da aplicação

A aplicação foi organizada em camadas para separar as responsabilidades e facilitar a manutenção do código.

O fluxo principal de uma requisição é:

    Cliente HTTP / Postman
             |
             v
         Controller
             |
             v
      BO (Business Object)
             |
             v
      DAO (Data Access Object)
             |
             v
       ConexaoFactory
             |
             v
       Oracle Database

### Entities

Contém as classes que representam os elementos do domínio do AcessoJá, com seus atributos, construtores, getters e setters.

### Controller

Responsável por receber requisições HTTP, mapear os endpoints REST e encaminhar as operações para a camada de regras de negócio.

Utiliza anotações do Spring, como `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping` e `@DeleteMapping`.

### BO — Business Object

Responsável por aplicar regras e validações antes de executar operações no banco.

Exemplos: validar a nota de uma avaliação, impedir a validação do próprio registro e verificar a situação de uma barreira antes de cadastrar um relato de resolução.

### DAO — Data Access Object

Responsável pela comunicação direta com o banco Oracle, executando comandos SQL por meio de JDBC.

Os DAOs realizam operações de INSERT, SELECT, UPDATE e DELETE.

### ConexaoFactory

Centraliza a criação de conexões JDBC com o Oracle, utilizando credenciais definidas por variáveis de ambiente.

### Tratamento de exceções

Utiliza uma classe com `@RestControllerAdvice` para tratar erros de validação, registros inexistentes e falhas relacionadas ao banco de dados.

### Programação orientada a objetos

O projeto aplica encapsulamento e herança, incluindo a especialização da classe `Registro` em:

- `Estabelecimento`
- `AreaAcessivel`
- `Barreira`

As classes especializadas reutilizam os atributos gerais de `Registro` e possuem atributos específicos de cada categoria.

---

## 📁 Estrutura do projeto

O código principal está organizado no pacote `br.com.acessoja`.

    acessoja/
    |
    |-- src/
    |   |-- main/
    |   |   |-- java/br/com/acessoja/
    |   |   |   |-- bo/
    |   |   |   |-- conexoes/
    |   |   |   |-- controller/
    |   |   |   |-- dao/
    |   |   |   |-- entities/
    |   |   |   |-- excecoes/
    |   |   |   |-- main/
    |   |   |   |-- util/
    |   |   |   `-- AcessojaApplication.java
    |   |   `-- resources/
    |   |       `-- application.properties
    |   `-- test/
    |
    |-- .mvn/
    |-- .gitignore
    |-- Dockerfile
    |-- mvnw
    |-- mvnw.cmd
    `-- pom.xml

### Responsabilidade dos pacotes

| Pacote | Responsabilidade |
|---|---|
| `entities` | Entidades e objetos de entrada/saída de dados |
| `dao` | Persistência e consultas ao banco Oracle |
| `bo` | Validações e regras de negócio |
| `controller` | Endpoints REST |
| `conexoes` | Gerenciamento das conexões JDBC |
| `excecoes` | Tratamento centralizado de erros |
| `util` | Funções auxiliares, incluindo geração de hash de senhas |
| `main` | Testes locais executáveis diretamente pelo IntelliJ |

---

## 🗄️ Modelo de dados

O banco de dados foi modelado utilizando Oracle SQL Developer Data Modeler.

O modelo relacional é composto por 12 tabelas:

| Tabela | Descrição |
|---|---|
| `USUARIO` | Armazena os dados dos usuários |
| `CIDADE` | Armazena as cidades cadastradas |
| `BAIRRO` | Relaciona os bairros às cidades |
| `REGISTRO` | Concentra as informações gerais de acessibilidade |
| `ESTABELECIMENTO` | Armazena os dados específicos dos estabelecimentos |
| `AREA_ACESSIVEL` | Armazena informações de espaços acessíveis |
| `BARREIRA` | Armazena os obstáculos urbanos cadastrados |
| `RECURSO_ACESSIBILIDADE` | Cataloga os recursos de acessibilidade |
| `ESTABELECIMENTO_RECURSO` | Relaciona estabelecimentos e recursos |
| `AVALIACAO` | Armazena avaliações dos estabelecimentos |
| `VALIDACAO` | Registra confirmações e contestações |
| `RELATO_RESOLUCAO` | Registra relatos de resolução de barreiras |

### Integridade dos dados

O modelo utiliza chaves primárias, estrangeiras e restrições como `NOT NULL`, `UNIQUE` e `CHECK`.

Entre os relacionamentos principais estão:

- Uma cidade possui vários bairros;
- Um bairro pode possuir vários registros;
- Um usuário pode ser autor de diferentes registros;
- Um registro pode representar um estabelecimento, uma área acessível ou uma barreira;
- Um estabelecimento pode possuir diversos recursos de acessibilidade;
- Um estabelecimento pode receber avaliações;
- Um registro pode receber validações comunitárias;
- Uma barreira pode possuir relatos de resolução.

A estrutura foi desenvolvida para preservar a integridade referencial e permitir sua utilização pela API Java.

---

## 🔗 Endpoints da API

**URL base local:** `http://localhost:8080`

Os endpoints utilizam JSON para receber e retornar informações.

### Endpoints principais

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/cidades` | Listar cidades |
| GET | `/cidades/{id}` | Buscar cidade por ID |
| POST | `/cidades` | Cadastrar cidade |
| PUT | `/cidades/{id}` | Atualizar cidade |
| DELETE | `/cidades/{id}` | Excluir cidade |
| GET | `/bairros` | Listar bairros |
| GET | `/bairros/{id}` | Buscar bairro |
| GET | `/bairros/cidade/{idCidade}` | Listar bairros de uma cidade |
| POST | `/bairros` | Cadastrar bairro |
| PUT | `/bairros/{id}` | Atualizar bairro |
| DELETE | `/bairros/{id}` | Excluir bairro |
| GET | `/usuarios` | Listar usuários |
| GET | `/usuarios/{id}` | Buscar usuário |
| POST | `/usuarios` | Cadastrar usuário |
| PUT | `/usuarios/{id}` | Atualizar usuário |
| DELETE | `/usuarios/{id}` | Excluir usuário |
| GET | `/registros` | Listar registros |
| GET | `/registros/{id}` | Buscar registro |
| GET | `/registros/tipo/{tipo}` | Filtrar por tipo |
| POST | `/registros` | Cadastrar registro geral |
| PUT | `/registros/{id}` | Atualizar registro |
| DELETE | `/registros/{id}` | Excluir registro |
| GET | `/estabelecimentos` | Listar estabelecimentos |
| GET | `/estabelecimentos/{id}` | Buscar estabelecimento |
| POST | `/estabelecimentos` | Cadastrar estabelecimento |
| PUT | `/estabelecimentos/{id}` | Atualizar estabelecimento |
| DELETE | `/estabelecimentos/{id}` | Excluir estabelecimento |
| GET | `/areas-acessiveis` | Listar áreas acessíveis |
| GET | `/areas-acessiveis/{id}` | Buscar área acessível |
| POST | `/areas-acessiveis` | Cadastrar área acessível |
| PUT | `/areas-acessiveis/{id}` | Atualizar área acessível |
| DELETE | `/areas-acessiveis/{id}` | Excluir área acessível |
| GET | `/barreiras` | Listar barreiras |
| GET | `/barreiras/{id}` | Buscar barreira |
| POST | `/barreiras` | Cadastrar barreira |
| PUT | `/barreiras/{id}` | Atualizar barreira |
| DELETE | `/barreiras/{id}` | Excluir barreira |
| GET | `/recursos-acessibilidade` | Listar recursos |
| GET | `/recursos-acessibilidade/{id}` | Buscar recurso |
| POST | `/recursos-acessibilidade` | Cadastrar recurso |
| PUT | `/recursos-acessibilidade/{id}` | Atualizar recurso |
| DELETE | `/recursos-acessibilidade/{id}` | Excluir recurso |
| GET | `/estabelecimentos-recursos` | Listar associações |
| GET | `/estabelecimentos-recursos/{idEstabelecimento}/{idRecurso}` | Buscar associação |
| GET | `/estabelecimentos-recursos/estabelecimento/{idEstabelecimento}` | Listar recursos associados |
| POST | `/estabelecimentos-recursos` | Criar associação |
| PUT | `/estabelecimentos-recursos/{idEstabelecimento}/{idRecurso}` | Substituir recurso associado |
| DELETE | `/estabelecimentos-recursos/{idEstabelecimento}/{idRecurso}` | Remover associação |
| GET | `/avaliacoes` | Listar avaliações |
| GET | `/avaliacoes/{id}` | Buscar avaliação |
| GET | `/avaliacoes/estabelecimento/{idEstabelecimento}` | Listar avaliações de um estabelecimento |
| POST | `/avaliacoes` | Cadastrar avaliação |
| PUT | `/avaliacoes/{id}` | Atualizar avaliação |
| DELETE | `/avaliacoes/{id}` | Excluir avaliação |
| GET | `/validacoes` | Listar validações |
| GET | `/validacoes/{id}` | Buscar validação |
| GET | `/validacoes/registro/{idRegistro}` | Listar validações de um registro |
| POST | `/validacoes` | Cadastrar validação |
| PUT | `/validacoes/{id}` | Atualizar validação |
| DELETE | `/validacoes/{id}` | Excluir validação |
| GET | `/relatos-resolucao` | Listar relatos |
| GET | `/relatos-resolucao/{id}` | Buscar relato |
| GET | `/relatos-resolucao/barreira/{idBarreira}` | Listar relatos de uma barreira |
| POST | `/relatos-resolucao` | Cadastrar relato |
| PUT | `/relatos-resolucao/{id}` | Atualizar descrição |
| PUT | `/relatos-resolucao/{id}/analisar` | Aprovar ou rejeitar relato |
| DELETE | `/relatos-resolucao/{id}` | Excluir relato pendente |

### Métodos HTTP utilizados

| Método | Finalidade | Resposta típica |
|---|---|---|
| GET | Consultar informações | 200 OK |
| POST | Cadastrar informações | 201 Created |
| PUT | Atualizar informações | 200 OK |
| DELETE | Excluir informações | 204 No Content |

A API também utiliza respostas de erro para indicar problemas de validação, registros inexistentes ou violações de integridade do banco.

---

## 🚀 Como executar o projeto

### Pré-requisitos

Antes de executar, tenha instalado:

- JDK 21;
- Git;
- IntelliJ IDEA ou outra IDE compatível com Java;
- Uma conexão válida com Oracle Database;
- As tabelas do modelo AcessoJá criadas no schema Oracle;
- Postman ou outro cliente HTTP, para testes.

O projeto inclui Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

### 1. Clonar o repositório

    git clone https://github.com/pedrofaleirosss/acessoja-api.git

    cd acessoja-api

### 2. Configurar o banco de dados

A conexão JDBC é realizada pela classe `ConexaoFactory`, no pacote `br.com.acessoja.conexoes`.

O projeto utiliza as seguintes variáveis de ambiente:

| Variável | Descrição |
|---|---|
| `DB_USUARIO` | Usuário de acesso ao Oracle |
| `DB_SENHA` | Senha do usuário Oracle |

Exemplo de configuração no Windows, utilizando PowerShell:

    $env:DB_USUARIO="SEU_USUARIO"
    $env:DB_SENHA="SUA_SENHA"

Essas variáveis ficam disponíveis na sessão atual do terminal.

Também é possível configurá-las no IntelliJ em:

**Run → Edit Configurations → Environment variables**

A classe de conexão utiliza o endereço Oracle definido no projeto:

    jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl

Caso seja utilizado outro servidor Oracle, o endereço JDBC precisará ser ajustado na `ConexaoFactory`.

**Importante:** nunca publique credenciais reais em arquivos versionados ou no histórico do Git.

### 3. Preparar o banco

Antes de iniciar a API, execute os scripts SQL de criação das tabelas e constraints do AcessoJá no schema Oracle utilizado pela aplicação.

Opcionalmente, execute os INSERTs de exemplo para disponibilizar dados iniciais para os testes.

A API utiliza a estrutura já existente no Oracle e não cria automaticamente as tabelas.

### 4. Executar a aplicação

Pelo terminal, no Windows:

    .\mvnw.cmd spring-boot:run

Em Linux ou macOS:

    ./mvnw spring-boot:run

Também é possível executar a classe principal `AcessojaApplication.java` diretamente pelo IntelliJ.

Com a aplicação iniciada, a API ficará disponível, por padrão, em:

    http://localhost:8080

Para verificar o funcionamento de um endpoint, acesse:

    http://localhost:8080/cidades

A consulta deverá retornar os dados das cidades existentes no banco Oracle.

### 5. Gerar o build

No Windows:

    .\mvnw.cmd clean package

Em Linux ou macOS:

    ./mvnw clean package

O JAR executável será gerado na pasta `target`.

A aplicação também pode ser iniciada utilizando:

    java -jar target/acessoja-0.0.1-SNAPSHOT.jar

O funcionamento depende de uma conexão Oracle válida e das variáveis de ambiente configuradas.

---

## 📬 Exemplos de requisições

As requisições abaixo podem ser executadas no Postman utilizando **Body → raw → JSON**.

Nos exemplos, os IDs são informados manualmente, conforme a implementação atual. Utilize identificadores disponíveis e referências a usuários e bairros existentes no banco.

### 1. Listar estabelecimentos

**GET** `/estabelecimentos`

Retorna os estabelecimentos cadastrados, combinando os dados das tabelas `REGISTRO` e `ESTABELECIMENTO`.

### 2. Cadastrar uma barreira urbana

**POST** `/barreiras`

    {
      "idRegistro": 1001,
      "idUsuarioAutor": 2,
      "idBairro": 1,
      "titulo": "Calçada com buracos",
      "descricao": "Buracos dificultam a passagem de cadeirantes.",
      "endereco": "Rua das Flores, 150",
      "tipoBarreira": "Piso danificado"
    }

O backend define automaticamente a data de cadastro, a moderação inicial como `PENDENTE` e a situação da barreira como `ATIVA`.

Uma resposta HTTP 201 indica que o cadastro foi realizado.

### 3. Consultar uma barreira

**GET** `/barreiras/1001`

Retorna os dados gerais do registro e as informações específicas da barreira.

### 4. Cadastrar um usuário

**POST** `/usuarios`

    {
      "idUsuario": 1001,
      "nome": "Maria Oliveira",
      "email": "maria.exemplo@email.com",
      "senha": "SenhaExemplo2026!"
    }

O backend define o perfil inicial como `CIDADAO` e a situação do usuário como ativo.

A senha recebida é transformada em hash antes de ser armazenada no banco, e o hash não é incluído nas respostas públicas do CRUD de usuários.

### 5. Avaliar um estabelecimento

**POST** `/avaliacoes`

    {
      "idAvaliacao": 1001,
      "idUsuario": 4,
      "idEstabelecimento": 1,
      "nota": 5,
      "comentario": "O local possui boa acessibilidade."
    }

A nota deve estar entre 1 e 5.

O banco impede que o mesmo usuário tenha duas avaliações cadastradas para o mesmo estabelecimento.

### 6. Validar um registro

**POST** `/validacoes`

    {
      "idValidacao": 1001,
      "idUsuario": 2,
      "idRegistro": 2,
      "tipoValidacao": "CONFIRMA",
      "comentario": "As informações do local estão corretas."
    }

Um usuário pode confirmar ou contestar um registro, respeitando as regras de negócio.

O registro precisa estar aprovado, e o usuário não pode ser o próprio autor da informação.

### 7. Informar a resolução de uma barreira

**POST** `/relatos-resolucao`

    {
      "idRelato": 1001,
      "idBarreira": 11,
      "idUsuario": 3,
      "descricao": "A calçada foi consertada."
    }

Para o cadastro funcionar, a barreira precisa existir, estar aprovada na moderação e possuir situação `ATIVA`.

Quando o relato é cadastrado, a barreira passa para `RESOLUCAO_INFORMADA`.

### 8. Analisar um relato

**PUT** `/relatos-resolucao/1001/analisar`

    {
      "idAdminRevisor": 1,
      "statusAnalise": "APROVADO"
    }

Se a análise for aprovada, o status da barreira passa para `RESOLVIDA`.

Caso seja rejeitada, a barreira retorna para `ATIVA`.

**Atenção:** a verificação atual utiliza o ID do administrador informado na requisição. Ainda não existe autenticação real para comprovar quem está executando a ação.

---

## 📋 Regras de negócio

A aplicação contém regras de negócio na camada BO, que são executadas antes das operações de persistência.

### Usuários

- Nome e e-mail são obrigatórios;
- O e-mail deve possuir formato válido;
- A senha deve possuir pelo menos oito caracteres;
- A senha é armazenada como hash gerado com PBKDF2;
- Novos usuários recebem o perfil `CIDADAO`;
- Os dados de hash não são retornados pelos endpoints de consulta de usuários.

### Registros

- Os tipos permitidos são `ESTABELECIMENTO`, `AREA` e `BARREIRA`;
- Título, descrição, endereço e bairro são obrigatórios;
- Novos registros são cadastrados inicialmente como `PENDENTE`;
- A data de cadastro é definida automaticamente.

### Estabelecimentos, áreas e barreiras

- Os cadastros especializados utilizam os atributos comuns da classe `Registro`;
- As operações de inserção, atualização e exclusão que envolvem duas tabelas utilizam transações JDBC;
- As barreiras são cadastradas inicialmente com situação `ATIVA`.

### Avaliações

- As notas devem estar entre 1 e 5;
- Os comentários respeitam o limite definido no banco;
- A data é gerada automaticamente;
- Um usuário pode possuir somente uma avaliação por estabelecimento.

### Validações

- Os tipos aceitos são `CONFIRMA` e `CONTESTA`;
- Um usuário não pode validar o próprio registro;
- Somente registros aprovados podem receber novas validações;
- Contestações exigem justificativa;
- A combinação entre usuário e registro é única no banco.

### Relatos de resolução

- Somente barreiras ativas e aprovadas podem receber novos relatos;
- Os relatos são cadastrados inicialmente como `PENDENTE`;
- Um relato pode ser aprovado ou rejeitado;
- A análise modifica a situação da barreira correspondente;
- Relatos já analisados não podem ser excluídos pelo endpoint comum;
- Operações que modificam relato e barreira utilizam transações com commit e rollback.

---

## ⚠️ Tratamento de erros

A aplicação possui um tratamento centralizado de exceções com `@RestControllerAdvice`.

| Código HTTP | Situação |
|---|---|
| 200 | Consulta ou atualização bem-sucedida |
| 201 | Recurso cadastrado com sucesso |
| 204 | Exclusão bem-sucedida |
| 400 | Requisição ou dados inválidos |
| 404 | Registro não encontrado |
| 409 | Violação de restrição de integridade |
| 500 | Erro interno ou falha de conexão |

Exemplo de erro de validação:

    {
      "erro": "A nota deve estar entre 1 e 5"
    }

O tratamento centralizado facilita a identificação de problemas e evita a repetição de código nos controllers.

---

## 🧪 Testes

Os testes funcionais foram realizados manualmente utilizando o Postman e as classes executáveis do pacote `main`.

### Testes pelo Postman

Foram exercitadas as principais operações:

- GET — consulta de registros;
- POST — inserção de novos dados;
- PUT — atualização de informações;
- DELETE — exclusão de registros;
- Consultas com parâmetros;
- Validações de regras de negócio;
- Verificação de restrições de integridade do Oracle.

### Testes locais em Java

O pacote `main` contém classes com o método `public static void main(String[] args)`, permitindo testar diretamente a conexão JDBC e operações DAO sem iniciar o servidor HTTP.

Entre os exemplos estão:

- `TesteConexao`;
- `TesteSelecionarCidade`;
- `TesteBuscarCidadePorId`;
- `TesteInserirCidade`;
- `TesteAtualizarCidade`;
- `TesteDeletarCidade`;
- `TesteSelecionarBarreiras`.

Essas classes complementam os testes no Postman e ajudam a demonstrar o funcionamento da comunicação entre Java e Oracle.

Os testes manuais não substituem uma suíte de testes automatizados.

---

## 🔒 Limitações da versão atual

Esta versão corresponde à implementação acadêmica do backend e deve ser utilizada em ambiente de desenvolvimento e demonstração controlada.

Embora possua os CRUDs das 12 entidades e regras de negócio, ainda existem algumas limitações:

- Não há autenticação de usuários por login;
- Não há gerenciamento de sessão ou tokens;
- Os endpoints não possuem autorização baseada na identidade autenticada;
- A moderação dos registros gerais ainda não possui um fluxo administrativo completo;
- Os identificadores são informados manualmente;
- As consultas ainda não oferecem filtros avançados;
- A integração com um front-end não faz parte deste repositório;
- A configuração de deploy e de integração entre origens ainda precisa de refinamento.

Por essas razões, **a API atual não deve ser exposta publicamente como um serviço de produção sem controles adicionais de segurança**.

---

## 🔮 Melhorias futuras

A arquitetura foi organizada para permitir a evolução do AcessoJá em próximas versões.

Entre as melhorias planejadas estão:

### Autenticação e autorização

Implementar login, identificação de usuários, sessões ou tokens e controle de permissões para cidadãos e administradores.

### Moderação administrativa

Criar operações específicas para aprovar e rejeitar novos registros, além de controlar quais informações podem ser publicadas.

### Identificadores automáticos

Utilizar sequences do Oracle para gerar IDs automaticamente, eliminando a necessidade de informá-los nas requisições.

### Filtros e pesquisas

Adicionar consultas por cidade, bairro, tipo de estabelecimento, recursos disponíveis e situação das barreiras.

### Front-end responsivo

Desenvolver uma interface web acessível para permitir a consulta e o compartilhamento de informações pela comunidade.

### Documentação interativa

Integrar OpenAPI/Swagger para facilitar a visualização e o teste dos endpoints.

### Testes automatizados

Desenvolver testes unitários e de integração para validar as regras de negócio, a persistência e as respostas HTTP.

### Deploy e infraestrutura

Evoluir a configuração da aplicação para execução em ambientes de nuvem, com variáveis de ambiente e gerenciamento adequado de conexões.

### Evolução da plataforma

Ampliar a cobertura geográfica, melhorar os mecanismos de participação comunitária e desenvolver novas ferramentas de consulta a informações sobre acessibilidade.

---

## 🎓 Contexto acadêmico

O AcessoJá foi desenvolvido como projeto da **Global Solution 2026.2**, no curso de **Engenharia de Software da FIAP**, dentro do tema CitizenTech.

O projeto integra conceitos de:

- Domain Driven Design e programação orientada a objetos;
- Desenvolvimento de APIs RESTful;
- Modelagem lógico-relacional;
- Banco de dados Oracle;
- Regras de negócio;
- Arquitetura em camadas;
- Persistência de dados com JDBC;
- Tratamento de exceções;
- Versionamento de código com Git e GitHub.

Além de sua finalidade acadêmica, o projeto demonstra como tecnologias de desenvolvimento de software podem ser utilizadas para propor soluções a desafios de inclusão e acessibilidade.

### Repositório

[github.com/pedrofaleirosss/acessoja-api](https://github.com/pedrofaleirosss/acessoja-api)

---

## 💙 AcessoJá

**Acessibilidade começa com informação.**

O AcessoJá parte da ideia de que informações compartilhadas e organizadas podem ajudar pessoas a tomar decisões com mais autonomia e incentivar a construção de cidades mais acessíveis.

O backend apresentado neste repositório representa a base tecnológica dessa proposta, reunindo os recursos de gerenciamento e colaboração necessários para a evolução da plataforma.
