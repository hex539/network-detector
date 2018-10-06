package(default_visibility = ["//visibility:private"])

android_library(
  name = "code",
  manifest = "AndroidManifest.xml",
  custom_package = "me.hex539.networkdetector",
  srcs = glob([
    "src/java/**",
  ]),
  assets_dir = "assets",
  assets = glob([
    "assets/**",
  ]),
  resource_files = glob([
    "res/**",
  ]),
  deps = [
    "//external:guava",
    "@androidsdk//com.android.support:cardview-v7-25.3.1",
    "@androidsdk//com.android.support:leanback-v17-25.3.1",
    "@androidsdk//com.android.support:preference-leanback-v17-25.3.1",
    "@androidsdk//com.android.support:recyclerview-v7-25.3.1",
  ],
)

android_binary(
  name = "app",
  manifest = "AndroidManifest.xml",
  custom_package = "me.hex539.networkdetector",
  multidex = "native",
  incremental_dexing = 0,
  dex_shards = 25,
  manifest_values = {
    "minSdkVersion": "26",
    "targetSdkVersion": "26",
  },
  dexopts = [
    "--min-sdk-version=26",
  ],
  deps = [
    ":code",
  ],
)
