package com.base.library.utils;

import android.util.Log;

import java.util.Locale;

/**
 * @author reber
 * on 2020/10/10 09:49
 */
public class LogUtil {

    private LogUtil() {
        throw new Error(getClass().getSimpleName() + " do not need instantiate!");
    }

    /**
     * 判断是否可以调试
     */
    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    /**
     * 打印log日志
     */
    private static void showLog(int logType, String msg) {
        if (isDebuggable()) {
            return;
        }
        // 获取文件名、方法名、所在行数
        StackTraceElement[] elements = new Throwable().getStackTrace();
        String className = elements[1].getFileName();
        String methodName = elements[1].getMethodName();
        int lineNumber = elements[1].getLineNumber();
        String content = String.format(Locale.CHINA, "==%s（%s：%d）%s", methodName, className, lineNumber, msg);
        switch (logType) {
            case Log.DEBUG:
                Log.d(className, content);
                break;
            case Log.ERROR:
                Log.e(className, content);
                break;
            case Log.INFO:
                Log.i(className, content);
                break;
            case Log.VERBOSE:
                Log.v(className, content);
                break;
            case Log.WARN:
                Log.w(className, content);
                break;
        }
    }


    public static void d(String msg) {
        showLog(Log.DEBUG, msg);
    }

    public static void e(String msg) {
        showLog(Log.ERROR, msg);
    }

    public static void i(String msg) {
        showLog(Log.INFO, msg);
    }

    public static void v(String msg) {
        showLog(Log.VERBOSE, msg);
    }

    public static void w(String msg) {
        showLog(Log.WARN, msg);
    }
}
