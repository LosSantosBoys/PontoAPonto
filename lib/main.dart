import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pontoaponto/core/services/auth_service.dart';
import 'package:pontoaponto/features/auth/pages/forgot_password_page.dart';
import 'package:pontoaponto/features/auth/pages/login_page.dart';
import 'package:pontoaponto/features/auth/pages/sign_up_page.dart';
import 'package:pontoaponto/features/home/pages/history_page.dart';
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
      theme: ThemeData(
        primaryColor: const Color(0xFF3755C1),
        primaryColorLight: const Color(0xFF3755C1),
        textButtonTheme: TextButtonThemeData(
          style: TextButton.styleFrom(
            foregroundColor: const Color(0XFF3755C1),
          ),
        ),
        appBarTheme: const AppBarTheme(
          titleTextStyle: TextStyle(
            fontWeight: FontWeight.bold,
            fontSize: 20,
            color: Color(0xFF1F2024),
          ),
          titleSpacing: 24,
        ),
        textTheme: GoogleFonts.interTextTheme(
          Theme.of(context).textTheme,
        ),
        fontFamily: GoogleFonts.inter().fontFamily,
        snackBarTheme: const SnackBarThemeData(
          backgroundColor: Colors.white,
          contentTextStyle: TextStyle(color: Color(0xFF1F2024)),
        ),
        inputDecorationTheme: InputDecorationTheme(
          fillColor: const Color(0xFFFAFAFA),
          filled: true,
          contentPadding: const EdgeInsets.symmetric(horizontal: 10),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide.none,
          ),
          enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: BorderSide.none,
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: Color(0XFF8395d6),
              width: 2,
            ),
          ),
          focusedErrorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: Color(0XFFFFADB9),
              width: 2,
            ),
          ),
          disabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: Color(0xFFC7C7CA),
            ),
          ),
          errorStyle: const TextStyle(
            color: Color(0XFFE30000),
          ),
          errorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: Color(0XFFE30000),
            ),
          ),
        ),
        scaffoldBackgroundColor: Colors.white,
        drawerTheme: const DrawerThemeData(
          backgroundColor: Colors.white,
        ),
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
        chipTheme: const ChipThemeData(
          selectedColor: Color(0xFF3755C1),
          backgroundColor: Color(0xFFF8F9FE),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(Radius.circular(8)),
            side: BorderSide(style: BorderStyle.none),
          ),
        ),
      ),
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
        '/history': (context) => const HistoryPage(),
      },
    );
  }
}
