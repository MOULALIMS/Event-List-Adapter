package com.example.revatrail2;

import android.provider.BaseColumns;

public final class MyContract {
    private MyContract() {}

    public static class MyEntry implements BaseColumns {
        public static final String TABLE_NAME = "my_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_LINK = "link";
    }
}