import 'package:flutter/material.dart';
import 'package:pontoaponto/core/widgets/custom_button.dart';
import 'package:pontoaponto/features/onboarding/widgets/plan_card.dart';

class PlanPage extends StatefulWidget {
  const PlanPage({super.key});

  @override
  State<PlanPage> createState() => _PlanPageState();
}

class _PlanPageState extends State<PlanPage> {
  String selected = "1 mês";

  Map<String, dynamic> featurePerPlan = {
    "gratuito": [
      "• Acesso a todas as funcionalidades",
      "• Suporte 24/7",
    ],
    "1 semana": [
      "• Acesso a todas as funcionalidades",
      "• Suporte 24/7",
      "• Sem anúncios",
      "• Viagens mais rápidas",
      "• Melhores motoristas",
    ],
    "1 mês": [
      "• Acesso a todas as funcionalidades",
      "• Suporte 24/7",
      "• Sem anúncios",
      "• Viagens mais rápidas",
      "• Melhores motoristas",
    ],
    "1 ano": [
      "• Acesso a todas as funcionalidades",
      "• Suporte 24/7",
      "• Sem anúncios",
      "• Viagens mais rápidas",
      "• Melhores motoristas",
    ],
  };

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              "Escolha o plano que melhor se encaixa com você.",
              style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 20),
            PlanCard(
              title: "Gratuito",
              isSelected: selected == "gratuito",
              price: 0,
              onTap: () {
                setState(() {
                  selected = "gratuito";
                });
              },
            ),
            const SizedBox(height: 5),
            PlanCard(
              title: "1 semana",
              isSelected: selected == "1 semana",
              price: 16.99,
              onTap: () {
                setState(() {
                  selected = "1 semana";
                });
              },
            ),
            const SizedBox(height: 5),
            PlanCard(
              title: "1 mês",
              price: 9.99,
              isSelected: selected == "1 mês",
              onTap: () {
                setState(() {
                  selected = "1 mês";
                });
              },
            ),
            const SizedBox(height: 5),
            PlanCard(
              title: "1 ano",
              price: 12.99,
              isSelected: selected == "1 ano",
              onTap: () {
                setState(() {
                  selected = "1 ano";
                });
              },
            ),
            const SizedBox(height: 20),
            const Text(
              "Recursos",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 20,
              ),
            ),
            const SizedBox(height: 10),
            ...featurePerPlan[selected].map<Widget>((feature) {
              return Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    feature,
                    style: const TextStyle(
                      fontSize: 16,
                    ),
                  ),
                  const SizedBox(height: 5),
                ],
              );
            }).toList(),
          ],
        ),
      ),
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(24.0),
        child: CustomButton(
          text: 'Continuar com $selected',
          onPressed: () {},
        ),
      ),
    );
  }
}
