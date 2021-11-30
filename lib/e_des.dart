import 'dart:async';

import 'package:flutter/services.dart';

class EDes {
  static const MethodChannel _channel = const MethodChannel('e_des');

  // static Future<String?> get platformVersion async {
  //   final String? version = await _channel.invokeMethod('getPlatformVersion');
  //   return version;
  // }

  static Future<String> encode(
      {required String secret,
      required String iv,
      required String data}) async {
    return await _channel.invokeMethod('encode', {
      'secret': secret,
      'iv': iv,
      'data': data,
    });
  }
}
