import 'package:pontoaponto/core/enum/service_status_enum.dart';
import 'package:pontoaponto/core/models/service_return.dart';
import 'package:pontoaponto/core/repositories/dio_repository.dart';
import 'package:pontoaponto/core/repositories/http_repository.dart';

class AuthService {
  final HttpRepository dio = DioRepository();

  Future<ServiceReturn> createAccount({required String email, required String password}) async {
    try {
      // TODO: Implementar a lógica de cadastro.

      // Simula uma requisição de cadastro.
      await Future.delayed(const Duration(seconds: 2));

      // Simula cadastro.
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
      // TODO: Implementar a lógica de login.

      // Simula uma requisição de login.
      await Future.delayed(const Duration(seconds: 2));

      // Simula um erro ao fazer login.
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
      // TODO: Implementar a lógica de logout.

      // Simula uma requisição de logout.
      await Future.delayed(const Duration(seconds: 2));

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
      // TODO: Implementar a lógica de deletar conta.

      // Simula uma requisição de deletar conta.
      await Future.delayed(const Duration(seconds: 2));

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
}
