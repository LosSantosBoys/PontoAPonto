import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'package:intl/intl.dart';
import 'package:pontoaponto/core/util/util.dart';
import 'package:pontoaponto/features/home/enum/service_enum.dart';
import 'package:pontoaponto/features/home/pages/history_page.dart';
import 'package:pontoaponto/features/home/widgets/section.dart';
import 'package:pontoaponto/features/home/widgets/service_button.dart';
import 'package:weather/weather.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  WeatherFactory wf = WeatherFactory(dotenv.env['WEATHER_API_KEY']!, language: Language.PORTUGUESE_BRAZIL);
  double temperature = 0;
  String weatherIcon = '01d';
  late PageController _pageController;
  int currentPage = 0;

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

      String place = placemarks[0].subAdministrativeArea ?? placemarks[0].administrativeArea ?? placemarks[0].country ?? 'Desconhecido';
      Weather w = await wf.currentWeatherByLocation(position.latitude, position.longitude);
      temperature = w.temperature?.celsius ?? 0;
      weatherIcon = w.weatherIcon ?? '01d';

      return place;
    } catch (e) {
      return Future.error('Erro ao obter a cidade.');
    }
  }

  @override
  void initState() {
    super.initState();
    _pageController = PageController();
  }

  @override
  void dispose() {
    _pageController.dispose();
    super.dispose();
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
      body: PageView(
        padEnds: false,
        pageSnapping: true,
        controller: _pageController,
        onPageChanged: (index) {
          setState(() {
            currentPage = index;
          });
        },
        children: [
          Column(
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
                        Text("${temperature.toStringAsFixed(1)}°C", style: const TextStyle(fontSize: 23)),
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
                                      Text("Carregando...", style: TextStyle(fontSize: 14)),
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
                                      Text(
                                        snapshot.data.toString(),
                                        style: const TextStyle(fontSize: 14),
                                        overflow: TextOverflow.ellipsis,
                                      ),
                                    ],
                                  );
                                }
                              },
                            ),
                          ],
                        ),
                      ],
                    ),
                    CachedNetworkImage(
                      imageUrl: "https://openweathermap.org/img/wn/$weatherIcon@2x.png",
                      width: 36,
                      height: 36,
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 10),
              Section(
                padding: const EdgeInsets.symmetric(horizontal: 24),
                title: "Serviços Principais",
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
          const HistoryPage(),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        backgroundColor: Colors.white,
        selectedItemColor: const Color(0xFF3755C1),
        iconSize: 28,
        unselectedItemColor: const Color(0xFF71727A),
        selectedLabelStyle: const TextStyle(fontSize: 14, fontWeight: FontWeight.bold),
        unselectedLabelStyle: const TextStyle(fontSize: 14),
        showSelectedLabels: true,
        showUnselectedLabels: true,
        currentIndex: currentPage,
        onTap: (int index) {
          _pageController.animateToPage(
            index,
            duration: const Duration(milliseconds: 300),
            curve: Curves.easeInOut,
          );
        },
        items: const [
          BottomNavigationBarItem(
            icon: Icon(Icons.home_outlined),
            activeIcon: Icon(Icons.home),
            label: "Início",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.receipt_outlined),
            activeIcon: Icon(Icons.receipt),
            label: "Atividade",
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person_outline),
            activeIcon: Icon(Icons.person),
            label: "Perfil",
          ),
        ],
      ),
    );
  }
}
