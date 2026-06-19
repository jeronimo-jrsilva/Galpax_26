package projeto;

import java.util.List;

public class TelaPagamento {

    public static void main(String[] args) {

        Bancodedados bd =
                new Bancodedados();

        bd.conectar();

        if(bd.verificar()) {

            System.out.println("=== MENSALIDADES ===");

            List<Mensalidade> lista =
                    bd.listarMensalidades();

            for(Mensalidade m : lista) {

                System.out.println(
                        "ID: " +
                        m.getid_mensalidade()

                        + " | Loja: " +
                        m.get_nome_loja()

                        + " | Valor: R$ " +
                        m.get_mensalidade()

                        + " | Status: " +
                        m.get_status()
                );
            }

            bd.desconectar();
        }
    }
}