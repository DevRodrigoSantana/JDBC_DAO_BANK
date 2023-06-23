# JDBC_DAO_BANK
Esse projeto tem objetivo melhorara minhas habilidades com banco de dados e com a linguagem Java. Sendo usado algumas técnicas como a arquitetura DAO para construção de uma replica de um caixa eletrônico.
# Sistema de Caixa Eletrônico com JDBC e Arquitetura DAO

Este é um repositório do GitHub contendo um projeto de um sistema de caixa eletrônico desenvolvido utilizando JDBC (Java Database Connectivity) e seguindo a arquitetura DAO (Data Access Object).

## Visão Geral do Projeto

O objetivo deste projeto é simular o funcionamento de um caixa eletrônico, onde os usuários podem realizar diversas operações bancárias, como saques, depósitos, transferências, consulta de saldo e extrato.



## Tecnologias Utilizadas

O projeto foi desenvolvido utilizando as seguintes tecnologias:

- Java: Linguagem de programação utilizada para implementação do sistema.
- JDBC (Java Database Connectivity): API do Java para conectar-se a bancos de dados relacionais.
- MySQL: Banco de dados utilizado para armazenar as informações dos usuários e transações.
- Arquitetura DAO (Data Access Object): Padrão de projeto que separa a lógica de acesso aos dados do restante do sistema.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:

- **src/main**: Contém o código-fonte principal do projeto.
  - **com.example.dao**: Pacote contendo as classes responsáveis pela comunicação com o banco de dados.
  - **com.example.model**: Pacote contendo as classes que representam os modelos de dados do sistema.
  - **com.example.service**: Pacote contendo as classes que implementam a lógica de negócio do sistema.
  - **com.example.util**: Pacote contendo classes utilitárias do sistema.
  - **com.example.Main**: Classe principal que inicia a aplicação.

- **src/test**: Contém os testes automatizados do projeto.

- **sql**: Contém os scripts SQL para criação do banco de dados e tabelas.

## Como Executar o Projeto

Para executar o projeto localmente, siga as instruções abaixo:

1. Certifique-se de ter o Java e o MySQL instalados em sua máquina.

2. Clone este repositório do GitHub em um diretório de sua preferência.

3. Importe o projeto em sua IDE favorita.

4. Execute o script SQL fornecido na classe "FabricacConexao" e "Conexao"  para criar o banco de dados. Para criar as tabelas necessárias use a classe "CriandoTabelas".

5. Compile e execute a classe `com.example.Main` para iniciar a aplicação.

## Contribuição

Se você deseja contribuir para este projeto, fique à vontade para enviar pull requests. Será um prazer revisar e incorporar suas contribuições.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT). Sinta-se livre para utilizar e modificar o código de acordo com suas necessidades.

## Contato

Se você tiver alguma dúvida ou sugestão em relação a este projeto, sinta-se à vontade para entrar em contato através do meu perfil no GitHub.
