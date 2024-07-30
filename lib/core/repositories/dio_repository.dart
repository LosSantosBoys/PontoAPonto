import 'package:dio/dio.dart';
import 'package:pontoaponto/core/models/http_options.dart';
import 'package:pontoaponto/core/models/http_return.dart';
import 'package:pontoaponto/core/repositories/http_repository.dart';
import 'package:pontoaponto/core/services/cache_service.dart';

class DioRepository implements HttpRepository {
  static final Dio _dio = Dio();
  static Dio get dio => _dio;
  final CacheService cache = CacheService();

  @override
  Future<HttpReturn> delete(String url, {HttpOptions? options}) async {
    try {
      final Response<dynamic> response = await dio.delete(url, options: options);

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response,
      );
    }
  }

  @override
  Future<HttpReturn> download(String url, String savePath, {HttpOptions? options}) async {
    try {
      final Response<dynamic> response = await dio.download(url, savePath, options: options);

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response,
      );
    }
  }

  @override
  Future<HttpReturn> get(String url, {HttpOptions? options}) async {
    if (!url.startsWith('http') && !url.startsWith('https')) {
      url = 'https://$url';
    }

    try {
      if (options?.useCache == true) {
        final dynamic data = await cache.getData(url);

        if (data != null) {
          return HttpReturn(
            statusCode: 200,
            data: data,
          );
        }
      }

      final Response<dynamic> response = await dio.get(url, options: options);

      if (response.statusCode == 200) {
        if (options?.useCache == true) {
          await cache.saveData(url, response.data);
        }
      }

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response?.data,
      );
    }
  }

  @override
  Future<HttpReturn> post(String url, body, {HttpOptions? options}) async {
    try {
      final Response<dynamic> response = await dio.post(url, data: body, options: options);

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response,
      );
    }
  }

  @override
  Future<HttpReturn> put(String url, body, {HttpOptions? options}) async {
    try {
      final Response<dynamic> response = await dio.put(url, data: body, options: options);

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response,
      );
    }
  }

  @override
  Future<HttpReturn> getUri(Uri uri, {HttpOptions? options}) async {
    try {
      if (options?.useCache == true) {
        final dynamic data = await cache.getData(uri.toString());

        if (data != null) {
          return HttpReturn(
            statusCode: 200,
            data: data,
          );
        }
      }

      final Response<dynamic> response = await dio.getUri(uri, options: options);

      if (response.statusCode == 200) {
        if (options?.useCache == true) {
          await cache.saveData(uri.toString(), response.data);
        }
      }

      return HttpReturn(
        statusCode: response.statusCode,
        data: response.data,
      );
    } on DioException catch (e) {
      return HttpReturn(
        statusCode: e.response?.statusCode ?? 400,
        data: e.response?.data,
      );
    }
  }
}
