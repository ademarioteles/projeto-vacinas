<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>

# 💉 API de Gerenciamento de Vacinação

<code><img height="20" src="https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white"></code>
<code><img height="20" src="https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white"></code>
<code><img height="20" src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white"></code>

## 🚀 Começando

Esta é uma API de Gerenciamento de Vacinação que permite o controle e registro de vacinações de pacientes, gerenciamento de vacinas e pacientes, e fornece informações estatísticas sobre a vacinação. A API foi desenvolvida para atender aos requisitos do projeto "Programação Web 2 - Oficial 2".

## 📋 Conteúdo do README

- [Visão Geral](#visão-geral)
- [Requisitos](#requisitos)
- [Configuração](#configuração)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Testes](#testes)
- [Docker](#docker)
- [Contribuição](#contribuição)
- [Autores](#autores)
- [Licença](#licença)
- [Referências](#referências)

  
##  📝  Visão Geral

A API é projetada para fornecer as seguintes funcionalidades:

Registro de vacinações de pacientes.
Gerenciamento de informações sobre vacinas e pacientes.
Estatísticas sobre vacinação, como doses aplicadas, doses atrasadas e vacinas por fabricante.

## 📦 Requisitos

Antes de iniciar, certifique-se de que possui os seguintes requisitos:

- [Java (versão 17)](https://www.java.com/)
- [MongoDB (versão X.X.X)](https://www.mongodb.com/)
- [Postman (versão X.X.X)](https://www.postman.com/)
- [Docker](https://www.docker.com/)
- [Docker-Compose](https://www.docker.com/)


## ⚙️ Configuração


### Clone este repositório:

```bash
git clone https://github.com/seu-usuario/api-gerenciamento-vacinacao.git
```

### Instale as dependências:
```bash

```

###  Configure as variáveis de ambiente (por exemplo, em um arquivo .env) para definir as configurações do banco de dados, URLs de outras APIs, etc.

```bash
MONGODB_URI=mongodb://localhost:27017/vacinacao
API_PACIENTES_URL=http://localhost:8080
API_VACINAS_URL=http://localhost:8081
```

###  Inicie o servidor:

```bash

A API estará acessível em http://localhost:5000.
```

###  ▶️ Uso

A API oferece vários endpoints para criar, ler, atualizar e excluir registros de vacinação, bem como para consultar informações estatísticas. Certifique-se de seguir a documentação dos endpoints.

###  🛣️ Endpoints

- [/vacinas](#vacinas): Gerenciamento de informações sobre vacinas.
- [/pacientes](#pacientes): Gerenciamento de informações sobre pacientes.
- [/vacinacoes](#vacinacoes): Registro de vacinações de pacientes.


Consulte a documentação dos endpoints para obter detalhes sobre como usar cada um deles.

### 🧪 Testes
A API inclui testes automatizados para garantir o funcionamento correto dos endpoints. Execute os testes da seguinte maneira:

```bash

Comando de testes

```
###  🐳 Docker

Se desejar, você pode executar a API em um contêiner Docker. Use o Docker Compose para criar o ambiente completo, incluindo o banco de dados MongoDB e outras dependências:

```bash

xxxxxxxxxxxx

```

A API estará acessível em http://localhost:5000, e o banco de dados MongoDB estará em execução no contêiner.

## 🤝 Contribuição

Se desejar contribuir para o desenvolvimento deste projeto, siga estas etapas:

1. Crie um fork do repositório.
2. Crie uma branch com sua feature: `git checkout -b minha-feature`
3. Faça commit das alterações: `git commit -m 'Adicionando nova feature'`
4. Faça push para a branch: `git push origin minha-feature`
5. Envie um Pull Request.

## ✍️ Autores


- Ademario Teles - [GitHub](https://github.com/usuario1)
- Ana Beatriz  - [GitHub](https://github.com/usuario2)
- Maiara Rodrigues  - [GitHub](https://github.com/usuario3)
- Vanessa Santana  - [GitHub](https://github.com/usuario4)
- Victor Caetano - [GitHub](https://github.com/usuario5)
- Taysa Barbosa  - [GitHub](https://github.com/usuario6)


## 📄 Licença

Este projeto é licenciado sob a Licença XYZ - consulte o arquivo [LICENSE](LICENSE) para obter detalhes.

## 📚 Referências
