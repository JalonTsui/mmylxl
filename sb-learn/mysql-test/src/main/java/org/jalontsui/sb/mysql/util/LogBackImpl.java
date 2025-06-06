package org.jalontsui.sb.mysql.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * 自定义mybatis的log输出
 *
 * @author JalonTsui
 * @Date 4:07 2025/6/7
 */

@Slf4j
public class LogBackImpl implements Log {

    private static boolean IS_USE_LOGBACK = true;

    public LogBackImpl(String clazz) {

    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        if (IS_USE_LOGBACK) {
            log.debug("{}", s);
        } else {
            System.out.println(s);
        }
    }

    @Override
    public void error(String s) {
        if (IS_USE_LOGBACK) {
            log.debug("{}", s);
        } else {
            System.out.println(s);
        }
    }

    @Override
    public void debug(String s) {
        if (IS_USE_LOGBACK) {
            log.debug("{}", s);
        } else {
            System.out.println(s);
        }
    }

    @Override
    public void trace(String s) {
        if (IS_USE_LOGBACK) {
            log.debug("{}", s);
        } else {
            System.out.println(s);
        }
    }

    @Override
    public void warn(String s) {
        if (IS_USE_LOGBACK) {
            log.debug("{}", s);
        } else {
            System.out.println(s);
        }
    }
}
