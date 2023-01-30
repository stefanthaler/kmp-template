import SwiftUI
import kmp

@main
struct iosApp: App {
    init() {
        // dependencies
        
        Dependencies().testIntegration()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
