package util;

public class ContextOperations {
    public static String getPathToRoot(String pathToTarget){
        pathToTarget = pathToTarget.substring(0,pathToTarget.indexOf("target"));
        return pathToTarget;
    }
}
