package org.jalontsui.sb.mysql.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.Log;

/**
 * 自定义mybatis的log输出
 *
 * todo 总结mybatis输出
 * https://blog.csdn.net/yaomingyang/article/details/119897450?ops_request_misc=%257B%2522request%255Fid%2522%253A%25220324b35c4c74b947beca024f9a484764%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=0324b35c4c74b947beca024f9a484764&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-8-119897450-null-null.142^v102^pc_search_result_base6&utm_term=mybatis%E8%AE%B0%E5%BD%95sql%E6%97%A5%E5%BF%97%E5%88%B0%E6%96%87%E4%BB%B6&spm=1018.2226.3001.4187
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
