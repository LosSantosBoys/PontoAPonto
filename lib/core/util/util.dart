import 'package:flutter/material.dart';

extension StringExtension on String {
  /// Capitaliza a primeira letra de uma String.
  ///
  /// Retorna uma nova string com a primeira letra capitalizada e o restante da string inalterado.
  String capitalize() {
    return "${this[0].toUpperCase()}${substring(1)}";
  }
}

extension SnackbarExtension on BuildContext {
  /// Mostra um snackbar com um ícone de erro e a mensagem de erro especificada.
  ///
  /// Exemplo de uso:
  /// ```dart
  /// showErrorSnackbar("Ocorreu um erro!");
  /// ```
  void showErrorSnackbar(String message) {
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            const Icon(
              Icons.error_outline,
              color: Colors.red,
            ),
            const SizedBox(width: 10),
            Expanded(child: Text(message)),
          ],
        ),
      ),
    );
  }

  /// Mostra um snackbar com um ícone de sucesso e a mensagem de sucesso especificada.
  ///
  /// Exemplo de uso:
  /// ```dart
  /// showSuccessSnackbar("Operação realizada com sucesso!");
  /// ```
  void showSuccessSnackbar(String message) {
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            const Icon(
              Icons.check_circle_outline,
              color: Colors.green,
            ),
            const SizedBox(width: 10),
            Text(message),
          ],
        ),
      ),
    );
  }

  /// Mostra um snackbar com um ícone de informação e a mensagem de informação especificada.
  ///
  /// Exemplo de uso:
  /// ```dart
  /// showInfoSnackbar("Informação importante.");
  /// ```
  void showInfoSnackbar(String message) {
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            const Icon(
              Icons.info_outline,
              color: Colors.blue,
            ),
            const SizedBox(width: 10),
            Text(message),
          ],
        ),
      ),
    );
  }

  /// Mostra um snackbar com um ícone de aviso e a mensagem de aviso especificada.
  ///
  /// Exemplo de uso:
  /// ```dart
  /// showWarningSnackbar("Atenção!");
  /// ```
  void showWarningSnackbar(String message) {
    ScaffoldMessenger.of(this).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            const Icon(
              Icons.warning_amber_outlined,
              color: Colors.orange,
            ),
            const SizedBox(width: 10),
            Text(message),
          ],
        ),
      ),
    );
  }
}
