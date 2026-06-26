# Changelog - Galpax_26

Todas as modificações notáveis neste projeto serão documentadas neste arquivo.
O formato é baseado no [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/).

## [1.1.0] - 2026-06-13
### Autor: Jeronimo (Professor)

### Adicionado
- **Unificação Galpax_26**: Consolidação definitiva das contribuições dos alunos (Isaac, Mateus, Artur, Lucas e Henrique) em um único core.
- **Motor High-Definition UI**: Implementação de renderização via `Graphics2D` com interpolação Bicúbica no `GerenciadorJanelas.java`, eliminando a pixelização de imagens e botões.
- **Sessão por E-mail**: Sistema de identificação de lojista via login, permitindo acesso a múltiplos empreendimentos vinculados ao mesmo usuário.
- **Master Install SQL**: Script unificado (`GALPAX_MASTER_INSTALL.sql`) que automatiza a criação do banco, tabelas, FKs e popula 50+ registros realistas.
- **Padronização Isaac**: Integração global dos botões de navegação estilizados do Isaac ("Voltar" e "Sair") em todas as janelas do sistema.
- **Atalho Global Ctrl+W**: Agora funciona em qualquer nível de foco, alternando dinamicamente entre Modo Janela e Tela Cheia (Totem).
- **Sistema de Segredos**: Implementação de `secrets.properties` para gerenciar credenciais de banco de dados localmente sem expô-las no Git.

### Alterado
- **Arquitetura Modal**: Telas de pagamento transformadas em `JDialog` Modais, garantindo que sub-janelas nunca abram por trás da tela principal.
- **Filtragem de Mensalidades**: A `TelaMensalidade.java` agora exibe apenas os empreendimentos do lojista logado através de um `INNER JOIN` robusto.
- **Higiene de Assets**: Remoção de dezenas de imagens órfãs e pastas redundantes (`src/Pagamento`), reduzindo o tamanho do repositório.
- **Layout de Login**: Centralização matemática dos campos de entrada para o padrão Totem (1920x1080).
- **Compatibilidade Window Builder**: Refatoração da classe `Login.java` para permitir edição visual e alinhamento preciso diretamente na IDE Eclipse.

### Corrigido
- **Z-Order de Janelas**: Problema onde janelas pequenas de pagamento eram "engolidas" pela janela principal maximizada.
- **Erro de Versão (JDK)**: Limpeza total de arquivos `.class` e pastas `bin` residuais dos computadores dos alunos, forçando a recompilação no JDK local (Java 21).
- **Nomenclatura SQL**: Sincronização entre os nomes de tabelas nos scripts (`cad_loja`) e no código Java.

## [1.0.0] - 2026-06-11
### Adicionado
- **GerenciadorJanelas.java**: Controle global para escalar a interface dinamicamente (16:9).
- **Mock Universal**: Sistema de persistência emulado na classe `Bancodedados.java`.
- **Atalhos de Teclado**: Ctrl+B (Bypass Admin), Ctrl+U (Bypass Lojista).
- **Layout HYPER MASSA**: Estetização de botões administrativos (1250x150px).
