package db;

import model.Despesa;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DespesaDAO {
    public void salvar(Despesa despesa) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO despesas (valor, categoria, descricao) VALUES (?, ?, ?)"
            );
            stmt.setDouble(1, despesa.valor);
            stmt.setString(2, despesa.categoria);
            stmt.setString(3, despesa.descricao);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
