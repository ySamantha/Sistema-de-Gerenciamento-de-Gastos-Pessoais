package strategy;

import model.Despesa;

public interface CategorizadorStrategy {
    String categorizar(Despesa despesa);
}
