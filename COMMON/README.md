## Spring Cloud - PROJETOS COMUNS

**OBS :** Em cada um dos fontes a idéia é disponibilizar READMEs com aquilo que é necessário prestar a atenção no código.
Para testar efetivamente o ambiente de microservices é preciso subir as dependências comuns de infra so spring cloud... Vamos a elas :


### -- Spring Cloud Config 

**Função :**

Provê um repositório central versionado de onde os serviços podem retirar suas propriedades personalizadas de maneira agnóstica, sem depender de recursos como filesystems locais ou variáveis de ambiente, coisas que nem sempre estão disponíveis em ambientes de cloud comerciais. Como o cloud config expõe as configurações via rest, serviços desenvolvidos em  outras linguagens podem fazer uso do componente.

**Como subir a instância do Spring Cloud Config :**

Primeiramente é necessário criar um repositório de propriedades. O Spring cloud utiliza git para este propósito. Para o nosso laboratório vamos criar um repositório git local com arquivos de configuração para cada aplicação do nosso ambiente de microservices. Siga estes passos : 

- Verifique dentro do projeto cloudConfigServer na pasta confs cada um dos arquivos e procure por endereços IP e configure-os para o IP da sua estação (mantenha as portas ... isso vai poupar trabalho).
- Crie na home do usuário que vai rodar o projeto o diretório "config-repo".
- Copie o conteúdo da pasta cloudConfigServer/confs para o diretório "config-repo".

- Dentro do diretório  "config-repo" digite o comando **git init .** .
- Ainda dentro deste diretório digite o comando **git add -A .** .
- Finalmente digite o comando **git commit -m "Adicionando propriedades dos servicos"** .

**Inicie o projeto :**

Importe o projeto para o Eclipse e faça um "Run as" "Spring Boot App".


**Teste :**

Vamos exibir as propriedades de um dos nossos serviços. No navegador coloque a URL http://<seu ip>:8001/servicea/default o conteúdo das propriedades do arquivo servicea.properties do seu repositório de configuração deve ser exibido em formato json.
Se tudo ocorreu bem você já tem seu repositório de configurações rodando ! Vamos trabalhar nos serviços do EUREKA agora !


### -- Spring Cloud Netflix Eureka

**Função :**

Componente de passive service discovery. Quando um serviço entra no ar ele se registra no Eureka e quando é necessário invocar um serviço específico recorre-se a este cadastro (diretamente ou através de outras ferramentas que usam este componente). Vários outros softwares usam o Eureka como base ( Ex: Ribbon , Hystrix). Ele também é capaz de clusterizar várias instâncias do mesmo serviço como um único registro na base, característica necessária para a realização do balanceamento de carga com o Ribbon… 
OBS: É preciso subir pelo menos 2 instâncias do eureka na infra da aplicação (ideal 3 ou mais), pois eles fazem replicação entre si.

**Como subir as instâncias do Eureka :**

Importe os projetos cloudEurekaServer e cloudEurekaServer2 para o eclipse, em seguida verifique 
em cada um dos projetos o src/main/resources/bootstrap.properties a propriedade spring.cloud.config.uri onde consta o IP do servidor de configuração para que a mesma reflita o IP do seu servidor de configuração. Finalmente é só rodar os projetos com um "Run As" "Spring Boot App". Ao subir a primeira instância é possível verificar alguns erros... isso se deve ao fato do Eureka estar buscando outras instâncias e não estar encontrando nada.... Ao subir a segunda instância o problema deve cessar.

**Teste :**

Verifique o console das instâncias do Eureka no navegador pela URL http://<seu IP>:<porta 8010 ou 8011> e verifique se as duas instâncias aparecem na parte da tela com o título "Instances currently registered with Eureka".  That's all folks ! Agora temos os aparato básico configurado para realizar nossos testes com os serviços.
