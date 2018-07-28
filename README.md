## Laboratório básico de microservices com a infra do Spring Cloud


## Como usar o código ?


-- Comece pela pasta **COMMON**. Nela você encontrará os projetos que são comuns a todos os testes (Veja os READMEs de cada projeto).

-- A pasta **TESTS** contém vários testes de serviços que rodam na infra definida em **COMMON** (Veja os READMEs).









## Conceitos de Microservices



– Um estilo arquitetural

– Uma alternativa a aplicações mais tradicionais (monolíticas)

– A decomposição de um sistema em um conjunto de pequenos serviços, cada qual rodando como um processo independente e se comunicando por meio de protocolos abertos.

– Um  SOA mais granular (Definição do cara do NETFLIX)

– Os serviços não precisam ser desenvolvidos em uma única linguagem e podem ter mecanismos de persistência diferentes (persistência poliglota).


## Solução Spring para Microservices

**OBS :** O ambiente de microservices do spring cloud aceita serviços desenvolvidos em outras linguagens, no entanto é preciso implementar a integração deste serviço com os componentes de infra fornecidos pelo Cloud.


**- SPRING BOOT**

Não faz parte do spring cloud (na verdade ele engloba o cloud assim como outros projetos da Spring). Permite o desenvolvimento rápido de aplicações java entreprise. Possui uma abordagem forte de convenção sobre configuração.


## Spring Cloud - Componentes

Basicamente o spring cloud é a implementação da stack de software para nuvem do netflix com uma roupagem mais facilitada.


**- SPRING CLOUD CONFIG**

Provê um repositório central versionado de onde os serviços podem retirar suas propriedades personalizadas de maneira agnóstica, sem depender de recursos como filesystems locais ou variáveis de ambiente, coisas que nem sempre estão disponíveis em ambientes de cloud comerciais. Como o cloud config expõe as configurações via rest, serviços desenvolvidos em  outras linguagens podem fazer uso do componente.


**- SPRING CLOUD NETFLIX EUREKA** 

Componente de passive service discovery. Quando um serviço entra no ar ele se registra no Eureka e quando é necessário invocar um serviço específico recorre-se a este cadastro (diretamente ou através de outras ferramentas que usam este componente). Vários outros softwares usam o Eureka como base ( Ex: Ribbon , Hystrix). Ele também é capaz de clusterizar várias instâncias do mesmo serviço como um único registro na base, característica necessária para a realização do balanceamento de carga com o Ribbon… 
OBS: É preciso subir pelo menos 2 instâncias do eureka na infra da aplicação (ideal 3 ou mais), pois eles fazem replicação entre si.


**- SPRING CLOUD NETFLIX RIBBON**

Componente   que disponibiliza a funcionalidade de balanceamento de carga do lado do cliente (no ambiente de microservices um serviço pode ser “cliente” do outro). Se existir mais de uma instância do serviço a ser chamado no ambiente, o ribbon ( integrado ao eureka ) automaticamente seleciona a instância mais apropriada de acordo com a métrica escolhida.


**- SPRING CLOUD FEIGN**

O Feign permite a criação de clientes rest de forma declarativa no java. Basta anotar uma interface com métodos que  descrevem as chamadas rest que se deseja encapsular e as anotações do Feign e Spring MVC permitem que esta interface seja injetada  em qualquer chamada dentro do ambiente de microservices sem qualquer codificação extra.

**- SPRING CLOUD NETFLIX HYSTRIX**

A principal função do Hystrix é implementar o padrão de projeto de microservices “Circuit Breaker”. Como em um ambiente de microservices é normal que exista um grau elevado de interdependência entre os serviços, uma falha em um dos componentes pode provocar uma reação em cadeia que resulta na queda de todo o ambiente. A função do circuit breaker é promover mecanismos de fallback para que a ausência de um serviço não provoque um estrago maior e permita o funcionamento, mesmo que parcial do ambiente. O Hystrix também possui mecanismos de verificação que restauram o funcionamento normal do ambiente assim que o serviço problemático volta a funcionar.  O hystrix também possui funcionalidades que permitem que serviços sejam chamados de maneira assíncrona ou mesmo reativa. É possível com o software hystrix turbine montar um dashboard com todos os “circuitos” do sistema para verificar se está tudo bem.

**- SPRING CLOUD BUS**

Componente que possibilita que serviços spring boot possam mudar suas propriedades de configuração on the fly de maneira segura.

**- SPRING CLOUD API GATEWAY  (NETFLIX ZUUL)**

Como o próprio nome sugere este componente é utilizado como um gateway de acesso para os serviços do ambiente. Permite expor somente os componentes públicos da API de microservices e também permite lidar de maneira centralizada com questões como segurança, single sign-on , cache e a tradução de protocolos de comunicação. Se integra a outros componentes do spring cloud para prover o balanceamento de carga se service discovery

