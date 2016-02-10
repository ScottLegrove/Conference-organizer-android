package com.scott.assignment1;

import android.provider.BaseColumns;

/**
 * Created by Tech on 18/11/2015.
 */
public final class ConferenceContract {
    private ConferenceContract(){ }

    public static abstract class Presenters implements BaseColumns {
        public static final String TABLE_NAME = "Presenters";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BIO = "bio";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_AFFILIATION = "affiliation";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_NAME + " TEXT, " +
                    COLUMN_NAME_BIO + " TEXT, " +
                    COLUMN_NAME_EMAIL + " TEXT, " +
                    COLUMN_NAME_AFFILIATION + " TEXT " +
                ")";

        public static final String DROP = "DROP TABLE " + TABLE_NAME;
    }
}
