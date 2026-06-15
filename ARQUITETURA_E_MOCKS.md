# 🏗️ Galpax_26: Arquitetura e Mocks

Este documento detalha a reengenharia estrutural aplicada ao projeto Galpax. O objetivo foi tornar o software agnóstico em relação à resolução de tela, seguro para demonstrações sem banco de dados ativo (Modo Offline) e visualmente coeso.

## 1. Mock Universal e Proxy Dinâmico
A dependência do MySQL foi isolada na classe `projeto.Bancodedados`. Implementamos um **Mock Universal** ativado por padrão (`isMock = true`) para permitir o desenvolvimento mesmo sem um servidor de banco de dados rodando.

### A Mágica: `ResultSet` Simulado (Reflection)
Para não quebrar os `JTables` e lógicas que esperavam um objeto `ResultSet`, utilizamos um **Proxy Dinâmico**. O Java "simula" um objeto ResultSet em tempo de execução, alimentado por dados em memória (Listas e Maps).

```java
// Exemplo da lógica na Bancodedados.java
private ResultSet createMockResultSet(List<Map<String, Object>> data) {
    mockIndex = -1;
    return (ResultSet) Proxy.newProxyInstance(
        ResultSet.class.getClassLoader(),
        new Class[] { ResultSet.class },
        (proxy, method, args) -> {
            String methodName = method.getName();
            if (methodName.equals("next")) {
                mockIndex++;
                return mockIndex < data.size();
            }
            if (methodName.equals("getString")) {
                String col = (String) args[0];
                return String.valueOf(data.get(mockIndex).get(col));
            }
            return null;
        }
    );
}
```

## 2. Escala Dinâmica (`GerenciadorJanelas.java`)
O `GerenciadorJanelas.java` atua como controlador global de interface, permitindo que o sistema rode tanto em modo **Totem (Full Screen 1080p)** quanto em modo **Laptop (Janela)**.

### Lógica de Aspect Ratio (16:9)
O sistema calcula a escala comparando a tela atual com a resolução base (1920x1080).
1.  **HQ Rendering:** Imagens e botões usam interpolação bicúbica para não pixelizar.
2.  **Letterbox:** Garante que a interface preencha o espaço sem distorcer os elementos.

## 3. Atalhos de Desenvolvedor (`Login.java`)
Atalhos injetados para agilizar os testes durante a aula:

| Atalho | Ação Executada | Uso Prático |
| :--- | :--- | :--- |
| **`Ctrl + A`** | Preenche `admin` / `admin`. | Login rápido no Painel Administrativo. |
| **`Ctrl + U`** | Preenche `usuario@galpax.com` / `123`. | Login rápido no Painel de Lojista. |
| **`Ctrl + W`** | Alterna entre Janela e Tela Cheia. | Teste de escala em tempo real. |

---
**Status da Integração:**
Módulos financeiros e operacionais estão integrados ao núcleo unificado, herdando automaticamente o gerenciamento de janelas e o sistema de mocks.
