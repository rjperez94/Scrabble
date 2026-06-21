public class RuntimeDetector {
    public enum TargetEnv { IDE, DESKTOP_JAR, CHEERPJ_BROWSER }

    public static TargetEnv getEnvironment() {
        // 1. Check for CheerpJ browser environment first
        String cheerpjVer = System.getProperty("cheerpj.version");
        String vmName = System.getProperty("java.vm.name");

        if (cheerpjVer != null || (vmName != null && vmName.toLowerCase().contains("cheerpj"))) {
            return TargetEnv.CHEERPJ_BROWSER;
        }

        // 2. Check if packaged as a JAR
        String className = RuntimeDetector.class.getSimpleName() + ".class";
        java.net.URL classUrl = RuntimeDetector.class.getResource(className);
        if (classUrl != null && "jar".equals(classUrl.getProtocol())) {
            return TargetEnv.DESKTOP_JAR;
        }

        // 3. Fallback to standard local IDE development
        return TargetEnv.IDE;
    }
}
