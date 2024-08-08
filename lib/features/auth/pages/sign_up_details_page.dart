import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mask_text_input_formatter/mask_text_input_formatter.dart';
import 'package:pontoaponto/core/const.dart';
import 'package:pontoaponto/core/enum/service_status_enum.dart';
import 'package:pontoaponto/core/models/service_return.dart';
import 'package:pontoaponto/core/services/auth_service.dart';
import 'package:pontoaponto/core/util/util.dart';
import 'package:pontoaponto/core/widgets/custom_button.dart';
import 'package:pontoaponto/features/auth/enum/user_type_enum.dart';
import 'package:pontoaponto/features/auth/pages/code_confirm_page.dart';

class SignUpDetailsPage extends StatefulWidget {
  const SignUpDetailsPage({super.key, required this.email, required this.password});

  final String email;
  final String password;

  @override
  State<SignUpDetailsPage> createState() => _SignUpDetailsPageState();
}

class _SignUpDetailsPageState extends State<SignUpDetailsPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  final TextEditingController nameController = TextEditingController();
  final TextEditingController phoneController = TextEditingController();
  final TextEditingController cpfCnpjController = TextEditingController();
  final TextEditingController birthdayController = TextEditingController();
  UserTypeEnum userType = UserTypeEnum.user;
  DateTime? pickedBirthday;
  final MaskTextInputFormatter phoneFormatter = MaskTextInputFormatter(
    mask: "(##) #####-####",
    filter: {"#": RegExp(r'[0-9]')},
  );
  final MaskTextInputFormatter cpfFormatter = MaskTextInputFormatter(
    mask: "###.###.###-##",
    filter: {"#": RegExp(r'[0-9]')},
  );
  final MaskTextInputFormatter cnpjFormatter = MaskTextInputFormatter(
    mask: "##.###.###/####-##",
    filter: {"#": RegExp(r'[0-9]')},
  );

  Future<void> selectBirthday(BuildContext context) async {
    // 100 anos atrás
    final DateTime hundredYearsAgo = DateTime.now().subtract(const Duration(days: 365 * 100));

    // 12 anos atrás
    final DateTime twelveYearsAgo = DateTime.now().subtract(const Duration(days: 365 * 12));

    // Seleciona a data de nascimento entre 12 e 100 anos atrás
    final DateTime? pickedDate = await showDatePicker(
      context: context,
      firstDate: hundredYearsAgo,
      lastDate: twelveYearsAgo,
    );

    if (pickedDate != null) {
      DateFormat formatter = DateFormat('dd/MM/yyyy');

      setState(() {
        birthdayController.text = formatter.format(pickedDate);
        pickedBirthday = pickedDate;
      });
    }
  }

  Future<void> _handleSignUp(BuildContext context) async {
    if (!_formKey.currentState!.validate() || pickedBirthday == null) {
      return;
    }

    final ServiceReturn result = await AuthService().createAccount(
      email: widget.email,
      password: widget.password,
      birthday: pickedBirthday!,
      cpf: cpfCnpjController.text.trim(),
      name: nameController.text.trim(),
      phone: phoneController.text.trim(),
      isDriver: userType.name != "user",
    );

    if (result.status == ServiceStatusEnum.success) {
      if (context.mounted) {
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => CodeConfirmPage(email: widget.email),
          ),
        );
      }
    } else {
      if (context.mounted) {
        context.showErrorSnackbar("Erro ao criar conta.");
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
          onPressed: () async => _handleSignUp(context),
        ),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.symmetric(horizontal: 24),
        child: Column(
          children: [
            const Padding(
              padding: EdgeInsets.symmetric(vertical: 10.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "Agora seus dados pessoais!",
                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 24),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 10),
            Form(
              key: _formKey,
              child: Column(
                children: [
                  // Nome
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text(
                          "Nome",
                          style: TextStyle(
                            fontWeight: FontWeight.w700,
                            fontSize: 16,
                          ),
                        ),
                        const SizedBox(height: 10),
                        TextFormField(
                          controller: nameController,
                          textCapitalization: TextCapitalization.words,
                          keyboardType: TextInputType.name,
                          decoration: const InputDecoration(
                            hintText: 'Nome',
                          ),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor, insira seu nome.';
                            }

                            return null;
                          },
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 5),
                  // Número de telefone
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text(
                          "Número",
                          style: TextStyle(
                            fontWeight: FontWeight.w700,
                            fontSize: 16,
                          ),
                        ),
                        const SizedBox(height: 10),
                        TextFormField(
                          controller: phoneController,
                          keyboardType: TextInputType.number,
                          inputFormatters: [phoneFormatter],
                          decoration: const InputDecoration(hintText: 'Número', prefixText: "+55 "),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor, insira seu número de telefone.';
                            }

                            return null;
                          },
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 5),
                  // Motorista ou Uusário
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        RadioListTile<UserTypeEnum>(
                          title: const Text("Motorista"),
                          value: UserTypeEnum.driver,
                          groupValue: userType,
                          fillColor: WidgetStateProperty.all(ThemeColors.primaryDarkest),
                          tileColor: const Color(0xFFFAFAFA),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          ),
                          onChanged: (UserTypeEnum? value) {
                            setState(() {
                              userType = value!;
                            });
                          },
                        ),
                        const SizedBox(height: 5),
                        RadioListTile<UserTypeEnum>(
                          title: const Text("Usuário"),
                          value: UserTypeEnum.user,
                          groupValue: userType,
                          fillColor: WidgetStateProperty.all(ThemeColors.primaryDarkest),
                          tileColor: const Color(0xFFFAFAFA),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(8),
                          ),
                          onChanged: (UserTypeEnum? value) {
                            setState(() {
                              userType = value!;
                            });
                          },
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 5),
                  // CPF ou CNPJ
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          userType == UserTypeEnum.driver ? "CNPJ" : "CPF",
                          style: const TextStyle(
                            fontWeight: FontWeight.w700,
                            fontSize: 16,
                          ),
                        ),
                        const SizedBox(height: 10),
                        TextFormField(
                          controller: cpfCnpjController,
                          keyboardType: TextInputType.number,
                          inputFormatters: [userType == UserTypeEnum.driver ? cnpjFormatter : cpfFormatter],
                          decoration: InputDecoration(
                            hintText: userType == UserTypeEnum.driver ? "CNPJ" : 'CPF',
                          ),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor, insira seu ${userType == UserTypeEnum.driver ? "CNPJ" : "CPF"}.';
                            }

                            return null;
                          },
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 5),
                  // Data de nascimento
                  Padding(
                    padding: const EdgeInsets.symmetric(vertical: 10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const Text(
                          "Data de Nascimento",
                          style: TextStyle(
                            fontWeight: FontWeight.w700,
                            fontSize: 16,
                          ),
                        ),
                        const SizedBox(height: 10),
                        TextFormField(
                          controller: birthdayController,
                          readOnly: true,
                          onTap: () => selectBirthday(context),
                          decoration: const InputDecoration(
                            hintText: 'Data de Nascimento',
                          ),
                          validator: (value) {
                            if (value == null || value.isEmpty) {
                              return 'Por favor, insira sua data de nascimento.';
                            }

                            return null;
                          },
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
