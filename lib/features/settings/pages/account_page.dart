import 'package:flutter/material.dart';
import 'package:pontoaponto/core/const.dart';

class AccountPage extends StatelessWidget {
  const AccountPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Conta"),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 10),
        child: Column(
          children: [
            Container(
              width: 150,
              height: 150,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(100),
                color: Colors.grey[300],
              ),
              child: const Icon(Icons.account_circle_outlined, size: 100),
            ),
            const SizedBox(height: 20),
            const Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Text(
                  // TODO: Substituir por dados reais
                  "Nome Sobrenome",
                  style: TextStyle(
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 5),
            TextField(
              controller: TextEditingController(text: "Nome Sobrenome"),
              readOnly: true,
            ),
            const SizedBox(height: 10),
            const Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                Text(
                  "E-mail",
                  style: TextStyle(
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ],
            ),
            const SizedBox(height: 5),
            TextField(
              controller: TextEditingController(text: "email"),
              readOnly: true,
              decoration: const InputDecoration(
                suffixIcon: Icon(
                  Icons.error_outline,
                  color: ThemeColors.errorDarkest,
                  semanticLabel: "Status de verificação de e-mail: verificado.",
                ),
              ),
            ),
            const SizedBox(height: 10),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                // TODO: Implementar ação de logout
                onPressed: () {},
                child: const Text("Sair"),
              ),
            ),
            SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: ThemeColors.errorDarkest,
                ),
                onPressed: () {
                  showDialog(
                    context: context,
                    builder: (context) {
                      return AlertDialog(
                        title: const Text("Deletar conta"),
                        content: const Column(
                          mainAxisSize: MainAxisSize.min,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.start,
                          children: [
                            Text("Você tem certeza que deseja deletar sua conta?"),
                            SizedBox(height: 10),
                            Text("Essa ação é irreversível e você perderá todos os seus dados."),
                          ],
                        ),
                        actions: [
                          TextButton(
                            onPressed: () => Navigator.pop(context),
                            child: const Text("Cancelar"),
                          ),
                          TextButton(
                            // TODO: Implementar ação de deletar conta
                            onPressed: () {},
                            child: const Text(
                              "Deletar conta",
                              style: TextStyle(color: ThemeColors.errorDarkest),
                            ),
                          ),
                        ],
                      );
                    },
                  );
                },
                child: const Text("Deletar conta"),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
