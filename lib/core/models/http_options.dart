import 'package:dio/dio.dart';

class HttpOptions extends Options {
  HttpOptions({this.useCache = true, super.responseType, super.headers});

  final bool useCache;
}