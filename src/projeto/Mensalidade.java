package projeto;

public class Mensalidade {
	
    private int id_mensalidade;
    private int id_loja;
    private double mensalidade;
    private String vencimento;
    private String data_pagamento;
    private String status;
    private String nome_loja;

    public int getid_mensalidade() {
        return id_mensalidade;
    }

    public void setid_mensalidade(int id_mensalidade) {
        this.id_mensalidade = id_mensalidade;
    }

    public int getid_loja() {
        return id_loja;
    }

    public void setid_loja(int id_loja) {
        this.id_loja = id_loja;
    }

    public double get_mensalidade() {
        return mensalidade;
    }

    public void set_mensalidade(double mensalidade) {
        this.mensalidade = mensalidade;
    }

    public String get_vencimento() {
        return vencimento;
    }

    public void set_vencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getdata_pagamento() {
        return data_pagamento;
    }

    public void setdata_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public String get_status() {
        return status;
    }

    public void set_status(String status) {
        this.status = status;
    }
    public String get_nome_loja() {
        return nome_loja;
    }

    public void set_nome_loja(String nome_loja) {
        this.nome_loja = nome_loja;
    }

}
