package com.bizzan.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Printer;


/**
 * 内存性能检测工具类,在application中start
 * @author 邱长海
 */

public class BlockDetectByPrinter {
    private static final String TAG = "BlockDetectByPrinter";
    public static void start() {

        Looper.getMainLooper().setMessageLogging(new Printer() {

            private static final String START = ">>>>> Dispatching";
            private static final String END = "<<<<< Finished";

            @Override
            public void println(String x) {
                if (x.startsWith(START)) {
                    LogMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });

    }

    public static class LogMonitor {

        private static LogMonitor sInstance = new LogMonitor();
        private HandlerThread mLogThread = new HandlerThread("log");
        private Handler mIoHandler;
        private static final long TIME_BLOCK = 500L;

        private LogMonitor() {
            mLogThread.start();
            mIoHandler = new Handler(mLogThread.getLooper());
        }

        private static Runnable mLogRunnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
                for (StackTraceElement s : stackTrace) {
                    sb.append(s.toString() + "\n");
                }
                WonderfulLogUtils.logi(TAG, sb.toString());
            }
        };

        public static LogMonitor getInstance() {
            return sInstance;
        }

        public void startMonitor() {
            mIoHandler.postDelayed(mLogRunnable, TIME_BLOCK);
        }

        public void removeMonitor() {
            mIoHandler.removeCallbacks(mLogRunnable);
        }

    }
}
