import 'package:flutter/material.dart';
import 'package:flutter_settings_ui/flutter_settings_ui.dart';

class SettingsPage extends StatelessWidget {
  const SettingsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Configurações"),
      ),
      body: SettingsList(
        contentPadding: EdgeInsets.zero,
        lightTheme: const SettingsThemeData(
          settingsListBackground: Colors.white,
        ),
        sections: [
          SettingsSection(
            title: const Text("Geral"),
            tiles: [
              SettingsTile(
                title: const Text("Conta"),
                leading: const Icon(Icons.account_circle_outlined),
              ),
              SettingsTile(
                title: const Text("Idioma"),
                leading: const Icon(Icons.language_outlined),
                trailing: const Text("Português"),
              ),
            ],
          ),
          SettingsSection(
            title: const Text("Privacidade e Segurança"),
            tiles: [
              SettingsTile(
                title: const Text("Motoristas denunciados"),
                leading: const Icon(Icons.block_outlined),
              ),
              SettingsTile(
                title: const Text("Autenticação de dois fatores"),
                leading: const Icon(Icons.lock_clock_outlined),
              ),
              SettingsTile.switchTile(
                initialValue: false,
                title: const Text("Autenicação biométrica"),
                leading: const Icon(Icons.lock_clock_outlined),
                onToggle: (bool value) {},
              ),
            ],
          ),
          SettingsSection(
            title: const Text("Feedback e Suporte"),
            tiles: [
              SettingsTile(
                title: const Text("Ajuda e FAQ"),
                leading: const Icon(Icons.question_answer_outlined),
              ),
              SettingsTile(
                title: const Text("Feedback"),
                leading: const Icon(Icons.feedback_outlined),
              ),
            ],
          ),
          SettingsSection(
            title: const Text("Legal"),
            tiles: [
              SettingsTile(
                title: const Text("Termos de Serviço"),
                leading: const Icon(Icons.description_outlined),
              ),
              SettingsTile(
                title: const Text("Política de Privacidade"),
                leading: const Icon(Icons.privacy_tip_outlined),
              ),
              SettingsTile(
                title: const Text("Licenças Open Source"),
                leading: const Icon(Icons.info_outline),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
