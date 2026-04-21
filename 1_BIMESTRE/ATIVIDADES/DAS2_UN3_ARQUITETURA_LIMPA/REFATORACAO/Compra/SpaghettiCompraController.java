import java.util.HashMap;
import java.util.Map;

public class SpaghettiCompraController {

    public Map<String, Object> calcularTotal(Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            Double valor = (Double) request.get("valor");
            String tipoCliente = (String) request.get("tipo_cliente"); // NORMAL | VIP

            if (valor == null || valor <= 0) {
                response.put("erro", "Valor inválido");
                response.put("status_code", 400);
                return response;
            }

            double total = valor;

            // desconto VIP
            if ("VIP".equals(tipoCliente)) {
                total = total * 0.9;
            }

            // desconto adicional
            if (valor > 100) {
                total = total * 0.95;
            }

            // valor mínimo
            if (total < 10) {
                total = 10;
            }

            response.put("valor_original", valor);
            response.put("tipo_cliente", tipoCliente);
            response.put("valor_final", Math.round(total * 100.0) / 100.0);
            response.put("status_code", 200);

            return response;

        } catch (Exception e) {
            response.put("erro", e.getMessage());
            response.put("status_code", 500);
            return response;
        }
    }
}