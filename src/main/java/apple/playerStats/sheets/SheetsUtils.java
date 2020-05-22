package apple.playerStats.sheets;

import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.List;

public class SheetsUtils {
    public static int getRowFromDiscord(String discordId, String idRange) throws IOException {
        ValueRange idValueRange = SheetsConstants.sheetsValues.get(SheetsConstants.spreadSheetId, idRange).execute();
        List<List<Object>> idValues = idValueRange.getValues();
        int idIndex = -1;
        int idValuesLength = idValues.size();
        for (int i = 0; i < idValuesLength; i++) {
            if (idValues.get(i).isEmpty())
                continue;
            String element = idValues.get(i).get(0).toString();
            if (element.equals(discordId)) {
                idIndex = i;
                break;
            }
        }
        return idIndex;
    }

    public static String addA1Notation(String base, int col, int row) {
        char[] baseChar = base.toCharArray();
        int baseCol = 0;
        int i = 0;
        int baseCharLength = baseChar.length;
        for (; i < baseCharLength && Character.isLetter(baseChar[i]); i++) {
            baseCol *= 26;
            baseCol += baseChar[i] - 64;
        }
        StringBuilder baseRow = new StringBuilder();
        for (; i < baseCharLength && Character.isDigit(baseChar[i]); i++) {
            baseRow.append(baseChar[i]);
        }
        if (!baseRow.toString().equals(""))
            row = row + Integer.parseInt(baseRow.toString());
        return getExcelColumnName(col + baseCol) + row;
    }

    private static String getExcelColumnName(int columnNumber) {
        int dividend = columnNumber;
        StringBuilder columnName = new StringBuilder();
        int modulo;

        while (dividend > 0) {
            modulo = (dividend - 1) % 26;
            columnName.insert(0, ((char) (65 + modulo)));
            dividend = (dividend - modulo) / 26;
        }

        return columnName.toString();
    }

}
