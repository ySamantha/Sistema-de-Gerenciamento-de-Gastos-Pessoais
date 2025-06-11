package observer;

public class NotificadorLembrete implements Notificador {
    @Override
    public void update(String mensagem) {
        System.out.println("🔔 Alerta: " + mensagem);
    }
}
