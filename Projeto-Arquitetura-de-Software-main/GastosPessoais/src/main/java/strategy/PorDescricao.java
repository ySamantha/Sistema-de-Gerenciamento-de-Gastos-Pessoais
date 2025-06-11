package strategy;

import model.Despesa;

public class PorDescricao implements CategorizadorStrategy {
    @Override
    public String categorizar(Despesa despesa) {
        if (despesa.descricao.toLowerCase().contains("supermercado")) {
            return "Alimentação";
        } else if (despesa.descricao.toLowerCase().contains("posto")) {
            return "Transporte";
        }
        return "Outros";
    }
}
