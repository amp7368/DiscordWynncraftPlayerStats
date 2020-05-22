package apple.playerStats.sheets;


import com.google.api.services.sheets.v4.model.ValueRange;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WriteStats {
    private static final String SHEET_NAME = "Sheet1";
    private static final String nameCol = "A";

    public static void writePlayer(String discordId, String name, JSONObject playerObject) throws IOException {
        int rowToWrite = SheetsUtils.getRowFromDiscord(discordId, String.format("%s!%s:%s", SHEET_NAME, nameCol, nameCol)) - 1;
        JSONObject playerData = (JSONObject) ((JSONArray) playerObject.get("data")).get(0);


        String range = "Sheet1!A1:B2";
        ValueRange valueRange = new ValueRange();
        List<List<Object>> values = new LinkedList<>();
        List<Object> firstList = new ArrayList<>();
        List<Object> secondList = new ArrayList<>();


        values.add(firstList);
        values.add(secondList);

        valueRange.setValues(values);
        SheetsConstants.sheetsValues.update(SheetsConstants.spreadSheetId, range, valueRange).setValueInputOption("USER_ENTERED").execute();
    }
}
