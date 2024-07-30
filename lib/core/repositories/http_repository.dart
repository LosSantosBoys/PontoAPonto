import 'package:pontoaponto/core/models/http_options.dart';
import 'package:pontoaponto/core/models/http_return.dart';

abstract class HttpRepository {
  Future<HttpReturn> get(String url, {HttpOptions? options});
  Future<HttpReturn> post(String url, dynamic body, {HttpOptions? options});
  Future<HttpReturn> put(String url, dynamic body, {HttpOptions? options});
  Future<HttpReturn> delete(String url, {HttpOptions? options});
  Future<HttpReturn> download(String url, String savePath, {HttpOptions? options});
  Future<HttpReturn> getUri(Uri uri, {HttpOptions? options});
}
