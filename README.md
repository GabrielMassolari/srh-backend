<h1 align="center">
    <img src="https://i.ibb.co/tzbzzs9/primary-Logo.png" alt="primary-Logo" border="0">
</h1>

<h1 align="center">
    Sistema de Recomendação Híbrido
</h1>

Sistema de recomendação híbrido implementado utilizando a estrutura de **WebService RESTful**. Esse sistema é o resultado do trabalho de conclusão de curso de Sistemas de Informação do IFES - Campus Cachoeiro de Itapemirim no ano de 2020/2021.

## Arquitetura do sistema

O sistema foi desenvolvido no formato de API RESTful, onde os dados são transmitidos e enviados no formato **JSON**. O sistema pode ser utilizado em conjunto com outras aplicações clientes para manuseio e visualização dos dados. Inicialmente o sistema foi pensado para utilização na seguinte arquitetura:

![Arquitetura do Sistema](https://i.ibb.co/Jd6wrnf/arquitetura.png)

Os outros componentes da arquitetura podem ser acessados pelos seguintes repositórios:

- [Cliente de Recomendação](https://github.com/herikLorencao/srh-client)
- [Cliente Administrativo](https://github.com/herikLorencao/srh-client-admin)

Além disso, existe também alguns clientes de recomendação específicos:

- [Cliente de Recomendação de Músicas](https://github.com/herikLorencao/srh-findbymusic)

## Funcionalidades

O principal intuito do sistema é oferecer um serviço de recomendação genérico que oferece os seguintes tipos de recomendação:

- Filtragem Colaborativa
- Filtragem Baseada em Conteúdo
- Filtragem Híbrida (Ponderada e Mista)
- Filtragem Híbrida (Combinação Sequencial)

Com os dados devidamente cadastrados (mostrados nos tópicos abaixo), é possível realizar o processo de recomendação passando os seguintes parâmetros:

- Precisão das notas de recomendação (escala decimal de 0 a 5 caracteres)
- Nota de corte (Para definição do ponte de corte das notas)
- Tipo de algoritmo e abordagem utilizada.

### Abordagens de Recomendação

O sistema prove algumas formas para geração da recomendação. Essas abordagens possibilitam formas de geração da recomendação utilizando recursos voltados para melhor performance, como concorrência e uso de matrizes de recomendação offline.

**OBS**: Vale ressaltar que a recomendação offline só estará disponível após a realização de recomendações normais (processadas em tempo de requisição) e a concorrência só estará disponível para as recomendações híbridas.

## Documentação

### Requisições da API

As requisições da API estão documentadas no Postman para consulta, elas podem ser encontradas no seguinte link:

[Documentação da API](https://documenter.getpostman.com/view/16559558/2s8YzMXkQb)

### Diagrama de classes

Segue abaixo a representação das classes do sistema na linguagem UML:

![Diagrama de Classes](https://i.ibb.co/1Kz7n4n/diagram.jpg)

## Utilização Básica do sistema

### Requisitos básicos

Para utilização do sistema é necessário possuir o ambiente de desenvolvimento JAVA na versão 14 instalado em sua máquina, juntamente de um banco de dados (por padrão é utilizado o PostgreSQL na versão 12).

### Instalação do sistema

Para uso do sistema basta clonar esse repositório com o seguinte comando:

```bash
git clone git@github.com:CaioFViana/srh-backend.git
```

Além disso, é necessário a criação de um base de dados no banco com o nome **srh** para o registro dos dados do sistema.

Após esses passos, basta executar a aplicação usando uma IDE de preferência ou o próprio Maven.

**OBS:** Na primeira execução do projeto no arquivo **resources/application-dev.properties** deixe a opção **spring.jpa.hibernate.ddl-auto**
com o valor **create** para cadastro dos itens iniciais para uso da API. Para as outras execuções deixe a opção como **update** para que a
base de dados não seja perdida. 

### Configuração e cadastro dos dados

Para a realização da recomendação é necessário o cadastro de alguns dados, em um fluxo simplificado, o cadastro pode ser feito seguindo a seguinte ordem:

- Cadastro do projeto
- Cadastro dos usuários
- Cadastro dos itens
- Vínculo dos usuários ao projeto
- Avaliação de itens por parte dos usuários
- Processo de recomendação

#### Dados de acesso a API

Para conexão básica na API e obtenção dos tokens JWT já estão disponíveis dois usuários por padrão na plataforma. Os dados
abaixo pode ser usados para geração do token:

- Perfil administrador:

```json
{
  "login": "admin",
  "password": "123456"
}
```

- Perfil de recomendação

```json
{
  "login": "client",
  "password": "123456"
}
```

#### Dados de acesso as medidas de justiça social

<P align="justify">
Para o funcionamento do algoritmo das medidas de justiça social (polarização, justiça individual, e justiça do grupo). Primeiramente é necessário que as recomendações 3, e 5 sejam geradas via Postman. Assim, o sistema cria as recomendações de todos os algoritmos. O sistema 3, é responsável por gerar as recomendações 1, 2, e 3. O sistema 4, captura as recomendações do algoritmo 1 e do algoritmo 2 quando ambas estão criadas, e o algoritmo 5, retorna as recomendações apenas do 5. Feito isso, é necessário realizar as inserções das avaliações na classe “RecommendationRating”, que podem ser feitas via H2. O bloco com as inserções (“INSERT RECOMMENDATION_RATING”) já está criado, então basta copia-lo e cola-lo dentro do H2. Com todos os passos realizados, é possível visualizar as medidas de justiça social usando o parâmetro do projeto, e do algoritmo, por exemplo, rpols/1/1, será mostrado a polarização do projeto 1, algoritmo 1, rindvs/1/1, será mostrado a justiça individual do projeto 1, e do algoritmo 1, e rgrps/1/1, será mostrado a medida de justiça do grupo do projeto 1, do algoritmo 1, e assim serve para todos os algoritmos existentes no projeto.</P>
