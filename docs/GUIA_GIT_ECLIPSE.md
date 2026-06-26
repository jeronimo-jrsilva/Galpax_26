# 🚀 Guia de Sobrevivência Git + Eclipse

Este guia ensina você a colaborar no projeto **Galpax_26** usando as ferramentas visuais do Eclipse.

## 1. Importando o Projeto pela Primeira Vez
1.  No Eclipse: `File` -> `Import...`
2.  Selecione `Git` -> `Projects from Git (with smart import)`.
3.  Escolha `Clone URI` e cole o link do repositório que o professor passar.
4.  Avance e conclua. O projeto aparecerá no seu *Project Explorer*.

## 2. Entrando na sua "Caixa de Areia" (Branch)
**NUNCA** trabalhe diretamente na branch `main`. 
1.  **Botão Direito** no projeto -> `Team` -> `Switch To...` -> `Other...`
2.  Procure pela branch com o seu nome (ex: `dev-isaac`).
3.  Clique em `Checkout`. Agora tudo o que você fizer estará seguro na sua área.

## 3. O Ciclo de Trabalho (Add -> Commit -> Push)
Sempre que terminar uma pequena tarefa:
1.  **Botão Direito** no projeto -> `Team` -> `Add to Index`.
2.  **Botão Direito** -> `Team` -> `Commit...`
3.  Escreva uma mensagem clara na aba **Git Staging** e clique em **Commit and Push**.

## 4. Resolvendo Conflitos (A Temida Tela Vermelha)
Se você tentar dar um `Push` e o Eclipse reclamar de conflito:
1.  Dê um `Team` -> `Pull`. O Eclipse marcará os arquivos com conflito.
2.  Abra o arquivo, escolha qual código deve ficar (o seu ou o do colega).
3.  Faça um novo `Add to Index` e `Commit`.

---
[[README|🏠 Voltar ao Início]]
