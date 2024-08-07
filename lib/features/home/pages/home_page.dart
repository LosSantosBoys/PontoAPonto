import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';
import 'package:pontoaponto/core/util/util.dart';
import 'package:pontoaponto/features/home/enum/service_enum.dart';
import 'package:pontoaponto/features/home/widgets/section.dart';
import 'package:pontoaponto/features/home/widgets/service_button.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  String formatDateTime(DateTime date) {
    DateFormat formatter = DateFormat('EEEE, d \'de\' MMMM', 'pt_BR');
    return formatter.format(date).capitalize();
  }

  Future<String> getCity() async {
    bool serviceEnabled;
    LocationPermission permission;

    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Serviço de localização desativado.');
    }

    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();

      if (permission == LocationPermission.denied) {
        return Future.error('Permissão de localização negada.');
      }
    }

    if (permission == LocationPermission.deniedForever) {
      return Future.error('Permissão de localização negada permanentemente. Abra as configurações do app para permitir a localização.');
    }

    Position position = await Geolocator.getCurrentPosition();

    try {
      List<Placemark> placemarks = await placemarkFromCoordinates(position.latitude, position.longitude);
      return placemarks[0].subAdministrativeArea ?? placemarks[0].administrativeArea ?? placemarks[0].country ?? 'Desconhecido';
    } catch (e) {
      return Future.error('Erro ao obter a cidade.');
    }
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF8F9FE),
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: const Text('Boa tarde, Usuário'),
        actions: [
          IconButton(
            onPressed: () {},
            icon: const Icon(Icons.notifications_none),
          ),
        ],
      ),
      body: Column(
        children: [
          Section(
            padding: const EdgeInsets.symmetric(horizontal: 24),
            title: "Clima e Tempo",
            action: IconButton(
              onPressed: () {},
              icon: const Icon(Icons.more_vert),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  children: [
                    const Text("24°C", style: TextStyle(fontSize: 23)),
                    const SizedBox(width: 15),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(formatDateTime(DateTime.now()), style: const TextStyle(fontSize: 14)),
                        FutureBuilder(
                          future: getCity(),
                          builder: (BuildContext context, AsyncSnapshot snapshot) {
                            if (snapshot.connectionState == ConnectionState.waiting) {
                              return const Row(
                                children: [
                                  Icon(Icons.place, size: 14, color: Colors.grey),
                                  SizedBox(width: 3),
                                  Text("Carregando...", style: const TextStyle(fontSize: 14)),
                                ],
                              );
                            } else if (snapshot.hasError) {
                              WidgetsBinding.instance.addPostFrameCallback(
                                (_) => context.showErrorSnackbar(snapshot.error.toString()),
                              );

                              return const Text("Desconhecido", style: TextStyle(fontSize: 14));
                            } else {
                              return Row(
                                children: [
                                  const Icon(Icons.place, size: 14, color: Colors.red),
                                  const SizedBox(width: 3),
                                  Text(snapshot.data.toString(), style: const TextStyle(fontSize: 14)),
                                ],
                              );
                            }
                          },
                        ),
                      ],
                    ),
                  ],
                ),
                const Icon(
                  Icons.cloud,
                  size: 36,
                  color: Color(0xFF3755C1),
                ),
              ],
            ),
          ),
          const SizedBox(height: 10),
          Section(
            padding: const EdgeInsets.symmetric(horizontal: 24),
            title: "Serviços Principais",
            action: RichText(
              text: TextSpan(
                text: "ver todos",
                style: const TextStyle(color: Colors.blue),
                recognizer: TapGestureRecognizer()
                  ..onTap = () {
                    // TODO: redirecionar para a tela de serviços
                  },
              ),
            ),
            child: Wrap(
              spacing: 16,
              runSpacing: 10,
              children: [
                ServiceButton(service: ServiceEnum.rentals, enabled: false),
                ServiceButton(service: ServiceEnum.deliveries, enabled: false),
                ServiceButton(service: ServiceEnum.flights, enabled: false),
                ServiceButton(service: ServiceEnum.rides),
                ServiceButton(service: ServiceEnum.public, enabled: false),
                const SizedBox(width: 36),
                ServiceButton(service: ServiceEnum.offers, enabled: false),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
