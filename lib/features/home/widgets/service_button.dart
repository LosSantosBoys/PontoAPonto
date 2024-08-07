import 'package:flutter/material.dart';
import 'package:pontoaponto/features/home/enum/service_enum.dart';

class ServiceButton extends StatelessWidget {
  ServiceButton({
    super.key,
    required this.service,
    this.enabled = true,
  });

  final ServiceEnum service;
  bool enabled;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: enabled ? () {} : null,
      borderRadius: BorderRadius.circular(10),
      child: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Builder(builder: (context) {
              IconData iconData;

              switch (service) {
                case ServiceEnum.deliveries:
                  iconData = Icons.delivery_dining;
                  break;
                case ServiceEnum.flights:
                  iconData = Icons.airplanemode_active;
                  break;
                case ServiceEnum.offers:
                  iconData = Icons.local_offer;
                  break;
                case ServiceEnum.public:
                  iconData = Icons.directions_bus;
                  break;
                case ServiceEnum.rides:
                  iconData = Icons.directions_car;
                  break;
                case ServiceEnum.rentals:
                default:
                  iconData = Icons.car_rental;
              }

              return Icon(
                iconData,
                size: 36,
                color: enabled ? const Color(0xFF3755C1) : const Color(0xFFD4D6DD),
              );
            }),
            const SizedBox(height: 10),
            Builder(builder: (context) {
              String text;

              switch (service) {
                case ServiceEnum.deliveries:
                  text = "Entregas";
                  break;
                case ServiceEnum.flights:
                  text = "Voos";
                  break;
                case ServiceEnum.offers:
                  text = "Ofertas";
                  break;
                case ServiceEnum.public:
                  text = "Transport Público";
                  break;
                case ServiceEnum.rides:
                  text = "Corridas";
                  break;
                case ServiceEnum.rentals:
                default:
                  text = "Aluguel";
              }

              return Text(
                text,
                style: TextStyle(
                  fontSize: 14,
                  color: enabled ? const Color(0xFF3755C1) : const Color(0xFFD4D6DD),
                ),
              );
            }),
          ],
        ),
      ),
    );
  }
}
