# ⌨️ Guia de Uso do Teclado Virtual Universal

Este documento explica a arquitetura do teclado virtual centralizado do projeto Galpax_26 e como integrá-lo a novas telas.

## 1. Visão Geral da Arquitetura

Para evitar a repetição de código e garantir um comportamento consistente, o teclado virtual foi refatorado em um sistema modular e centralizado. Ele é composto por três classes principais que trabalham juntas:

### a. `GerenciadorTeclado.java` (O Cérebro)
- **Função:** É o controlador central que gerencia todo o sistema.
- **Padrão Singleton:** Existe apenas **uma** instância desta classe em toda a aplicação, garantindo um único ponto de controle.
- **Responsabilidades:**
    - Manter uma lista de todos os campos de texto (`JTextField`, `JPasswordField`) que podem usar o teclado.
    - Saber qual campo de texto está atualmente em foco (`campoAtivo`).
    - Controlar a visibilidade do teclado (mostrar/esconder).

### b. `TecladoUI.java` (O Corpo)
- **Função:** É a representação visual do teclado.
- **`JDialog` Flutuante:** É um diálogo que flutua sobre a tela ativa, posicionado na parte inferior, sem interferir no layout da janela principal.
- **Responsabilidades:**
    - Conter os painéis de teclado (alfanumérico e numérico).
    - Trocar entre os layouts de teclado (letras ou números).

### c. `confteclado.java` (O Construtor de Painéis)
- **Função:** É a classe que efetivamente constrói os painéis com os botões.
- **Design Centralizado:** O design visual dos botões (a classe `BotaoTeclado`) e a disposição das teclas estão definidos aqui. Qualquer alteração visual no teclado é feita nesta classe.
- **Responsabilidades:**
    - Criar os `JPanels` para o teclado completo e numérico.
    - Definir a ação de cada botão, que agora se comunica com o `GerenciadorTeclado` para enviar texto ao campo ativo.

## 2. Como Adicionar o Teclado a uma Nova Tela

Integrar o teclado em uma nova `JFrame` ou `JDialog` agora é um processo extremamente simples.

### Passo 1: Inicialize o Gerenciador

No construtor da sua tela, logo após inicializar o `JFrame` principal, adicione a seguinte linha. Isso "conecta" a sua tela ao sistema de teclado.

```java
public class MinhaNovaTela {
    private JFrame frame;
    private JTextField meuCampoDeTexto;

    public MinhaNovaTela() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        // ... configurações do seu frame ...

        // INICIALIZA O GERENCIADOR DE TECLADO
        GerenciadorTeclado.getInstance().inicializar(frame);

        // ... resto do seu código ...
    }
}
```

### Passo 2: Registre os Campos de Texto

Para cada `JTextField` ou `JPasswordField` que deve acionar o teclado, adicione a seguinte linha logo após a criação do componente:

```java
// ... dentro do seu método initialize() ...

meuCampoDeTexto = new JTextField();
meuCampoDeTexto.setBounds(10, 10, 200, 25);
frame.getContentPane().add(meuCampoDeTexto);

// REGISTRA O CAMPO NO GERENCIADOR
GerenciadorTeclado.getInstance().registrarCampo(meuCampoDeTexto);

// ---

meuOutroCampo = new JTextField();
meuOutroCampo.setBounds(10, 40, 200, 25);
frame.getContentPane().add(meuOutroCampo);

// REGISTRA O OUTRO CAMPO
GerenciadorTeclado.getInstance().registrarCampo(meuOutroCampo);
```

**Pronto!** Apenas com esses dois passos, seus campos de texto agora usarão o teclado universal, com o comportamento de aparecer/desaparecer automaticamente e com o design padrão do projeto. Não é mais necessário criar painéis, listeners de mouse ou métodos de ajuda em cada tela.
