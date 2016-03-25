package android.ei6.biz.rdvlistemodule;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabien on 18/03/2016.
 */
public class HistoryFile {
    private final Context mContext;
    private File mContactFile;

    public HistoryFile(Context cxt) {
        mContext  =cxt;
    }

    boolean isFileExist() {
        mContactFile = new File(mContext.getExternalFilesDir(null),"contacts.txt");
        return mContactFile.exists();
    }

    List<String> getContacts() throws Exception {
        List<String> contacts = new ArrayList<>();
        FileReader fr = new FileReader(mContactFile);

        BufferedReader br = new BufferedReader(fr);
        String ligne;
        while((ligne=br.readLine())!=null) {
            contacts.add(ligne);
        }
        br.close();
        return contacts;
    }


    void saveContacts(String message) throws IOException {
        if(!isFileExist()) {
            mContactFile.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(mContactFile,true));
        bw.write(message);
        bw.newLine();
        bw.close();
    }
}
