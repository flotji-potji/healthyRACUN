package com.example.android.myfishy.utilities;

import android.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExtractCSV {

    private BufferedReader br;

    public ExtractCSV(InputStream is) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(is));
    }

    private List<String> next() throws IOException {
        char[] charLine = br.readLine().toCharArray();
        List<String> res = new ArrayList<>();
        CharState state = CharState.DELIMITER;
        String cellContext = "";
        Pair<CharState, String> ret;
        for (int i = 0; i < charLine.length; i++){
            ret = state.handleChar(charLine, i, cellContext);
            state = ret.first;
            cellContext = ret.second;
            if (state == CharState.DELIMITER){
                res.add(cellContext);
                cellContext = "";
            }
        }
        res.add(cellContext);
        return res;
    }

}
