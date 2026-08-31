package pe.biblioteca.prestamos.factory;


public class MensajeNotificacionFactory {

    public static String crearMensaje(String estado, Long ejemplarId, String nombreSocio) {
        return switch (estado) {
            case "REGISTRADA" -> "Hola " + nombreSocio + ", tu préstamo del ejemplar #"
                    + ejemplarId + " ha sido registrado exitosamente.";
            case "RECHAZADA" -> "Hola " + nombreSocio + ", lamentablemente no se pudo registrar "
                    + "el préstamo del ejemplar #" + ejemplarId + ".";
            case "DEVUELTO" -> "Hola " + nombreSocio + ", la devolución del ejemplar #"
                    + ejemplarId + " ha sido registrada. ¡Gracias!";
            default -> "Notificación sobre el ejemplar #" + ejemplarId + ".";
        };
    }
}
