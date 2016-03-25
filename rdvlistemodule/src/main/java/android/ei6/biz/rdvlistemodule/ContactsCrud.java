package android.ei6.biz.rdvlistemodule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by fabien on 23/03/2016.
 */
public class ContactsCrud {
    private static final int VERSION=1;
    private static final String NOM_BDD="Contacts.sqlite";
    private static final String TABLE_CONTACTS="table_contacts";

    private static final String COL_ID="_id";
    private static final int NUM_COL_ID=0;

    private static final String COL_NOM="NOM";
    private static final int NUM_COL_NOM=1;

    private static final String COL_HORAIRE="HORAIRE";
    private static final int NUM_COL_HORAIRE=2;

    private static final String COL_TELEPHONE="TELEPHONE";
    private static final int NUM_COL_TELEPHONE=3;

    private SQLiteDatabase bdd;

    private ContractsStructureDB contacts;

    public ContactsCrud(Context cxt) {
        contacts = new ContractsStructureDB(cxt,NOM_BDD,null,1);
    }

    public void openForWrite() {
        bdd = contacts.getWritableDatabase();
    }
    public void openForRead() {
        bdd = contacts.getReadableDatabase();
    }
    public void close() {
        bdd.close();
    }

    public long insertContact(Contacts ct) {
        ContentValues cv = new ContentValues();
        cv.put(COL_NOM, ct.getNom());
        cv.put(COL_HORAIRE, ct.getNom());
        cv.put(COL_TELEPHONE, ct.getNom());

        return bdd.insert(TABLE_CONTACTS,null,cv);
    }

    public ArrayList<Contacts> getAllContacts() {
        ArrayList<Contacts> retval = new ArrayList<>();

        Cursor c = bdd.query(TABLE_CONTACTS,
                                new String[]{COL_ID,COL_NOM,COL_HORAIRE,COL_TELEPHONE},
                                null, null, null,null,COL_NOM);

        if(c.getCount()>0){
            while(c.moveToNext()) {
                retval.add(new Contacts(c.getString(NUM_COL_NOM),
                                                c.getString(NUM_COL_HORAIRE),
                                                c.getString(NUM_COL_TELEPHONE)));
            }
        }
         return retval;
    }
}
