import 'package:pontoaponto/core/enum/service_status_enum.dart';

class ServiceReturn {
  const ServiceReturn({
    required this.status,
    this.message,
    this.data,
  });

  final ServiceStatusEnum status;
  final String? message;
  final dynamic data;
}
