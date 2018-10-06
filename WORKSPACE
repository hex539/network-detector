workspace(name = "networkdetector")

android_sdk_repository(
  name = "androidsdk",
  path = "/usr/local/android-sdk",
  api_level = 26,
  build_tools_version = "27.0.3",
)

git_repository(
  name = 'com_google_protobuf',
  remote = 'https://github.com/google/protobuf',
  tag = "v3.5.1.1",
)

git_repository(
  name = "org_pubref_rules_maven",
  remote = "https://github.com/pubref/rules_maven",
  tag = "v0.2.0",
)

maven_jar(
  name = "okhttp",
  artifact = "com.squareup.okhttp3:okhttp:3.8.1",
)

maven_jar(
  name = "okio",
  artifact = "com.squareup.okio:okio:1.13.0",
)

maven_jar(
  name = "guava_maven",
  artifact = "com.google.guava:guava:24.0-android",
)

bind(
  name = "guava",
  actual = "@guava_maven//jar",
)

maven_jar(
  name = "junit4",
  artifact = "junit:junit:4.12",
)

maven_jar(
  name = "mockito",
  artifact = "org.mockito:mockito-all:1.10.19",
)

maven_jar(
  name = "truth",
  artifact = "com.google.truth:truth:0.27",
)

maven_jar(
  name = "nullable",
  artifact = "com.google.code.findbugs:jsr305:3.0.2",
)

load("@org_pubref_rules_maven//maven:rules.bzl", "maven_repositories")
maven_repositories()

load("@org_pubref_rules_maven//maven:rules.bzl", "maven_repository")
maven_repository(
  name = "parboiled_java",
  deps = ["org.parboiled:parboiled-java:1.1.8"],
  transitive_deps = [
    '4ce3ecdc7115bcbf9d4ff4e6ec638e60760819df:org.ow2.asm:asm:5.2',
    '2de10833bb3ade1939b1489b7656c9de20343e14:org.ow2.asm:asm-analysis:5.2',
    '733a8d67f6f4174d12142b7bbcfc496a6d99882e:org.ow2.asm:asm-tree:5.2',
    '9408ea14e73b7c9b427545a1b84923d6afaf8e1e:org.ow2.asm:asm-util:5.2',
    'af12604d2e555c65107d744bc502dcd9d1c0cad9:org.parboiled:parboiled-core:1.1.8',
    'df6ca179d7a1cd0b556a3f46c219503d0ab80388:org.parboiled:parboiled-java:1.1.8',
  ],
)

load("@parboiled_java//:rules.bzl", "parboiled_java_compile")
parboiled_java_compile()
