# 📐 Diretrizes de Desenvolvimento do Front-End (Galpax_26)

Para garantir que o software seja executado sem distorções tanto em **Totens Touchscreen (1080p)** quanto em **Laptops/Monitores de Desenvolvimento** no Linux (especialmente sob gerenciadores de janelas tiling como o **Niri Wayland**), todas as novas telas devem seguir rigorosamente estes padrões de arquitetura visual e de código.

---

## 1. Janelas Principais de Tela Cheia (`JFrame`)

Devem ser usadas para telas principais do fluxo do sistema (ex: *Login*, *Menu Admin*, *Cadastro de Veículos*, *Visualização de Galpões*, etc.).

### 📝 Padrão de Código:
* **Tamanho Base:** Devem ser desenhadas na resolução base de **1920x1080**.
* **Remover Decoração:** Usar `frame.setUndecorated(true)` para remover barras de títulos e botões do sistema operacional.
* **Escala Adaptativa:** No final do método `initialize()`, **DEVE** ser feita a chamada do controlador de escala dinâmico:
  ```java
  GerenciadorJanelas.configurarJanela(frame);
  ```

### 💻 Exemplo Prático:
```java
private void initialize() {
    frame = new JFrame();
    frame.setUndecorated(true);
    frame.setSize(1920, 1080);
    frame.getContentPane().setLayout(null);

    // ... Seus botões, inputs e imagens de fundo desenhados na escala de 1920x1080 ...

    // Chamada obrigatória no final para auto-ajuste e suporte ao Totem/Laptop
    GerenciadorJanelas.configurarJanela(frame);
}
```

---

## 2. Diálogos Flutuantes e Janelas Pequenas (`JDialog`)

Devem ser usadas para todas as janelas do tipo pop-up, modais e seletores de opções (ex: *Seletor de Pagamento*, *Tela de Recibo*, etc.). 
> **IMPORTANTE:** No Linux, gerenciadores de janela baseados em Wayland (como o Niri) forçam `JFrame` sem decoração a maximizar automaticamente. Usar `JDialog` garante que a janela permaneça flutuante e no tamanho correto.

### 📝 Padrão de Código:
* **Herança:** A classe deve estender `JDialog` em vez de encapsular um `JFrame`.
* **Construtor:** Deve receber o `JFrame parent` como parâmetro e repassá-lo na chamada do `super()` configurando como modal (`true`).
* **Tamanho Fixo:** Configurar o tamanho exato da janela (geralmente `500` por `400` para bater com o asset `fundopagamento.png`).
* **Sem Escala Dinâmica:** **NÃO** deve ser chamada a classe `GerenciadorJanelas`.
* **Centralização:** Usar `setLocationRelativeTo(parent)` para centralizar a janelinha sobre a tela mãe.

### 💻 Exemplo Prático:
```java
public class SuaTelaDialog extends JDialog {

    public SuaTelaDialog(JFrame parent, String titulo) {
        // Passa o parent e define modal como true
        super(parent, titulo, true); 
        
        setSize(500, 400); // Tamanho correspondente ao seu asset de fundo
        setUndecorated(true); // Remove a barra do SO
        setLocationRelativeTo(parent); // Centraliza sobre a janela pai
        getContentPane().setLayout(null);

        // ... Seus botões e labels ...

        setVisible(true);
    }
}
```

---

## 3. Legibilidade, Cores e Contraste

* **Contraste de Fundo Escuro:** As telas que utilizarem fundos escuros (como o asset `fundopagamento.png`) **devem obrigatoriamente** ter a cor do texto de seus `JLabel` configurada para branco:
  ```java
  suaLabel.setForeground(Color.WHITE);
  ```
  *(Evitar cores como o azul marinho `new Color(14, 23, 49)` sobre fundos escuros, pois inviabilizam a leitura no terminal touch).*

---

## 4. Gerenciamento e Carregamento de Assets

* **Independência de Caminhos Absolutos:** Nunca use caminhos locais como `C:/usuarios/...` para imagens.
* **Pasta de Imagens:** Todas as imagens devem ser salvas dentro de `src/imagens/` (que é empacotada no build do projeto).
* **Carregamento via Resource:** Carregue as imagens dinamicamente:
  ```java
  new ImageIcon(SuaClasse.class.getResource("/imagens/nome_da_imagem.png"))
  ```
