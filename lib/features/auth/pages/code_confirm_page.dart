import 'package:flutter/material.dart';
import 'package:pinput/pinput.dart';
import 'package:pontoaponto/core/enum/service_status_enum.dart';
import 'package:pontoaponto/core/models/service_return.dart';
import 'package:pontoaponto/core/services/auth_service.dart';
import 'package:pontoaponto/core/util/util.dart';
import 'package:pontoaponto/core/widgets/custom_button.dart';

class CodeConfirmPage extends StatefulWidget {
  const CodeConfirmPage({super.key, required this.email});

  final String email;

  @override
  State<CodeConfirmPage> createState() => _CodeConfirmPageState();
}

class _CodeConfirmPageState extends State<CodeConfirmPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController codeController = TextEditingController();

  Future<void> _confirmCode(BuildContext context) async {
    if (!_formKey.currentState!.validate()) {
      return;
    }

    final ServiceReturn result = await AuthService().verifyOtp(
      email: widget.email,
      otp: codeController.text.trim(),
    );

    if (result.status == ServiceStatusEnum.success) {
      if (context.mounted) {
        Navigator.pushNamed(context, '/home');
      }
    } else {
      if (context.mounted) {
        context.showErrorSnackbar("Erro ao verificar o código de verificação. ${result.message}");
      }
    }
  }

  Future<void> _resendCode(BuildContext context) async {
    final ServiceReturn result = await AuthService().generateOtp(email: widget.email);

    if (result.status == ServiceStatusEnum.success) {
      if (context.mounted) {
        context.showSuccessSnackbar("Código de verificação reenviado com sucesso.");
      }
    } else {
      if (context.mounted) {
        context.showErrorSnackbar(result.message ?? "Erro ao reenviar o código de verificação.");
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
      ),
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(24.0),
        child: CustomButton(
          text: 'Criar conta',
          onPressed: () async => await _confirmCode(context),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 24),
        child: Column(
          children: [
            const Padding(
              padding: EdgeInsets.symmetric(vertical: 10.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Verificação de segurança",
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),
                  ),
                  SizedBox(height: 10),
                  Text(
                    "Enviamos um código ao seu e-mail! Dê uma olhada e o insira embaixo para verificar.",
                    style: TextStyle(fontWeight: FontWeight.w300, fontSize: 16),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 20),
            Form(
              key: _formKey,
              child: Pinput(
                length: 4,
                controller: codeController,
                validator: (String? value) {
                  if (value!.isEmpty) {
                    return 'Por favor, insira o código de verificação.';
                  }
                  return null;
                },
              ),
            ),
            const SizedBox(height: 20),
            TextButton(
              onPressed: () async => await _resendCode(context),
              child: const Text("Reenviar código"),
            ),
          ],
        ),
      ),
    );
  }
}
