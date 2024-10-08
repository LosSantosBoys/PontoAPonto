import 'package:flutter/material.dart';

class Section extends StatelessWidget {
  const Section({
    super.key,
    this.title,
    required this.child,
    this.titleWidget,
    this.action,
    this.padding = const EdgeInsets.symmetric(horizontal: 24, vertical: 10),
  });

  final String? title;
  final Widget? titleWidget;
  final Widget? action;
  final Widget child;
  final EdgeInsets padding;

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      padding: padding,
      child: Column(
        children: [
          if (titleWidget != null)
            titleWidget!
          else
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 10),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    title ?? "",
                    style: const TextStyle(
                      fontSize: 18,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  if (action != null) action!,
                ],
              ),
            ),
          const SizedBox(height: 5),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 10),
            child: child,
          ),
        ],
      ),
    );
  }
}
