package de.nenick.workinterruption.dataaccess.api.support;

public class SQLiteMap implements DatabaseConfig.DatabaseMap {

    private String _dbFile;

    /**
     * This constructor will use in-memory database.
     */
    public SQLiteMap() {}

    /**
     * This constructor will use a database file
     *
     * @param dbFile: path to the SQLite database file
     */
    public SQLiteMap(String dbFile) {
        _dbFile = dbFile;
    }

    public String getDriverClassName() {
        return "org.sqlite.JDBC";
    }

    public String getConnectionString() {
        if (_dbFile == null)
            return "jdbc:sqlite::memory:";
        else
            return String.format("jdbc:sqlite:%s", _dbFile);
    }

    public String getScrubSQL(String sql) {
        return sql;
    }

    public String getSelectLastInsertIdentity() {
        return "SELECT last_insert_rowid() AS id";
    }

    public int getResultSetType() {
        return ResultSet.TYPE_FORWARD_ONLY;
    }
}
