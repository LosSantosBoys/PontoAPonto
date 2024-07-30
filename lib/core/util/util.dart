extension StringExtension on String {
  /// Capitaliza a primeira letra de uma String.
  ///
  /// Retorna uma nova string com a primeira letra capitalizada e o restante da string inalterado.
  String capitalize() {
    return "${this[0].toUpperCase()}${substring(1)}";
  }
}
