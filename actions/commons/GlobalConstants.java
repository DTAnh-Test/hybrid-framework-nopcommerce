package commons;

import java.io.File;

public class GlobalConstants {
	// Chứa những thông tin dùng chung cho cả framework
		// Url/ Database/ Server/ Enviroment/....
		// Timeout
        // Username/ Pass
        // Third Party
        // Retry test failed
        // Path, ..........
        // Cloud Testing: Browserstack, Saucelab, CrossbrowserTesting (Access Token/ ID)

    public static final String OS_NAME = System.getProperty("os.name");
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String UPLOAD_FILE_PATH = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles";
    public static final String DOWNLOAD_FILE_PATH = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles";
    public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
    public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
    public static final String ADMIN_USERNAME = "admin@yourstore.com";
    public static final String ADMIN_PASSWORD = "admin";
    public static final long LONG_TIMEOUT = 30;
    public static final long SHORT_TIMEOUT = 5;
}
