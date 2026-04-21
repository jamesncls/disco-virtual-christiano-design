import java.util.HashMap;
import java.util.Map;

public class FreteController {

    public Map<String, Object> calcularFrete(Map<String, Object> request) {
        try {
            Double peso = (Double) request.get("peso");
            Double distancia = (Double) request.get("distancia");
            String tipoCliente = (String) request.get("tipo_cliente"); // NORMAL ou VIP

            // validação misturada no controller
            if (peso == null || peso <= 0) {
                Map<String, Object> erro = new HashMap<>();
                erro.put("erro", "Peso inválido");
                erro.put("status_code", 400);
                return erro;
            }

            if (distancia == null || distancia <= 0) {
                Map<String, Object> erro = new HashMap<>();
                erro.put("erro", "Distância inválida");
                erro.put("status_code", 400);
                return erro;
            }

            // regra de negócio misturada
            double valorBase = 10.0;
            double valorPeso;

            if (peso <= 1) {
                valorPeso = 5.0;
            } else if (peso <= 5) {
                valorPeso = 10.0;
            } else {
                valorPeso = 20.0;
            }

            double valorDistancia = distancia * 0.5;
            double total = valorBase + valorPeso + valorDistancia;

            // desconto misturado no controller
            if ("VIP".equalsIgnoreCase(tipoCliente)) {
                total = total * 0.8;
            }

            // regra adicional perdida no meio do código
            if (total < 15) {
                total = 15;
            }

            // resposta montada no mesmo lugar
            Map<String, Object> response = new HashMap<>();
            response.put("peso", peso);
            response.put("distancia", distancia);
            response.put("tipo_cliente", tipoCliente);
            response.put("valor_frete", Math.round(total * 100.0) / 100.0);
            response.put("status_code", 200);

            return response;

        } catch (Exception e) {
            Map<String, Object> erro = new HashMap<>();
            erro.put("erro", e.getMessage());
            erro.put("status_code", 500);
            return erro;
        }
    }
}