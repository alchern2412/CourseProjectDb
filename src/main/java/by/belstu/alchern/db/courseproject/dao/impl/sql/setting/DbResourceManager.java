package by.belstu.alchern.db.courseproject.dao.impl.sql.setting;

import java.util.ResourceBundle;

public class DbResourceManager {
    private static DbResourceManager instance = new DbResourceManager();

    private DbResourceManager() {}

    public static DbResourceManager getInstance() {
        return instance;
    }

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public String getValue(String key) {
        return bundle.getString(key);
    }

}
