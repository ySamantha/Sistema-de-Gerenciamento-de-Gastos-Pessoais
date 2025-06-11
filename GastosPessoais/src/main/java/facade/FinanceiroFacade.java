package facade;

import db.DespesaDAO;
import model.Despesa;
import observer.Orcamento;
import strategy.CategorizadorStrategy;

public class FinanceiroFacade {
    private CategorizadorStrategy estrategia;
    private Orcamento orcamento;
    private DespesaDAO despesaDAO;

    public FinanceiroFacade(CategorizadorStrategy estrategia, Orcamento orcamento) {
        this.estrategia = estrategia;
        this.orcamento = orcamento;
        this.despesaDAO = new DespesaDAO();
    }

    public void registrarDespesa(double valor, String descricao) {
       Despesa despesa = new Despesa(valor, "", descricao);
       String categoria = estrategia.categorizar(despesa);
       despesa.setCategoria(categoria);
       despesaDAO.salvar(despesa);
       orcamento.atualizarOrcamento(valor);
    }
}
