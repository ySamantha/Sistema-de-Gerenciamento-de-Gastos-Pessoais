package command;

import db.DatabaseConnection;
import model.Despesa;

import java.sql.PreparedStatement;

public class RegistrarDespesaCommand implements Command {
    private final Despesa despesa;

    public RegistrarDespesaCommand(Despesa despesa) {
        this.despesa = despesa;
    }

    public void execute() {
        try {
            var conn = DatabaseConnection.getInstance().getConnection();
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
