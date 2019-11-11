# AndroidBankApp
# Teste para a TCS
Sobre o app

Arquitetura: MVVM com JectPack
Optei por fazer um app utilizando como base as ultimas recomendações do Google.
Os layouts utilizarem o DataBinding o que facilitou muito o link com as ViewModels
O Room fui utilizado para a interface com o SQLite e para acessá-lo utilizei as Cotoutines do Kotlin.
Outra novidade que gostei muito foi utilizar o Retrofi2 com coroutines o que eliminou um monte de boilerplate.
A utlização do DataBingind e do ViewModel com injeção de dependência permitiu que os dados sobrevivessem ao giro da tela, evitou memory leaks e deixou a navegação muito fluida.
A API no Heroku apresentou alguns errinhos de troca da conta pela agência bancária, mais contornei no código.
No códig utilizei os princípios da Clean Architeture do uncle Bob quando a nomeação de varíaveis, legibilidade e organização do código.
Por fim implementei testes unitários simples e um teste de UI com Expresso.
Para qualquer dúvida por gentileza entrem em contato: elcioabrahao@usp.br.

Elcio A.
