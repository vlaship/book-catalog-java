package dev.vlaship.book.catalog.config;

import dev.vlaship.book.catalog.generator.SnowflakeIdGenerator;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeIdProperties {

    private static final long maxWorkerId = ~(-1L << SnowflakeIdGenerator.WORKER_ID_BITS);
    private static final long maxDatacenterId = ~(-1L << SnowflakeIdGenerator.DATACENTER_ID_BITS);

    private long workerId = 1;
    private long datacenterId = 1;

    public void setWorkerId(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;

    }

    public void setDatacenterId(long datacenterId) {
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.datacenterId = datacenterId;
    }
}
