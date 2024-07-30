import 'package:flutter/material.dart';
import 'package:timeago/timeago.dart' as timeago;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  timeago.setLocaleMessages('pt_BR_short', timeago.PtBrShortMessages());

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(),
      initialRoute: '/',
      routes: {},
    );
  }
}
