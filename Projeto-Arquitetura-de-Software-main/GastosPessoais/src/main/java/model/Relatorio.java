package model;

import db.DatabaseConnection;
import observer.Orcamento;

import java.sql.Connection;
import java.sql.ResultSet;

public class Relatorio {
    private Orcamento orcamento;

    public Relatorio(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public void mostrar() {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM despesas");

            System.out.println("\n--- Despesas Registradas ---");
            while (rs.next()) {
                int id = rs.getInt("id");
                double valor = rs.getDouble("valor");
                String categoria = rs.getString("categoria");
                String descricao = rs.getString("descricao");
                System.out.printf("[%d] R$ %.2f | %s | %s\n", id, valor, categoria, descricao);
            }

            double renda = orcamento.getRenda();
            double saldo = orcamento.getSaldo();
            double saudeFinanceira = (renda > 0) ? (saldo / renda) * 100 : 0;

            System.out.println("\n--- Saude Financeira ---");
            System.out.printf("Renda: R$ %.2f\n", renda);
            System.out.printf("Saldo: R$ %.2f\n", saldo);
            System.out.printf("Porcentagem usada: %.2f%%\n", 100 - saudeFinanceira);

            if (saudeFinanceira <= 0) {
                System.out.println("Aviso: Voce ultrapassou sua renda mensal!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apagarTudo() {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            var stmt = conn.createStatement();
            stmt.execute("DELETE FROM despesas");
            System.out.println("Todas as despesas foram apagadas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
