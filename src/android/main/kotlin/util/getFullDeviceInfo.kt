package util

/**
 * Get device model and OS.
 *
 * All the system variables are listed here: https://developer.android.com/reference/android/os/Build.html
 *
 * @return a string with the device information
 * */
actual fun getFullDeviceInfo(): String {
    /** API Level */
    val sdkVersion = android.os.Build.VERSION.SDK_INT

    /** User-visible version string */
    val sdkUserVisible = android.os.Build.VERSION.RELEASE

    /** Device. Name of the industrial design */
    val device = android.os.Build.DEVICE

    /** Model. End-user-visible name for the end product */
    val model = android.os.Build.MODEL

    /** Product. Name of the overall product */
    val product = android.os.Build.PRODUCT

    return "====== SDK info ======\n" +
            "version: $sdkVersion;\n" +
            "user-visible version: $sdkUserVisible.\n" +
            " \n" +
            "====== Device info ======\n" +
            "device: $device;\n" +
            "model (raw): $model;\n" +
            "model (processed): ${getDeviceModel()}\n" +
            "overall product: $product."
}