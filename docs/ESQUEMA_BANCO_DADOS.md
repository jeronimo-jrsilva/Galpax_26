# 🗄️ Esquema do Banco de Dados: Galpax

Este documento contém o script necessário para criar o banco de dados local. Estas tabelas devem existir no MySQL para o funcionamento pleno do sistema.

## 📊 Estrutura de Dados

O sistema foca no relacionamento entre Lojas e suas respectivas Mensalidades.

## 📜 Script de Instalação (Master)

Utilize o arquivo **`GALPAX_MASTER_INSTALL.sql`** localizado na pasta **`db/`** deste projeto para criar todas as tabelas e popular o sistema com dados de teste.

### Como usar:
1.  Abra seu MySQL Workbench ou terminal.
2.  Execute o comando: `source db/GALPAX_MASTER_INSTALL.sql;`
3.  Configure seu arquivo `secrets.properties` com as credenciais do seu banco local.

---
[🏠 Voltar ao Início](../README.md)
