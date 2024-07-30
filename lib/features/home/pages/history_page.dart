import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:pontoaponto/features/home/widgets/section.dart';

class HistoryPage extends StatefulWidget {
  const HistoryPage({super.key});

  @override
  State<HistoryPage> createState() => _HistoryPageState();
}

class _HistoryPageState extends State<HistoryPage> {
  int selectedIndex = 0;
  final PageController controller = PageController(initialPage: 0);

  @override
  void initState() {
    super.initState();
    controller.addListener(() {
      if (controller.hasClients) {
        setState(() {
          selectedIndex = controller.page!.toInt();
        });
      }
    });
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  void selectIndex(int index) {
    controller.animateToPage(
      index,
      duration: const Duration(milliseconds: 300),
      curve: Curves.easeInOut,
    );
  }

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
            SizedBox(
              height: 50,
              child: ListView(
                shrinkWrap: true,
                scrollDirection: Axis.horizontal,
                children: [
                  RawChip(
                    label: Text(
                      "Todas",
                      style: TextStyle(
                        color: selectedIndex == 0 ? Colors.white : const Color(0xFF1F2024),
                      ),
                    ),
                    selected: selectedIndex == 0,
                    onSelected: (value) => selectIndex(value ? 0 : selectedIndex),
                    showCheckmark: false,
                  ),
                  const SizedBox(width: 10),
                  RawChip(
                    label: Text(
                      "Concluídas",
                      style: TextStyle(
                        color: selectedIndex == 1 ? Colors.white : const Color(0xFF1F2024),
                      ),
                    ),
                    selected: selectedIndex == 1,
                    onSelected: (value) => selectIndex(value ? 1 : selectedIndex),
                    showCheckmark: false,
                  ),
                  const SizedBox(width: 10),
                  RawChip(
                    label: Text(
                      "Canceladas",
                      style: TextStyle(
                        color: selectedIndex == 2 ? Colors.white : const Color(0xFF1F2024),
                      ),
                    ),
                    selected: selectedIndex == 2,
                    onSelected: (value) => selectIndex(value ? 2 : selectedIndex),
                    showCheckmark: false,
                  ),
                  const SizedBox(width: 10),
                  RawChip(
                    label: Text(
                      "Planejadas",
                      style: TextStyle(
                        color: selectedIndex == 3 ? Colors.white : const Color(0xFF1F2024),
                      ),
                    ),
                    selected: selectedIndex == 3,
                    onSelected: (value) => selectIndex(value ? 3 : selectedIndex),
                    showCheckmark: false,
                  ),
                  const SizedBox(width: 10),
                ],
              ),
            ),
            const SizedBox(height: 10),
            SizedBox(
              width: double.infinity,
              height: 600,
              child: PageView(
                controller: controller,
                children: [
                  // Hoje
                  ListView(
                    shrinkWrap: true,
                    children: [Text("Hoje")],
                  ),
                  // Ontem
                  ListView(
                    shrinkWrap: true,
                    children: [Text("Ontem")],
                  ),
                  // Semana
                  ListView(
                    shrinkWrap: true,
                    children: [Text("Semana")],
                  ),
                  // Mês
                  ListView(
                    shrinkWrap: true,
                    children: [Text("Mês")],
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
