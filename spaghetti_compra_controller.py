# compra_controller.py

class CompraController:

    def calcular_total(self, request):
        try:
            valor = request.get("valor")
            tipo_cliente = request.get("tipo_cliente")  # NORMAL | VIP

            if valor is None or valor <= 0:
                return {"erro": "Valor inválido", "status_code": 400}

            total = valor

            # desconto VIP
            if tipo_cliente == "VIP":
                total = total * 0.9

            # desconto adicional
            if valor > 100:
                total = total * 0.95

            # valor mínimo
            if total < 10:
                total = 10

            return {
                "valor_original": valor,
                "tipo_cliente": tipo_cliente,
                "valor_final": round(total, 2),
                "status_code": 200
            }

        except Exception as e:
            return {
                "erro": str(e),
                "status_code": 500
            }