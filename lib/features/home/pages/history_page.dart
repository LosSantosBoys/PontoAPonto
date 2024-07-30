import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:pontoaponto/features/home/widgets/section.dart';

class HistoryPage extends StatelessWidget {
  const HistoryPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(24),
        child: Column(
          children: [
            Section(
              title: "Última viagem",
              padding: const EdgeInsets.symmetric(vertical: 10),
              action: IconButton(
                onPressed: () {},
                icon: const Icon(Icons.more_vert),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Mapa
                  Container(
                    height: 200,
                    width: double.infinity,
                    decoration: BoxDecoration(
                      color: Colors.grey[300],
                      borderRadius: BorderRadius.circular(10),
                    ),
                  ),
                  const SizedBox(height: 10),
                  // Informações
                  const Text(
                    "Endereço de origem",
                    style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 10),
                  Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      const Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text("R\$ 21.00", style: TextStyle(fontSize: 14)),
                          SizedBox(height: 3),
                          Text("24 de Abril - 12:00", style: TextStyle(fontSize: 14)),
                        ],
                      ),
                      const SizedBox(width: 10),
                      ElevatedButton(
                        onPressed: () {},
                        style: ElevatedButton.styleFrom(
                          elevation: 0,
                          backgroundColor: const Color(0xFFF8F9FE),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                            side: const BorderSide(color: Color(0xFFE8E9F1), width: 1),
                          ),
                        ),
                        child: const Row(
                          children: [
                            Text("Remarcar"),
                            SizedBox(width: 5),
                            Icon(Icons.replay),
                          ],
                        ),
                      ),
                    ],
                  )
                ],
              ),
            ),
            const SizedBox(height: 10),
            const Divider(endIndent: 48, indent: 48),
            const SizedBox(height: 10),
            DefaultTabController(
              length: 4,
              child: Column(
                children: [
                  const TabBar(
                    indicator: BoxDecoration(),
                    isScrollable: true,
                    tabs: [
                      Tab(child: Chip(label: Text("Todas"))),
                      Tab(child: Chip(label: Text("Concluídas"))),
                      Tab(child: Chip(label: Text("Canceladas"))),
                      Tab(child: Chip(label: Text("Planejadas"))),
                    ],
                  ),
                  const SizedBox(height: 10),
                  SizedBox(
                    height: 600,
                    child: TabBarView(
                      children: [
                        // Todas
                        ListView(
                          shrinkWrap: true,
                          children: [Text("Todas")],
                        ),
                        // Concluídas
                        ListView(
                          shrinkWrap: true,
                          children: [Text("Concluídas")],
                        ),
                        // Canceladas
                        ListView(
                          shrinkWrap: true,
                          children: [Text("Canceladas")],
                        ),
                        // Planejadas
                        ListView(
                          shrinkWrap: true,
                          children: [Text("Planejadas")],
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
