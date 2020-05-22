package apple.playerStats.sheets;

import apple.playerStats.StatsMain;
import com.google.api.services.sheets.v4.Sheets;

public class SheetsConstants {
    public static final String spreadSheetId = "17dheMIUDh0O6Xx4hupUL1aR89HCByOe9T-8m-BQUJCk";
    public static Sheets.Spreadsheets.Values sheetsValues = StatsMain.service.spreadsheets().values();
}
