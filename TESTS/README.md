## Testes das features do Spring Cloud

Este pequeno conjunto de serviços feitos com o Spring Boot foi pensado para demonstrar o uso de algumas das principais ferramentas do Spring Cloud. São elas :

**- SPRING CLOUD NETFLIX RIBBON**

Componente   que disponibiliza a funcionalidade de balanceamento de carga do lado do cliente (no ambiente de microservices um serviço pode ser “cliente” do outro). Se existir mais de uma instância do
serviço a ser chamado no ambiente, o ribbon ( integrado ao eureka ) automaticamente seleciona a instância mais apropriada de acordo com a métrica escolhida.

**- SPRING CLOUD FEIGN**

O Feign permite a criação de clientes rest de forma declarativa no java. Basta anotar uma interface com métodos que  descrevem as chamadas rest que se deseja encapsular e as anotações do Feign e Spring
MVC permitem que esta interface seja injetada  em qualquer chamada dentro do ambiente de microservices sem qualquer codificação extra.

**- SPRING CLOUD NETFLIX HYSTRIX**

A principal função do Hystrix é implementar o padrão de projeto de microservices “Circuit Breaker”. Como em um ambiente de microservices é normal que exista um grau elevado de interdependência entre os
serviços, uma falha em um dos componentes pode provocar uma reação em cadeia que resulta na queda de todo o ambiente. A função do circuit breaker é promover mecanismos de fallback para que a ausência de
um serviço não provoque um estrago maior e permita o funcionamento, mesmo que parcial do ambiente. O Hystrix também possui mecanismos de verificação que restauram o funcionamento normal do ambiente assim
que o serviço problemático volta a funcionar.  O hystrix também possui funcionalidades que permitem que serviços sejam chamados de maneira assíncrona ou mesmo reativa. É possível com o software hystrix
turbine montar um dashboard com todos os “circuitos” do sistema para verificar se está tudo bem.

**- SPRING CLOUD BUS**

Componente que possibilita que serviços spring boot possam mudar suas propriedades de configuração on the fly de maneira segura.

**- SPRING CLOUD API GATEWAY  (NETFLIX ZUUL)**

Como o próprio nome sugere este componente é utilizado como um gateway de acesso para os serviços do ambiente. Permite expor somente os componentes públicos da API de microservices e também permite lidar
de maneira centralizada com questões como segurança, single sign-on , cache e a tradução de protocolos de comunicação. Se integra a outros componentes do spring cloud para prover o balanceamento de carga
se service discovery.


## Testes


Os serviços desenvolvidos e seus relacionamentos podem ser representados conforme a figura abaixo :

<p align="center">
![diagrama_servicos](.services.png)
</p>

Os microserviços **A** e **B** contém serviços rest que expõe propriedades específicas advindas do Spring Cloud Config, enquando que o serviço **C** condensa e expoõe o resultado das chamadas de **A** e **B**. (alguns destes serviços tem diferentes versões, o que será explicado mais adiante).

## Cenários de Teste

**OBS:** Antes de iniciar qualquer dos testes abaixo é necessário subir os serviços de infra conforme o explicado na pasta COMMON.

- **Testando os serviços :**

Rode os projetos service-a-v1,service-a-v2,service-b e verifique se os mesmos estão funcionando acessando no navegador o endereço "http://{seuip}:{porta do serviço}". As propriedades exibidas devem ser as mesmas  atribuidas aquele serviço no nosso repositório de configuração. Em seguida derrube o serviço do projeto service-a-v2 e suba o projeto service-c-v1 e teste seu funcionamento acessando o endereço "http://{seu ip}:{porta do serviço}/allprops", neste caso a saída do serviço deve ser o conjunto das propriedades definidas nos serviços **A** e **B** se estiver tudo correto derrube o serviço do projeto service-c-v1 e repita o procedimento com o service-c-v2.

- **Testando o balanceamento de carga do lado do cliente (Ribbon) :**

A idéia aqui é subir mais de uma instância do serviço **A** (que no nosso caso retornam propriedades diferentes de maneira proposital) e verificar nas chamadas ao serviço **C** que as duas instâncias são chamadas devido ao balanceamento de carga provido pelo Ribbon em conjunto com o Eureka. Para este teste é necessário subir os serviços dos projetos service-a-v1,service-a-v2,service-b e service-c-v1 para ficarmos com o ambiente conforme diagrama abaixo :

<p align="center">
![diagrama_servicos](https://github.com/enzopoeta/lab-microservices-spring-cloud/blob/master/TESTS/service-ribbon.png)
</p>

Repare que se chamarmos no navegador o serviço **C** várias vezes a saída correspondente ao serviço **A** muda, o que nos indica que a instância de **A** chamada também está mudando. Se removermos uma das instâncias de **A** nosso sistema ainda estará funcionando e as chamadas do serviço **C** irão refletir tal fato. Se removermos todas as instâncias de **A** ou a única instância de **B** o serviço **C** deixará de funcionar.A invocação dos serviços pelo serviço C está sendo feita via Rest Template do Spring.

- **Testando clientes rest declarativos e o pattern circuit breaker (Feign e Hystrix)**

Neste teste vamos rodar uma versão mais resiliente do serviço **C** para isso além dos serviços constantes nos projetos service-a-v1,service-a-v2,service-b precisamos rodar também a versão 2 do serviço **C** (service-c-v2). Com tudo no ar teremos a situação conforme o diagrama abaixo :


<p align="center">
![diagrama_servicos](https://github.com/enzopoeta/lab-microservices-spring-cloud/blob/master/TESTS/service-circuitbreaker.png)
</p>

Neste caso podemos  repetir todos os experimentos que fizemos no item anterior... A diferença é que se removermos todas as instâncias do serviço **A** ou do serviço **B** O serviço C não deixará de funcionar por conta do Hystrix (nosso circuit breaker) que obriga o serviço **C** a realizar uma chamada de fallback na ausência de qualquer dos outros serviços. O interessante é que ao subirmos os serviços depencencias novamente depois de alguns momentos o Hystrix irá restaurar o funcionamento normal do serviço **C**. Essa feature permite que desenvolvamos aplicações extremamente resilientes. Também é possível observar no código do projeto service-c-v2 que as chamadas aos serviços **A**  e **B** estão sendo feitas pelo Spring Cloud Feign.


- **Alterando configurações on the fly com o Spring Cloud Bus**

Breve


- **Expondo os serviços com o Spring Cloud API**

Breve
