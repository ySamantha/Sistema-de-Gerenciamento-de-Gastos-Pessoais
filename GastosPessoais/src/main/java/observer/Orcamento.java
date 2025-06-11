package observer;

import java.util.ArrayList;
import java.util.List;

public class Orcamento {
    private double limite = 100.0;
    private double totalGasto = 0.0;
    private double renda = 0.0;
    private List<Notificador> notificadores = new ArrayList<>();

    private boolean avisou70Porcento = false;
    private boolean avisouUltrapassouLimite = false;
    private boolean avisouUltrapassouRenda = false;

    public void adicionarNotificador(Notificador n) {
        notificadores.add(n);
    }

    public void atualizarOrcamento(double novoGasto) {
        totalGasto += novoGasto;

        if (renda > 0) {
            if (!avisou70Porcento && totalGasto > renda * 0.7 && totalGasto <= renda) {
                notificar("Voce ja gastou mais de 70% da sua renda. Considere reduzir gastos.");
                avisou70Porcento = true;
            }

            if (!avisouUltrapassouRenda && totalGasto > renda) {
                notificar("Voce gastou mais do que a sua renda mensal!");
                avisouUltrapassouRenda = true;
            }
        }

        if (!avisouUltrapassouLimite && totalGasto > limite) {
            notificar("Voce ultrapassou seu limite de R$ " + limite);
            avisouUltrapassouLimite = true;
        }
    }

    private void notificar(String mensagem) {
        for (Notificador n : notificadores) {
            n.update(mensagem);
        }
    }

    public void setLimite(double novoLimite) {
        this.limite = novoLimite;
    }

    public void resetarGasto() {
        this.totalGasto = 0.0;
        avisou70Porcento = false;
        avisouUltrapassouLimite = false;
        avisouUltrapassouRenda = false;
    }
    
    public double getTotalGasto() {
        return totalGasto;
    }


    public void setRenda(double renda) {
        this.renda = renda;
    }

    public double getRenda() {
        return this.renda;
    }

    public double getLimiteSugerido() {
        return renda * 0.7;
    }

    public double getSaldo() {
        return renda - totalGasto;
    }
}
