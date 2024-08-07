import 'package:flutter/material.dart';
import 'package:pontoaponto/core/util/util.dart';
import 'package:pontoaponto/features/auth/enum/social_enum.dart';

class SocialButton extends StatelessWidget {
  const SocialButton({super.key, required this.type, this.enabled = true});

  final SocialEnum type;
  final bool enabled;

  @override
  Widget build(BuildContext context) {
    return TextButton(
      onPressed: enabled
          ? () async {
              switch (type) {
                case SocialEnum.google:
                  // TODO: Implementar login com Google
                  break;
                case SocialEnum.meta:
                // TODO: Implementar login com Meta
                default:
                  return;
              }
            }
          : null,
      style: TextButton.styleFrom(
        minimumSize: const Size(double.infinity, 44),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(8),
          side: const BorderSide(
            color: Color(0xFFD9D9D9),
            width: 1,
          ),
        ),
      ),
      child: Row(
        children: [
          Image(
            image: AssetImage('assets/icons/${type.name}.png'),
            color: enabled ? null : Colors.grey,
            semanticLabel: "Ícone da empresa ${type.name.capitalize()}",
          ),
          const Spacer(),
          Text(
            "Entrar com ${type.name.capitalize()}",
            style: TextStyle(
              color: enabled ? null : Colors.grey,
            ),
          ),
          const Spacer(),
        ],
      ),
    );
  }
}
