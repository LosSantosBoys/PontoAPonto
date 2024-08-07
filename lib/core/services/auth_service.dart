import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:intl/intl.dart';
import 'package:pontoaponto/core/enum/service_status_enum.dart';
import 'package:pontoaponto/core/models/http_options.dart';
import 'package:pontoaponto/core/models/http_return.dart';
import 'package:pontoaponto/core/models/service_return.dart';
import 'package:pontoaponto/core/repositories/dio_repository.dart';
import 'package:pontoaponto/core/repositories/http_repository.dart';

class AuthService {
  final HttpRepository dio = DioRepository();
  AndroidOptions _getAndroidOptions() => const AndroidOptions(encryptedSharedPreferences: true);
  final FlutterSecureStorage storage = const FlutterSecureStorage();
  final String _server = dotenv.env['SERVER']!;

  Future<ServiceReturn> createAccount({
    required String name,
    required String email,
    required String password,
    required String phone,
    required String cpf,
    required DateTime birthday,
    required bool isDriver,
  }) async {
    DateFormat formatter = DateFormat('dd-MM-yyyy');

    try {
      HttpReturn response = await dio.post(
        '$_server/api/v1/signup${isDriver ? '/driver' : '/user'}',
        {
          "name": name,
          "email": email,
          "password": password,
          "phone": phone.replaceAll(RegExp('[^a-zA-Z0-9]'), ''),
          "cpf": cpf.replaceAll(RegExp('[^a-zA-Z0-9]'), ''),
          "birthday": formatter.format(birthday),
        },
      );

      if (response.statusCode != 201) {
        return ServiceReturn(
          status: ServiceStatusEnum.error,
          message: response.data['message'],
        );
      }

      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao cadastrar.",
      );
    }
  }

  Future<ServiceReturn> login({required String email, required String password}) async {
    try {
      HttpReturn response = await dio.post('$_server/api/v1/signin', {
        "email": email,
        "password": password,
        "userType": 0,
      });

      if (response.statusCode != 200) {
        return ServiceReturn(
          status: ServiceStatusEnum.error,
          message: response.data['message'],
        );
      }

      await storage.write(
        key: "token",
        value: response.data['token'],
        aOptions: _getAndroidOptions(),
      );

      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao fazer login.",
      );
    }
  }

  Future<ServiceReturn> logout() async {
    try {
      await storage.delete(key: "token");

      // Simula logout.
      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao fazer logout.",
      );
    }
  }

  Future<ServiceReturn> deleteAccount() async {
    try {
      HttpReturn response = await dio.delete('$_server/api/v1/user/me/delete');

      if (response.statusCode != 200) {
        return ServiceReturn(
          status: ServiceStatusEnum.error,
          message: response.data['message'],
        );
      }

      // Simula uma requisição de deletar conta.
      await Future.delayed(const Duration(seconds: 2));
      await storage.delete(key: "token");

      // Simula deletar conta.
      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao tentar deletar a conta.",
      );
    }
  }

  Future<ServiceReturn> resetPassword() async {
    try {
      // TODO: Implementar a lógica de resetar a senha.

      // Simula uma requisição de resetar a senha.
      await Future.delayed(const Duration(seconds: 2));

      // Simula resetar a senha.
      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao resetar a senha.",
      );
    }
  }

  Future<ServiceReturn> generateOtp({required String email}) async {
    try {
      HttpReturn response = await dio.patch('$_server/api/v1/signup/otp/new', {
        "email": email,
      });

      if (response.statusCode != 204) {
        return ServiceReturn(
          status: ServiceStatusEnum.error,
          message: response.data['message'],
        );
      }

      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao gerar o código de verificação.",
      );
    }
  }

  Future<ServiceReturn> verifyOtp({required String email, required String otp}) async {
    try {
      HttpReturn response = await dio.patch('$_server/api/v1/signup/otp/validate', {
        "email": email,
        "otp": otp,
        "userType": 0,
      });

      if (response.statusCode != 204) {
        return ServiceReturn(
          status: ServiceStatusEnum.error,
          message: response.data['message'],
        );
      }

      return const ServiceReturn(
        status: ServiceStatusEnum.success,
      );
    } catch (e) {
      return const ServiceReturn(
        status: ServiceStatusEnum.error,
        message: "Erro ao verificar o código de verificação.",
      );
    }
  }

  Future<bool> isAuthenticated() async {
    try {
      final String? token = await storage.read(key: "token");

      if (token == null) {
        return false;
      }

      HttpReturn response = await dio.get(
        '$_server/api/v1/user/me',
        options: HttpOptions(headers: {"token": token}),
      );

      if (response.statusCode != 200) {
        return false;
      }

      return true;
    } catch (e) {
      return false;
    }
  }
}
