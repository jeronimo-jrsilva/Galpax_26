# 🏗️ Galpax_26: Arquitetura

Este documento detalha a estrutura do projeto Galpax_26. O objetivo foi tornar o software agnóstico em relação à resolução de tela e visualmente coeso.

## 1. Conexão com Banco de Dados
A dependência do MySQL é gerenciada na classe `projeto.Bancodedados`. O sistema utiliza o arquivo `secrets.properties` para carregar as credenciais localmente.

### Fallback Localhost
Caso o arquivo de configuração não seja encontrado, o sistema tenta conectar em `localhost:3306` com usuário `root` sem senha.

## 2. Escala Dinâmica (`GerenciadorJanelas.java`)
O `GerenciadorJanelas.java` atua como controlador global de interface, permitindo que o sistema rode tanto em modo **Totem (Full Screen 1080p)** quanto em modo **Laptop (Janela)**.

### Lógica de Aspect Ratio (16:9)
O sistema calcula a escala comparando a tela atual com a resolução base (1920x1080).
1.  **HQ Rendering:** Imagens e botões usam interpolação bicúbica para não pixelizar.
2.  **Letterbox:** Garante que a interface preencha o espaço sem distorcer os elementos.

---
**Status da Integração:**
Módulos financeiros e operacionais estão integrados ao núcleo unificado, herdando automaticamente o gerenciamento de janelas.
