package com.example.demo.server;

/**
 * @Author hanxiang
 * @email gordon.han@adtiming.com
 * @Date 2022/2/24 15:28
 * @Description
 **/
import com.google.protobuf.MessageLite;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPOutputStream;

public abstract class PbBuiler {

    private static final Logger LOG = LogManager.getLogger();

    @FunctionalInterface
    public interface PBWriter {
        void writeDelimited(MessageLite ml);
    }

    public List<String> str2list(String str) {
        return str2list(str, String::toString, "[,\r\n]");
    }

    public List<String> str2list(String str, String splitStr) {
        return str2list(str, String::toString, splitStr);
    }

    public List<String> str2list(String str, Function<String, String> mapper) {
        return str2list(str, mapper, "[,\r\n]");
    }

    public List<String> str2list(String str, Function<String, String> mapper, String splitStr) {
        if (StringUtils.isEmpty(str))
            return Collections.emptyList();
        return Stream.of(str.split(splitStr))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .map(mapper)
                .collect(Collectors.toList());
    }

    public void build(String name, File dir, Consumer<PBWriter> fn) {
        File src = new File(dir, name + ".gz.tmp");
        File dst = new File(dir, name + ".gz");
        LOG.info("start build {}", name);
        long start = System.currentTimeMillis();

        try (RandomAccessFile fo = new RandomAccessFile(src, "rw")) {
            GZIPOutputStream gzout = new GZIPOutputStream(new FileOutputStream(fo.getFD()));
            AtomicInteger count = new AtomicInteger();
            fn.accept(ml -> {
                try {
                    ml.writeDelimitedTo(gzout);
                    count.incrementAndGet();
                } catch (IOException e) {
                    LOG.error("write {} pb error", name, e);
                }
            });
            gzout.finish();
            fo.writeInt(count.get());
            fo.close();

            if (!src.renameTo(dst)) {
                LOG.error("mv {} to {} error", src, dst);
            }
        } catch (Exception e) {
            LOG.error("build {} error", name, e);
        }
        LOG.debug("build {} finished, cost {} ms", name, System.currentTimeMillis() - start);
    }
}
