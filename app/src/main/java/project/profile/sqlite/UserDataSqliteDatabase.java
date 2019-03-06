package project.profile.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserDataSqliteDatabase extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "userDatabase";
    public static final String TABLE_NAME = "USER";
    public static final String USERNAME = "userName";
    public static final String USER_ID = "_id";
    public static final String NAME = "name";
    public static final String GENDER = "gender";
    public static final String EMAIL = "email";
    public static final String USER_DOB = "userDob";
    public static final String COUNTRY = "country";
    public static final String POSTAL_ADDRESS = "address";

    public UserDataSqliteDatabase(Context context) {
        super(context, DATABASE_NAME,
                null,
                6);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String createQuery =
                "CREATE TABLE " +
                        TABLE_NAME +
                        " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        USERNAME + " VARCHAR(200), " +
                        GENDER + " VARCHAR(50)," +
                        EMAIL + " VARCHAR(300)," +
                        NAME + " VARCHAR(300)," +
                        USER_DOB + " VARCHAR(300)," +
                        POSTAL_ADDRESS + " VARCHAR(500)," +
                        COUNTRY + " VARCHAR(300));";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);

    }
}
