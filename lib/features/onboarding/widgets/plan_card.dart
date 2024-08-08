import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class PlanCard extends StatelessWidget {
  PlanCard({
    super.key,
    this.price = 9.90,
    required this.title,
    this.isSelected = false,
    this.onTap,
  });

  bool isSelected = false;
  final String title;
  final VoidCallback? onTap;
  final double price;
  final NumberFormat priceFormatter = NumberFormat.currency(locale: 'pt_BR', symbol: 'R\$');

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: OutlineInputBorder(
        borderRadius: BorderRadius.circular(8),
        borderSide: BorderSide(
          color: isSelected ? Theme.of(context).primaryColor : const Color(0xFFD4D6DD),
          width: 1,
        ),
      ),
      color: Colors.transparent,
      elevation: 0,
      child: InkWell(
        onTap: onTap,
        child: Padding(
          padding: const EdgeInsets.all(12.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  Visibility(
                    visible: isSelected,
                    maintainAnimation: true,
                    maintainState: true,
                    maintainSize: true,
                    child: AnimatedOpacity(
                      duration: const Duration(milliseconds: 300),
                      curve: Curves.fastOutSlowIn,
                      opacity: isSelected ? 1 : 0,
                      child: Icon(
                        Icons.check,
                        color: Theme.of(context).primaryColor,
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 5),
              Text(
                title,
                style: const TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 24,
                ),
              ),
              const SizedBox(height: 20),
              RichText(
                text: TextSpan(
                  text: priceFormatter.format(price),
                  children: const [
                    TextSpan(
                      text: " / semana",
                      style: TextStyle(
                        fontWeight: FontWeight.w300,
                        fontSize: 14,
                      ),
                    ),
                  ],
                  style: const TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 16,
                    color: Color(0xFF1F2024),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
