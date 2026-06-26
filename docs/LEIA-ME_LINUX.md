# 🐧 Instruções de Execução no Linux - Projeto Galpax Consolidado

Este diretório contém a unificação do trabalho de todos os alunos da turma **PS_2026.07.46**:
* **Henrique**: `src/projeto/telaCartãoDébito.java` (Validações, botões e máscara).
* **Isaac**: `src/projeto/telaPIX.java` (Tela Pix implementada).
* **Lucas**: `src/projeto/TelaMensalidade.java` (Visual e lógica de mensalidades).
* **Mateus**: `src/projeto/CadastroLoja.java` e `src/projeto/telaCartãoCredito.java` (Novos visuais, confirmadores e parcelas).

---

## 🚀 Como Executar o Projeto

Para sua facilidade, criei o script `run_galpax.sh`. Basta executar o seguinte comando no seu terminal para compilar e abrir a tela de login:

```bash
./run_galpax.sh
```

---

## 🗄️ Configuração do Banco de Dados (MySQL/MariaDB)

Como você mencionou que não tem banco de dados instalado neste sistema Linux (CachyOS), siga o passo a passo abaixo para configurar o banco de dados local compatível com o projeto (que espera o usuário `root`, senha `Aluno` e banco `galpax`):

### 1. Instalar o MariaDB (Equivalente ao MySQL)
No terminal do CachyOS, instale os pacotes:
```bash
sudo pacman -S mariadb
```

### 2. Inicializar o Diretório de Dados do Banco
```bash
sudo mariadb-install-db --user=mysql --basedir=/usr --datadir=/var/lib/mysql
```

### 3. Iniciar o Serviço do Banco de Dados
```bash
sudo systemctl start mariadb
```

### 4. Configurar a Senha do Root do MySQL para `Aluno`
Para alinhar com a senha esperada pelo código do projeto (`Aluno`), execute:
```bash
sudo mysql_secure_installation
```
*(Siga os passos do prompt para definir a senha do usuário `root` como `Aluno`).*

Ou diretamente via shell administrativo:
```bash
sudo mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'Aluno'; FLUSH PRIVILEGES;"
```

### 5. Criar o Banco e Importar a Estrutura
Crie a database `galpax` e importe o arquivo `GALPAX_MASTER_INSTALL.sql` que já está na raiz deste projeto:
```bash
mysql -u root -pAluno -e "CREATE DATABASE galpax;"
mysql -u root -pAluno galpax < GALPAX_MASTER_INSTALL.sql
```

---

## 📝 Visualização no Zed

Para visualizar, navegar pelo código e editar no editor Zed, basta abrir esta pasta executando:

```bash
zed .
```
*(Ou abrindo o Zed e selecionando esta pasta `GALPAX_CONSOLIDADO`).*
