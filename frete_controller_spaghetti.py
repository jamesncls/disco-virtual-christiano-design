# frete_controller.py

class FreteController:

    def calcular_frete(self, request):
        try:
            peso = request.get("peso")
            distancia = request.get("distancia")
            tipo_cliente = request.get("tipo_cliente")  # NORMAL | VIP

            # validação misturada
            if peso is None or peso <= 0:
                return {"erro": "Peso inválido", "status_code": 400}

            if distancia is None or distancia <= 0:
                return {"erro": "Distância inválida", "status_code": 400}

            # regra de negócio misturada
            valor_base = 10

            if peso <= 1:
                valor_peso = 5
            elif peso <= 5:
                valor_peso = 10
            else:
                valor_peso = 20

            valor_distancia = distancia * 0.5

            total = valor_base + valor_peso + valor_distancia

            # regra de desconto misturada
            if tipo_cliente == "VIP":
                total = total * 0.8  # 20% desconto

            # regra adicional perdida no meio
            if total < 15:
                total = 15  # frete mínimo

            # resposta montada aqui
            return {
                "peso": peso,
                "distancia": distancia,
                "tipo_cliente": tipo_cliente,
                "valor_frete": round(total, 2),
                "status_code": 200
            }

        except Exception as e:
            return {
                "erro": str(e),
                "status_code": 500
            }