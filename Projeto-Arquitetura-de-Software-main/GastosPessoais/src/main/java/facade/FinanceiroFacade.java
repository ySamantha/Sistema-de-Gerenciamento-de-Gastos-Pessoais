package facade;

import command.RegistrarDespesaCommand;
import model.Despesa;
import observer.Orcamento;
import strategy.CategorizadorStrategy;

public class FinanceiroFacade {
    private CategorizadorStrategy estrategia;
    private Orcamento orcamento;

    public FinanceiroFacade(CategorizadorStrategy estrategia, Orcamento orcamento) {
        this.estrategia = estrategia;
        this.orcamento = orcamento;
    }

    public void registrarDespesa(double valor, String descricao) {
        String categoria = estrategia.categorizar(new Despesa(valor, "", descricao));
        Despesa despesa = new Despesa(valor, categoria, descricao);
        new RegistrarDespesaCommand(despesa).execute();
        orcamento.atualizarOrcamento(valor);
    }
}
