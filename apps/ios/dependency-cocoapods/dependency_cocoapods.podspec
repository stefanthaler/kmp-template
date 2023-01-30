Pod::Spec.new do |spec|
    spec.name                     = 'dependency_cocoapods'
    spec.version                  = '1.0.0-override-in-library'
    spec.homepage                 = 'https://yourhomepage.at'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'KMP library: dependency-cocoapods'
    spec.vendored_frameworks      = 'build/cocoapods/framework/dependency-cocoapods.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '16.0'
    spec.dependency 'AFNetworking', '~> 4.0.1'
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':apps:ios:dependency-cocoapods',
        'PRODUCT_MODULE_NAME' => 'dependency-cocoapods',
    }
                
    spec.script_phases = [
        {
            :name => 'Build dependency_cocoapods',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../../../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end