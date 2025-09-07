 # Sistema de Aluguel de Carros

## Demonstração do Projeto

Veja abaixo uma demonstração do sistema funcionando:

![Demonstração do Sistema](./docs/demontracao.gif)

---

## 🛠️ Tecnologias e Arquitetura

Este projeto foi desenvolvido utilizando as seguintes tecnologias, padrões e camadas arquiteturais:

### 🧪 Tecnologias Utilizadas

- **Java** – Linguagem de programação principal utilizada no backend.
- **Spring Boot** – Framework para criação de aplicações Java robustas, com configuração simplificada e suporte a boas práticas de desenvolvimento web e APIs REST.
- **PostgreSQL** – Banco de dados relacional utilizado para armazenar informações de usuários, pedidos, automóveis e contratos.
- **Maven** – Gerenciador de dependências e automação de build para projetos Java.
- **Spring Data JPA** – Abstração para comunicação com o banco de dados relacional, utilizando repositórios baseados em interfaces.

---

### 🧱 Arquitetura do Sistema

O sistema segue o padrão **MVC (Model-View-Controller)**, complementado com as camadas de **DTO**, **Service**, **Repository** e **Facade**, promovendo uma arquitetura limpa, modular e de fácil manutenção.

#### 📦 Camadas da Aplicação

- **Controller**
  - Responsável por receber as requisições HTTP dos usuários (clientes ou agentes).
  - Converte os dados recebidos em objetos apropriados (DTOs) e delega a lógica de negócio para a camada `Facade` ou `Service`.

- **DTO (Data Transfer Object)**
  - Objetos simples usados para transportar dados entre o frontend e o backend.
  - Evitam expor diretamente as entidades do modelo de domínio nas interfaces públicas da API.

- **Facade**
  - Camada intermediária que atua como **fachada** para orquestrar chamadas aos `Services`.
  - Centraliza fluxos mais complexos, agregando chamadas a múltiplos serviços quando necessário.

- **Service**
  - Contém a **lógica de negócio** da aplicação.
  - Realiza validações, regras de negócio e chamadas a outros serviços, se necessário.

- **Repository**
  - Interface que estende `JpaRepository` ou `CrudRepository`, fornecendo acesso ao banco de dados.
  - Responsável por executar operações de CRUD (Create, Read, Update, Delete) nas entidades.

- **Model (Entidades)**
  - Representam as tabelas do banco de dados.
  - Contêm os atributos principais de cada entidade do domínio: usuário, pedido, contrato, automóvel, etc.

---

### 🔗 Integração com o Banco de Dados

- O sistema utiliza o **PostgreSQL** como banco de dados relacional.
- As entidades do modelo são mapeadas com **JPA (Java Persistence API)**.
- As operações de persistência (inserir, atualizar, consultar e deletar) são realizadas através dos `Repositories`.

---

### 📐 Benefícios da Arquitetura

- **Manutenibilidade**: Separação clara de responsabilidades facilita alterações e testes.
- **Escalabilidade**: Facilidade para adicionar novos módulos ou fluxos complexos sem afetar outras partes do sistema.
- **Reutilização**: Serviços e DTOs podem ser reutilizados em diferentes partes da aplicação.
- **Segurança**: Exposição controlada de dados via DTOs, evitando leaks de entidades do domínio.

---

## Diagramas do Projeto

Clique [aqui](https://github.com/viniciusgomesrod/lds-aluguel-de-carros/blob/main/docs/diagramas.md) para acessar os diagramas do projeto, incluindo diagramas de classes, casos de uso, pacotes, componentes e implantação.

---

## Histórias de Usuário

Clique [aqui](https://github.com/viniciusgomesrod/lds-aluguel-de-carros/blob/main/docs/historias-de-usuario.md) para acessar as histórias de usuário do projeto, detalhando os requisitos e funcionalidades do sistema sob a perspectiva dos usuários finais.

---

### Ferramentas Utilizadas

- **VSCode**: Editor de código leve e multiplataforma, com suporte a extensões e integração com sistemas de versionamento.
- **Drawio**: Ferramenta online para criação de diagramas, fluxogramas e modelagens visuais.
- **AstahUML**: Software para modelagem UML, auxiliando na representação de classes, casos de uso e diagramas de sequência.
- **GitHub**: Plataforma de hospedagem e versionamento de código baseada em Git, que facilita o trabalho colaborativo em equipe.

---

## Licença

Este projeto está licenciado sob a [MIT License](./LICENSE).
Você pode usar, modificar e distribuir este código, desde que os **devidos créditos sejam dados**, com link para o [original repository](https://github.com/viniciusgomesrod/lds-aluguel-de-carros). 
Este projeto foi desenvolvido por [Vinícius Gomes](https://github.com/viniciusgomesrod) e [Thomás Ramos](https://github.com/Thomasramos02).
