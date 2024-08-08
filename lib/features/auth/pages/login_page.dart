import 'package:flutter/material.dart';
import 'package:pontoaponto/core/widgets/custom_button.dart';
import 'package:pontoaponto/features/auth/enum/social_enum.dart';
import 'package:pontoaponto/features/auth/widgets/social_button.dart';

class LoginPage extends StatelessWidget {
  const LoginPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(24),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Spacer(),
            Container(
              width: 210,
              height: 210,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(12),
                image: const DecorationImage(
                  image: AssetImage('assets/images/logo.png'),
                  fit: BoxFit.contain,
                ),
              ),
            ),
            const SizedBox(height: 24),
            const Text(
              "PontoAPonto",
              style: TextStyle(
                fontSize: 36,
                fontWeight: FontWeight.bold,
              ),
            ),
            const SizedBox(height: 10),
            const Text(
              'Vamos entrar na sua conta!',
              style: TextStyle(
                fontSize: 16,
                fontWeight: FontWeight.w300,
              ),
            ),
            const Spacer(),
            Column(
              children: [
                const SocialButton(type: SocialEnum.google, enabled: false),
                const SizedBox(height: 10),
                const SocialButton(type: SocialEnum.meta, enabled: false),
                const SizedBox(height: 10),
                CustomButton(text: "Login", onPressed: () => Navigator.pushNamed(context, "/login-details")),
              ],
            ),
            TextButton(
              onPressed: () {
                Navigator.pushNamed(context, "/signup");
              },
              child: const Text(
                'Não tem conta? Crie uma.',
                style: TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.w500,
                  color: Colors.black,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
