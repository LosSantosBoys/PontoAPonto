import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:pontoaponto/features/home/enum/history_status_enum.dart';
import 'package:pontoaponto/features/home/enum/history_type_enum.dart';

class HistoryTile extends StatelessWidget {
  HistoryTile({
    super.key,
    required this.address,
    required this.price,
    required this.status,
    required this.date,
    required this.type,
  });

  final String address;
  final double price;
  final HistoryStatus status;
  final DateTime date;
  final HistoryType type;

  final Map<HistoryStatus, Color> statusColors = {
    HistoryStatus.cancelled: Colors.red,
    HistoryStatus.planned: Colors.orange,
    HistoryStatus.done: Colors.green,
  };
  final Map<HistoryStatus, String> statusLabels = {
    HistoryStatus.cancelled: 'cancelada',
    HistoryStatus.planned: 'planejada',
    HistoryStatus.done: 'concluída',
  };
  final Map<HistoryType, IconData> typeIcons = {
    HistoryType.rental: Icons.car_rental,
    HistoryType.plane: Icons.flight,
    HistoryType.trip: Icons.directions_car,
    HistoryType.delivery: Icons.local_shipping,
    HistoryType.publicTransport: Icons.airport_shuttle,
  };
  final NumberFormat priceFormatter = NumberFormat.currency(locale: 'pt_BR', symbol: 'R\$');

  @override
  Widget build(BuildContext context) {
    return ListTile(
      contentPadding: const EdgeInsets.symmetric(horizontal: 24),
      leading: Container(
        height: 56,
        width: 56,
        decoration: BoxDecoration(
          color: Colors.transparent,
          borderRadius: BorderRadius.circular(100),
          border: Border.all(
            color: const Color(0xFFE8E9F1),
            width: 1,
          ),
        ),
        child: Icon(
          typeIcons[type],
          color: Theme.of(context).primaryColor,
        ),
      ),
      title: Padding(
        padding: const EdgeInsets.only(bottom: 5),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              address,
              style: const TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
            Text(
              priceFormatter.format(price),
              style: const TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
      subtitle: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            DateFormat('dd \'de\' MMMM - HH:mm').format(date),
            style: const TextStyle(
              color: Colors.grey,
            ),
          ),
          Text(
            statusLabels[status]!,
            style: TextStyle(
              color: statusColors[status],
            ),
          ),
        ],
      ),
      onTap: () {},
    );
  }
}
