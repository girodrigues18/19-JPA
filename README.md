# 🛠️ Projeto Assistência Técnica - Introdução ao JPA

Este repositório contém a implementação prática da disciplina **Programação Orientada a Objetos com Java e Web** da FIAP. O objetivo principal é construir uma API REST robusta para o gerenciamento de fluxos de ordens de serviço de uma assistência técnica.

## 📌 Cenário e Objetivos
A aplicação gerencia o fluxo de recebimento de equipamentos (notebooks, celulares e tablets) para diagnóstico e reparo, mapeando o ciclo de vida completo de uma Ordem de Serviço (OS) através dos seguintes estados:
- `ABERTA`: A OS é registrada no sistema.
- `EM_ANDAMENTO`: Um técnico assume a responsabilidade pelo reparo.
- `CONCLUIDA`: O equipamento é consertado e devolvido ao cliente.
- `CANCELADA`: O cliente desiste do atendimento.

### Metas do Projeto
- **Modelagem do Domínio:** Criação de tabelas relacionadas para `Cliente`, `Equipamento` e `Ordem de Serviço`.
- **Persistência de Dados:** Integração do ecossistema Spring Boot com Jakarta Persistence (JPA) e Hibernate, conectados a um banco de dados Oracle.
- **Implementação do CRUD:** Controle transacional de todas as operações e regras de negócio.
- **Exposição da API:** Endpoints REST organizados e testáveis via Postman ou Insomnia.

---

## 🚀 Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot 3.x**
  - Spring Web (Criação de APIs REST)
  - Spring Data JPA (Gerenciamento de repositórios dinâmicos)
  - Validation (Validação de beans de entrada com `@NotBlank`, `@Size`, `@Email`)
  - Actuator (Monitoramento e verificação de integridade da conexão)
- **Hibernate** (Provedor oficial da especificação JPA)
- **Oracle Database Driver (OJDBC)**
- **Lombok** (Otimização de entidades com `@Getter`, `@Setter` e `@NoArgsConstructor`)

---

## 📂 Estrutura de Camadas e Pastas
O projeto segue uma arquitetura corporativa em camadas bem definidas sob o escopo do componente scan principal:

```text
br/fiap/assistencia/
├── AssistenciaApplication.java     # Classe principal de boot
├── config/                         # Testes e configurações de inicialização (ConexaoConfig)
├── domain/                         # Entidades de mapeamento JPA e regras de domínio
├── repository/                     # Interfaces Spring Data (ClienteRepository)
├── service/                        # Camada de orquestração de negócios e demarcação transacional
└── web/
    ├── controller/                 # Controladores REST responsáveis pelo tratamento HTTP
    ├── dto/                        # Objetos de Transferência de Dados imutáveis (records)
    └── error/                      # Tratamento global e centralizado de exceções
```

- **Controller:** Recebe requisições HTTP, dispara as validações com `@Valid` e devolve DTOs de resposta.
- **Service:** Concentra as regras de negócio e gerencia transações com a anotação `@Transactional`.
- **Repository:** Interfaces puras estendendo `JpaRepository<T, ID>`, eliminando códigos repetitivos de acesso a dados (DAOs).

---

## ⚠️ Boas Práticas Adotadas
1. **Records para DTOs:** Uso de `record` (Java 16+) para contratos de entrada (`Request`) e saída (`Response`), garantindo imutabilidade e separação de dados sensíveis (ex: senhas trafegam na entrada, mas nunca são expostas na resposta).
2. **Cuidado com o Lombok em Entidades:** Utilização estrita de `@Getter`, `@Setter` e `@NoArgsConstructor` nas classes do pacote `domain` em vez do genérico `@Data`, evitando recursões infinitas e consultas desnecessárias (Lazy Loading) em mapeamentos bidirecionais.
3. **Mapeamento Explícito de Schemas:** Gerenciamento seguro via parâmetro `spring.jpa.hibernate.ddl-auto=none` nas propriedades do projeto, elegendo o script SQL nativo como única fonte da verdade e protegendo a integridade dos dados.
4. **Tratamento de Ausência com Optional:** Repositórios retornando caixas tipadas (`Optional<T>`) obrigando o tratamento correto em tempo de compilação com métodos elegantes como `.orElseThrow()`.

---
Prof. Dr. Antonio Marcos Selmini  
*Sistemas de Informação - FIAP*
