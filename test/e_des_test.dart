import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:e_des/e_des.dart';

void main() {
  const MethodChannel channel = MethodChannel('e_des');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  // test('getPlatformVersion', () async {
  //   expect(await EDes.platformVersion, '42');
  // });
}
