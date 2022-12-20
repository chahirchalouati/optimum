package com.crcl.audit.domain;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.Ordering.DESCENDING;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table
@Data
public class Audit {
    @PrimaryKeyColumn(name = "isbn", ordinal = 2, type = CLUSTERED, ordering = DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "title", ordinal = 0, type = PARTITIONED)
    private String title;
    @PrimaryKeyColumn(name = "publisher", ordinal = 1, type = PARTITIONED)
    private String publisher;
}
