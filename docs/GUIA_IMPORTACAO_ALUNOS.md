# 📥 Guia de Importação: Galpax_26 no Eclipse

Este guia foi criado para garantir que você consiga baixar e configurar o projeto de forma rápida e sem erros.

## 1. Preparação
Antes de começar, certifique-se de que o Eclipse está aberto e que você tem o link do repositório em mãos:
`https://github.com/jeronimo-jrsilva/Galpax_26.git`

## 2. O Passo a Passo da Importação

> [!tip] **Por que usar 'Clone URI'?**
> Embora o Eclipse mostre uma opção chamada "GitHub", usamos o **Clone URI** por ser mais direto e não exigir que você conecte sua conta do GitHub nas configurações do Eclipse, o que evita erros de autenticação comuns.

1.  **Menu Superior:** Vá em `File` (Arquivo) -> `Import...`
2.  **Origem:** Na caixa de busca, digite "Git". Escolha a opção **Projects from Git (with smart import)** e clique em `Next`.
3.  **Fonte:** Selecione **Clone URI** e clique em `Next`.
4.  **Conexão:** 
    - No campo **URI**, cole o link do GitHub.
    - O Eclipse preencherá o restante automaticamente.
    - Se pedir senha, use seu **Usuário do GitHub** e seu **Personal Access Token (PAT)**.
5.  **Ramos (Branches):** Você verá uma lista de nomes (`main`, `dev-isaac`, etc.). 
    - **Atenção:** Mantenha **TODAS** as branches marcadas e clique em `Next`.
    - Na tela seguinte, em **Initial Branch**, escolha a branch com o **seu nome** (ex: `dev-seu-nome`). O professor usará a `main`.
6.  **Pasta Local:** O Eclipse sugerirá uma pasta no seu computador. Clique em `Next`.
7.  **Finalização:** O Eclipse analisará o projeto. Quando aparecer o nome "Galpax_26", clique em `Finish`.

## 3. Configurando o Acesso (Obrigatório)

O GitHub não aceita sua senha normal para enviar arquivos pelo Eclipse. Você precisa gerar um **Token de Acesso (PAT)**.

1.  **Convite:** Verifique seu e-mail e aceite o convite do professor para ser colaborador do projeto.
2.  **Gerar Token:**
    - No GitHub: Vá em suas fotos (canto superior direito) -> `Settings` -> `Developer settings` (lá embaixo).
    - Selecione `Personal access tokens` -> `Tokens (classic)`.
    - Clique em `Generate new token (classic)`.
    - Dê um nome (ex: "Eclipse Senac") e marque a caixa **`repo`**.
    - **IMPORTANTE:** Copie o código gerado e guarde em um lugar seguro. Você usará esse código como "senha" no Eclipse.

---
## 💡 Dificuldades Comuns e Dicas
*(Esta seção será atualizada conforme as dúvidas da aula)*

- **Erro "Project Galpax_26 already exists":** Isso acontece se você já tentou importar antes. Clique com o botão direito no projeto antigo dentro do Eclipse, escolha `Delete` (não precisa apagar os arquivos do disco) e tente importar novamente.
- **O projeto não aparece como "Java":** Se o ícone do projeto não tiver um "J" pequeno, clique com o botão direito nele -> `Configure` -> `Convert to Faceted Form...` e marque `Java`.
- **Erro de Bibliotecas:** O MySQL Connector já está na pasta `lib`, mas se houver erros vermelhos, avise o professor.
