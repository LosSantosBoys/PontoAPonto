import 'package:flutter/material.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pontoaponto/core/const.dart';
import 'package:pontoaponto/core/services/auth_service.dart';
import 'package:pontoaponto/features/auth/pages/forgot_password_page.dart';
import 'package:pontoaponto/features/auth/pages/login_page.dart';
import 'package:pontoaponto/features/auth/pages/sign_up_page.dart';
import 'package:pontoaponto/features/home/pages/history_page.dart';
import 'package:pontoaponto/features/home/pages/home_page.dart';
import 'package:pontoaponto/features/onboarding/pages/plan_page.dart';
import 'package:pontoaponto/features/settings/pages/account_page.dart';
import 'package:pontoaponto/features/settings/pages/settings_page.dart';
import 'package:timeago/timeago.dart' as timeago;
import 'package:intl/date_symbol_data_local.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await initializeDateFormatting('pt_BR', null);
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
        primaryColor: ThemeColors.primaryDarkest,
        primaryColorLight: ThemeColors.primaryDark,
        textButtonTheme: TextButtonThemeData(
          style: TextButton.styleFrom(
            foregroundColor: ThemeColors.primaryDarkest,
          ),
        ),
        appBarTheme: const AppBarTheme(
            titleTextStyle: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 20,
              color: Color(0xFF1F2024),
            ),
            titleSpacing: 24,
            backgroundColor: Colors.white,
            elevation: 0),
        textTheme: GoogleFonts.interTextTheme(
          Theme.of(context).textTheme,
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            backgroundColor: ThemeColors.primaryDarkest,
            elevation: 0,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
              side: BorderSide.none,
            ),
            textStyle: const TextStyle(
              color: ThemeColors.neutralLightLightest,
              fontWeight: FontWeight.w600,
            ),
            foregroundColor: ThemeColors.neutralLightLightest,
            disabledForegroundColor: ThemeColors.neutralLightDarkest,
            disabledBackgroundColor: ThemeColors.neutralLightMedium,
          ),
        ),
        fontFamily: GoogleFonts.inter().fontFamily,
        snackBarTheme: const SnackBarThemeData(
          backgroundColor: Colors.white,
          contentTextStyle: TextStyle(color: Color(0xFF1F2024)),
        ),
        inputDecorationTheme: InputDecorationTheme(
          fillColor: ThemeColors.neutralLightLight,
          filled: true,
          hintStyle: const TextStyle(
            color: ThemeColors.neutralDarkLightest,
            fontWeight: FontWeight.w300,
            fontSize: 13,
          ),
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
              color: ThemeColors.primaryLightest,
              width: 2,
            ),
          ),
          focusedErrorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: ThemeColors.errorMedium,
              width: 2,
            ),
          ),
          disabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: ThemeColors.neutralLightDarkest,
            ),
          ),
          errorStyle: const TextStyle(
            color: ThemeColors.errorDarkest,
          ),
          errorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(8),
            borderSide: const BorderSide(
              color: ThemeColors.errorDarkest,
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
        '/plan': (context) => const PlanPage(),
        '/settings': (context) => const SettingsPage(),
        '/account': (context) => const AccountPage(),
      },
    );
  }
}
