package android.ei6.biz.rdvlistemodule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fabien on 23/03/2016.
 */
public class ContractsStructureDB extends SQLiteOpenHelper {

    private static final String TABLE_CONTACTS = "table_contacts";
    private static final String ID_COL = "_id";
    private static final String COL_NOM = "NOM";
    private static final String COL_HORAIRE = "HORAIRE";
    private static final String COL_TELEPHONE = "TELEPHONE";

    private static final String CREATE_BDD = "CREATE TABLE "+TABLE_CONTACTS+
            "("+ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_NOM+" TEXT NOT NULL, "+
            COL_HORAIRE+" TEXT NOT NULL, "+
            COL_TELEPHONE+" TEXT NOT NULL);";

    public ContractsStructureDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE "+TABLE_CONTACTS);
        onCreate(db);
    }
}
