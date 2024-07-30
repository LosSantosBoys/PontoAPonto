import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:pontoaponto/core/services/auth_service.dart';
import 'package:pontoaponto/features/auth/pages/forgot_password_page.dart';
import 'package:pontoaponto/features/auth/pages/login_page.dart';
import 'package:pontoaponto/features/auth/pages/sign_up_page.dart';
import 'package:pontoaponto/features/home/pages/home_page.dart';
import 'package:timeago/timeago.dart' as timeago;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  timeago.setLocaleMessages('pt_BR_short', timeago.PtBrShortMessages());
  await dotenv.load(fileName: '.env');

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(),
      initialRoute: '/',
      routes: {
        '/': (context) => FutureBuilder<bool>(
              future: AuthService().isAuthenticated(),
              builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
                switch (snapshot.connectionState) {
                  case ConnectionState.done:
                    return snapshot.data! ? const HomePage() : const LoginPage();
                  default:
                    return const Scaffold(
                      body: Center(
                        child: CircularProgressIndicator(),
                      ),
                    );
                }
              },
            ),
        '/login': (context) => const LoginPage(),
        '/signup': (context) => const SignUpPage(),
        '/home': (context) => const HomePage(),
        '/forgot-password': (context) => ForgotPasswordPage(),
      },
    );
  }
}
