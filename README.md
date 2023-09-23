#######################################################################################

INSTRUÇÕES

Antes de subir os projetos, deve ter as devidas configurações citadas acima na máquina.

1 - Clonar o projeto da branch "main".

2 - Abrir o projeto "agendamento-transferencia-backend" em uma IDE de sua preferência com Java 11 instalado e configurado na máquina.

3 - Buildar e startar a aplicação backend (agendamento-transferencia-backend).

4 - Abrir o projeto "agendamento-transferencia-frontend".

5 - Executar o comando "npm install" no terminal da IDE.

6 - Subir a aplicação com o comando no terminal da IDE: "npm run dev".

5 - Acessar o endereço padrão: http://localhost:5173/

#######################################################################################

CONFIGURAÇÕES

Ferramentas utilizada para o desenvolvimento:
 
IntelliJ IDEA 2022.2.3 (Community Edition)
Visual Studio Code 1.82.2
Insomnia 2023.5.8

Frameworks: 
Spring 2.7.0
Vue.js 3.3.4
Node.JS 18.15.0


Arquitetura/Design Pattern: MVC -  Fácil manter, testar e atualizar. É simples incluir novos clientes apenas incluindo seus visualizadores e controles. possibilita a divisão do projeto em camadas muito bem definidas

Banco de dados: H2

Liguagens:
Java 11 para utilizar os recursos do java 8 + aprimoramentos de inicialização, desempenho e o uso de memória.

JavaScript

Maven como ferramenta para automação de compilação e gerenciamento de dependências.

Dependências:
Lombok - Para utilizar features do "Builder".

Springboot Dev Tools - Reinicializaçao rápida a cada modificação.

Spring Data JPA - Conexão com o banco de dados e herdar metodos para manipulação.

Spring WEB - Criaçao de aplicaçao restful/utilizar os recursos da arquitetura rest.

Jackson Modules - Dependência para manipulação de datas.

H2 - Banco de dados em memória.

#######################################################################################

BANCO DE DADOS

Após subir a aplicação, acessar a URL para o banco de dados: http://localhost:8080/h2

Utilizar com os seguintes dados:

Saved Settings:	Generic H2(Embedded)

Setting Name: Generic H2(Embedded)

Driver Class: org.h2.Driver

JDBC URL: jdbc:h2:mem:banco

User Name: admin

Password: 123

Configurações do banco se encontram no application.properties

#######################################################################################

ENDPOINTS

Adicionar Agendamento: http://localhost:8080/api/agendamento-transferencia/add

Payload:
 
 {
   
   "contaOrigem":"String",
   
   "contaDestino":"String",
   
   "valorTransferenciaTaxa":Double,
   
   "dataTransferencia":"String"
 
 }

Editar Agendamento: http://localhost:8080/api/agendamento-transferencia/edit

payload:

{
  
  "id": long,
  
  "contaOrigem":"String",
 
  "contaDestino":"String",
 
  "valorTransferencia":Double,
  
  "dataTransferencia":"String"

}

Get Agendamento By Id: http://localhost:8080/api/agendamento-transferencia/{id}

Get Agendamentos: http://localhost:8080/api/agendamento-transferencia/agendamentos

Delete Agendamento By Id: http://localhost:8080/api/agendamento-transferencia/{id}

#######################################################################################
