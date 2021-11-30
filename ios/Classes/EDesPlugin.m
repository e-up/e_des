#import "EDesPlugin.h"
#if __has_include(<e_des/e_des-Swift.h>)
#import <e_des/e_des-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "e_des-Swift.h"
#endif

@implementation EDesPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftEDesPlugin registerWithRegistrar:registrar];
}
@end
