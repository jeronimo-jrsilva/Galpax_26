# 🚗 Galpax: Sistema de Gestão de Galpões e Estacionamento

O **Galpax** é um sistema robusto e integrado de gestão de lojistas, controle de mensalidades e fluxo de estacionamento, projetado especificamente para terminais de autoatendimento (Totens).

## 🎯 O Projeto
O sistema visa otimizar as operações diárias de galpões e estacionamentos comerciais de médio e grande porte, garantindo agilidade no atendimento e precisão no controle financeiro.

### 🚀 Principais Funcionalidades
- **Interface Otimizada:** Escala dinâmica nativa para proporção 16:9 e renderização em alta definição para totens e terminais.
- **Gestão Financeira:** Controle centralizado de cobranças e controle de mensalidades por lojista.
- **Segurança e Controle de Acesso:** Autenticação multinível (Administrador e Lojista) com proteção de credenciais locais.
- **Integração de Banco de Dados:** Backend preparado para MySQL, integrando todas as tabelas transacionais de lojistas, vagas e cobranças.

## 📂 Documentação do Sistema
- [📋 Documento de Requisitos](docs/Requisitos%20Galpax.docx) - Especificação funcional do sistema.
- [👥 Estrutura da Equipe](docs/EQUIPE_SENAC.md) - Divisão dos módulos de desenvolvimento.
- [🗄️ Modelagem de Banco de Dados](docs/ESQUEMA_BANCO_DADOS.md) - Esquema e script de instalação do MySQL.

## 🛠️ Como Iniciar o Desenvolvimento

### Pré-requisitos
*   Java JDK 17 ou superior
*   MySQL Server 8.x
*   IDE recomendada: Eclipse ou IntelliJ IDEA

### Instalação e Execução
1.  **Clonar o Repositório:** Clone o projeto localmente em seu workspace.
2.  **Configurar o Banco de Dados:**
    *   Execute o script localizado em [db/GALPAX_MASTER_INSTALL.sql](file:///C:/Users/shaol/git/Galpax_26/db/GALPAX_MASTER_INSTALL.sql) no seu servidor MySQL local.
3.  **Configurar Segredos (Propriedades):**
    *   Crie um arquivo chamado `secrets.properties` na raiz do projeto, baseado no arquivo [secrets.template.properties](file:///C:/Users/shaol/git/Galpax_26/secrets.template.properties) existente, preenchendo as suas credenciais locais do banco de dados.
4.  **Executar o Projeto:**
    *   Importe o projeto na sua IDE Java de preferência e execute a classe principal (Entry Point).
