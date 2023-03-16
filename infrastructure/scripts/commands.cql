-- create post namespaces
CREATE
    KEYSPACE IF NOT EXISTS audit_docker WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS audit_docker WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS audit_dev WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS audit_prod WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};

-- create post profile
CREATE
    KEYSPACE IF NOT EXISTS notification_docker WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS notification_dev WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS notification_docker WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
CREATE
    KEYSPACE IF NOT EXISTS notification_prod WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 1};
